package com.st.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class FilesInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_info);
    }

    public void getInfo(View v)
    {
        TextView tvInfo = (TextView) this.findViewById(R.id.tvInfo);

        File dir  = getFilesDir();
        tvInfo.append( dir.toString() + "\n");

        String [] files = fileList();

        tvInfo.append("\nList of Files \n");
        for(String file : files)
            tvInfo.append( file.toString() + "\n");

    }
}
