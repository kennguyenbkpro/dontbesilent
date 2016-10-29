package com.dontbesilent.dontbesilent.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.MainApplication;
import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Host;
import com.dontbesilent.dontbesilent.item.ItemStaffPick;
import com.dontbesilent.dontbesilent.util.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LamHX on 29/10/2016.
 */
public class StaffPickHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemStaffPick> mStaffPickList = new ArrayList<>();
    private List<Host> hostArrayList = new ArrayList<>();

    private int HEADER_CAMPAIGN = 0;
    private int HEADER_HALL_OF_FAME = 1;

    private DatabaseReference mDatabase;
    private OnHostSelectListener onHostSelectListener;

    public StaffPickHeaderAdapter(List<Host> hosts, List<ItemStaffPick> staffPicks) {
//        this.mStaffPickList = dataList;

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("hosts").limitToLast(20).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Host host = dataSnapshot.getValue(Host.class);
                host.id = dataSnapshot.getKey();
                hostArrayList.add(0, host);
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Test
        for (int i = 0; i < 10; i++) {
            mStaffPickList.add(new ItemStaffPick("http://www.urwallpapers.com/download/7547/Macro/hd_wallpaper_wallpaper_download_free_grass-1024x600.jpg", "Hoang Xuan Lam"));
//            hostArrayList.add(new ItemHallOfFame());
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_CAMPAIGN) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_staff_pick, parent, false);
            return new HeaderHolder(view);
        } else if (viewType == HEADER_HALL_OF_FAME) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_decription_hall_of_fame, parent, false);
            return new HallOfFameHeader(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hall_of_fame, parent, false);
            return new HallOfFameHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(position >= getItemCount()) return;
        if (position == HEADER_CAMPAIGN) { //header
            if (holder instanceof HeaderAdapter.HeaderItemHolder) {

            }
        } else if (position == HEADER_HALL_OF_FAME) {
            if(holder instanceof HallOfFameHeader) {
                Drawable img = MainApplication.getAppContext().getResources().getDrawable(
                        R.drawable.flag);
                img.setBounds(0, 0, Utils.dpToPx(40), Utils.dpToPx(40));
                ((HallOfFameHeader) holder).mTextHallOfFameHeader.setCompoundDrawables(img, null, null, null);

            }
        } else {
            if (holder instanceof HallOfFameHolder) {
                ((HallOfFameHolder)holder).mName.setText(hostArrayList.get(position  - 2).name);
                if(hostArrayList.get(position  - 2).isVerified) {
                    Drawable img = MainApplication.getAppContext().getResources().getDrawable(
                            R.drawable.checked);
                    img.setBounds(0, 0, Utils.dpToPx(16), Utils.dpToPx(16));
                    ((HallOfFameHolder)holder).mName.setCompoundDrawablePadding(5);
                    ((HallOfFameHolder)holder).mName.setCompoundDrawables(null, null, img, null);
                }
                ((HallOfFameHolder)holder).mProj.setText(hostArrayList.get(position - 2).numberOfCampaign + " campaigns");
                Picasso.with(MainApplication.getAppContext()).load(hostArrayList.get(position - 2).avatar).into(((HallOfFameHolder)holder).mAvata);
                ((HallOfFameHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onHostSelectListener != null){
                            onHostSelectListener.onSelect(hostArrayList.get(position - 2));
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return hostArrayList.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_CAMPAIGN;
        } else if (position == 1) {
            return HEADER_HALL_OF_FAME;
        } else {
            return 2;
        }
    }

    public void setOnHostSelectListener(OnHostSelectListener onHostSelectListener) {
        this.onHostSelectListener = onHostSelectListener;
    }

    public class HallOfFameHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mProj;
        ImageView mAvata;

        public HallOfFameHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_name_of_fame);
            mProj = (TextView) itemView.findViewById(R.id.tv_project_of_fame);
            mAvata = (ImageView) itemView.findViewById(R.id.img_avata_of_fame);
        }
    }

    public class HallOfFameHeader extends RecyclerView.ViewHolder {

        TextView mTextHallOfFameHeader;

        public HallOfFameHeader(View itemView) {
            super(itemView);
            mTextHallOfFameHeader = (TextView) itemView.findViewById(R.id.tv_decription_fame);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;

        public HeaderHolder(View view) {
            super(view);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.horizontal_recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            HeaderAdapter adapter = new HeaderAdapter();
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(adapter);

            mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    int pos = parent.findContainingViewHolder(view).getAdapterPosition();
                    if (pos != 0 && pos != mStaffPickList.size() -1) {
//                    outRect.top = Utils.dpToPx(3);
                        outRect.left = Utils.dpToPx(1);
//                        outRect.right = Utils.dpToPx(1);
                    } else if(pos != mStaffPickList.size() - 2) {

                    }
                }
            });
        }
    }

    class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderItemHolder> {
        @Override
        public HeaderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header_staffpick, parent, false);

            return new HeaderItemHolder(itemView);
        }

        @Override
        public void onBindViewHolder(HeaderItemHolder holder, int position) {
            Picasso.with(MainApplication.getAppContext()).load(mStaffPickList.get(position).mUrl).into(holder.imgStaffPick);
            holder.txtDecription.setText(mStaffPickList.get(position).mDecription);
        }

        @Override
        public int getItemCount() {
            return mStaffPickList.size();
        }

        public class HeaderItemHolder extends RecyclerView.ViewHolder {
            ImageView imgStaffPick;
            TextView txtDecription;

            public HeaderItemHolder(View view) {
                super(view);
                imgStaffPick = (ImageView) view.findViewById(R.id.image_item_staff_pick);
                txtDecription = (TextView) view.findViewById(R.id.staffpick_description_title);

            }
        }
    }

    public interface OnHostSelectListener {
        void onSelect(Host host);
    }

}
