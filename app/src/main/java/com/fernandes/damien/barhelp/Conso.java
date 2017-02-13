package com.fernandes.damien.barhelp;

/**
 * Created by damien on 12/02/17.
 */

public abstract class Conso {
    protected String name;
    protected final double prix;
    protected String desc;
    protected String imgPath;



    public Conso(double prix) {
        this.prix = prix;
    }

    public String getDesc() {
        return desc;
    }

    public String getImgPath() { return imgPath; }

    public String getName() {
        return name;
    }

    public double getPrix() {
        return prix;
    }
}
