package com.fernandes.damien.barhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class ListItemActivity extends AppCompatActivity {
    private ArrayList<Conso> listItem = new ArrayList<>();
    private GridView listConso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        listItem.add(new Cafe());
        listItem.add(new Biere("SB"));
        listItem.add(new Soda("Coca"));

        listConso = (GridView) findViewById(R.id.gridview);

        listConso.setAdapter(new GridViewAdapter(getApplicationContext(), R.layout.activity_list_item, listItem));


    }
}
