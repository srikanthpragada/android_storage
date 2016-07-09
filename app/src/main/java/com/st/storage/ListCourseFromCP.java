package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListCourseFromCP extends Activity {
	@Override
	public void onCreate(Bundle  data) {
		super.onCreate(data);
		setContentView(R.layout.activity_list_course_rows);
		try {

			Cursor courses = getContentResolver().query(
					  CoursesContentProvider.CONTENT_URI,
  				      null,null,null,null);

			String from [] = { CoursesContentProvider.COURSE_NAME,
					           CoursesContentProvider.COURSE_FEE,
					           CoursesContentProvider.COURSE_DURATION };

			int to [] = { R.id.textName, R.id.textFee, R.id.textDuration};
			
			SimpleCursorAdapter ca  = new SimpleCursorAdapter(
					this,R.layout.course_row, courses,from,to
					);
			
		    ListView listCourses = (ListView) this.findViewById( R.id.listCourses);
		    listCourses.setAdapter(ca);
			
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
