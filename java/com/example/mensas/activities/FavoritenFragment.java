package com.example.mensas.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mensas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoritenFragment extends Fragment {

    private static final String TAG = "FavoritenFragment";

    Button button;
    TextView textview;
    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favoriten, container, false);
        mQueue = Volley.newRequestQueue(getActivity());

        textview = (TextView) view.findViewById(R.id.meal_name);


        button = (Button) view.findViewById(R.id.button_favoriten);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clickeeeeedaaaaaa");
                connexionTest();
            }
        });


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void connexionTest() {

        jsonParse();
    }

    /* Check if connected to Network */

    public boolean isOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void jsonParse() {
        //Roles: "USER"
        final String username = "group-j";
        final String password = "9wEA4G^wp9";

//        String url = "http://htw-mensa-app.de/mensa/";
        String url = "http://htw-mensa-app.de/mensa/meals?id=320&date=2018-06-22";
//        String url = "https://api.myjson.com/bins/1af1t7";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("Meal Groups");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject meal_groups = jsonArray.getJSONObject(i);

                                String meal_type = meal_groups.getString("Name");

                                JSONArray meals = meal_groups.getJSONArray("Meals");

//                                String meal_name = meals.get;
//                                meals.length()
                                for (int j = 0; j < meals.length(); j++) {
                                    JSONObject meal_names_allergens = meals.getJSONObject(j);

                                    String meal_name = meal_names_allergens.getString("Name");

                                    JSONArray allergens = meal_names_allergens.getJSONArray("Allergens");
                                    List<String> allergien = new ArrayList<String>();
                                    for (int k = 0; k < allergens.length(); k++) {
                                        String allergie = allergens.getString(k);
                                        allergien.add(allergie);
                                    }
                                    Log.d(TAG, "===========================================");

                                    Log.d(TAG, "meal_groups at index: " + i + " : " + meal_groups);

                                    Log.d(TAG, "meal_type: " + meal_type);

                                    Log.d(TAG, "JSONARRAY with the meals" + meals);

                                    Log.d(TAG, "meal_name: " + meal_name);

                                    Log.d(TAG, "Allergien: " + allergien.toString());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                Log.d(TAG, "onErrorResponse: ERROR jsonParse");
            }
        }) {
            /** Passing some request headers* */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                String creds = String.format("%s:%s", username, password);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                headers.put("Authorization", auth);
                return headers;
            }
        };
        mQueue.add(request);
    }
}

