package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.CampaignComment;

import java.util.ArrayList;

/**
 * Created by CuTi on 10/29/2016.
 */
public class CampaignCommentAdapter extends RecyclerView.Adapter<CampaignCommentAdapter.Holder> {

    private ArrayList<CampaignComment> mCampaignComments = new ArrayList<>();

    public void setData(ArrayList<CampaignComment> campaignComments) {
        mCampaignComments.clear();
        if (campaignComments != null) {
            mCampaignComments.addAll(campaignComments);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
//        return mCampaignComments.size();
        return 20;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_campaign_comment, parent, false);
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
        public TextView mTvName;
        public TextView mTvUserType;
        public TextView mTvContent;

        public Holder(View itemView) {
            super(itemView);
            mImvAvatar = (ImageView) itemView.findViewById(R.id.comment_item_imv_avatar);
            mTvName = (TextView) itemView.findViewById(R.id.comment_item_tv_name);
            mTvUserType = (TextView) itemView.findViewById(R.id.comment_item_tv_user_type);
            mTvContent = (TextView) itemView.findViewById(R.id.comment_item_tv_content);
        }
    }
}
