package com.fernandes.damien.barhelp;

/**
 * Created by damien on 13/02/17.
 */

public class BierePression extends Conso {
    public BierePression() {
        this("");
    }

    public BierePression(String name) {
        super(1.50);
        this.name="Bière Pression";
        this.imgPath = R.drawable.bierepression;
    }

}
