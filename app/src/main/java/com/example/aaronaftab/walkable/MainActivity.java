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
import android.view.Gravity;
import android.util.Log;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int count = 0;

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
                && intent.hasExtra("location1") && intent.hasExtra("location2")) {
            Log.i("Passed info", intent.getStringExtra("class1")
                    + intent.getStringExtra("class2") + intent.getStringExtra("location1")
                    + intent.getStringExtra("location2") + intent.getStringExtra("time"));
            //create instance of relativelayout
            RelativeLayout rellayout = new RelativeLayout(this);
            //create textviews
            TextView firstEvent = new TextView(MainActivity.this);
            TextView secondEvent = new TextView(MainActivity.this);
            TextView thirdEvent = new TextView(MainActivity.this);
            //set textview attributes
            firstEvent.setId(count++);
            secondEvent.setId(count++);
            thirdEvent.setId(count++);
//            LayoutParams lp = (RelativeLayout.LayoutParams) firstEvent.getLayoutParams();
//            LayoutParams lp2 = (RelativeLayout.LayoutParams) secondEvent.getLayoutParams();
            RelativeLayout.LayoutParams attributes = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams attributes2 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams attributes3 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            attributes.addRule(RelativeLayout.BELOW, R.id.top);
            attributes2.addRule(RelativeLayout.BELOW, R.id.top);
            attributes3.addRule(RelativeLayout.BELOW, R.id.top);
            attributes.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            attributes2.addRule(RelativeLayout.ALIGN_RIGHT, firstEvent.getId());
            attributes3.addRule(RelativeLayout.ALIGN_RIGHT, secondEvent.getId());
            firstEvent.setLayoutParams(attributes);
            secondEvent.setLayoutParams(attributes2);
            thirdEvent.setLayoutParams(attributes3);
            firstEvent.setBackgroundColor(Color.GREEN);
            thirdEvent.setBackgroundColor(Color.GREEN);
            firstEvent.setLines(2);
            thirdEvent.setLines(2);
            //add textview
            //View relativeLayout = findViewById(R.id.textView);
            rellayout.addView(firstEvent, attributes);
            rellayout.addView(secondEvent, attributes2);
            rellayout.addView(thirdEvent, attributes3);
        } else {
            String err = "All fields must be filled.";
            Toast message = Toast.makeText(MainActivity.this, err, Toast.LENGTH_LONG);
            message.setGravity(Gravity.CENTER, 0, 0);
            message.show();
        }


        //getIntent().getStringExtra("class1");
        //getIntent().getStringExtra("class2");
    }

    public void openAddClassActivity() {
        Intent start = new Intent(this, Main2Activity.class);
        startActivity(start);
    }
}
