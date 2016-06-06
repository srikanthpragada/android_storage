package com.st.storage;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;

import java.util.ArrayList;

public class ModifyCourseRowsActivity extends ListActivity {
    @Override
    public void onStart() {
        super.onStart();
        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor coursesCursor = db.query(
                    STDatabase.COURSES_TABLE_NAME, null, null, null, null, null, null);

            // Convert Cursor to list of Course Objects
            ArrayList<Course> courses = new ArrayList<>();
            while (coursesCursor.moveToNext()) {
                Course c = new Course();
                c.setId(coursesCursor.getInt(coursesCursor.getColumnIndex(STDatabase.COURSES_ID)));
                c.setName(coursesCursor.getString(coursesCursor.getColumnIndex(STDatabase.COURSES_NAME)));
                c.setFee(coursesCursor.getInt(coursesCursor.getColumnIndex(STDatabase.COURSES_FEE)));
                c.setDuration(coursesCursor.getInt(coursesCursor.getColumnIndex(STDatabase.COURSES_DURATION)));
                courses.add(c);
            }
            coursesCursor.close();
            db.close();
            CoursesAdapter adapter = new CoursesAdapter(this, courses);
            getListView().setAdapter(adapter);

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
