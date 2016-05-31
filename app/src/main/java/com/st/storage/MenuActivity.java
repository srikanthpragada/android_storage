package com.st.storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void addCourse(View v) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);

    }

    public void listCourses(View v) {
        Intent intent = new Intent(this, ListCoursesActivity.class);
        startActivity(intent);

    }

    public void externalStorage(View v) {
        Intent intent = new Intent(this, ExternalStorageActivity.class);
        startActivity(intent);

    }
}
