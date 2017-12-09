package com.prash.headysat.domain.model;

/**
 * Created by prash on 09/12/17.
 */

public class Products {
    private String id;

    private Tax tax;

    private String date_added;

    private String name;

    private Variants[] variants;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Tax getTax ()
    {
        return tax;
    }

    public void setTax (Tax tax)
    {
        this.tax = tax;
    }

    public String getDate_added ()
    {
        return date_added;
    }

    public void setDate_added (String date_added)
    {
        this.date_added = date_added;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Variants[] getVariants ()
    {
        return variants;
    }

    public void setVariants (Variants[] variants)
    {
        this.variants = variants;
    }
}
