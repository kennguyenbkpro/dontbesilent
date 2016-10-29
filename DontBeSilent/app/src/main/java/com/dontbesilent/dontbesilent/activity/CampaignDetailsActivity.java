package com.dontbesilent.dontbesilent.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.dontbesilent.dontbesilent.R;

public class CampaignDetailsActivity extends AppCompatActivity {

    private FloatingActionButton mFabDonate;
    private View mLayoutDonate;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_campaign_details);

        findViewById(R.id.activity_campaign_details).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                return false;
            }
        });
        mLayoutDonate = findViewById(R.id.layout_donate);
        mFabDonate = (FloatingActionButton) findViewById(R.id.campaign_detail_fab_donate);
        mFabDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHideLayoutDonate();
            }
        });

        mBottomSheetBehavior = BottomSheetBehavior.from(mLayoutDonate);
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        findViewById(R.id.campaign_detail_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            return;
        }
        super.onBackPressed();
    }

    private void showHideLayoutDonate() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }
}
