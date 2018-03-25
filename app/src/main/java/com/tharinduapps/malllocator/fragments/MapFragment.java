package com.tharinduapps.malllocator.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tharinduapps.malllocator.R;
import com.tharinduapps.malllocator.activities.MainActivity;
import com.tharinduapps.malllocator.activities.MallDetailsActivity;
import com.tharinduapps.malllocator.database.DBHelper;
import com.tharinduapps.malllocator.models.Mall;

import java.util.ArrayList;

/**
 * Created by tharindu on 3/23/18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap map;
    private DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        dbHelper = new DBHelper(getActivity());
        initMap();
        return view;
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initMarkers() {

        ArrayList<Mall> malls = dbHelper.getAllMalls();
        if (malls != null && map != null) {

            if (malls.size() < 1) {
                map.clear();
            } else {
                LatLng latLng;
                Marker marker;
                for (Mall mall : malls) {
                    latLng = new LatLng(mall.getLat(), mall.getLon());
                    marker = map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    marker.setTag(mall);
                }
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.getUiSettings().setCompassEnabled(false);
        map.getUiSettings().setRotateGesturesEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);

        LatLng center = new LatLng(6.890348, 79.898356);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12.0f));
        initMarkers();

        map.setOnMarkerClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.didDBChange) {
            initMarkers();
            MainActivity.didDBChange = false;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        boolean doNotMoveCameraToCenterMarker = true;

        Intent detailsIntent = new Intent(getActivity(), MallDetailsActivity.class);
        detailsIntent.putExtra("mall", (Mall) marker.getTag());
        startActivity(detailsIntent);
        getActivity().overridePendingTransition(R.anim.slide_to_right, R.anim.keep_active);

        return doNotMoveCameraToCenterMarker;
    }
}
