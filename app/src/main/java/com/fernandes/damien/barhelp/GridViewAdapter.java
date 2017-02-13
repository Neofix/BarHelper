package com.fernandes.damien.barhelp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 12/02/17.
 */

public class GridViewAdapter extends ArrayAdapter<Conso> {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context=context;
        this.data=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.grid_one_item, parent, false);

        ImageView img = (ImageView) rowView.findViewById(R.id.imageViewItem);
        TextView name = (TextView) rowView.findViewById(R.id.textViewName);

        img.setImageResource(getItem(position).getImgPath());
        name.setText(getItem(position).getName());

        return rowView;
       // return super.getView(position, convertView, parent);
    }
}
