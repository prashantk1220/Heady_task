package com.prash.headysat.domain.model;

/**
 * Created by prash on 09/12/17.
 */

public class Categories {
    private String id;

    private String name;

    private String[] child_categories;

    private Products[] products;

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

    public String[] getChild_categories ()
    {
        return child_categories;
    }

    public void setChild_categories (String[] child_categories)
    {
        this.child_categories = child_categories;
    }

    public Products[] getProducts ()
    {
        return products;
    }

    public void setProducts (Products[] products)
    {
        this.products = products;
    }
}
