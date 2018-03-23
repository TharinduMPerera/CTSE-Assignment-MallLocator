package com.tharinduapps.malllocator.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharindu on 3/23/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> fragmentListTitels = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentListTitels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentListTitels.get(position);
    }



    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentListTitels.add(title);
    }
}
