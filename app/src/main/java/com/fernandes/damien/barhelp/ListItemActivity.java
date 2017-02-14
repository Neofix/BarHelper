package com.fernandes.damien.barhelp;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private HashMap<Conso, Integer> nbOccurence = new HashMap<>();
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        listItem.add(new Cafe());
        listItem.add(new Biere());
        listItem.add(new BierePression());
        listItem.add(new Soda("Soda"));

        listConso = (GridView) findViewById(R.id.gridview);
        listConso.setOnItemClickListener(this);



        for(Conso lesConso : listItem)
            nbOccurence.put(lesConso, 0);

        text = (TextView) findViewById(R.id.textView);
        text.setText("");
        listConso.setAdapter(new GridViewAdapter(getApplicationContext(), R.layout.activity_list_item, listItem));


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
            Texty= Texty + nombre +" "+ objet.getName() + "\n";
            somme= somme + objet.getPrix() * nombre;
        }
        Texty=Texty+"Somme Total : "+String.format("%.2f", somme)+ " €";

        text.setText(Texty);

        Toast.makeText(getApplicationContext(), "1 "+ listItem.get(position).getName()+" ajouté au panier",Toast.LENGTH_SHORT).show();

    }
}
