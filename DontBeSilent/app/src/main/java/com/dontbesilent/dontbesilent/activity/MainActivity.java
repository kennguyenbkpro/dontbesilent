package com.dontbesilent.dontbesilent.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.fragment.Fragment1;
import com.dontbesilent.dontbesilent.fragment.Fragment2;
import com.dontbesilent.dontbesilent.fragment.Fragment3;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private Fragment1 mFragment1;
    private Fragment2 mFragment2;
    private Fragment3 mFragment3;

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
                            case R.id.bottom_nav_main_action_1:
                                showFragment1();
                                break;
                            case R.id.bottom_nav_main_action_2:
                                showFragment2();
                                break;
                            case R.id.bottom_nav_main_action_3:
                                showFragment3();
                                break;
                        }
                        return false;
                    }
                });

        if (savedInstanceState == null) {
            showFragment1();
        }
    }

    private void showFragment1() {
        if (mFragment1 == null) {
            mFragment1 = Fragment1.getInstance();
        }
        showFragment(mFragment1);
    }

    private void showFragment2() {
        if (mFragment2 == null) {
            mFragment2 = Fragment2.getInstance();
        }
        showFragment(mFragment2);
    }

    private void showFragment3() {
        if (mFragment3 == null) {
            mFragment3 = Fragment3.getInstance();
        }
        showFragment(mFragment3);
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
