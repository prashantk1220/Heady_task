package com.prash.headysat.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prash on 09/12/17.
 */

public class Categories extends RealmObject{
    @PrimaryKey
    private String id;

    private String name;


    private RealmList<String> child_categories  = new RealmList<>();

    private RealmList<Products> products = new RealmList<>();

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public RealmList<String> getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(RealmList<String> child_categories) {
        this.child_categories = child_categories;
    }

    public RealmList<Products> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Products> products) {
        this.products = products;
    }
}
