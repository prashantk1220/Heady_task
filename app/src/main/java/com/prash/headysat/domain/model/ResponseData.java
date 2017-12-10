package com.prash.headysat.domain.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by prash on 09/12/17.
 */

public class ResponseData extends RealmObject{

    private RealmList<Rankings> rankings = new RealmList<>();

    @PrimaryKey
    private String _id;


    private RealmList<Categories> categories = new RealmList<>();

    public RealmList<Rankings> getRankings ()
    {
        return rankings;
    }

    public void setRankings (RealmList<Rankings> rankings)
    {
        this.rankings = rankings;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public RealmList<Categories> getCategories ()
    {
        return categories;
    }

    public void setCategories (RealmList<Categories> categories)
    {
        this.categories = categories;
    }
}
