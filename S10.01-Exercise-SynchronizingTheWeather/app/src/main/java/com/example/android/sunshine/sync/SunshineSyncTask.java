package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

//COMPLETED(1) Create a class called SunshineSyncTask
public class SunshineSyncTask {

    /**
     * Performs the network request for updated weather, parses the JSON from that request, and
     * inserts the new weather information into our ContentProvider. Will notify the user that new
     * weather has been loaded if the user hasn't been notified of the weather within the last day
     * AND they haven't disabled notifications in the preferences screen.
     *
     * @param context Used to access utility methods and the ContentResolver
     */
    //COMPLETED (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    synchronized public static void syncWeather(Context context) {
//COMPLETED (3) Within syncWeather, fetch new weather data
        try {
            URL weatherRequestUrl = NetworkUtils.getUrl(context);
            String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
            ContentValues[] weatherValues = OpenWeatherJsonUtils
                    .getWeatherContentValuesFromJson(context, jsonWeatherResponse);

            //COMPLETED(4) If we have valid results, delete the old data and insert the new
            if (weatherValues != null && weatherValues.length != 0) {
                ContentResolver sunshineContentResolver = context.getContentResolver();
                sunshineContentResolver.delete(WeatherContract.WeatherEntry.CONTENT_URI,
                        null,
                        null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}