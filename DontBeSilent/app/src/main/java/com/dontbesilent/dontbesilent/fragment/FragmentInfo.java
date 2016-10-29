package com.dontbesilent.dontbesilent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.UserInfo;
import com.dontbesilent.dontbesilent.util.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by CuTi on 10/27/2016.
 */

public class FragmentInfo extends BaseFragment {
    private ImageView coverImageView;
    private ImageView avtImageView;
    private View verifyView;
    private TextView nameTextView;
    private TextView desTextView;

    private UserInfo userInfo;

    private Button userCampaign;


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
        View contentView = inflater.inflate(R.layout.user_info_tab_layout, container, false);
        coverImageView = (ImageView) contentView.findViewById(R.id.cover_img);
        avtImageView = (ImageView) contentView.findViewById(R.id.avatar_img);
        verifyView = (View) contentView.findViewById(R.id.verify_ic);
        nameTextView = (TextView) contentView.findViewById(R.id.name_tv);
        desTextView = (TextView) contentView.findViewById(R.id.description_tv);
        userCampaign = (Button) contentView.findViewById(R.id.user_campaign);
        userCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCampaign fragmentCampaign = new FragmentCampaign();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_holder, fragmentCampaign)
                        .addToBackStack(null)
                        .commit();
            }
        });

        fillData(userInfo);
        return contentView;
    }

    private void fillData(UserInfo userInfo){
        if (userInfo == null) return;
        nameTextView.setText(userInfo.getName());
        desTextView.setText(userInfo.getDescription());
        if (!Utils.isEmpty(userInfo.getAvtUrl())){
            Picasso.with(getContext()).load(userInfo.getAvtUrl()).into(avtImageView);
        }
        if (!Utils.isEmpty(userInfo.getAvtUrl())){
            Picasso.with(getContext()).load(userInfo.getCoverUrl()).into(coverImageView);
        }
        verifyView.setVisibility(userInfo.isVerified()? View.VISIBLE : View.INVISIBLE);
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
