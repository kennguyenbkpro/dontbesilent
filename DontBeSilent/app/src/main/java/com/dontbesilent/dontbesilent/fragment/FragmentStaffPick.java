package com.dontbesilent.dontbesilent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.StaffPickHeaderAdapter;

/**
 * Created by LamHX on 29/10/2016.
 */
public class FragmentStaffPick extends BaseFragment {
    private RecyclerView mRvStaffPick;
    private LinearLayoutManager mLinearLayoutManager;
    private StaffPickHeaderAdapter mAdapter;

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
        mAdapter = new StaffPickHeaderAdapter(null, null);
        mRvStaffPick.setLayoutManager(mLinearLayoutManager);
        mRvStaffPick.setAdapter(mAdapter);
        return contentView;
    }
}
