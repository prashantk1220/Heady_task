package com.prash.headysat.domain.model;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prash on 09/12/17.
 */

public class Products extends RealmObject {
    @PrimaryKey
    private String id;

    private Tax tax;

    private String date_added;

    private String name;

    private RealmList<Variants> variants = new RealmList<>();

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

    public RealmList<Variants> getVariants ()
    {
        return variants;
    }

    public void setVariants (RealmList<Variants> variants)
    {
        this.variants = variants;
    }
}
