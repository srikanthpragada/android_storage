package com.st.storage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DatabaseDemoActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_databasedemo);
	}

	public void addCourse(View v) {
	    Intent intent = new Intent(this, AddCourseRowActivity.class);
	    startActivity(intent);
	}

	public void deleteCourse(View v) {
		Intent intent = new Intent(this, DeleteCourseRowActivity.class);
		startActivity(intent);
	}

	public void listCourses(View v) {
	    Intent intent = new Intent(this, ListCourseRowsActivity.class);
	    startActivity(intent);
	}


	public void updateCourse(View v) {
		Intent intent = new Intent(this, UpdateCourseActivity.class);
		startActivity(intent);
	}

	public void searchCourses(View v) {
		Intent intent = new Intent(this, SearchCoursesActivity.class);
		startActivity(intent);
	}
}
