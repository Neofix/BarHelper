package com.fernandes.damien.barhelp;

import java.util.Comparator;

/**
 * Created by fernandd on 14/02/2017.
 */
public class ConsoSort  implements Comparator<Conso>{
    @Override
    public int compare(Conso o1, Conso o2) {
        String f = o1.getClass().getSimpleName();
        String s = o2.getClass().getSimpleName();

        int result= f.compareTo(s);
        return result;
    }
}
