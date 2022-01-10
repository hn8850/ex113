package com.example.ex113;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * @author Harel Navon harelnavon2710@gmail.com
 * @version 1.0
 * @since 10/1/2022
 * This is a small application designed to try out working with files!
 */

public class MainActivity extends AppCompatActivity {
    String str, line;
    EditText et;
    TextView tv;
    FileOutputStream fos;
    OutputStreamWriter osw;
    BufferedWriter bw;
    FileInputStream fis;
    InputStreamReader isr;
    BufferedReader br;
    StringBuffer sb;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
        si = new Intent(this, credits.class);
        str = "";


    }

    @Override
    protected void onStart() {
        super.onStart();
        tv.setText(read());
    }


    /**
     * Saves the text written by the user to the file before closing the Activity.
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (et.getText().toString().length() != 0) {
            str = read() + et.getText().toString();
            write();
        }
    }

    /**
     * Resets the text in the text view and in the file.
     * @param view
     */
    public void reset(View view) {
        str = "";
        write();
        tv.setText("");
    }

    /**
     * Closes the Activity.
     * @param view
     */
    public void close(View view) {
        finish();
    }

    /**
     * Saves the text written by the user to the file and displays all of the text in the file/
     * @param view
     */
    public void saver(View view) {
        str = read() + et.getText().toString();
        write();
        tv.setText(str);
        et.setText("");
    }

    /**
     * Writes the current value of str into the file 'test.txt'.
     */
    public void write() {
        try {
            fos = openFileOutput("test.txt", MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            Toast.makeText(this, "No file found!!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Reads the file 'test.txt' and returns its text in str.
     */
    public String read() {
        str = "";
        try {
            fis = openFileInput("test.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuffer();
            line = br.readLine();
            while (line != null) {
                sb.append(line + '\n');
                line = br.readLine();
            }
            str = sb.toString();
            br.close();
        } catch (IOException e) {
            Toast.makeText(this, "No file found!!", Toast.LENGTH_SHORT).show();
        }
        return str;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(si);
        return true;
    }
}