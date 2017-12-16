package com.prash.headysat.di;

import com.prash.headysat.MainApplication;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;
import com.prash.headysat.presentation.services.RetroService;
import com.prash.headysat.presentation.ui.activity.MainActivity;
import com.prash.headysat.presentation.ui.activity.MainPresenter;

import dagger.Component;

/**
 * Created by prash on 16/12/17.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void injectMain(MainActivity mainActivity);
    void injectPresenter(MainPresenter mainPresenter);
    void injectRetro(RetroService retroService);
}
