package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Donation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CuTi on 10/29/2016.
 */
public class CampaignDonationAdapter extends RecyclerView.Adapter<CampaignDonationAdapter.Holder> {

    private ArrayList<Donation> mDonations = new ArrayList<>();

    public void setData(ArrayList<Donation> donations) {
        mDonations.clear();
        if (donations != null) {
            mDonations.addAll(donations);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDonations.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_donation, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        if(position >= getItemCount()) return;
        try {
            holder.fillData(mDonations.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public ImageView mImv;
        public TextView mTvContent;

        public Holder(View itemView) {
            super(itemView);
            mImv = (ImageView) itemView.findViewById(R.id.donation_item_imv);
            mTvContent = (TextView) itemView.findViewById(R.id.donation_item_tv_content);
        }

        public void fillData(Donation donation) {
            try {
                mTvContent.setText(donation.description);
                if (!TextUtils.isEmpty(donation.avatarUrl)) {
                    Picasso.with(itemView.getContext()).load(donation.avatarUrl).into(mImv);
                } else {
                    mImv.setImageResource(R.drawable.dummy_rounded_avatar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
