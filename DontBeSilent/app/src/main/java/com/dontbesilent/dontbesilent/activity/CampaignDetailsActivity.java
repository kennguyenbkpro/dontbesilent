package com.dontbesilent.dontbesilent.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignActivityAdapter;
import com.dontbesilent.dontbesilent.adapter.CampaignAdapter;
import com.dontbesilent.dontbesilent.adapter.CampaignCommentAdapter;
import com.dontbesilent.dontbesilent.adapter.CampaignDonationAdapter;
import com.dontbesilent.dontbesilent.data.*;
import com.dontbesilent.dontbesilent.util.DummyDataUtils;
import com.dontbesilent.dontbesilent.util.Utils;

import java.util.ArrayList;

public class CampaignDetailsActivity extends AppCompatActivity {

    public static final String KEY_CAMPAIGN_ID = "CAMPAIGN_ID";

    private CoordinatorLayout mRootView;
    private FloatingActionButton mFabDonate;
    private BottomSheetBehavior mBottomSheetBehavior;
    private View mLayoutDonationActivityComment;

    private LinearLayout mLayoutDonation;
    private LinearLayout mLayoutActivities;
    private LinearLayout mLayoutComments;

    private View mBtnSeeMoreDonation;
    private View mBtnSeeMoreActivities;
    private View mBtnSeeMoreComments;

    private ArrayList<Donation> mDonations = new ArrayList<>();
    private ArrayList<com.dontbesilent.dontbesilent.data.CampaignActivity> mActivities = new ArrayList<>();
    private ArrayList<CampaignComment> mComments = new ArrayList<>();

    private static final int MAX_SAMPLE_DONATION_ACT_COMMENT_DISPLAYED = 3;

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
        mFabDonate = (FloatingActionButton) findViewById(R.id.campaign_detail_fab_donate);
        mFabDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHideLayoutDonate();
            }
        });

        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.layout_donate));
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        findViewById(R.id.campaign_detail_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mLayoutDonation = (LinearLayout) findViewById(R.id.campaign_detail_donation);
        mLayoutActivities = (LinearLayout) findViewById(R.id.campaign_detail_activities);
        mLayoutComments = (LinearLayout) findViewById(R.id.campaign_detail_comments);

        mLayoutDonationActivityComment = findViewById(R.id.campaign_info_donation_activity_comment);
        mBtnSeeMoreDonation = findViewById(R.id.campaign_detail_btn_see_more_donation);
        mBtnSeeMoreDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreDonation();
            }
        });

        mBtnSeeMoreActivities = findViewById(R.id.campaign_detail_btn_see_more_activities);
        mBtnSeeMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreActivities();
            }
        });

        mBtnSeeMoreComments = findViewById(R.id.campaign_detail_btn_see_more_comments);
        mBtnSeeMoreComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreComments();
            }
        });

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Fade faadeTransition = new Fade();
                        faadeTransition.addTarget(mLayoutDonationActivityComment);
                        faadeTransition.setDuration(150);
                        TransitionManager.beginDelayedTransition(mRootView, faadeTransition);
                        mLayoutDonationActivityComment.setVisibility(View.VISIBLE);
                    }
//                    mLayoutDonationActivityComment.setVisibility(View.VISIBLE);
//                    runDonationActivityCommentAnim();
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
        }*/
        mLayoutDonationActivityComment.setVisibility(View.VISIBLE);

        mDonations = DummyDataUtils.getDummyDonation();
        mActivities = DummyDataUtils.getDummyActivities();
        mComments = DummyDataUtils.getDummyComments();
        populateDonation();
        populateActivities();
        populateComments();

        String campaignId = getIntent().getExtras().getString(KEY_CAMPAIGN_ID);
        Campaign campaign = DatabaseManager.getInstance().getCampaigns().get(campaignId);
        if (campaign != null) {
            CampaignAdapter.Holder holder = new CampaignAdapter.Holder(findViewById(R.id.campaign_info_header));
            holder.fillData(campaign);
        } else {
            finish();
        }
    }

    private void populateDonation() {
        int sample = Math.min(MAX_SAMPLE_DONATION_ACT_COMMENT_DISPLAYED, mDonations.size());
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < sample; i++) {
            View itemView = layoutInflater.inflate(R.layout.rv_item_donation, mLayoutDonation, false);
            CampaignDonationAdapter.Holder holder = new CampaignDonationAdapter.Holder(itemView);
//            itemView.setAlpha(0.0f);
            holder.fillData(mDonations.get(i));
            mLayoutDonation.addView(itemView);
        }
        if (sample < MAX_SAMPLE_DONATION_ACT_COMMENT_DISPLAYED) {
            mBtnSeeMoreDonation.setVisibility(View.GONE);
        } else {
            mBtnSeeMoreDonation.setVisibility(View.VISIBLE);
        }
    }

    private void populateActivities() {
        int sample = Math.min(MAX_SAMPLE_DONATION_ACT_COMMENT_DISPLAYED, mActivities.size());
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < sample; i++) {
            View itemView = layoutInflater.inflate(R.layout.rv_item_campaign_activity, mLayoutActivities, false);
            CampaignActivityAdapter.Holder holder = new CampaignActivityAdapter.Holder(itemView);
//            itemView.setAlpha(0.0f);
            holder.fillData(mActivities.get(i));
            mLayoutActivities.addView(itemView);
        }
        if (sample < MAX_SAMPLE_DONATION_ACT_COMMENT_DISPLAYED) {
            mBtnSeeMoreActivities.setVisibility(View.GONE);
        } else {
            mBtnSeeMoreActivities.setVisibility(View.VISIBLE);
        }
    }

    private void populateComments() {
        int sample = Math.min(MAX_SAMPLE_DONATION_ACT_COMMENT_DISPLAYED, mComments.size());
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < sample; i++) {
            View itemView = layoutInflater.inflate(R.layout.rv_item_campaign_comment, mLayoutComments, false);
            CampaignCommentAdapter.Holder holder = new CampaignCommentAdapter.Holder(itemView);
//            itemView.setAlpha(0.0f);
            holder.fillData(mComments.get(i));
            mLayoutComments.addView(itemView);
        }
        if (sample < MAX_SAMPLE_DONATION_ACT_COMMENT_DISPLAYED) {
            mBtnSeeMoreComments.setVisibility(View.GONE);
        } else {
            mBtnSeeMoreComments.setVisibility(View.VISIBLE);
        }
    }

    private void runDonationActivityCommentAnim() {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList<Animator> animators = new ArrayList<>();
        int count = 0;
        for (int i = 0 ; i < mLayoutDonation.getChildCount(); i++) {
            View childView = mLayoutDonation.getChildAt(i);
            ObjectAnimator animator = ObjectAnimator.ofFloat(childView, "alpha", 0.0f, 1.0f);
            animator.setStartDelay(count * 20);
            animators.add(animator);
            count++;
        }
        for (int i = 0 ; i < mLayoutActivities.getChildCount(); i++) {
            View childView = mLayoutActivities.getChildAt(i);
            ObjectAnimator animator = ObjectAnimator.ofFloat(childView, "alpha", 0.0f, 1.0f);
            animator.setStartDelay(count * 20);
            animators.add(animator);
            count++;
        }
        for (int i = 0 ; i < mLayoutComments.getChildCount(); i++) {
            View childView = mLayoutComments.getChildAt(i);
            ObjectAnimator animator = ObjectAnimator.ofFloat(childView, "alpha", 0.0f, 1.0f);
            animator.setStartDelay(count * 20);
            animators.add(animator);
            count++;
        }

        if (animators.size() > 0) {
            animatorSet.playTogether(animators);
            animatorSet.start();
        }
    }

    private void seeMoreDonation() {
        Intent intent = new Intent(this, CampaignDonationListActivity.class);
        if (Utils.materialDesignSupported()) {
            View btnSeeMore = findViewById(R.id.campaign_detail_btn_see_more_donation);
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeClipRevealAnimation(btnSeeMore,
                            btnSeeMore.getWidth()/2, btnSeeMore.getHeight()/2,
                            0, 0);
            startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private void seeMoreActivities() {
        Intent intent = new Intent(this, CampaignActivityListActivity.class);
        if (Utils.materialDesignSupported()) {
            View btnSeeMore = findViewById(R.id.campaign_detail_btn_see_more_activities);
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeClipRevealAnimation(btnSeeMore,
                            btnSeeMore.getWidth()/2, btnSeeMore.getHeight()/2,
                            0, 0);
            startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private void seeMoreComments() {
        Intent intent = new Intent(this, CampaignCommentListActivity.class);
        if (Utils.materialDesignSupported()) {
            View btnSeeMore = findViewById(R.id.campaign_detail_btn_see_more_comments);
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeClipRevealAnimation(btnSeeMore,
                            btnSeeMore.getWidth()/2, btnSeeMore.getHeight()/2,
                            0, 0);
            startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            startActivity(intent);
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
