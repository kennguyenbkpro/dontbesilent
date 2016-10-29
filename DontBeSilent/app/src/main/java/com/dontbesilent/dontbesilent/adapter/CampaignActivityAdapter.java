package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CuTi on 10/29/2016.
 */
public class CampaignActivityAdapter extends RecyclerView.Adapter<CampaignActivityAdapter.Holder> {

    private ArrayList<com.dontbesilent.dontbesilent.data.CampaignActivity> mActivities = new ArrayList<>();

    public void setData(ArrayList<com.dontbesilent.dontbesilent.data.CampaignActivity> campaignActivities) {
        mActivities.clear();
        if (campaignActivities != null) {
            mActivities.addAll(campaignActivities);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mActivities.size();
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
            holder.fillData(mActivities.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public ImageView mImvAvatar;
        public TextView mTvContent;
        public ImageView mImv;

        public Holder(View itemView) {
            super(itemView);
            mImvAvatar = (ImageView) itemView.findViewById(R.id.activity_item_imv_avatar);
            mTvContent = (TextView) itemView.findViewById(R.id.activity_item_tv_content);
            mImv = (ImageView) itemView.findViewById(R.id.activity_item_imv);
        }

        public void fillData(com.dontbesilent.dontbesilent.data.CampaignActivity activity) {
            try {
                mTvContent.setText(activity.content);
                if (!TextUtils.isEmpty(activity.url)) {
                    Picasso.with(itemView.getContext()).load(activity.url).into(mImv);
                    mImv.setVisibility(View.VISIBLE);
                } else {
                    mImv.setImageDrawable(null);
                    mImv.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(activity.avatarUrl)) {
                    Picasso.with(itemView.getContext()).load(activity.avatarUrl).into(mImvAvatar);
                } else {
                    mImvAvatar.setImageResource(R.drawable.dummy_rounded_avatar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
