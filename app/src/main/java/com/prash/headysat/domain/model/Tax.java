package com.prash.headysat.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prash on 09/12/17.
 */

public class Tax extends RealmObject {

    @PrimaryKey
    private String name;

    private String value;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }
}
