package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button GetCityID,GetWeatherByID,GetWeatherByCityName;
    EditText ETCityName;
    ListView list;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetCityID=findViewById(R.id.btnGitCityID);
        GetWeatherByID=findViewById(R.id.btnGetWeatherByCityID);
        GetWeatherByCityName=findViewById(R.id.GetWeatherByCityName);
        ETCityName=findViewById(R.id.editTextCityName);
        list=findViewById(R.id.List);


        WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);


        GetCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityID="";
                //instance from the class
                weatherDataService.getCityID("Miami", new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"some thing going wrong!",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String cityId) {
                        Toast.makeText(MainActivity.this,"Returned the ID:"+cityID,Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        GetWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weatherDataService.getCityForecastByID("Miami");


            }

        });






    }
}