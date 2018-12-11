package com.example.aaronaftab.walkable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        int randVal = (int) (Math.random() * 255);

//        TextView three = findViewById(R.id.textView3);
//        String val = getIntent().getStringExtra("class1")
//                + getIntent().getStringExtra("class2")
//                + getIntent().getStringExtra("location1")
//                + getIntent().getStringExtra("location2");
//        three.setText(val);
        //FIX ISSUE BELOW ON CHECKING IF TEXTVIEW FIELDS ARE FILLED
        if (getIntent().hasExtra("class1") && getIntent().hasExtra("class2")
                && getIntent().hasExtra("location1")
                && getIntent().hasExtra("location2")) {
            //create textviews
            TextView firstEvent = new TextView(this);
            TextView secondEvent = new TextView(this);
            //set textview attributes
            firstEvent.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            secondEvent.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            firstEvent.setBackgroundColor(Color.GREEN);
            secondEvent.setBackgroundColor(Color.GREEN);
            firstEvent.setLines(2);
            secondEvent.setLines(2);
        } else {
            TextView one = findViewById(R.id.textView6);
            one.setBackgroundColor(Color.rgb(randVal, randVal, randVal));
        }


        //getIntent().getStringExtra("class1");
        //getIntent().getStringExtra("class2");
    }

    public void openAddClassActivity() {
        Intent start = new Intent(this, Main2Activity.class);
        startActivity(start);
    }
}
