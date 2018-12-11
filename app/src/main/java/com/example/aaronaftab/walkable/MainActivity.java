package com.example.aaronaftab.walkable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddClassActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        //FIX ISSUE BELOW ON CHECKING IF TEXTVIEW FIELDS ARE FILLED
        if (intent.hasExtra("class1") && intent.hasExtra("class2")
                && intent.hasExtra("location1")
                && intent.hasExtra("location2")) {
            //create textviews
            TextView firstEvent = new TextView(MainActivity.this);
            TextView secondEvent = new TextView(MainActivity.this);
            //set textview attributes
            RelativeLayout.LayoutParams attributes = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            attributes.addRule(RelativeLayout.BELOW, R.id.textView);
            firstEvent.setLayoutParams(attributes);
            secondEvent.setLayoutParams(attributes);
            firstEvent.setBackgroundColor(Color.GREEN);
            secondEvent.setBackgroundColor(Color.GREEN);
            firstEvent.setLines(2);
            secondEvent.setLines(2);
            //add textview
            View relativeLayout = findViewById(R.id.textView);
            ((RelativeLayout) relativeLayout).addView(firstEvent);
            ((RelativeLayout) relativeLayout).addView(secondEvent);
        } else {
            String err = "All fields must be filled.";
            Toast.makeText(MainActivity.this, err, Toast.LENGTH_LONG).show();
        }


        //getIntent().getStringExtra("class1");
        //getIntent().getStringExtra("class2");
    }

    public void openAddClassActivity() {
        Intent start = new Intent(this, Main2Activity.class);
        startActivity(start);
    }
}
