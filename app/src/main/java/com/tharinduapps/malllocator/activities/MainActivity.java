package com.tharinduapps.malllocator.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.tharinduapps.malllocator.R;
import com.tharinduapps.malllocator.adapters.ViewPagerAdapter;
import com.tharinduapps.malllocator.fragments.ListViewFragment;
import com.tharinduapps.malllocator.fragments.MapFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private ImageButton settingsBtn;
    private TabLayout homeTabLayout;
    private ViewPager homeViewPager;
    private View firstTab;
    private View secondTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setHomeTabLayout();
    }

    private void initUI(){
        initElements();
        initOnClicks();
    }

    private void initElements(){
        settingsBtn = (ImageButton)findViewById(R.id.settingsBtn);
        homeTabLayout = (TabLayout)findViewById(R.id.homeTabLayout);
        homeViewPager = (ViewPager)findViewById(R.id.homeViewPager);
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

    private void setHomeTabLayout(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new ListViewFragment(),"List View");
        viewPagerAdapter.addFragment(new MapFragment(),"Map View");

        homeViewPager.setAdapter(viewPagerAdapter);
        homeTabLayout.setupWithViewPager(homeViewPager);

        firstTab = ((ViewGroup) homeTabLayout.getChildAt(0)).getChildAt(0);
        secondTab = ((ViewGroup) homeTabLayout.getChildAt(0)).getChildAt(1);

        firstTab.setBackground(getDrawable(R.drawable.shape_tab_one_selected));
        secondTab.setBackground(getDrawable(R.drawable.shape_tab_two_unselected));

        homeTabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        int selectedTabPosition = tab.getPosition();
        if(selectedTabPosition == 0){
            firstTab.setBackground(getDrawable(R.drawable.shape_tab_one_selected));
            secondTab.setBackground(getDrawable(R.drawable.shape_tab_two_unselected));
        }else{
            firstTab.setBackground(getDrawable(R.drawable.shape_tab_one_unselected));
            secondTab.setBackground(getDrawable(R.drawable.shape_tab_two_selected));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        // do nothing
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // do nothing
    }
}
