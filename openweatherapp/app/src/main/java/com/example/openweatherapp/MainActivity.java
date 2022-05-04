package com.example.openweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView zipcode, lati,logn, type,t1,t2,t3,t4, ww1,ww2, ww3,ww4, eastern1, eastern2, eastern3, eastern4,cutemp,cityn;
    Button start;
    String latitude;
    String longitude;
    String cweather;
    String city;
    Double ctemp;
    String frame1, frame2, frame3, frame4;
    ImageView p1,p2,p3,p4;
    String weatherpic1, weatherpic2, weatherpic3, weatherpic4;
    private String temperr;
    Double temper1, temper2, temper3, temper4;
    String weatherdescription1,weatherdescription2, weatherdescription3,weatherdescription4, time1, time2, time3, time4;

    private String geourl;
    private String oneurl;
    private String apikey = "f8940a3d9d6d9616aa98ed7574a7fbe4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zipcode = findViewById(R.id.editTextzipcode);
        cutemp = findViewById(R.id.currenttemp);
        cityn = findViewById(R.id.cityname);
        t1= findViewById(R.id.hour);
        t2= findViewById(R.id.hour2);
        t3= findViewById(R.id.hour3);
        t4= findViewById(R.id.hour4);
        ww1 = findViewById(R.id.w1);
        ww2 = findViewById(R.id.w2);
        ww3 = findViewById(R.id.w3);
        ww4 = findViewById(R.id.w4);
        lati = findViewById(R.id.lat);
        logn = findViewById(R.id.log);
        type = findViewById(R.id.current);
        start = findViewById(R.id.run);
        eastern1 = findViewById(R.id.est1);
        eastern2 = findViewById(R.id.est2);
        eastern3 = findViewById(R.id.est3);
        eastern4 = findViewById(R.id.est4);
        p1 = findViewById(R.id.imageView2);
        p2 = findViewById(R.id.imageView3);
        p3 = findViewById(R.id.imageView4);
        p4 = findViewById(R.id.imageView5);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temperr = zipcode.getText().toString();
                new getinformation().execute();



            }
        });

    }
    private class getinformation extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            geourl = "https://api.openweathermap.org/geo/1.0/zip?zip=" + temperr+ ",US&appid="+ apikey;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            lati.setText(latitude);
            logn.setText(longitude);
            cityn.setText(city);
            new getweather().execute();
        }

        @Override
        protected String doInBackground(String... Strings){
            try{
                URL geocodingURL = new URL(geourl);
                URLConnection geocodingURlconnection = geocodingURL.openConnection();
                InputStream geocodingInputStream = geocodingURlconnection.getInputStream();
                BufferedReader georeader = new BufferedReader(new InputStreamReader(geocodingInputStream));
                String line = "";
                line += georeader.readLine();
                JSONObject k = new JSONObject(line);
                latitude=k.get("lat").toString();
                longitude=k.get("lon").toString();
                city = k.get("name").toString();


            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }
    private class getweather extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            oneurl = "https://api.openweathermap.org/data/2.5/onecall?lat="+latitude+ "&lon="+longitude+"&exclude=minutely&units=imperial&appid=" + apikey;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            type.setText(cweather);
            t1.setText(String.valueOf(temper1));
            t2.setText(String.valueOf(temper2));
            t3.setText(String.valueOf(temper3));
            t4.setText(String.valueOf(temper4));
            cutemp.setText(String.valueOf(ctemp));
            ww1.setText(weatherdescription1);
            ww2.setText(weatherdescription2);
            ww3.setText(weatherdescription3);
            ww4.setText(weatherdescription4);
            eastern1.setText(time1+ ":00" + " " + frame1);
            eastern2.setText(time2+ ":00" + " " + frame2);
            eastern3.setText(time3+ ":00" + " " + frame3);
            eastern4.setText(time4+ ":00" + " " + frame4);
            if(weatherpic1.equals("Drizzle")){
                p1.setImageResource(R.drawable.rain);
            }
            if(weatherpic1.equals("Rain")){
                p1.setImageResource(R.drawable.rain);
            }
            if(weatherpic1.equals("Snow")){
                p1.setImageResource(R.drawable.snow);
            }
            if(weatherpic1.equals("Clouds")){
                p1.setImageResource(R.drawable.clouds);
            }
            if(weatherpic1.equals("Clear")){
                p1.setImageResource(R.drawable.clear);
            }
            if(weatherpic1.equals("Thunderstorm")){
                p2.setImageResource(R.drawable.thunder);
            }
            if(weatherpic2.equals("Drizzle")){
                p2.setImageResource(R.drawable.rain);
            }
            if(weatherpic2.equals("Rain")){
                p2.setImageResource(R.drawable.rain);
            }
            if(weatherpic2.equals("Snow")){
                p2.setImageResource(R.drawable.snow);
            }
            if(weatherpic2.equals("Clouds")){
                p2.setImageResource(R.drawable.clouds);
            }
            if(weatherpic2.equals("Clear")){
                p2.setImageResource(R.drawable.clear);
            }
            if(weatherpic2.equals("Thunderstorm")){
                p2.setImageResource(R.drawable.thunder);
            }
            if(weatherpic3.equals("Drizzle")){
                p3.setImageResource(R.drawable.rain);
            }
            if(weatherpic3.equals("Rain")){
                p3.setImageResource(R.drawable.rain);
            }
            if(weatherpic3.equals("Snow")){
                p3.setImageResource(R.drawable.snow);
            }
            if(weatherpic3.equals("Clouds")){
                p3.setImageResource(R.drawable.clouds);
            }
            if(weatherpic3.equals("Clear")){
                p3.setImageResource(R.drawable.clear);
            }
            if(weatherpic3.equals("Thunderstorm")){
                p3.setImageResource(R.drawable.thunder);
            }
            if(weatherpic4.equals("Drizzle")){
                p4.setImageResource(R.drawable.rain);
            }
            if(weatherpic4.equals("Rain")){
                p4.setImageResource(R.drawable.rain);
            }
            if(weatherpic4.equals("Snow")){
                p4.setImageResource(R.drawable.snow);
            }
            if(weatherpic4.equals("Clouds")){
                p4.setImageResource(R.drawable.clouds);
            }
            if(weatherpic4.equals("Clear")){
                p4.setImageResource(R.drawable.clear);
            }


        }

        @Override
        protected String doInBackground(String... Strings){
            try{
                URL onecodingURL = new URL(oneurl);
                URLConnection onecodingURlconnection = onecodingURL.openConnection();
                InputStream geocodingInputStream = onecodingURlconnection.getInputStream();
                BufferedReader onereader = new BufferedReader(new InputStreamReader(geocodingInputStream));
                String line = "";
                line += onereader.readLine();
                JSONObject weather = new JSONObject(line);
                JSONObject currentweather = weather.getJSONObject("current");
                JSONArray array = currentweather.getJSONArray("weather");
                JSONObject k = array.getJSONObject(0);
                cweather = k.getString("description");
                ctemp = currentweather.getDouble("temp");
                JSONArray hourlyweather = weather.getJSONArray("hourly");
                temper1 = weather.getJSONArray("hourly").getJSONObject(0).getDouble("temp");
                temper2 = weather.getJSONArray("hourly").getJSONObject(1).getDouble("temp");
                temper3 = weather.getJSONArray("hourly").getJSONObject(2).getDouble("temp");
                temper4 = weather.getJSONArray("hourly").getJSONObject(3).getDouble("temp");
                time1 = weather.getJSONArray("hourly").getJSONObject(0).getString("dt");
                Long ti = Long.parseLong(time1);
                Date date = new java.util.Date(ti*1000l);
                SimpleDateFormat sd = new java.text.SimpleDateFormat("HH");
                int ko = Integer.parseInt(sd.format(date));
                if(ko==0){
                    frame1 = "AM";
                    time1 = "12";
                }
                else if(ko<12){
                    frame1 = "AM";
                    time1 = String.valueOf(ko);
                }
                if(ko>12){
                    frame1= "PM";
                    int po = ko-12;
                    time1= String.valueOf(po);
                }
                if(ko==12){
                    frame1 = "PM";
                    time1 = "12";
                }

                time2 = weather.getJSONArray("hourly").getJSONObject(1).getString("dt");
                ti = Long.parseLong(time2);
                date = new java.util.Date(ti*1000l);
                sd = new java.text.SimpleDateFormat("HH");
                ko = Integer.parseInt(sd.format(date));
                if(ko>12){
                    frame2 = "PM";
                    int po = ko-12;
                    time2= String.valueOf(po);
                }
                else if(ko<12){
                    frame2 = "AM";
                    time2 = String.valueOf(ko);
                }
                if(ko==12){
                    frame2 = "PM";
                    time2= "12";
                }
                if(ko==0){
                    frame2 = "AM";
                    time2 = "12";
                }
                time3 = weather.getJSONArray("hourly").getJSONObject(2).getString("dt");
                ti = Long.parseLong(time3);
                date = new java.util.Date(ti*1000l);
                sd = new java.text.SimpleDateFormat("HH");
                ko = Integer.parseInt(sd.format(date));
                if(ko>12){
                    frame3= "PM";
                    int po = ko-12;
                    time3= String.valueOf(po);
                }
                else if(ko<12){
                    frame3= "AM";
                    time3 = String.valueOf(ko);
                }
                if(ko==12){
                    frame3 = "PM";
                    time3 = "12";
                }
                if(ko==0){
                    frame3 = "AM";
                    time3 = "12";
                }

                time4 = weather.getJSONArray("hourly").getJSONObject(3).getString("dt");
                ti = Long.parseLong(time4);
                date = new java.util.Date(ti*1000l);
                sd = new java.text.SimpleDateFormat("HH");
                ko = Integer.parseInt(sd.format(date));
                if(ko>12){
                    frame4 = "PM";
                    int po = ko-12;
                    time4= String.valueOf(po);
                }
                else if(ko<12){
                    frame4 = "AM";
                    time4 = String.valueOf(ko);
                }
                if(ko==12){
                    frame4 = "PM";
                    time4 = "12";
                }
                if(ko==0){
                    frame4 = "AM";
                    time4 = "12";
                }

                weatherdescription1 = weather.getJSONArray("hourly").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description");
                weatherdescription2 = weather.getJSONArray("hourly").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description");
                weatherdescription3 = weather.getJSONArray("hourly").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("description");
                weatherdescription4 = weather.getJSONArray("hourly").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("description");
                weatherpic1 = weather.getJSONArray("hourly").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");
                weatherpic2 = weather.getJSONArray("hourly").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");
                weatherpic3 = weather.getJSONArray("hourly").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");
                weatherpic4 = weather.getJSONArray("hourly").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");




            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }

}

