package com.example.aaronaftab.walkable;

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

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GeoDataClient mGeoDataClient;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mGeoDataClient = Places.getGeoDataClient(this, null);


        final String TAG = "Main2Activity";
        final PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        final PlaceAutocompleteFragment autocompleteFragment2 = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent();
                //backToMain.putExtra("location1", autocompleteFragment.get);
                openMainScreen();
            }
        });

        //String url = "https://maps.googleapis.com/maps/api/place/autocomplete/output?parameters";
        //JsonObjectRequest jsonobjectrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()) {

    }

    public void openMainScreen() {
        Intent start = new Intent(this, MainActivity.class);
        startActivity(start);
    }

}
