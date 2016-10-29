package com.dontbesilent.dontbesilent.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Host;
import com.dontbesilent.dontbesilent.fragment.FragmentInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by khanhnq2 on 30/10/2016.
 */

public class HostActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_act_layout);
        final FragmentInfo fragmentInfo = (FragmentInfo) getSupportFragmentManager().findFragmentById(R.id.frag_info);
        if (getIntent().getExtras() != null){
            String id = getIntent().getExtras().getString(EXTRA_ID);
            FirebaseDatabase.getInstance().getReference().child("hosts").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (fragmentInfo != null){
                        Host host = dataSnapshot.getValue(Host.class);
                        fragmentInfo.setUserInfo(host);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
