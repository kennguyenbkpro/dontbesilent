package com.dontbesilent.dontbesilent;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by CuTi on 10/29/2016.
 */

public class MainApplication extends Application {
    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FirebaseApp.initializeApp(this);
    }

    public static synchronized MainApplication getInstance() {
        return mInstance;
    }
}
