package com.prash.headysat.presentation.adapters;

import com.prash.headysat.domain.model.Categories;
import com.prash.headysat.domain.model.Products;
import com.prash.headysat.domain.model.RankingProducts;
import com.prash.headysat.domain.model.Rankings;
import com.prash.headysat.domain.model.ResponseData;
import com.prash.headysat.domain.model.Variants;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by prash on 10/12/17.
 */

public class RealmORMAdapter {


    Realm mRealm;
    ResponseData mAllValues;

    public RealmORMAdapter(){
        mRealm = Realm.getDefaultInstance();
        mAllValues = mRealm.where(ResponseData.class).equalTo("_id", "0").findFirst();
    }

    public RealmList<Categories> getCategories(){
        return mRealm.where(ResponseData.class).findFirst().getCategories();
    }

    public RealmList<Rankings> getRankings(){
        return mRealm.where(ResponseData.class).findFirst().getRankings();
    }

    public RealmList<Products> getRankingsCategory(int rankNo){
        RealmQuery<Rankings> query = mRealm.where(Rankings.class);
        RealmResults<Rankings> results = query.findAll();
        return getProductsbyRanking(results.get(rankNo).getProducts());
    }

    public RealmList<Products> getProductsbyRanking(RealmList<RankingProducts> rankProducts){
        RealmList<Products> productsList = new RealmList<>();

        for (RankingProducts prod : rankProducts) {
            Products product = mRealm.where(Products.class).equalTo("id", prod.getId()).findFirst();
            productsList.add(product);
        }

        return productsList;
    }

    public RealmList<Products> getProductsbyRanking(RealmResults<RankingProducts> rankProducts){
        RealmList<Products> productsList = new RealmList<>();

        for (RankingProducts prod : rankProducts) {
            Products product = mRealm.where(Products.class).equalTo("id", prod.getId()).findFirst();
            productsList.add(product);
        }

        return productsList;
    }


    public RealmList<Variants> getVariants(String prodID){
        Products product = mRealm.where(Products.class).equalTo("id", prodID).findFirst();
        return product.getVariants();
    }

    public RealmList<Products> sortRankings(int filter, boolean decend){
        String filterName;
        switch (filter){
            case 0: filterName = "orders";
                  break;
            case 1:  filterName = "views";
                break;
            case 2:  filterName = "shares";
                break;
            default: filterName = "orders";
        }
        RealmQuery<RankingProducts> query = mRealm.where(RankingProducts.class);
        RealmResults<RankingProducts> result;
        if(decend){
            result = query.sort(filterName, Sort.DESCENDING).findAll();
        }
        else{
            result = query.sort(filterName).findAll();
        }
        return getProductsbyRanking(result);
    }




}