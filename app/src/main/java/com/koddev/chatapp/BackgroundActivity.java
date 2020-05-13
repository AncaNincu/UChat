package com.koddev.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.koddev.chatapp.Adapter.CustomListAdapter;

public class BackgroundActivity extends AppCompatActivity {

    private FirebaseUser fuser;
    private DatabaseReference  reference;
    private String[] userBackgrounds;
    private String[] backgroundTitle;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change background");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BackgroundActivity.this, MainActivity.class));
            }
        });
        getBackgroudList();

    }
    protected void getBackgroudList()
    {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid()).child("background");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i;
                i=0;
                userBackgrounds = new String[(int)dataSnapshot.getChildrenCount()];
                backgroundTitle = new String[(int)dataSnapshot.getChildrenCount()];
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String back = snapshot.getValue().toString();
                    userBackgrounds[i++] = back;
                }
                setListView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            //
                //
            }
        });
    }
    public void setListView()
    {
        CustomListAdapter adapter = new CustomListAdapter(this, userBackgrounds, backgroundTitle);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid()).child("backgroundImageURL");
                reference.setValue(userBackgrounds[+position]);
                String SelectedItem = "Background was changed!";
                Toast.makeText(getApplicationContext(), SelectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
