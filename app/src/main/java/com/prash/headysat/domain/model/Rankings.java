package com.prash.headysat.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prash on 09/12/17.
 */

public class Rankings extends RealmObject{

    private RealmList<RankingProducts> products = new RealmList<>();

    @PrimaryKey
    @SerializedName("ranking")
    @Expose
    private String rankingname;

    public RealmList<RankingProducts> getProducts ()
    {
        return products;
    }

    public void setProducts (RealmList<RankingProducts> products)
    {
        this.products = products;
    }

    public String getRankingname()
    {
        return rankingname;
    }

    public void setRankingname(String rankingname)
    {
        this.rankingname = rankingname;
    }

}
