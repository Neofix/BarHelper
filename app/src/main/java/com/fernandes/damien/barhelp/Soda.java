package com.fernandes.damien.barhelp;

/**
 * Created by damien on 12/02/17.
 */

public class Soda extends Conso {
    public Soda() {
        this("");
    }

    public Soda(String name) {
        super(1.50);
        this.name=name;
        this.imgPath = "Biere.png";
    }
}
