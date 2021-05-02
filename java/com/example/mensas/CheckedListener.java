package com.example.mensas;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CompoundButton;

import com.example.mensas.Adapters.MensaRecyclerViewAdapter;
import com.example.mensas.database.Mensa;
import com.google.gson.Gson;

import java.util.List;

public class CheckedListener implements android.widget.CompoundButton.OnCheckedChangeListener {
    List<Mensa> mensen;
    List<Mensa> favoritenMensa;
    int position;
    MensaRecyclerViewAdapter rr;
    MensaRecyclerViewAdapter.MensaViewHolder holder;
    private Activity activity;

    private static final String TAG = "CheckedListener";

    public CheckedListener(MensaRecyclerViewAdapter.MensaViewHolder holder, List<Mensa> mensen, List<Mensa> favoritenMensa, int position, MensaRecyclerViewAdapter rr, Activity act) {
        this.mensen = mensen;
        this.favoritenMensa = favoritenMensa;
        this.position = position;
        this.rr = rr;
        this.holder = holder;
        this.activity=act;

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("sharedPref_Mensen", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Mensa testM1 = mensen.get(position);

        if (compoundButton.isChecked()) {
            favoritenMensa.add(mensen.get(position));
            testM1.setIsFavorite(true);
            mensen.remove(testM1);
            mensen.add(0, testM1);


        } else {
            testM1.setIsFavorite(false);
            mensen.remove(testM1);
            mensen.add( testM1);
        }
        rr.notifyDataSetChanged();
        String json = gson.toJson(mensen);
        editor.putString("task_list", json);
        editor.apply();



    }


}