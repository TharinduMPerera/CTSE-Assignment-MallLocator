package com.tharinduapps.malllocator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tharinduapps.malllocator.R;
import com.tharinduapps.malllocator.models.Mall;

public class MallDetailsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private ImageButton backBtn;
    private Mall mall;
    private TextView title;
    private TextView contactTextView;
    private TextView locationTextView;
    private ImageView coverImage;
    private RatingBar ratingBar;

//    private View mapView;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_details);

        initUI();
        setObject();
        setUI();
        initMap();
    }

    private void initUI(){
        initElements();
        initOnClicks();
    }

    private void initElements(){
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        title = (TextView) findViewById(R.id.title);
        contactTextView = (TextView) findViewById(R.id.contactTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        coverImage = (ImageView) findViewById(R.id.coverImage);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
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

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        mapView = mapFragment.getView();
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
            contactTextView.setText("Contact: "+mall.getTelephone());
            locationTextView.setText("Location: "+mall.getCity());
            ratingBar.setRating(mall.getRate());
            coverImage.setImageResource(getResources().getIdentifier(mall.getCoverImage(),"drawable",getPackageName()));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if(mall!=null){
            LatLng latLog = new LatLng(mall.getLat(), mall.getLon());
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLog, 15.0f));

            map.addMarker(new MarkerOptions()
                    .position(latLog)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
    }
}
