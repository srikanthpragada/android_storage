package com.st.storage;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteCourseFromCPActivity extends Activity {
	private EditText editName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_course_row);
		editName = (EditText) this.findViewById(R.id.editName);
    }


	public void deleteCourse(View v) {
		try {
			int count = getContentResolver().delete(
					    Uri.parse("content://com.st.courses"),
                        "name = ?",
					    new String[] {editName.getText().toString()});

            if (count == 1)
			  Toast.makeText(this, "Deleted Course Successfully!",
					Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(this, "Course Not Found!",
						Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
