package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private String mForecast;
    private TextView mWeatherDisplay;

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );

        mWeatherDisplay = (TextView) findViewById ( R.id.tv_display_weather );

        // COMPLETED (2) Display the weather forecast that was passed from MainActivity
        Intent intentThatStartedThisActivity = getIntent ();
        if (intentThatStartedThisActivity.hasExtra ( Intent.EXTRA_TEXT )) {
            mForecast = intentThatStartedThisActivity.getStringExtra ( intentThatStartedThisActivity.EXTRA_TEXT );
            mWeatherDisplay.setText ( mForecast );
        }
    }
}