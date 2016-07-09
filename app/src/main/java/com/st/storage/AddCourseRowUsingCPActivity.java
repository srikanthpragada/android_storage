package com.st.storage;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourseRowUsingCPActivity extends Activity {
	private EditText editName, editFee, editDuration;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_course_row);
		editName = (EditText) this.findViewById(R.id.editName);
		editFee = (EditText) this.findViewById(R.id.editFee);
		editDuration = (EditText) this.findViewById(R.id.editDuration);
	}


	public void addCourse(View v) {

		// get access to writeable database
		try {

			ContentValues values = new ContentValues();
			values.put( CoursesContentProvider.COURSE_NAME, editName.getText().toString());
			values.put( CoursesContentProvider.COURSE_FEE , editFee.getText().toString());
			values.put( CoursesContentProvider.COURSE_DURATION, editDuration.getText().toString());

			Uri uri = getContentResolver().insert(
					CoursesContentProvider.CONTENT_URI,values);
			if ( uri == null)
				Toast.makeText(this, "Sorry! Could not add course!",
						Toast.LENGTH_SHORT).show();
			else
			    Toast.makeText(this, "Added Course Successfully!",
					Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
