package com.fernandes.damien.barhelp;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.preference.DialogPreference;
import android.support.annotation.IntegerRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ListItemActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener{
    private ArrayList<Conso> listItem = new ArrayList<>();
    private TextView textViewSomme;
    private GridView listConso;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private HashMap<Conso, Integer> nbOccurence = new HashMap<>();
    private ListView listRes;
    private TextView text;
    private ActionBarDrawerToggle drawerToggle;
    private Button buttonPaye;
    private ArrayList<Conso> consoList= new ArrayList<>();
    private ArrayList<Integer> nbList= new ArrayList<>();
    private Double somme = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        listItem.add(new Cafe());
        listItem.add(new Biere());
        listItem.add(new BierePression());
        listItem.add(new Soda("Soda"));

        listConso = (GridView) findViewById(R.id.gridview);
        listRes = (ListView) findViewById(R.id.listViewRes);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        buttonPaye = (Button) findViewById(R.id.buttonPaye);
        textViewSomme = (TextView) findViewById(R.id.textViewSomme);

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
        listConso.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Conso nameClass = listItem.get(position);
                final NumberPicker myNumberPicker = new NumberPicker(ListItemActivity.this);
                myNumberPicker.setMinValue(1);
                myNumberPicker.setMaxValue(50);
                NumberPicker.OnValueChangeListener myValChangedListener = new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                    }
                };
                myNumberPicker.setOnValueChangedListener(myValChangedListener);

                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemActivity.this).setView(myNumberPicker);
                builder.setTitle("Quantité désirée")
                        .setMessage("Veuillez choisir la quantité de "+nameClass.getName()+" à ajouter :")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addToHashMap(nameClass, myNumberPicker.getValue());
                                DisplayToast(nameClass, myNumberPicker.getValue());
                                RefreshData(nbOccurence);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });
        listRes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Conso nameClass = consoList.get(position);
                Integer nombre = nbList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemActivity.this);
                builder.setTitle("Tout supprimé ?")
                        .setMessage("Voulez-vous vraiment supprimer les "+nombre+" "+nameClass.getName()+" de la liste ?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                nbOccurence.put(nameClass, 0);
                                RefreshData(nbOccurence);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });
        buttonPaye.setOnClickListener(this);

        for(Conso lesConso : listItem)
            nbOccurence.put(lesConso, 0);
        RefreshData(nbOccurence);

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

            Conso nameClass = listItem.get(position);
            String Texty = "";
            Integer nombre = 1;
            addToHashMap(nameClass, nombre);

        /*for(Map.Entry<Conso, Integer> entry : nbOccurence.entrySet())
        {
            Integer nombre = entry.getValue();
            Conso objet = entry.getKey();
            consoList.add(objet);
            nbList.add(nombre);
            somme = somme + objet.getPrix() * nombre;
        }*/


            RefreshData(nbOccurence);


            textViewSomme.setText(String.format("%.2f", somme) + " €");
            DisplayToast(nameClass, nombre);

    }
    private void DisplayToast(Conso laConso, Integer leNombre)
    {
        Toast.makeText(getApplicationContext(), leNombre+" "+ laConso.getName()+" ajouté au panier",Toast.LENGTH_SHORT).show();

    }

    private void addToHashMap(Conso laConso, int quantite){
        if(nbOccurence.containsKey(laConso))
            nbOccurence.put(laConso, nbOccurence.get(laConso) + quantite);
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
    private void RefreshData(HashMap<Conso,Integer> data){
        somme=0.0;
        consoList = new ArrayList<>();
        nbList = new ArrayList<>();
        for(Map.Entry<Conso, Integer> entry : nbOccurence.entrySet())
        {
            Integer nombre = entry.getValue();
            Conso objet = entry.getKey();
            consoList.add(objet);
            nbList.add(nombre);
            somme = somme + objet.getPrix() * nombre;
        }
        textViewSomme.setText(String.format("%.2f", somme)+ " €");
        listRes.setAdapter(new ListViewAdapter(getApplicationContext(),R.layout.activity_list_item, consoList, nbList));
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonPaye)
        {
            for(Map.Entry<Conso, Integer> entry : nbOccurence.entrySet())
            {
                Conso objet = entry.getKey();
                nbOccurence.put(objet, 0);
            }
            RefreshData(nbOccurence);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        return false;
    }
}
