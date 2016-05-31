package com.st.storage;

import java.io.File;
import java.io.FileWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalStorageActivity extends Activity {
    private TextView textMessage;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externalstorage);
        textMessage = (TextView) this.findViewById(R.id.textMessage);
    }

    public void checkAvailability(View v) {
        String state = Environment.getExternalStorageState();

        textMessage.append(state + "\n");

        if (Environment.MEDIA_MOUNTED.equals(state))
            textMessage.append("Available and writable");
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            textMessage.append("Available and read only");
        else
            textMessage.append("Not available");
    }

    public void createDirectory(View v) {
        try {

            // path is /mnt/sdcard/Download
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            textMessage.append("\n" + dir.getAbsolutePath());
            // File dir = Environment.getExternalStorageDirectory();
            File mydir = new File(dir, "demo");
            if (!mydir.exists()) {
                if (mydir.mkdir())
                    textMessage.append("\nCreated demo directory...");
                else {
                    textMessage.append("\nCould not create demo directory...");
                    return;
                }
            } else
                textMessage.append("\nUsing demo directory...");


        } catch (Exception ex) {
            textMessage.append("\nError: " + ex.getMessage());
        }

    }

    public void createFile(View v) {
        try {
            // File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File dir = this.getExternalFilesDir(null);
            textMessage.append("\n" + dir.getAbsolutePath());
            File file = new File(dir, "numbers.txt");

            FileWriter fw = new FileWriter(file);
            for (int i = 1; i <= 10; i++)
                fw.write(i + "\n");
            fw.close();

        } catch (Exception ex) {
            textMessage.append("\nError: " + ex.getMessage());
        }
    }


    public void listFolders(View v) {
        try {
            File dir = Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_MUSIC);
            if (dir.exists())
                textMessage.append("\n" + dir.getAbsolutePath() + " is existing");
            else {
                textMessage.append("\n" + dir.getAbsolutePath() + " is not found!");
                return;
            }

            String[] files = dir.list();

            for (String file : files) {
                textMessage.append("\n" + file);
            }
        } catch (Exception ex) {
            textMessage.append("\nError: " + ex.getMessage());
        }
    }
}
