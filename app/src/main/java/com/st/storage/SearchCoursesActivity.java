package com.st.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SearchCoursesActivity extends AppCompatActivity {
    ListView lvCourses;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_courses);
        lvCourses =(ListView) findViewById(R.id.lvCourses);
        editName = (EditText) findViewById(R.id.editName);

    }

    public void search(View v) {
        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor courses = db.query(
                    STDatabase.COURSES_TABLE_NAME,null,
                    STDatabase.COURSES_NAME + " like  ?",
                    new String[] { "%" + editName.getText().toString()  + "%"}
                    ,null,null,null);

            String from [] = { "name", "fee","duration"};
            int to [] = { R.id.textName, R.id.textFee, R.id.textDuration};

            SimpleCursorAdapter ca  = new SimpleCursorAdapter(
                    this,R.layout.course_search_result_row, courses,from,to
            );

            lvCourses.setAdapter(ca);

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
