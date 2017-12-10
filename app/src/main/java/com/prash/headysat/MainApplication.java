package com.prash.headysat;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.prash.headysat.presentation.adapters.RealmORMAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by prash on 09/12/17.
 */

public class MainApplication extends Application {

    Retrofit mRetrofit;
    RealmORMAdapter mRealmORMAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mRealmORMAdapter = new RealmORMAdapter();

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getURL("BASE_URL"))
                .build();

        if(BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

    }

    public String getURL(String key) {
        AssetManager assetManager = this.getAssets();
        Properties properties = new Properties();
        try {
            InputStream inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return properties.getProperty(key);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    public RealmORMAdapter getRealmORMAdapter(){
        return mRealmORMAdapter;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if(mRetrofit != null)
            mRetrofit = null;
        if(mRealmORMAdapter != null)
            mRealmORMAdapter = null;
    }
}
