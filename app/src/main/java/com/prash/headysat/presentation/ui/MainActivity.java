package com.prash.headysat.presentation.ui;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.prash.headysat.R;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;
import com.prash.headysat.presentation.adapters.ViewPagerAdapter;
import com.prash.headysat.presentation.services.RetrofitService;
import com.prash.headysat.domain.model.Products;
import com.prash.headysat.domain.model.ResponseData;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    RetrofitService mRetrofitService;
    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;


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
        setUpViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }



    public void setUpViewPager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoryFragment(), "Shop by category");
        adapter.addFragment(new RankingFragment(), "Shop by ranking");
        mViewPager.setAdapter(adapter);
    }


    // called from the RetrofitService class using IOC
    public void setResponseDataToDb(Realm realm){
    ResponseData allValues = realm.where(ResponseData.class).equalTo("_id", "0").findFirst();
        String prodId = allValues.getRankings().get(0).getProducts().get(1).getId();
        Products product = realm.where(Products.class).equalTo("id", prodId).findFirst();
        //textView.setText(product.getName());

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
