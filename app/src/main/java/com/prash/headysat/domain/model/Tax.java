package com.prash.headysat.domain.model;

/**
 * Created by prash on 09/12/17.
 */

public class Tax {
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
