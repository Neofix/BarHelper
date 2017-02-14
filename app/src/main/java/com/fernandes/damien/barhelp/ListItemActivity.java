package com.fernandes.damien.barhelp;

import android.content.res.Configuration;
import android.support.annotation.IntegerRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ListItemActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ArrayList<Conso> listItem = new ArrayList<>();
    private GridView listConso;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private HashMap<Conso, Integer> nbOccurence = new HashMap<>();
    private TextView text;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        listItem.add(new Cafe());
        listItem.add(new Biere());
        listItem.add(new BierePression());
        listItem.add(new Soda("Soda"));

        listConso = (GridView) findViewById(R.id.gridview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        listConso.setOnItemClickListener(this);


        for(Conso lesConso : listItem)
            nbOccurence.put(lesConso, 0);

        text = (TextView) findViewById(R.id.textView);
        text.setText("");
        listConso.setAdapter(new GridViewAdapter(getApplicationContext(), R.layout.activity_list_item, listItem));


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // synchroniser le drawerToggle après la restauration via onRestoreInstanceState
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Double somme = 0.0;
        Conso ItemClicked = listItem.get(position);
        String Texty ="";


        Conso nameClass = ItemClicked;
        if(nbOccurence.containsKey(nameClass))
            nbOccurence.put(nameClass, nbOccurence.get(nameClass) + 1);


        for(Map.Entry<Conso, Integer> entry : nbOccurence.entrySet())
        {
            Integer nombre = entry.getValue();
            Conso objet = entry.getKey();
            if(nombre>0) {
                Texty = Texty + nombre + " " + objet.getName() + "\n";
                somme = somme + objet.getPrix() * nombre;
            }
        }
        Texty=Texty+"Somme Total : "+String.format("%.2f", somme)+ " €";

        text.setText(Texty);

        Toast.makeText(getApplicationContext(), "1 "+ listItem.get(position).getName()+" ajouté au panier",Toast.LENGTH_SHORT).show();

    }

    private void afficherCacherToolbar() {
        if(toolbar.getAlpha() == 1){ //si alpha==1 alors elle est affichee

            //cacher
            toolbar.animate()
                    .alpha(0) //la rendre invisible
                    .translationY(-toolbar.getHeight()) //la déplacer vers le haut
                    .start();
        }
        else{ //si alpha==0 alors elle est cachee

            //afficher
            toolbar.animate()
                    .alpha(1) //la rendre visible
                    .translationY(0) //retour à la position d'origine
                    .start();
        }
    }
}
