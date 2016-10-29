package com.dontbesilent.dontbesilent.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.activity.CampaignDetailsActivity;
import com.dontbesilent.dontbesilent.adapter.CampaignAdapter;
import com.dontbesilent.dontbesilent.data.Campaign;
import com.dontbesilent.dontbesilent.util.Utils;

/**
 * Created by CuTi on 10/27/2016.
 */

public class FragmentCampaign extends BaseFragment implements CampaignAdapter.OnCampaignSelectedListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRvCampaigns;
    private CampaignAdapter mCampaignAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private SwipeRefreshLayout mSwipeToRefresh;
    private Handler mHandlerUI;

    public static FragmentCampaign getInstance() {
        Bundle bundle = new Bundle();
        FragmentCampaign fragment = new FragmentCampaign();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_campaign, container, false);
        mRvCampaigns = (RecyclerView) contentView.findViewById(R.id.rv_campaigns);
        mSwipeToRefresh = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe_refresh_layout);
        mSwipeToRefresh.setOnRefreshListener(this);
        mHandlerUI = new Handler(Looper.getMainLooper());
        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mCampaignAdapter = new CampaignAdapter();
        mCampaignAdapter.setOnCampaignSelectedListener(this);

        mRvCampaigns.setLayoutManager(mLinearLayoutManager);
        mRvCampaigns.setAdapter(mCampaignAdapter);
        mRvCampaigns.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                if (parent.findContainingViewHolder(view).getAdapterPosition() > 0) {
                    outRect.top = Utils.dpToPx(10);
//                }
            }
        });
    }

    private void loadCampaigns() {
        // TODO
    }

    @Override
    public void onCampaignSelected(Campaign campaign, CampaignAdapter.Holder holder) {
        Intent intent = new Intent(getContext(), CampaignDetailsActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                holder.mCampaignInfoHeader, getString(R.string.transition_campaign));
        startActivity(intent, activityOptionsCompat.toBundle());
    }

    @Override
    public void onRefresh() {
        mSwipeToRefresh.setRefreshing(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandlerUI.post(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeToRefresh.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
