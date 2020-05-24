package com.koddev.chatapp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.koddev.chatapp.LoginActivity;
import com.koddev.chatapp.R;
import com.koddev.chatapp.ResetPasswordActivity;

import java.util.Calendar;
import java.util.HashMap;

import pl.droidsonroids.gif.*;

public class GiftFragment extends Fragment {
    private FirebaseUser fuser;
    private DatabaseReference reference1,reference2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_gift, container, false);
        final GifImageView gif1 =(GifImageView) view.findViewById(R.id.gif1);
        final GifImageView gif2 =(GifImageView) view.findViewById(R.id.gif2);
        final TextView text1 =(TextView) view.findViewById(R.id.text1);
        final TextView text2 =(TextView) view.findViewById(R.id.text2);

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        Calendar calendar= Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month= calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final String today = year+ "" + month + "" + day;

        final SharedPreferences preferences = getContext().getSharedPreferences("PREFS",0);
        final boolean currentDay = preferences.getBoolean(today,false);


        gif1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gif1.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE);
                gif2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // Hide your View after 3 seconds
                        reference1 = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid()).child("dailyGift");
                        reference1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String res= (String) dataSnapshot.getValue();
                                if(!today.equals(res))
                                {
                                    reference2 = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid()).child("background");
                                    //HashMap<String, String> hashMap = new HashMap<>();
                                    //hashMap.put("daily1","https://firebasestorage.googleapis.com/v0/b/uchat-750ef.appspot.com/o/uploads%2Fbackground%2Fbackground_hearts.png?alt=media&token=103b7191-7964-4c77-ae50-b6ecd139801f");
                                    //reference2.setValue(hashMap);
                                    reference2.push().setValue("https://firebasestorage.googleapis.com/v0/b/uchat-750ef.appspot.com/o/uploads%2Fbackground%2Fbackground_hearts.png?alt=media&token=103b7191-7964-4c77-ae50-b6ecd139801f");
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean(today,true);
                                    editor.apply();
                                }
                                else {
                                    text2.setText("You received your new background!");
                                }
                                text2.setVisibility(View.VISIBLE);
                                reference1.setValue(today);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });

                    }
                }, 3500);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
