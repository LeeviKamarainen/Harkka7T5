package com.example.harkka7t5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {
    Context context = null;
    TextView text2;
    EditText text;
    EditText etext;
    String tiedosto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        text2 = (TextView) findViewById(R.id.textView);
        text = (EditText) findViewById(R.id.editText4);
        etext = (EditText) findViewById(R.id.editText3);
        etext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    tiedosto = etext.getText().toString();
                }
                return false;
            }
        });
    }

    public void readFile(View v) {
        if (tiedosto == "") {
            text2.setText("Anna tiedostonimi.");
        } else {
            try {
                InputStream ins = context.openFileInput(tiedosto);
                text2.setText("");
                BufferedReader br = new BufferedReader(new InputStreamReader(ins));
                String s = "";
                while ((s = br.readLine()) != null) {
                    text2.append(s);
                }
                ins.close();
            } catch (IOException e) {
                Log.e("IOException", "Virhe syötteessä.");
            }
        }
    }

    public void writeFile(View v) {
        if (tiedosto == "") {
            text2.setText("Anna tiedostonimi.");
        } else {
            try {
                OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(tiedosto, Context.MODE_APPEND));

                String s = text.getText().toString();

                ows.write(s + "\n");
                ows.close();
            } catch (IOException e) {
                Log.e("IOException", "Virhe syötteessä.");
            }
        }
    }
}