package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListCourseRowsActivity extends Activity {
	@Override
	public void onCreate(Bundle  data) {
		super.onCreate(data);
		setContentView(R.layout.activity_list_course_rows);
		try {
			STDatabase dbhelper = new STDatabase(this);
			SQLiteDatabase db = dbhelper.getReadableDatabase();
			Cursor  courses = db.query( 
					  STDatabase.COURSES_TABLE_NAME,null,null,null,null,null,null);

			String from [] = { "name", "fee","duration"};
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
