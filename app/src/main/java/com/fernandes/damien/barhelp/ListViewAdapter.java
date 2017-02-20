package com.fernandes.damien.barhelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fernandd on 14/02/2017.
 */
public class ListViewAdapter extends ArrayAdapter<Conso> {
    private Context context;
    private int resource;
    private List<Conso> dataKey;
    private List<Integer> dataValue;
    private HashMap<Conso, Integer> res = new HashMap<>();

    public ListViewAdapter(Context context, int resource, ArrayList<Conso> objects, ArrayList<Integer> objectsValue) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.dataKey=objects;
        this.dataValue= objectsValue;
        for (int i = dataKey.size()-1; i >= 0; i--) {
            if(dataValue.get(i)==0) {
                dataKey.remove(i);
                dataValue.remove(i);
            }
        }

    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_one_item, parent, false);

        ImageView img = (ImageView) rowView.findViewById(R.id.imageViewListDraw);
        TextView name = (TextView) rowView.findViewById(R.id.textViewListDraw);
        TextView somme = (TextView) rowView.findViewById(R.id.textViewSomme);


        img.setImageResource(dataKey.get(position).getImgPath());
        name.setText(dataValue.get(position) + " " + dataKey.get(position).getName());

        rowView.setVisibility((dataValue.get(position) == 0) ? View.GONE : View.VISIBLE);

        return rowView;
        //return super.getView(position, convertView, parent);
    }
}
