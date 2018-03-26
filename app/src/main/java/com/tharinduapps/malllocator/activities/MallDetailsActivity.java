package com.tharinduapps.malllocator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tharinduapps.malllocator.R;
import com.tharinduapps.malllocator.models.Mall;

public class MallDetailsActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private Mall mall;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_details);

        initUI();
        setObject();
        setUI();
    }

    private void initUI(){
        initElements();
        initOnClicks();
    }

    private void initElements(){
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        title = (TextView) findViewById(R.id.title);
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

    private void setObject() {
        Object object = getIntent().getExtras().getParcelable("mall");
        if (object!=null){
            mall = (Mall) object;
        }
    }

    private void setUI(){
        if(mall!=null) {
            title.setText(mall.getName());
        }
    }
}
