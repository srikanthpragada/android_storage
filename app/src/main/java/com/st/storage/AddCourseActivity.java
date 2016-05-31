package com.st.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class AddCourseActivity extends AppCompatActivity {
    private final String  FILENAME = "courses.txt";
    private FileOutputStream fileStream;
    private PrintWriter writer;
    private EditText editId,editName, editDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        editId = (EditText) findViewById(R.id.editId);
        editName = (EditText) findViewById(R.id.editName);
        editDuration = (EditText) findViewById(R.id.editDuration);

    }

    @Override
    public void onStart() {
        super.onStart();
        openFile();

    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            writer.close();
            fileStream.close();
            Log.d("Storage","onStop() closed files");
        }
        catch(Exception ex) {
            Log.e("Storage","Error closing file :" + ex.getMessage());
        }
    }

    public void openFile() {
        try {
            fileStream = this.openFileOutput(FILENAME, MODE_APPEND);
            writer = new PrintWriter(fileStream, true);
            Log.d("Storage","Opening files!");
        }
        catch(Exception ex) {
            Log.e("Storage", "Error opening file : " + ex.getMessage());
        }
    }

    public void addCourse(View v) {
        String course;

        course = editId.getText().toString() + "," +  editName.getText().toString()+ ","
                 + editDuration.getText().toString();

        writer.write(course + "\n");
        Log.d("Storage","Added Course " + course);
        Toast.makeText(this,"Added Course Sucessfully!", Toast.LENGTH_SHORT).show();
    }
}
