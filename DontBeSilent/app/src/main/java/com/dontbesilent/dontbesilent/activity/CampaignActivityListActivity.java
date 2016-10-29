package com.dontbesilent.dontbesilent.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignActivityAdapter;
import com.dontbesilent.dontbesilent.util.Utils;

public class CampaignActivityListActivity extends AppCompatActivity {

    private RecyclerView mRvActivities;
    private LinearLayoutManager mRvActivitiesLayoutManager;
    private CampaignActivityAdapter mCampaignActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_activity_list);

        mRvActivities = (RecyclerView) findViewById(R.id.rv_campaign_activity_list);
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
    }
}
