package com.example.aaronaftab.walkable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

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

        if (getIntent().hasExtra("class1") && getIntent().hasExtra("class2")
                && getIntent().hasExtra("location1")
                && getIntent().hasExtra("location2")) {
            //TextView firstEvent = new TextView();
            //firstEvent.setLayoutParams();
        }

        //getIntent().getStringExtra("class1");
        //getIntent().getStringExtra("class2");


    }

    public void openAddClassActivity() {
        Intent start = new Intent(this, Main2Activity.class);
        startActivity(start);
    }
}
