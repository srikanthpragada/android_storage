package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteSelectedCourseActivity extends Activity {
	private Spinner spNames;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_selected_course);
		spNames  = (Spinner) findViewById(R.id.spNames);
		populateSpinner();
    }

	public void populateSpinner() {
		try {
			STDatabase dbhelper = new STDatabase(this);
			SQLiteDatabase db = dbhelper.getReadableDatabase();

			Cursor courses = db.query(
					STDatabase.COURSES_TABLE_NAME,
					new String[]{STDatabase.COURSES_NAME},
					null, null, null, null, STDatabase.COURSES_NAME, null);

			ArrayList<String> names = new ArrayList<>();

			while (courses.moveToNext()) {
				names.add(courses.getString(0));
			}

			courses.close();
			db.close();

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, names);
			spNames.setAdapter(adapter);
		}catch(Exception ex) {
			Log.d("Storage", "Error -> " + ex.getMessage());
		}

	}

	public void deleteCourse(View v) {
		try {
			STDatabase dbhelper = new STDatabase(this); 
			SQLiteDatabase db = dbhelper.getWritableDatabase();
			// get selected coursename
			String name = spNames.getSelectedItem().toString();
			Log.d("Storage", "Selected Course :" + name);
			String cond = STDatabase.COURSES_NAME + " = ?";
			int count = db.delete(STDatabase.COURSES_TABLE_NAME,cond,
					   new String[] { name });

            if (count >= 1) {
				Toast.makeText(this, "Deleted Course(s) Successfully!",
						Toast.LENGTH_SHORT).show();
				populateSpinner();
			}
			else
				Toast.makeText(this, "Course Not Found!",
						Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
