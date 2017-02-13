package com.fernandes.damien.barhelp;

/**
 * Created by damien on 12/02/17.
 */

public class Biere extends Conso {
    public Biere() {
        this("");
    }

    public Biere(String name) {
        super(1.50);
        this.name=name;
        this.imgPath = R.drawable.biere;
    }
}
