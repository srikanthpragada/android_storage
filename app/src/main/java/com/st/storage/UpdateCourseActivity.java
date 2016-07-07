package com.st.storage;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UpdateCourseActivity extends Activity {
	private EditText editName, editFee, editDuration;
	private LinearLayout layoutDetails ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatecourse);
		editName = (EditText) this.findViewById(R.id.editName);
		editFee = (EditText) this.findViewById(R.id.editFee);
		editDuration = (EditText) this.findViewById(R.id.editDuration);
		layoutDetails = (LinearLayout) this.findViewById(R.id.layoutDetails);
	}
	
	public void getDetails(View v) {

		
		try {
			STDatabase dbhelper = new STDatabase(this); 
			SQLiteDatabase db = dbhelper.getReadableDatabase();
			
			Cursor  details = db.query(STDatabase.COURSES_TABLE_NAME, null,
					STDatabase.COURSES_NAME + " = ?",  // select
					new String[]{editName.getText().toString()}, //  selectargs,
					null, null, null);

			if ( details.moveToFirst()) { // found row
				  editFee.setText(  details.getString( details.getColumnIndex( STDatabase.COURSES_FEE)));
				  editDuration.setText(  details.getString( details.getColumnIndex( STDatabase.COURSES_DURATION)));
				  layoutDetails.setVisibility( View.VISIBLE);
			}
			else{
				  layoutDetails.setVisibility( View.INVISIBLE);
			      Toast.makeText(this, "Sorry! Course not found!", Toast.LENGTH_LONG).show();
			}
			
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

   }
	


	public void updateCourse(View v) {

		// get access to writeable database
		try {
			STDatabase dbhelper = new STDatabase(this); 
			SQLiteDatabase db = dbhelper.getWritableDatabase();

			// execute update command

			ContentValues values = new ContentValues();
			values.put(STDatabase.COURSES_FEE , editFee.getText().toString());
			values.put(STDatabase.COURSES_DURATION, editDuration.getText().toString());

			int count  = db.update(STDatabase.COURSES_TABLE_NAME,values,
					STDatabase.COURSES_NAME + " = ?" ,  // select
					new String[] { editName.getText().toString() });

			if ( count == 1)
    			Toast.makeText(this, "Updated Course Successfully!",
					Toast.LENGTH_LONG).show();
			else
				Toast.makeText(this, "Sorry! Could not update course!",
						Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
