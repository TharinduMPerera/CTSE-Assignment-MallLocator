package com.tharinduapps.malllocator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.tharinduapps.malllocator.R;

public class MallDetailsActivity extends AppCompatActivity {

    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_details);

        initUI();
    }

    private void initUI(){
        initElements();
        initOnClicks();
    }

    private void initElements(){
        backBtn = (ImageButton)findViewById(R.id.backBtn);
    }

    private void initOnClicks(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.keep_active, R.anim.slide_to_left);
            }
        });
    }
}
