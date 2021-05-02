package com.example.mensas.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mensas.Adapters.MensaRecyclerViewAdapter;
import com.example.mensas.R;
import com.example.mensas.database.Mensa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MensaFragment extends Fragment implements MensaRecyclerViewAdapter.OnMensaListener {

    private static final String TAG = "MensaFragment";

    private RecyclerView mRecyclerview;
    private List<Mensa> mensen;

    Context mContext;

    // Button fav
//    ToggleButton fav_button;

    Dialog myDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }

    public MensaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mensa, container, false);
        mRecyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        Activity act = this.getActivity();
        MensaRecyclerViewAdapter mAdapter = new MensaRecyclerViewAdapter(mContext, mensen, this , act);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mAdapter);
        return rootView;
    }

    private void initDataset() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPref_Mensen", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task_list", null);
        Type type = new TypeToken<ArrayList<Mensa>>() {
        }.getType();
        mensen = gson.fromJson(json, type);

        if (mensen == null) {
            mensen = new ArrayList<>();


            String line = "";
            try {
                InputStream is = getResources().openRawResource(R.raw.mensen);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

                reader.readLine();
                mensen = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    // Split by ','
                    String[] tokens = line.split(",");

                    // read the data
                    Mensa mensa = new Mensa();
                    mensa.setMid(Integer.parseInt(tokens[0]));
                    mensa.setmName(tokens[1]);
                    mensa.setmAddr(tokens[2]);
                    mensa.setmPlz(tokens[3]);
                    mensa.setmLat(tokens[4]);
                    mensa.setmLon(tokens[5]);
                    mensa.setIsFavorite(false);
                    mensen.add(mensa);

                    Log.d("MensaFragment", "Just Created: " + mensa);

                }
            } catch (IOException e) {
                Log.wtf("MensaFragment", "Error reading data file on line " + line);
                e.printStackTrace();
            }

            SharedPreferences sharedPref = this.getActivity().getSharedPreferences("sharedPref_Mensen", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            gson = new Gson();
            json = gson.toJson(mensen);
            editor.putString("task_list", json);
            editor.apply();

            Log.d("MensaFragment", "\n \n json: " + json);
        }
    }




    @Override
    public void onMensaClick(int position) {
        mensen.get(position);

        // test Dialog
        Dialog myDialog;
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.text_row_item_meal);
        // Transparent Background
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        TextView dialog_name_meal = (TextView) myDialog.findViewById(R.id.mensa_meal);
//        TextView dialog_name_allergen = (TextView) myDialog.findViewById(R.id.me);


        String openTime = "08:00";
        String closeTime = "14:30";
        // to fix
//        dialog_name_meal.setText("Öffnungszeiten: " + openTime + " - " + closeTime);
//        dialog_name_allergen.setText(mensen.get(position).mAddr);

        myDialog.show();

        Log.d(TAG, "onMensaClick: " + String.valueOf(mensen.get(position)));
    }
}
