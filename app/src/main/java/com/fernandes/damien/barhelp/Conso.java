package com.fernandes.damien.barhelp;

/**
 * Created by damien on 12/02/17.
 */

public abstract class Conso {
    protected String name;
    protected double prix;
    protected String desc;

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public double getPrix() {
        return prix;
    }
}
