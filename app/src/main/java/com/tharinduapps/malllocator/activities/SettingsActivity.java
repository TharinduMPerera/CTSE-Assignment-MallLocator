package com.tharinduapps.malllocator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.tharinduapps.malllocator.R;

public class SettingsActivity extends AppCompatActivity {

    ImageButton closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initUI();
    }

    private void initUI(){
        initElements();
        initOnClicks();
    }

    private void initElements(){
        closeBtn = (ImageButton)findViewById(R.id.closeBtn);
    }

    private void initOnClicks(){
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.keep_active, R.anim.slide_to_bottom);
            }
        });
    }
}
