package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.CampaignActivity;

import java.util.ArrayList;

/**
 * Created by CuTi on 10/29/2016.
 */
public class CampaignActivityAdapter extends RecyclerView.Adapter<CampaignActivityAdapter.Holder> {

    private ArrayList<CampaignActivity> mCampaigns = new ArrayList<>();

    public void setData(ArrayList<CampaignActivity> campaignActivities) {
        mCampaigns.clear();
        if (campaignActivities != null) {
            mCampaigns.addAll(campaignActivities);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
//        return mCampaigns.size();
        return 20;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_campaign_activity, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if(position >= getItemCount()) return;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public ImageView mImvAvatar;
        public TextView mTvContent;

        public Holder(View itemView) {
            super(itemView);
            mImvAvatar = (ImageView) itemView.findViewById(R.id.activity_item_imv_avatar);
            mTvContent = (TextView) itemView.findViewById(R.id.activity_item_tv_content);
        }
    }
}
