package com.example.aaronaftab.walkable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.places.ui.*;
import com.google.android.gms.common.*;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.*;
import android.util.Log;
import com.google.android.gms.common.api.*;
import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    //objects to use in the methods below
    EditText className1;
    EditText className2;
    TextView locationStorage;
    TextView locationStorage2;
    String cName1;
    String cName2;
    String loc1;
    String loc2;
    PlaceAutocompleteFragment autocompleteFragment;
    PlaceAutocompleteFragment autocompleteFragment2;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GeoDataClient mGeoDataClient;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mGeoDataClient = Places.getGeoDataClient(this, null);

        final String TAG = "Main2Activity";
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment2 = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);
        locationStorage = findViewById(R.id.locStorage1);
        locationStorage2 = findViewById(R.id.locStorage2);
        //set hints
        autocompleteFragment.setHint("Enter Location");
        autocompleteFragment2.setHint("Enter Location");
        //set place selection listener
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                locationStorage.setText(place.getAddress());
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
                locationStorage2.setText(place.getAddress());
                //Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


        Button button = findViewById(R.id.submitButton);
        className1 = findViewById(R.id.className1);
        className2 = findViewById(R.id.className2);
        button.setOnClickListener(new RelativeLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent backToMain = new Intent(Main2Activity.this, MainActivity.class);
                String[] args = new String[5];
                cName1 = className1.getText().toString();
                cName2 = className2.getText().toString();
                loc1 = locationStorage.getText().toString();
                loc2 = locationStorage2.getText().toString();
                args[0] = cName1;
                args[1] = cName2;
                args[2] = loc1;
                args[3] = loc2;
                args[4] = getTime();
                passInfo(Main2Activity.this, args);
//                if (cName1.length() != 0) {
//                    backToMain.putExtra("class1", cName1);
//                }
//                if (cName2.length() != 0) {
//                    backToMain.putExtra("class2", cName2);
//                }
//                if (loc1.length() != 0) {
//                    backToMain.putExtra("location1", loc1);
//                }
//                if (loc2.length() != 0) {
//                    backToMain.putExtra("location2", loc2);
//                }
//                //store time in an extra in backToMain.
//                String time = getTime();
//                backToMain.putExtra("time", time);
                startActivity(backToMain);
            }
        });

    }


    public static void passInfo(Context context, String[] passingArgs) {
        SharedPreferences prefs = context.getSharedPreferences("ClassLoc", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("class1", passingArgs[0]);
        editor.putString("class2", passingArgs[1]);
        editor.putString("location1", passingArgs[2]);
        editor.putString("location2", passingArgs[3]);
        editor.putString("time", passingArgs[4]);
        editor.apply();
    }


    public String getTime() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String TAG = "Main2Activity";
        String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="
                + autocompleteFragment.getText(R.id.place_autocomplete_fragment).toString()
                + "&destinations=" + autocompleteFragment2.getText(R.id.place_autocomplete_fragment2).toString()
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
                            JSONObject response1 = (JSONObject) response.get("duration");
                            time = response1.getString("text");
                        } catch (JSONException e) {
                            System.out.println("JSON issue");
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
