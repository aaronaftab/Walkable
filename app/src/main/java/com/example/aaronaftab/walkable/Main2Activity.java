package com.example.aaronaftab.walkable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.places.ui.*;
import com.google.android.gms.common.*;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.*;
import android.util.Log;
import com.google.android.gms.common.api.*;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    //objects to use in the methods below
    final PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
            getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
    final PlaceAutocompleteFragment autocompleteFragment2 = (PlaceAutocompleteFragment)
            getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);
    final Intent backToMain = new Intent(this, MainActivity.class);
    EditText className1 = findViewById(R.id.className1);
    EditText className2 = findViewById(R.id.className2);
    final String cName1 = className1.getText().toString();
    final String cName2 = className2.getText().toString();
    final String loc1 = autocompleteFragment.getText(R.id.place_autocomplete_fragment).toString();
    final String loc2 = autocompleteFragment2.getText(R.id.place_autocomplete_fragment2).toString();
    final String TAG = "Main2Activity";
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GeoDataClient mGeoDataClient;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mGeoDataClient = Places.getGeoDataClient(this, null);


        //set hints
        autocompleteFragment.setHint("Enter Location");
        autocompleteFragment2.setHint("Enter Location");
        //set place selection listener
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                autocompleteFragment.setText(place.getAddress());
                //Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                autocompleteFragment2.setText(place.getAddress());
                //Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new RelativeLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cName1.length() != 0) {
                    backToMain.putExtra("class1", cName1);
                }
                if (cName2.length() != 0) {
                    backToMain.putExtra("class2", cName2);
                }
                if (loc1.length() != 0) {
                    backToMain.putExtra("location1", loc1);
                }
                if (loc2.length() != 0) {
                    backToMain.putExtra("location2", loc2);
                }
                //store time in an extra in backToMain.
                Log.i("check", "got message");
                String time = getTime();
                Log.i("check", "JSON request worked.");
                backToMain.putExtra("time", time);
                openMainScreen(backToMain);
            }
        });

    }

    public void openMainScreen(Intent callBack) {
        startActivity(callBack);
    }

    public String getLocation(PlaceAutocompleteFragment search, int id) {
        return search.getText(id).toString();
    }

    public void saveTime(String input) {
        time = input;
    }

    public String getTime() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="
                + getLocation(autocompleteFragment, R.id.place_autocomplete_fragment)
                + "&destinations=" + getLocation(autocompleteFragment2, R.id.place_autocomplete_fragment2)
                + "&key=" + getString(R.string.api_key) + "&mode=walking";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            Log.d(TAG, response.toString());
                            String t = response.getString("time");
                            saveTime(t);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w(TAG, error.toString());
            }
        });
        queue.add(jsonObjectRequest);
        return time;
    }
}
