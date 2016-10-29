package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Campaign;

import java.util.ArrayList;

/**
 * Created by CuTi on 10/27/2016.
 */

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.Holder> {

    private ArrayList<Campaign> mCampaigns = new ArrayList<>();

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
        try {
            Campaign campaign = mCampaigns.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
