package com.dontbesilent.dontbesilent.data;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CPU10312-Local on 10/30/2016.
 */
public class DatabaseManager {
    private static DatabaseManager mDatabaseManager;
    private static Object object = new Object();
    private Map<String, Host> mapHost = new HashMap<>();
    private Map<String, Campaign> mapCampaign = new HashMap<>();
    private Map<String, Event> mapEvent = new HashMap<>();
    private DatabaseReference mDatabase;
    private List<Listener> mListner = new ArrayList<>();

    public static DatabaseManager getInstance() {
        if(mDatabaseManager == null) {
            synchronized (object) {
                if(mDatabaseManager == null) {
                    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                    mDatabaseManager = new DatabaseManager();
                }
            }
        }
        return mDatabaseManager;
    }

    public void addListener(Listener listener) {
        mListner.add(listener);
    }

    public void removeListener(Listener listener) {
        mListner.remove(listener);
    }

    public Map<String, Campaign> getCampaigns() {
        return mapCampaign;
    }

    public Map<String, Host> getHosts() {
        return mapHost;
    }

    public Map<String, Event> getEvents() {
        return mapEvent;
    }

    public DatabaseManager() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("events").limitToLast(20).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Event event = dataSnapshot.getValue(Event.class);
                    event.id = dataSnapshot.getKey();
                    mapEvent.put(event.id, event);
                    for (Listener listener : mListner) {
                        listener.onDataChange();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("campaigns").limitToLast(20).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Campaign campaign = dataSnapshot.getValue(Campaign.class);
                campaign.id = dataSnapshot.getKey();
                mapCampaign.put(campaign.id, campaign);
                for (Listener listener : mListner) {
                    listener.onDataChange();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("hosts").limitToLast(20).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Host host = dataSnapshot.getValue(Host.class);
                host.id = dataSnapshot.getKey();
                mapHost.put(host.id, host);
                for (Listener listener : mListner) {
                    listener.onDataChange();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static interface Listener {
        void onDataChange();
    }
}
