package com.fernandes.damien.barhelp;

/**
 * Created by damien on 12/02/17.
 */

public abstract class Conso {
    protected String name;
    protected final double prix;
    protected String desc;

    public Conso(double prix) {
        this.prix = prix;
    }

    protected String imgPath;

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
