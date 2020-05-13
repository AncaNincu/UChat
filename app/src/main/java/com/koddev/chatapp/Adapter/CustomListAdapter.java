package com.koddev.chatapp.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.koddev.chatapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] backgroundList;

    public CustomListAdapter(Activity context, String[] backgroundList, String[] text) {
        super(context, R.layout.list_backgroundactivity, text);

        this.context = context;
        this.backgroundList = backgroundList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_backgroundactivity, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.backgroundList);

        imageView.setImageDrawable(getImageDrawableRes(backgroundList[position]));

        return rowView;
    };
    protected Drawable getImageDrawableRes(String drawableRes)
    {
        Integer res;
        Drawable d;
        Bitmap bitmap = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(drawableRes);
            bitmap =  BitmapFactory.decodeStream((InputStream)url.getContent());
        } catch (IOException e) {
            //Log.e(TAG, e.getMessage());
        }
        d = new BitmapDrawable(context.getResources(), bitmap);
        return d;
    }
}