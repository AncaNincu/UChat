package com.koddev.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.koddev.chatapp.Adapter.PickUpListAdapter;

public class PickupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pick-up lines");
    }

    public void setListView() {
        String[] pickups= {
                "Well, here I am. What are your other two wishes?",
                "Hey, my name's Microsft. Can I crash at your place tonight?",
                "Are you French? Because Eiffel for you.",
                "There is something wrong with my cell phone. It doesn't have your number in it.",
                "If I could rearrange the alphabet, I’d put ‘U’ and ‘I’ together."
        };
        PickUpListAdapter adapter = new PickUpListAdapter(PickupActivity.this, pickups);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }
}