package com.prash.headysat.domain.network;

import com.prash.headysat.domain.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by prash on 09/12/17.
 */

public interface GetResponse {
    @GET("/")
    Call<ResponseData> getResponse();
}
