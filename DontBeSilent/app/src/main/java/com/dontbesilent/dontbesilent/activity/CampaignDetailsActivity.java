package com.dontbesilent.dontbesilent.activity;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignActivityAdapter;
import com.dontbesilent.dontbesilent.adapter.CampaignCommentAdapter;
import com.dontbesilent.dontbesilent.util.Utils;

public class CampaignDetailsActivity extends AppCompatActivity {

    private CoordinatorLayout mRootView;
    private FloatingActionButton mFabDonate;
    private View mLayoutDonate;
    private BottomSheetBehavior mBottomSheetBehavior;
    private View mLayoutActivityAndComment;

    private RecyclerView mRvActivities;
    private LinearLayoutManager mRvActivitiesLayoutManager;
    private CampaignActivityAdapter mCampaignActivityAdapter;

    private RecyclerView mRvComments;
    private LinearLayoutManager mRvCommentsLayoutManager;
    private CampaignCommentAdapter mCampaignCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_campaign_details);
        mRootView = (CoordinatorLayout) findViewById(R.id.activity_campaign_details);
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

        mRvActivities = (RecyclerView) findViewById(R.id.campaign_detail_rv_activity);
        mRvActivitiesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCampaignActivityAdapter = new CampaignActivityAdapter();
        mRvActivities.setLayoutManager(mRvActivitiesLayoutManager);
        mRvActivities.setAdapter(mCampaignActivityAdapter);
        mRvActivities.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.findContainingViewHolder(view).getAdapterPosition() > 0) {
                    outRect.top = Utils.dpToPx(2);
                }
            }
        });

        mRvComments = (RecyclerView) findViewById(R.id.campaign_detail_rv_comment);
        mRvCommentsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCampaignCommentAdapter = new CampaignCommentAdapter();
        mRvComments.setLayoutManager(mRvCommentsLayoutManager);
        mRvComments.setAdapter(mCampaignCommentAdapter);
        mRvComments.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.findContainingViewHolder(view).getAdapterPosition() > 0) {
                    outRect.top = Utils.dpToPx(2);
                }
            }
        });

        mLayoutActivityAndComment = findViewById(R.id.campaign_info_activity_and_comment);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Slide slideTransition = new Slide(Gravity.BOTTOM);
//                        slideTransition.addTarget(mLayoutActivityAndComment);
//                        TransitionManager.beginDelayedTransition(mRootView, slideTransition);

                        Fade slideTransition = new Fade();
                        slideTransition.addTarget(mLayoutActivityAndComment);
                        slideTransition.setDuration(150);
                        TransitionManager.beginDelayedTransition(mRootView, slideTransition);

                        mLayoutActivityAndComment.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        } else {
            mLayoutActivityAndComment.setVisibility(View.VISIBLE);
        }
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
