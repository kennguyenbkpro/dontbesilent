package com.dontbesilent.dontbesilent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dontbesilent.dontbesilent.R;

/**
 * Created by CuTi on 10/27/2016.
 */

public class FragmentEvent extends BaseFragment {
    public static FragmentEvent getInstance() {
        Bundle bundle = new Bundle();
        FragmentEvent fragment = new FragmentEvent();
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
        View contentView = inflater.inflate(R.layout.fragment_event, container, false);
        return contentView;
    }
}
