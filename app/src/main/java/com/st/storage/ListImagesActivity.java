package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

public class ListImagesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_albums);

		Cursor cur = getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);

		TextView textAlbums = (TextView) this.findViewById(R.id.textAlbums);


		while (cur.moveToNext()) {

			int nameIndex = cur
					.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
			String name = cur.getString(nameIndex);

			textAlbums.append("Name :" + name + "\n");

		}

	}

}
