package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query";
    public static final String QUERY_FOR_WEATHER_BY_ID = "https://www.metaweather.com/api/location/search";
    Context context;
    String cityId="";

    public WeatherDataService(Context context) {
        this.context=context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityId);
    }

    public void getCityID(String cityName,  VolleyResponseListener volleyResponseListener){

        // RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = QUERY_FOR_CITY_ID +cityName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject CityInfo = response.getJSONObject("0");
                            cityId=CityInfo.getString("woeid");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                       // Toast.makeText(context,"The city ID is:"+cityId.toString(),Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cityId);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onError("Something going wrong!");

                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        //return  cityId;

    }


    public interface CityByIDResponseListener {
        void onError(String message);

      void onResponse(WeatherReportModel weatherReportModel) ;


    }
//
   public void getCityForecastByID (String cityID,  CityByIDResponseListener cityByIDResponseListener) {

       String url = QUERY_FOR_WEATHER_BY_ID + cityID;
       List<WeatherReportModel> report = new ArrayList<>();

       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
               (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                   @Override
                   public void onResponse(JSONObject response) {
                       Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                       JSONArray minutely;

                       try {
                           minutely = response.getJSONArray("minutely");
                       } catch (JSONException e) {
                           throw new RuntimeException(e);
                       }



                       try {
                           JSONObject firstMinuteFromAPI =(JSONObject) minutely.get(0);


                           WeatherReportModel firstMinute = new WeatherReportModel();

                           firstMinute.setPrecipitation(20);
                           firstMinute.setDt(1);

                           CityByIDResponseListener.onResponse(firstMinute);

                       } catch (JSONException e) {
                           throw new RuntimeException(e);
                       }



                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();


                   }
               });

   }
//
//    public List <WeatherReportModel> getCityForecastByName(String cityName){
//
//    }
}
