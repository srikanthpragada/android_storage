package com.st.storage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UpdateCourseRowActivity extends AppCompatActivity {
    private EditText editName, editFee, editDuration;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course_row);

        editName = (EditText) this.findViewById(R.id.editName);
        editFee = (EditText) this.findViewById(R.id.editFee);
        editDuration = (EditText) this.findViewById(R.id.editDuration);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        Log.d("Storage", "Updating course with id  " + id);

        getDetails();
    }

    public void getDetails() {
        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();

            Cursor details = db.query(STDatabase.COURSES_TABLE_NAME, null,
                    STDatabase.COURSES_ID + " = ?",  // select
                    new String[]{String.valueOf(id)}, //  selectargs,
                    null, null, null);

            details.moveToFirst();

            editName.setText(details.getString(details.getColumnIndex(STDatabase.COURSES_NAME)));
            editFee.setText(details.getString(details.getColumnIndex(STDatabase.COURSES_FEE)));
            editDuration.setText(details.getString(details.getColumnIndex(STDatabase.COURSES_DURATION)));


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
            values.put(STDatabase.COURSES_NAME, editName.getText().toString());
            values.put(STDatabase.COURSES_FEE, editFee.getText().toString());
            values.put(STDatabase.COURSES_DURATION, editDuration.getText().toString());

            int count = db.update(STDatabase.COURSES_TABLE_NAME, values,
                    STDatabase.COURSES_ID + " = ?",  // select
                    new String[]{String.valueOf(id)});

            if (count == 1)
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
