package com.prash.headysat.presentation.ui.activity;

import android.support.annotation.NonNull;

import com.prash.headysat.di.ActivityComponent;
import com.prash.headysat.presentation.services.RetroService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by prash on 13/12/17.
 */

public class MainPresenter {

    ActivityComponent activityComponent;
    private MainView view;

    @Inject
    RetroService retroService;

    public MainPresenter(@NonNull ActivityComponent appComponent) {
        activityComponent = appComponent;
        activityComponent.injectPresenter(this);
    }

    public void setView(@NonNull MainView view) {
        this.view = view;
        retroService.getResponseData();
    }
}
