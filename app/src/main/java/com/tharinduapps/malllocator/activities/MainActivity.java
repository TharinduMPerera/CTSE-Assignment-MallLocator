package com.tharinduapps.malllocator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.tharinduapps.malllocator.R;

public class MainActivity extends AppCompatActivity {

    ImageButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI(){
        initElements();
        initOnClicks();
    }

    private void initElements(){
        settingsBtn = (ImageButton)findViewById(R.id.settingsBtn);
    }

    private void initOnClicks(){
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(filterIntent);
                overridePendingTransition(R.anim.slide_to_top, R.anim.keep_active);
            }
        });
    }
}
