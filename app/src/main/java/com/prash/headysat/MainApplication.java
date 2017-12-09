package com.prash.headysat;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by prash on 09/12/17.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
