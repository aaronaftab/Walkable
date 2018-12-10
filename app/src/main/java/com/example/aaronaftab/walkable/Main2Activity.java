package com.example.aaronaftab.walkable;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import com.google.android.gms.location.places.ui.*;
import com.google.android.gms.common.*;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import com.google.android.gms.common.api.*;
import android.util.Log;
import android.widget.EditText;
import android.text.InputType;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GeoDataClient mGeoDataClient;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mGeoDataClient = Places.getGeoDataClient(this, null);

        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainScreen();
            }
        });

//        EditText location1 = findViewById(R.id.location1);
//        location1.setInputType(InputType.TYPE_NULL);
//        location1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPlaceSearch();
//            }
//        });
//        location1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    openPlaceSearch();
//                }
//            }
//        });

        //String url = "https://maps.googleapis.com/maps/api/place/autocomplete/output?parameters";
        //JsonObjectRequest jsonobjectrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()) {

        }


    public void openMainScreen() {
        Intent start = new Intent(this, MainActivity.class);
        startActivity(start);
    }

    public void openPlaceSearch() {
        int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            System.out.println("You have an error: " + e);
        } catch (GooglePlayServicesNotAvailableException e) {
            System.out.println("You have an error: " + e);
        }
    }
}
