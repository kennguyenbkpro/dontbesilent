package com.dontbesilent.dontbesilent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;

/**
 * Created by LamHX on 29/10/2016.
 */
public class StaffPickAdapter extends RecyclerView.Adapter<StaffPickAdapter.StaffPickHolder> {


    @Override
    public StaffPickHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(StaffPickHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class StaffPickHolder extends RecyclerView.ViewHolder {
        ImageView imgStaffPick;
        TextView txtDecription;

        public StaffPickHolder(View view) {
            super(view);
            imgStaffPick = (ImageView) view.findViewById(R.id.image_item_staff_pick);
            txtDecription = (TextView) view.findViewById(R.id.staffpick_description_title);

        }
    }
}
