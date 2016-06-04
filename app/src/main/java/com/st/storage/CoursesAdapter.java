package com.st.storage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


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
                    Log.d("Storage","About to delete course  :" + course.getId());
                }

            });


            btnUpdate.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    Log.d("Storage","About to update course  :" + course.getId());
                }

            });

        }
        return convertView;
    }

}
