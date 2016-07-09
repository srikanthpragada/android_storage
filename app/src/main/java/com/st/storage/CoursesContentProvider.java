package com.st.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class CoursesContentProvider extends ContentProvider {
    public static final Uri CONTENT_URI =
            Uri.parse("content://com.st.courses");

    public static  final String COURSE_NAME =   STDatabase.COURSES_NAME;
    public static  final String COURSE_DURATION =   STDatabase.COURSES_DURATION;
    public static  final String COURSE_FEE =   STDatabase.COURSES_FEE;

    public CoursesContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        STDatabase dbhelper = new STDatabase(getContext());
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            int count = db.delete(STDatabase.COURSES_TABLE_NAME,
                      selection, selectionArgs);
            return count;
        }
        catch(Exception ex){
            Log.d("Storage", "Error in delete() --> " + ex.getMessage());
            return 0;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        STDatabase dbhelper = new STDatabase(getContext());
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            db.insert(STDatabase.COURSES_TABLE_NAME, null,values);
            return uri;
        }
        catch(Exception ex){
            Log.d("Storage", "Error in insert() --> " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        STDatabase dbhelper = new STDatabase(getContext());
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor  courses = db.query(
                STDatabase.COURSES_TABLE_NAME,null,null,null,null,null,null);
        return courses;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
