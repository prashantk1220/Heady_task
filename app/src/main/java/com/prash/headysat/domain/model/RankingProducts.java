package com.prash.headysat.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prash on 10/12/17.
 */

public class RankingProducts extends RealmObject{

    @PrimaryKey
    private String id;

    @SerializedName("view_count")
    @Expose
    private long views;

    @SerializedName("order_count")
    @Expose
    private long orders;

    private long shares;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getOrders() {
        return orders;
    }

    public void setOrders(long orders) {
        this.orders = orders;
    }

    public long getShares() {
        return shares;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }
}
