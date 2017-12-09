package com.prash.headysat.domain.model;

/**
 * Created by prash on 09/12/17.
 */

public class Response {
    private Rankings[] rankings;

    private String _id;

    private Categories[] categories;

    public Rankings[] getRankings ()
    {
        return rankings;
    }

    public void setRankings (Rankings[] rankings)
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

    public Categories[] getCategories ()
    {
        return categories;
    }

    public void setCategories (Categories[] categories)
    {
        this.categories = categories;
    }
}
