package com.prash.headysat.presentation.services;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.prash.headysat.di.ActivityComponent;
import com.prash.headysat.presentation.ui.activity.MainActivity;
import com.prash.headysat.R;
import com.prash.headysat.domain.model.ResponseData;
import com.prash.headysat.domain.network.GetResponse;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by prash on 09/12/17.
 */

public class RetroService {

    ActivityComponent activityComponent;

    @Inject
    Context context;

    @Inject
    Realm realmInstance;

    @Inject
    GetResponse responseService;

    public RetroService(@NonNull ActivityComponent appComponent) {
        this.activityComponent = appComponent ;
        activityComponent.injectRetro(this);
    }


    public void getResponseData(){
        // If no intenet show error message to user
        if(!isNetworkAvailable()){
            ((MainActivity) context).showFeed(context.getResources().getString(R.string.no_internet));
            return;
        }

        Call<ResponseData> getData = responseService.getResponse();
        getData.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.code() ==200) {
                    ResponseData responseData = response.body();
                    responseData.set_id("0");
                    realmInstance.beginTransaction();
                    ResponseData realmResponseData = realmInstance.copyToRealmOrUpdate(responseData);
                    realmInstance.commitTransaction();
                    ((MainActivity) context).setUpViewPager();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                ((MainActivity) context).showFeed(context.getResources().getString(R.string.server_error));
                Timber.d(t.getMessage());
            }
        });
    }

    public boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();

    }
}
