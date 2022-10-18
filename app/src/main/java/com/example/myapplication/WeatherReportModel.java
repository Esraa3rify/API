package com.example.myapplication;

public class WeatherReportModel {

    private int dt;
    private int precipitation;

    public WeatherReportModel(int dt, int precipitation) {
        this.dt = dt;
        this.precipitation = precipitation;
    }

    public WeatherReportModel() {

    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "dt=" + dt +
                ", precipitation=" + precipitation +
                '}';
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }
}
