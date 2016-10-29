package com.dontbesilent.dontbesilent;

import android.app.Application;

/**
 * Created by CuTi on 10/29/2016.
 */

public class MainApplication extends Application {
    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MainApplication getInstance() {
        return mInstance;
    }
}
