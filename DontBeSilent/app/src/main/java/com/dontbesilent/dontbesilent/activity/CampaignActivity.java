package com.dontbesilent.dontbesilent.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dontbesilent.dontbesilent.R;

/**
 * Created by CuTi on 10/29/2016.
 */

public class CampaignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign_act_layout);
//        final FragmentCampaign fragmentInfo = (FragmentCampaign) getSupportFragmentManager().findFragmentById(R.id.campaign_frag);
//        if (getIntent().getExtras() != null){
//            FirebaseDatabase.getInstance().getReference().child("hosts").child("-KVG9km7EGeirW-Aelgd").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (fragmentInfo != null){
//                        Host host = dataSnapshot.getValue(Host.class);
//                        fragmentInfo.setUserInfo(host);
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
        }
}
