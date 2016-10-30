package com.dontbesilent.dontbesilent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignMapAdapter;
import com.dontbesilent.dontbesilent.data.Campaign;
import com.dontbesilent.dontbesilent.data.DatabaseManager;
import com.dontbesilent.dontbesilent.data.Event;
import com.dontbesilent.dontbesilent.fragment.MapsFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by lamhx on 30/10/2016.
 */

public class CampaignMapDetails extends AppCompatActivity {
    public static final String EXTRA_ID_EVENT = "EXTRA_ID_CampaignMapDetails";

    private RecyclerView mRecyclerView;
//    private SupportMapFragment mapFragment;
//    private GoogleMap map;
//    private GoogleApiClient mGoogleApiClient;
    private LinearLayoutManager mLinearLayoutManager;
    private CampaignMapAdapter mAdapter;
    private MapsFragment mapFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign_map_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String eventId = intent.getExtras().getString(EXTRA_ID_EVENT);
        Event event = DatabaseManager.getInstance().getEvents().get(eventId);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_campain);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new CampaignMapAdapter();
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        /*mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }
            });
        }*/

        mAdapter.setCampaignClickListener(new CampaignClickListener() {
            @Override
            public void onClick(Campaign campaign) {
                Intent intent = new Intent(CampaignMapDetails.this, CampaignDetailsActivity.class);
                intent.putExtra(CampaignDetailsActivity.KEY_CAMPAIGN_ID, campaign.id);
                startActivity(intent);
            }
        });

        if(mapFragment == null) {
            mapFragment = MapsFragment.getInstance();
        }

        mapFragment.setListener(new CampaignMapClickListener() {
            @Override
            public void onClick(int pos) {
                mRecyclerView.getLayoutManager().scrollToPosition(pos);
            }
        });

        showFragment(mapFragment);
    }

    /*protected void loadMap(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {

        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }*/

    public interface CampaignClickListener {
        void onClick(Campaign campaign);
    }

    public interface CampaignMapClickListener {
        void onClick(int pos);
    }

    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction
                    .replace(R.id.content_main, fragment)
                    .commit();
        }
    }
}
