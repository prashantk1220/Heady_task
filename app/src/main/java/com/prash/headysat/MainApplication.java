package com.prash.headysat;

import android.app.Application;
import android.content.Context;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by prash on 09/12/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRealmConfiguration();

        if(BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

    }

    public void initRealmConfiguration(){
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
