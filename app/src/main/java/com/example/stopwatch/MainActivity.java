package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer ;
    private Button buReset ;
    private ToggleButton buToggle ;
    private long pauseOffSet = 0 ;
    private boolean isPlaying  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find All
        {
            chronometer = findViewById(R.id.chronometerID);
            buReset = findViewById(R.id.buResetID);
            buToggle = findViewById(R.id.buToggleID);
        }

        // Customize Toggle Button
        {
            buToggle.setText(null);
            buToggle.setTextOn(null);
            buToggle.setTextOff(null);
        }

        // Listener Of Toggle Button
        {
            buToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    // b is the state of our button, it will be true if we pressed the button else it will be false
                    if (b){
                        chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
                        chronometer.start();
                        isPlaying = true;
                    } else {
                        chronometer.stop();
                        pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
                        isPlaying = false;
                    }
                }
            });
        }

        // Listener of Reset Button
        {
            buReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    pauseOffSet = 0 ;
                    chronometer.start();
                    isPlaying = true;
                }
            });
        }
    }
}