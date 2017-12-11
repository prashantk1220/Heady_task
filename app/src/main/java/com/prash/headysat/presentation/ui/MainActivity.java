package com.prash.headysat.presentation.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.prash.headysat.R;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;
import com.prash.headysat.presentation.adapters.ViewPagerAdapter;
import com.prash.headysat.presentation.services.RetrofitService;

public class MainActivity extends AppCompatActivity {

    RetrofitService mRetrofitService;
    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    RealmORMAdapter mRealmORMAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        mRetrofitService = new RetrofitService(this);
        mRetrofitService.getResponseData();

        //setUpViewPager();

    }


    //Called from Retrofit Services once the data is persisted using IOC
    public void setUpViewPager(){
        mRealmORMAdapter = new RealmORMAdapter();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoryFragment(), "Shop by category");
        adapter.addFragment(new RankingFragment(), "Shop by ranking");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public RealmORMAdapter getRealmORMAdapter(){
        return mRealmORMAdapter;
    }

    public void showFeed(String msg){
        Snackbar.make(findViewById(R.id.tabLayout), msg, Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRetrofitService = null;
    }
}
