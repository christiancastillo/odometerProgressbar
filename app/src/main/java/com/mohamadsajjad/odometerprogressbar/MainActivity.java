package com.mohamadsajjad.odometerprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mohamadsajjad.progressbarlibrary.CustomProgress;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CustomProgress customProgress = findViewById(R.id.speed_meter);
        customProgress
                .setPercent(83)
                .setTitle("Percent")
                .setDescription("Description of this progress")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customProgress.start();
                    }
                });
    }
}