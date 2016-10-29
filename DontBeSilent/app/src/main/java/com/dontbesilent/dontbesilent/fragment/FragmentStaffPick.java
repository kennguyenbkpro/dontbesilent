package com.dontbesilent.dontbesilent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.activity.HostActivity;
import com.dontbesilent.dontbesilent.adapter.StaffPickHeaderAdapter;
import com.dontbesilent.dontbesilent.data.Host;

/**
 * Created by LamHX on 29/10/2016.
 */
public class FragmentStaffPick extends BaseFragment implements  SwipeRefreshLayout.OnRefreshListener, StaffPickHeaderAdapter.OnHostSelectListener {
    private RecyclerView mRvStaffPick;
    private LinearLayoutManager mLinearLayoutManager;
    private StaffPickHeaderAdapter mAdapter;

    private SwipeRefreshLayout mSwipeToRefresh;

    private Handler mHandlerUI;

    public static FragmentStaffPick getInstance() {
        Bundle bundle = new Bundle();
        FragmentStaffPick fragment = new FragmentStaffPick();
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
        View contentView = inflater.inflate(R.layout.fragment_staff_pick, container, false);
        mRvStaffPick = (RecyclerView) contentView.findViewById(R.id.staff_pick_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new StaffPickHeaderAdapter();
        mRvStaffPick.setLayoutManager(mLinearLayoutManager);
        mRvStaffPick.setAdapter(mAdapter);
        mAdapter.setOnHostSelectListener(this);

        mSwipeToRefresh = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe_refresh_layout);
        mSwipeToRefresh.setOnRefreshListener(this);

        mHandlerUI = new Handler(Looper.getMainLooper());

        return contentView;
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

    @Override
    public void onSelect(Host host) {
        Intent intent = new Intent(getContext(), HostActivity.class);
        intent.putExtra(HostActivity.EXTRA_ID, host.id);
        startActivity(intent);
    }
}
