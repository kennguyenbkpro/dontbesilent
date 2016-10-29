package com.dontbesilent.dontbesilent.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Host;
import com.dontbesilent.dontbesilent.fragment.FragmentCampaign;
import com.dontbesilent.dontbesilent.fragment.FragmentInfo;
import com.dontbesilent.dontbesilent.fragment.FragmentStaffPick;
import com.dontbesilent.dontbesilent.fragment.MapsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView mBottomNavigationView;
    private FragmentCampaign mFragmentCampaign;
    private FragmentStaffPick mFragmentEvent;
    private FragmentInfo mFragmentInfo;
    private MapsFragment mFragmentMap;

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

        if (Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[] {
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.ACCESS_FINE_LOCATION"
            }, 0);
        } else  {
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void showFragmentCampaign() {
        if (mFragmentCampaign == null) {
            mFragmentCampaign = FragmentCampaign.getInstance();
        }
        showFragment(mFragmentCampaign);
    }

    private void showFragmentEvent() {
        if (mFragmentEvent == null) {
            mFragmentEvent = FragmentStaffPick.getInstance();
        }
        showFragment(mFragmentEvent);
    }

    private void showFragmentInfo() {
        if (mFragmentInfo == null) {
            mFragmentInfo = FragmentInfo.getInstance();
            Host userInfo = new Host();
            userInfo.name = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            userInfo.isVerified = false;
            userInfo.avatar = null;
            userInfo.cover = null;
            mFragmentInfo.setUserInfo(userInfo);
        }
        showFragment(mFragmentInfo);
//        if(mFragmentMap == null) {
//            mFragmentMap = MapsFragment.getInstance();
//        }
//        showFragment(mFragmentMap);
    }

    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction
                    .replace(R.id.content_main, fragment)
                    .commit();
        }
    }
}
