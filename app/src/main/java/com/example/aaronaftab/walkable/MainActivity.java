package com.example.aaronaftab.walkable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import java.util.Map;
import java.util.HashMap;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import android.util.Log;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private int opened = 0;

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
        if (opened != 0) {
            TableLayout tablelayout = findViewById(R.id.TableLayout);
            TableRow row = (TableRow) tablelayout.getChildAt(count++);
            TextView firstEvent = new TextView(MainActivity.this);
            TextView secondEvent = new TextView(MainActivity.this);
            TextView thirdEvent = new TextView(MainActivity.this);
            TableLayout.LayoutParams layoutparams = new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            firstEvent.setLayoutParams(layoutparams);
            secondEvent.setLayoutParams(layoutparams);
            thirdEvent.setLayoutParams(layoutparams);
            //set text
            Map<String, ?> values = getInfo(this);
            firstEvent.setText((String) values.get("class1"));
            secondEvent.setText((String) (values.get("time")));
            thirdEvent.setText((String) values.get("class2"));

            row.addView(firstEvent);
            row.addView(secondEvent);
            row.addView(thirdEvent);

            this.setContentView(tablelayout, new TableLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
        opened++;


//        Intent intent = getIntent();
//        String time = intent.getStringExtra("time");
//        EditText setT = findViewById(R.id.timeText);
//        setT.setText(time);
        /*
        //FIX ISSUE BELOW ON CHECKING IF TEXTVIEW FIELDS ARE FILLED
        if (intent.hasExtra("class1") && intent.hasExtra("class2")
                && intent.hasExtra("location1") && intent.hasExtra("location2")) {

            RelativeLayout rellayout = new RelativeLayout(this);
            //create textviews


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
            Log.i("Passed info", intent.getStringExtra("class1")
                    + intent.getStringExtra("class2") + intent.getStringExtra("location1")
                    + intent.getStringExtra("location2"));
            String err = "All fields must be filled.";
            Toast message = Toast.makeText(MainActivity.this, err, Toast.LENGTH_LONG);
            message.setGravity(Gravity.CENTER, 0, 0);
            message.show();
        }
        */

        //getIntent().getStringExtra("class1");
        //getIntent().getStringExtra("class2");

    }

    public static Map<String, ?> getInfo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("ClassLoc", MODE_PRIVATE);
        return prefs.getAll();
    }

    public void openAddClassActivity() {
        Intent start = new Intent(this, Main2Activity.class);
        startActivity(start);
    }
}
