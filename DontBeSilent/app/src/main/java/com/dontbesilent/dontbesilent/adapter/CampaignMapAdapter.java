package com.dontbesilent.dontbesilent.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.CacheVariant;
import com.dontbesilent.dontbesilent.MainApplication;
import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.activity.CampaignMapDetails;
import com.dontbesilent.dontbesilent.data.Campaign;
import com.dontbesilent.dontbesilent.data.DatabaseManager;
import com.dontbesilent.dontbesilent.data.Host;
import com.dontbesilent.dontbesilent.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lamhx on 30/10/2016.
 */

public class CampaignMapAdapter extends RecyclerView.Adapter<CampaignMapAdapter.Holder> {

    private ArrayList<Campaign> mCampaigns = CacheVariant.ARRAY_ITEM_CAMPAIGN;

    private CampaignMapDetails.CampaignClickListener mCampaignClickListener;

    public void setCampaignClickListener (CampaignMapDetails.CampaignClickListener listener) {
        mCampaignClickListener = listener;
    }
    public CampaignMapAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campaign, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(CampaignMapAdapter.Holder holder, int position) {

        final Campaign campaign = mCampaigns.get(position);
        Host host = DatabaseManager.getInstance().getHosts().get(mCampaigns.get(position).hostId);

        holder.mHost.setText(host.name);
        if(host.isVerified) {
            Drawable img = MainApplication.getAppContext().getResources().getDrawable(
                    R.drawable.checked);
            img.setBounds(0, 0, Utils.dpToPx(16), Utils.dpToPx(16));
            holder.mHost.setCompoundDrawablePadding(5);
            holder.mHost.setCompoundDrawables(null, null, img, null);
        }
        Picasso.with(MainApplication.getAppContext()).load(host.avatar).into(holder.mAvata);

        holder.mCampaignTitle.setText(campaign.name);
        holder.mCampaignShortDetail.setText(campaign.desception);
        holder.mTvFollowersNum.setText(String.valueOf(campaign.numFollower));
        holder.mTvDonationPercent.setText(String.valueOf((int) (campaign.incomeMoney * 100 / campaign.goalMoney)));
        holder.mProgress.setProgress((int) (campaign.incomeMoney * 100 / campaign.goalMoney));
        holder.mTvValidTimeValue.setText(campaign.startTime);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCampaignClickListener != null) {
                    mCampaignClickListener.onClick(campaign);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCampaigns.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView mHost;
        ImageView mAvata;

        TextView mCampaignTitle;
        TextView mCampaignShortDetail;

        public TextView mTvFollowersNum;
        public TextView mTvDonationPercent;
        public TextView mTvValidTimeValue;
        public ProgressBar mProgress;

        public Holder(View itemView) {
            super(itemView);
            mHost = (TextView) itemView.findViewById(R.id.tv_campaign_fame);
            mAvata = (ImageView) itemView.findViewById(R.id.comment_item_imv_avatar);

            mCampaignTitle = (TextView) itemView.findViewById(R.id.campaign_title);
            mCampaignShortDetail = (TextView) itemView.findViewById(R.id.campaign_short_detail);

            mTvFollowersNum = (TextView) itemView.findViewById(R.id.tv_followers_num);
            mTvDonationPercent = (TextView) itemView.findViewById(R.id.tv_donation_percent);
            mTvValidTimeValue = (TextView) itemView.findViewById(R.id.tv_valid_time_value);
            mProgress = (ProgressBar) itemView.findViewById(R.id.item_campaign_progress);
        }
    }
}
