package com.st.storage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ContentProviderMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_menu);
    }

    public void callCountriesInfo(View v) {

        Intent intent  = new Intent (this, GetCountriesInfo.class);
        startActivity(intent);
    }

    public void callListCourses(View v) {

        Intent intent  = new Intent (this, ListCourseFromCP.class);
        startActivity(intent);
    }


    public void callAddCourse(View v) {

        Intent intent  = new Intent (this, AddCourseRowUsingCPActivity.class);
        startActivity(intent);
    }


    public void callDeleteCourse(View v) {

        Intent intent  = new Intent (this, DeleteCourseFromCPActivity.class);
        startActivity(intent);
    }






}
