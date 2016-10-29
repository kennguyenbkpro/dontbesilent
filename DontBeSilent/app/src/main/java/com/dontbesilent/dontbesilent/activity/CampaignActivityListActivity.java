package com.dontbesilent.dontbesilent.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignActivityAdapter;
import com.dontbesilent.dontbesilent.util.DummyDataUtils;
import com.dontbesilent.dontbesilent.util.Utils;

public class CampaignActivityListActivity extends AppCompatActivity {

    private RecyclerView mRvActivities;
    private LinearLayoutManager mRvActivitiesLayoutManager;
    private CampaignActivityAdapter mCampaignActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRvActivities = (RecyclerView) findViewById(R.id.rv_campaign_activity_list);
        mRvActivitiesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCampaignActivityAdapter = new CampaignActivityAdapter();
        mRvActivities.setLayoutManager(mRvActivitiesLayoutManager);
        mRvActivities.setAdapter(mCampaignActivityAdapter);

//        mRvActivities.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                if (parent.findContainingViewHolder(view).getAdapterPosition() > 0) {
//                    outRect.top = Utils.dpToPx(2);
//                }
//            }
//        });

        if (DummyDataUtils.mActivities == null || DummyDataUtils.mActivities.isEmpty()) {
            DummyDataUtils.getDummyActivities();
        }
        mCampaignActivityAdapter.setData(DummyDataUtils.mActivities);
    }
}
