package com.st.storage;

import android.app.Activity;
import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListCoursesWithListViewActivity extends ListActivity {
    private final String FILENAME = "courses.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView lvCourses = this.getListView();

        ArrayList<Map<String, String>> courses = new ArrayList<>();

        try {
            FileInputStream file = this.openFileInput(FILENAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line = br.readLine();
            while (line != null) {
                String parts[] = line.split(",");
                HashMap<String, String> course = new HashMap<>();
                course.put("id", parts[0]);
                course.put("name", parts[1]);
                course.put("duration", parts[2]);
                courses.add(course);
                line = br.readLine();
            }
            file.close();

            SimpleAdapter adapter = new SimpleAdapter(this,courses, R.layout.course_layout,
                       new String [] { "name","duration"},
                       new int[] { R.id.textName, R.id.textDuration}
                       );
             lvCourses.setAdapter(adapter);

        } catch (Exception ex) {
            Log.d("Storage", "Error opening file : " + ex.getMessage());
        }
    }

}
