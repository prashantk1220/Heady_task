package com.prash.headysat;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prash.headysat.domain.model.ResponseData;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm realm = Realm.getDefaultInstance();
    }


    // called from the RetrofitService class using IOC
    public void setResponseDataToDb(ResponseData data){

    }

    public void showFeed(String msg){
        Snackbar.make(findViewById(R.id.title), msg, Snackbar.LENGTH_LONG).show();
    }
}
