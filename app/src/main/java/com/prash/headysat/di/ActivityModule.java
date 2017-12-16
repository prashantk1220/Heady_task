package com.prash.headysat.di;

import android.content.Context;

import com.prash.headysat.MainApplication;
import com.prash.headysat.domain.network.GetResponse;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;
import com.prash.headysat.presentation.services.RetroService;
import com.prash.headysat.presentation.ui.activity.MainActivity;
import com.prash.headysat.presentation.ui.activity.MainPresenter;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prash on 16/12/17.
 */
@Module
public class ActivityModule {

    private static final String BASE_URL = "https://stark-spire-93433.herokuapp.com" ;

    private MainActivity mainActivity;

    private  Context context;

    public ActivityModule(MainActivity mainActivty){
        this.mainActivity = mainActivty;
        this.context = mainActivty;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    MainActivity provideActivity() { return mainActivity; }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    GetResponse responseService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetResponse service = retrofit.create(GetResponse.class);
        return  service;
    }

    @Provides
    RetroService retroService(){
        return new RetroService(mainActivity.getComponent());
    }

    @Provides
    MainPresenter mainPresenter() {
        return new MainPresenter(mainActivity.getComponent());
    }



}
