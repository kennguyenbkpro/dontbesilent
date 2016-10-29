package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Campaign;

import java.util.ArrayList;

/**
 * Created by CuTi on 10/27/2016.
 */

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.Holder> {

    private ArrayList<Campaign> mCampaigns = new ArrayList<>();
    private OnCampaignSelectedListener onCampaignSelectedListener;

    public void setData(ArrayList<Campaign> campaigns) {
        mCampaigns.clear();
        if (campaigns != null) {
            mCampaigns.addAll(campaigns);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_campaign, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if(position >= getItemCount()) return;
        try {
//            Campaign campaign = mCampaigns.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onCampaignSelectedListener != null) {
                        onCampaignSelectedListener.onCampaignSelected(null, holder);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public ImageView mImvBanner;
        public ImageView mImvBookmark;
        public TextView mTvCampaignName;
        public TextView mTvCampaignDescription;
        public TextView mTvEventName;
        public TextView mTvOperationName;
        public TextView mTvDuration;
        public TextView mTvLocation;
        public TextView mTvType;
        public ProgressBar mProgress;

        public Holder(View itemView) {
            super(itemView);
            mImvBanner = (ImageView) itemView.findViewById(R.id.item_campaign_imv_image);
            mImvBookmark = (ImageView) itemView.findViewById(R.id.item_campaign_imv_bookmark);
            mTvCampaignName = (TextView) itemView.findViewById(R.id.item_campaign_tv_name);
            mTvCampaignDescription = (TextView) itemView.findViewById(R.id.item_campaign_tv_description);
            mTvOperationName = (TextView) itemView.findViewById(R.id.item_campaign_tv_operation_name);
//            mTvEventName = (TextView) itemView.findViewById(R.id.item_campaign_tv_event_name);
//            mTvDuration = (TextView) itemView.findViewById(R.id.item_campaign_tv_duration);
//            mTvLocation = (TextView) itemView.findViewById(R.id.item_campaign_tv_location);
            mTvType = (TextView) itemView.findViewById(R.id.item_campaign_tv_type);
            mProgress = (ProgressBar) itemView.findViewById(R.id.item_campaign_progress);
        }
    }

    public void setOnCampaignSelectedListener(OnCampaignSelectedListener onCampaignSelectedListener) {
        this.onCampaignSelectedListener = onCampaignSelectedListener;
    }

    public interface OnCampaignSelectedListener {
        void onCampaignSelected(Campaign campaign, Holder holder);
    }
}
