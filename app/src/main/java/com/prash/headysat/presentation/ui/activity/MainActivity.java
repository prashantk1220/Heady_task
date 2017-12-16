package com.prash.headysat.presentation.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.prash.headysat.MainApplication;
import com.prash.headysat.R;
import com.prash.headysat.di.ActivityComponent;
import com.prash.headysat.di.ActivityModule;


import com.prash.headysat.di.DaggerActivityComponent;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;
import com.prash.headysat.presentation.adapters.ViewPagerAdapter;
import com.prash.headysat.presentation.services.RetroService;
import com.prash.headysat.presentation.ui.CategoryFragment;
import com.prash.headysat.presentation.ui.ProductsFragment;
import com.prash.headysat.presentation.ui.RankingFragment;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;

public class MainActivity extends AppCompatActivity implements MainView{

    ActivityComponent activityComponent;

    @BindView(R.id.tabLayout) TabLayout mTabLayout;
    @BindView(R.id.viewPager) ViewPager mViewPager;

    @Inject
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();

        activityComponent.injectMain(MainActivity.this);

        ButterKnife.bind(this);

        presenter.setView(this);


    }

    public ActivityComponent getComponent() {
        return activityComponent;
    }


    @Override
    public void setUpViewPager(){

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductsFragment(), "Shop by Products");
        adapter.addFragment(new CategoryFragment(), "Shop by category");
        adapter.addFragment(new RankingFragment(), "Shop by ranking");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showFeed(String msg){
        Snackbar.make(findViewById(R.id.tabLayout), msg, Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
