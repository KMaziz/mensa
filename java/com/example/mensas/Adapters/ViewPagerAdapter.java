package com.example.mensas.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> Fragment = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    @Override
    public Fragment getItem(int position) {
        return Fragment.get(position);
    }

    @Override
    public int getCount() {
        return Fragment.size();
    }

    public void add(Fragment Frag){
        Fragment.add(Frag);

    }
}
