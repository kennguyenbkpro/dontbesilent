package com.dontbesilent.dontbesilent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.activity.InputCampaignActivity;

/**
 * Created by CuTi on 10/27/2016.
 */

public class FragmentInfo extends BaseFragment {
    public static FragmentInfo getInstance() {
        Bundle bundle = new Bundle();
        FragmentInfo fragment = new FragmentInfo();
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
        View contentView = inflater.inflate(R.layout.fragment_info, container, false);
        contentView.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentInfo.this.getActivity(), InputCampaignActivity.class);
                startActivity(intent);
            }
        });
        return contentView;
    }
}
