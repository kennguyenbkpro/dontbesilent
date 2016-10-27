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

public class Fragment2 extends BaseFragment {
    public static Fragment2 getInstance() {
        Bundle bundle = new Bundle();
        Fragment2 fragment = new Fragment2();
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
        View contentView = inflater.inflate(R.layout.fragment_2, container, false);
        return contentView;
    }
}
