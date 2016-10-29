package com.dontbesilent.dontbesilent.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.fragment.FragmentCampaign;
import com.dontbesilent.dontbesilent.fragment.FragmentEvent;
import com.dontbesilent.dontbesilent.fragment.FragmentInfo;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private FragmentCampaign mFragmentCampaign;
    private FragmentEvent mFragmentEvent;
    private FragmentInfo mFragmentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation_main);
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottom_nav_main_campaign:
                                showFragmentCampaign();
                                break;
                            case R.id.bottom_nav_main_event:
                                showFragmentEvent();
                                break;
                            case R.id.bottom_nav_main_info:
                                showFragmentInfo();
                                break;
                        }
                        return false;
                    }
                });

        if (savedInstanceState == null) {
            showFragmentCampaign();
        }
    }

    private void showFragmentCampaign() {
        if (mFragmentCampaign == null) {
            mFragmentCampaign = FragmentCampaign.getInstance();
        }
        showFragment(mFragmentCampaign);
    }

    private void showFragmentEvent() {
        if (mFragmentEvent == null) {
            mFragmentEvent = FragmentEvent.getInstance();
        }
        showFragment(mFragmentEvent);
    }

    private void showFragmentInfo() {
        if (mFragmentInfo == null) {
            mFragmentInfo = FragmentInfo.getInstance();
        }
        showFragment(mFragmentInfo);
    }

    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();
        }
    }
}