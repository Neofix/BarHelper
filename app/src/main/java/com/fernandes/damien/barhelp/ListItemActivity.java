package com.fernandes.damien.barhelp;

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

public class ListItemActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ArrayList<Conso> listItem = new ArrayList<>();
    private GridView listConso;
    private ArrayList<Conso> panier = new ArrayList<>();
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

        text = (TextView) findViewById(R.id.textView);
        text.setText("");
        listConso.setAdapter(new GridViewAdapter(getApplicationContext(), R.layout.activity_list_item, listItem));


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Double somme = 0.0;
        panier.add(listItem.get(position));
        Toast.makeText(getApplicationContext(), "1 "+ listItem.get(position).getName()+" ajouté au panier", Toast.LENGTH_SHORT).show();
        //text.setText(text.getText()+listItem.get(position).getName()+"\n");*
        for(Conso uneConso:panier)
            somme=somme+uneConso.getPrix();
        DecimalFormat df = new DecimalFormat("#.####");
        text.setText(String.format("%.2f", somme)+ " €");
    }
}
