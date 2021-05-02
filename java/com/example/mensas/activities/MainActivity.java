package com.example.mensas.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mensas.Adapters.ViewPagerAdapter;
import com.example.mensas.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisierung BottomNavigatonView
        bottomNav =  findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Initialisierung ViewPager
        viewPager = findViewById(R.id.viewpager);
        setupFm(getSupportFragmentManager(), viewPager);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new PageChange());

        // onLaunch Fragment
        // ERROR HERE
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MensaFragment()).commit();
    }

    //ViewPager setupfm Overlapping problem !

    public static void setupFm(FragmentManager fragmentManager, ViewPager viewPager){
        ViewPagerAdapter Adapter = new ViewPagerAdapter(fragmentManager);

        Adapter.add(new MensaFragment());
        Adapter.add(new FavoritenFragment());
        Adapter.add(new NearestFragment());

        viewPager.setAdapter(Adapter);
    }

    // ViewPager, OnPageChangeListener

    public class PageChange implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

            switch (position) {
                case 0:
                    bottomNav.setSelectedItemId(R.id.nav_mensa);
                    break;
                case 1:
                    bottomNav.setSelectedItemId(R.id.nav_favoriten);
                    break;
                case 2:
                    bottomNav.setSelectedItemId(R.id.nav_nearest);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_mensa:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.nav_favoriten:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.nav_nearest:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };


}
