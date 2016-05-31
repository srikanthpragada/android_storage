package com.st.storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ListCoursesActivity extends AppCompatActivity {
    private final String  FILENAME = "courses.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);
        TextView textCourses = (TextView) findViewById(R.id.textCourses);
        try {
            FileInputStream file = this.openFileInput(FILENAME);
            BufferedReader br = new BufferedReader( new InputStreamReader(file));
            String line = br.readLine();
            while (line != null) {
                String parts[] = line.split(",");
                textCourses.append( parts[0] + "\n");  // id
                textCourses.append( parts[1] + "\n");  // name
                textCourses.append( parts[2] + "\n");  // duration
                textCourses.append( "----------------\n");
                line = br.readLine();
            }
            file.close();
        }
        catch(Exception ex )
        {
            Log.d("Storage","Error opening file : " + ex.getMessage());
        }

    }


}
