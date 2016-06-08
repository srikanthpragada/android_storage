package com.st.storage;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CoursesAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Course> courses;


    public CoursesAdapter(Context ctx, ArrayList<Course> courses) {
        this.ctx = ctx;
        this.courses = courses;
    }

    public int getCount() {
        return courses.size();
    }

    public Object getItem(int position) {
        return courses.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.custom_course, null);
            Button btnDelete = (Button) convertView
                    .findViewById(R.id.btnDelete);
            Button btnUpdate = (Button) convertView
                    .findViewById(R.id.btnUpdate);

            final Course course = courses.get(position);

            TextView textName  = (TextView) convertView
                    .findViewById(R.id.textName);
            textName.setText(course.getName());

            TextView textFee  = (TextView) convertView
                    .findViewById(R.id.textFee);
            textFee.setText( String.valueOf(course.getFee()));

            TextView textDuration  = (TextView) convertView
                    .findViewById(R.id.textDuration);
            textDuration.setText( String.valueOf(course.getDuration()));


            btnDelete.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    List<Course> coures = CoursesAdapter.this.courses;
                    Log.d("Storage","About to delete course  :" + course.getId());
                    // delete course
                    try {
                        STDatabase dbhelper = new STDatabase(CoursesAdapter.this.ctx);
                        SQLiteDatabase db = dbhelper.getWritableDatabase();

                        String cond = STDatabase.COURSES_ID + " = ?";
                        int count = db.delete(STDatabase.COURSES_TABLE_NAME,cond,
                                new String[] { String.valueOf(course.getId())});

                        if (count == 1) {
                            Toast.makeText(CoursesAdapter.this.ctx,
                                    "Deleted Course Successfully!",
                                    Toast.LENGTH_SHORT).show();
                            // delete from collection
                            for(Course c : courses) {
                                if (c.getId() == course.getId())
                                    courses.remove(c);
                            }
                            // update view about change in Adapter's data
                            CoursesAdapter.this.notifyDataSetChanged();
                        }
                        else
                            Toast.makeText(CoursesAdapter.this.ctx,
                                    "Course Could not be deleted!",
                                    Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Toast.makeText(CoursesAdapter.this.ctx,
                                ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            });


            btnUpdate.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    Log.d("Storage","About to update course  :" + course.getId());
                    Intent intent = new Intent( CoursesAdapter.this.ctx,
                              UpdateCourseRowActivity.class);
                    intent.putExtra("id", course.getId());
                    CoursesAdapter.this.ctx.startActivity(intent);
                }

            });

        }
        return convertView;
    }

}
