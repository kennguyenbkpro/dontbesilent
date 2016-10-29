package com.dontbesilent.dontbesilent;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

/**
 * Created by CuTi on 10/29/2016.
 */

public class MainApplication extends Application {
    private static MainApplication mInstance;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FirebaseApp.initializeApp(this);
        mContext = getApplicationContext();
    }

    public static synchronized MainApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext(){
        return mContext;
    }
}
