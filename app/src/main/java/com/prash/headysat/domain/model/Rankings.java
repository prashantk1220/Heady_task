package com.prash.headysat.domain.model;

/**
 * Created by prash on 09/12/17.
 */

public class Rankings {
    private Products[] products;

    private String ranking;

    public Products[] getProducts ()
    {
        return products;
    }

    public void setProducts (Products[] products)
    {
        this.products = products;
    }

    public String getRanking ()
    {
        return ranking;
    }

    public void setRanking (String ranking)
    {
        this.ranking = ranking;
    }

}
