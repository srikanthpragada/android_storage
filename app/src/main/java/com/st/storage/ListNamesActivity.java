package com.st.storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ListNamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_names);
        TextView textNames = (TextView) findViewById(R.id.textNames);
        try {
            InputStream file = getResources().openRawResource( R.raw.names);
            Scanner scanner  = new Scanner(file);
            String line = scanner.nextLine();
            while (line != null) {
                textNames.append(line + "\n");
                line = scanner.nextLine();
            }
            file.close();
        }
        catch(Exception ex )
        {
            Log.d("Storage","Error opening file : " + ex.getMessage());
        }

    }


}
