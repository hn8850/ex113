package com.example.ex113;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class credits extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        tv = findViewById(R.id.TEXT);
        tv.setText(getString(R.string.creds));

    }


    /**
     *  Closes the Activity.
     */
    public void go(View view) {
        finish();
    }

}