package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Campaign;
import com.dontbesilent.dontbesilent.data.DatabaseManager;
import com.dontbesilent.dontbesilent.data.Event;
import com.dontbesilent.dontbesilent.data.Host;
import com.dontbesilent.dontbesilent.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by CuTi on 10/27/2016.
 */

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.Holder> {

    private ArrayList<Campaign> mCampaigns = new ArrayList<>();
    private OnCampaignSelectedListener onCampaignSelectedListener;

    public CampaignAdapter() {
        DatabaseManager.getInstance().addListener(new DatabaseManager.Listener() {
            @Override
            public void onDataChange() {
                ArrayList<Campaign> campaigns = new ArrayList<>();
                for(Map.Entry entry : DatabaseManager.getInstance().getCampaigns().entrySet()) {
                    campaigns.add(0, (Campaign) entry.getValue());
                }
                mCampaigns = campaigns;
                notifyDataSetChanged();
            }
        });
        for (Map.Entry entry : DatabaseManager.getInstance().getCampaigns().entrySet()) {
            mCampaigns.add(0, (Campaign) entry.getValue());
        }
    }

    @Override
    public int getItemCount() {
        return mCampaigns.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_campaign, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if(position >= getItemCount()) return;
        try {
            Campaign campaign = mCampaigns.get(position);
            holder.mTvCampaignName.setText(campaign.name);
            holder.mTvCampaignDescription.setText(campaign.desception);
            holder.mTvFollowersNum.setText(String.valueOf(campaign.numFollower));
            holder.mTvDonationPercent.setText(String.valueOf((int) (campaign.incomeMoney * 100 / campaign.goalMoney)) + "%");
            holder.mProgress.setProgress((int)(campaign.incomeMoney * 100 / campaign.goalMoney));
            holder.mTvValidTimeValue.setText(campaign.startTime);
            if (!Utils.isEmpty(campaign.image)){
                Picasso.with(holder.itemView.getContext()).load(campaign.image).into(holder.mImvBanner);
            }
            Event event = DatabaseManager.getInstance().getEvents().get(campaign.eventId);
            if(event != null) {
                holder.mTvEventName.setText(event.name);
            }
            Host host = DatabaseManager.getInstance().getHosts().get(campaign.hostId);
            if(host != null) {
                holder.mTvOperationName.setText(host.name);
            }
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
        public RelativeLayout mCampaignInfoHeader;
        public ImageView mImvBanner;
        public ImageView mImvBookmark;
        public TextView mTvCampaignName;
        public TextView mTvCampaignDescription;
        public TextView mTvEventName;
        public TextView mTvOperationName;
        public TextView mTvDuration;
        public TextView mTvLocation;
        public TextView mTvFollowersNum;
        public TextView mTvDonationPercent;
        public TextView mTvValidTimeValue;
        public TextView mTvType;
        public ProgressBar mProgress;

        public Holder(View itemView) {
            super(itemView);
            mCampaignInfoHeader = (RelativeLayout) itemView.findViewById(R.id.campaign_info_header);
            mImvBanner = (ImageView) itemView.findViewById(R.id.item_campaign_imv_image);
            mImvBookmark = (ImageView) itemView.findViewById(R.id.item_campaign_imv_bookmark);
            mTvCampaignName = (TextView) itemView.findViewById(R.id.item_campaign_tv_name);
            mTvCampaignDescription = (TextView) itemView.findViewById(R.id.item_campaign_tv_description);
            mTvOperationName = (TextView) itemView.findViewById(R.id.item_campaign_tv_operation_name);
            mTvFollowersNum = (TextView) itemView.findViewById(R.id.tv_followers_num);
            mTvDonationPercent = (TextView) itemView.findViewById(R.id.tv_donation_percent);
            mTvValidTimeValue = (TextView) itemView.findViewById(R.id.tv_valid_time_value);
            mTvEventName = (TextView) itemView.findViewById(R.id.item_campaign_tv_event_name);
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
