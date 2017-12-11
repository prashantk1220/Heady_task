package com.prash.headysat.presentation.services;

import android.app.Activity;
import android.content.Context;

import com.prash.headysat.presentation.ui.MainActivity;
import com.prash.headysat.MainApplication;
import com.prash.headysat.R;
import com.prash.headysat.domain.model.ResponseData;
import com.prash.headysat.domain.network.GetResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by prash on 09/12/17.
 */

public class RetrofitService {

    GetResponse mResponseService;
    Context mContext;
    MainApplication mApplication;
    Realm mRealm;

    public RetrofitService(Context context){
        mContext = context;
        mApplication = ((MainApplication)((Activity) context).getApplication());
        mResponseService = mApplication.getRetrofit().create(GetResponse.class);
        mRealm = Realm.getDefaultInstance();
    }

    public void getResponseData(){
        // If no intenet show error message to user
        if(!mApplication.isNetworkAvailable()){
            ((MainActivity) mContext).showFeed(mContext.getResources().getString(R.string.no_internet));
            return;
        }

        Call<ResponseData> getData = mResponseService.getResponse();
        getData.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.code() ==200) {
                    ResponseData responseData = response.body();
                    responseData.set_id("0");
                    mRealm.beginTransaction();
                    ResponseData realmResponseData = mRealm.copyToRealmOrUpdate(responseData);
                    mRealm.commitTransaction();
                    ((MainActivity) mContext).setUpViewPager();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                ((MainActivity) mContext).showFeed(mContext.getResources().getString(R.string.server_error));
                Timber.d(t.getMessage());
            }
        });
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(mResponseService != null) {
            mContext = null;
            mApplication = null;
            mResponseService = null;
        }
    }
}
