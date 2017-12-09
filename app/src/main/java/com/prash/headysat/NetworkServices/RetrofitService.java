package com.prash.headysat.NetworkServices;

import android.app.Activity;
import android.content.Context;

import com.prash.headysat.MainActivity;
import com.prash.headysat.MainApplication;
import com.prash.headysat.R;
import com.prash.headysat.domain.model.ResponseData;
import com.prash.headysat.domain.network.GetResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prash on 09/12/17.
 */

public class RetrofitService {

    GetResponse mResponseService;
    Context mContext;
    MainApplication mApplication;

    public RetrofitService(Context context){
        mContext = context;
        mApplication = ((MainApplication)((Activity) context).getApplication());
        mResponseService = mApplication.getRetrofit().create(GetResponse.class);
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
                    ((MainActivity) mContext).setResponseDataToDb(responseData);
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                ((MainActivity) mContext).showFeed(mContext.getResources().getString(R.string.server_error));
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
