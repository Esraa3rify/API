package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query";
    Context context;
    String cityId="";

    public WeatherDataService(Context context) {
        this.context=context;
    }

    public String getCityID(String cityName){

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
                        Toast.makeText(context,"The city ID is:"+cityId.toString(),Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show();

                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        return  cityId;

    }
//
//    public List <WeatherReportModel> getCityForecastByID (String cityID){
//
//    }
//
//    public List <WeatherReportModel> getCityForecastByName(String cityName){
//
//    }
}
