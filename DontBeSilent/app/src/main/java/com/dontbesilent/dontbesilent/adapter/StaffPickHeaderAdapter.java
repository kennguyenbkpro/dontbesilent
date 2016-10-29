package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.item.ItemStaffPick;

import java.util.List;

/**
 * Created by LamHX on 29/10/2016.
 */
public class StaffPickHeaderAdapter extends RecyclerView.Adapter<StaffPickHeaderAdapter.HeaderHolder> {

    private List<ItemStaffPick> mStaffPickList;
    public StaffPickHeaderAdapter(List<ItemStaffPick> dataList) {
        this.mStaffPickList = dataList;
    }

    @Override
    public HeaderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_staffpick, parent, false);

        return new HeaderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeaderHolder holder, int position) {
//        holder.imgStaffPick
    }

    @Override
    public int getItemCount() {
        return mStaffPickList.size();
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        ImageView imgStaffPick;
        TextView txtDecription;

        public HeaderHolder(View view) {
            super(view);
            imgStaffPick = (ImageView) view.findViewById(R.id.image_item_staff_pick);
            txtDecription = (TextView) view.findViewById(R.id.staffpick_description_title);

        }
    }
}
