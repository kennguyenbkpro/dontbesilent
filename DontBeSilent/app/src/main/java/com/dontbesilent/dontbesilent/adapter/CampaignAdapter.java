package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.CacheVariant;
import com.dontbesilent.dontbesilent.MainApplication;
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
                if(CacheVariant.ARRAY_ITEM_CAMPAIGN == null) {
                    CacheVariant.ARRAY_ITEM_CAMPAIGN = new ArrayList<>();
                }
                CacheVariant.ARRAY_ITEM_CAMPAIGN.clear();
                for(Map.Entry entry : DatabaseManager.getInstance().getCampaigns().entrySet()) {
                    campaigns.add(0, (Campaign) entry.getValue());
                    CacheVariant.ARRAY_ITEM_CAMPAIGN.add((Campaign) entry.getValue());
                }
                mCampaigns = campaigns;
                notifyDataSetChanged();
            }
        });
        if(CacheVariant.ARRAY_ITEM_CAMPAIGN == null) {
            CacheVariant.ARRAY_ITEM_CAMPAIGN = new ArrayList<>();
        }
        CacheVariant.ARRAY_ITEM_CAMPAIGN.clear();
        for (Map.Entry entry : DatabaseManager.getInstance().getCampaigns().entrySet()) {
            mCampaigns.add(0, (Campaign) entry.getValue());
            CacheVariant.ARRAY_ITEM_CAMPAIGN.add((Campaign) entry.getValue());
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
        if (position >= getItemCount()) return;
        try {
            final Campaign campaign = mCampaigns.get(position);
            holder.fillData(campaign);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onCampaignSelectedListener != null) {
                        onCampaignSelectedListener.onCampaignSelected(campaign, holder);
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

        public void fillData(Campaign campaign) {
            try {
                mTvCampaignName.setText(campaign.name);
                mTvCampaignDescription.setText(campaign.desception);
                mTvFollowersNum.setText(String.valueOf(campaign.numFollower));
                mTvDonationPercent.setText(String.valueOf((int) (campaign.incomeMoney * 100 / campaign.goalMoney)) + "%");
                mProgress.setProgress((int)(campaign.incomeMoney * 100 / campaign.goalMoney));
                mTvValidTimeValue.setText(campaign.startTime);
                if (!Utils.isEmpty(campaign.image)){
                    Picasso.with(MainApplication.getAppContext()).load(campaign.image).into(mImvBanner);
                }
                Event event = DatabaseManager.getInstance().getEvents().get(campaign.eventId);
                if (event != null) {
                    mTvEventName.setText(event.name);
                    mTvEventName.setVisibility(View.VISIBLE);
                } else {
                    mTvEventName.setVisibility(View.GONE);
                }
                Host host = DatabaseManager.getInstance().getHosts().get(campaign.hostId);
                if (host != null) {
                    mTvOperationName.setText(host.name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setOnCampaignSelectedListener(OnCampaignSelectedListener onCampaignSelectedListener) {
        this.onCampaignSelectedListener = onCampaignSelectedListener;
    }

    public interface OnCampaignSelectedListener {
        void onCampaignSelected(Campaign campaign, Holder holder);
    }
}
