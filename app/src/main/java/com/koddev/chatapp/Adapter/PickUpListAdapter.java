package com.koddev.chatapp.Adapter;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.koddev.chatapp.R;


public class PickUpListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] pickup;

    public PickUpListAdapter(Activity context, String[] pickup) {
        super(context, R.layout.list_pickupline, pickup);

        this.context = context;
        this.pickup = pickup;
    }

    @NonNull
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_pickupline, null,true);

        TextView textDate = (TextView) rowView.findViewById(R.id.filmdates);

        textDate.setText(pickup[position]);

        return rowView;
    };
}

