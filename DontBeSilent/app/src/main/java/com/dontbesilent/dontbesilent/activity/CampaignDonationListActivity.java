package com.dontbesilent.dontbesilent.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignCommentAdapter;
import com.dontbesilent.dontbesilent.adapter.CampaignDonationAdapter;
import com.dontbesilent.dontbesilent.util.Utils;

public class CampaignDonationListActivity extends AppCompatActivity {

    private RecyclerView mRvDonations;
    private LinearLayoutManager mRvDonationsLayoutManager;
    private CampaignDonationAdapter mCampaignDonationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_donation_list);
        mRvDonations = (RecyclerView) findViewById(R.id.rv_campaign_donation_list);
        mRvDonationsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCampaignDonationAdapter = new CampaignDonationAdapter();
        mRvDonations.setLayoutManager(mRvDonationsLayoutManager);
        mRvDonations.setAdapter(mCampaignDonationAdapter);
        mRvDonations.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.findContainingViewHolder(view).getAdapterPosition() > 0) {
                    outRect.top = Utils.dpToPx(2);
                }
            }
        });
    }
}
