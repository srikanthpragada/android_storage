package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

public class ListAlbumsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_albums);
/*
		Log.d("Storage", MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI.toString());
		Log.d("Storage", MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
		*/

		Cursor cur = getContentResolver().query(
				MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null,
				null);

		TextView textAlbums = (TextView) this.findViewById(R.id.textAlbums);


		while (cur.moveToNext()) {

			// int albumIdIndex =
			// cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
			int albumId = cur.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ID);
			int albumIndex = cur.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM);
			int artistIndex = cur
					.getColumnIndex(MediaStore.Audio.AlbumColumns.ARTIST);
			int nosongsIndex = cur
					.getColumnIndex(MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS);
			// int albumId = cur.getInt(albumIdIndex);
			String id = cur.getString(albumId);
			String name = cur.getString(albumIndex);
			String artist = cur.getString(artistIndex);
			String nosongs = cur.getString(nosongsIndex);

			textAlbums.append(String.format("%s - %s - %s - %s\n",
					id,name, artist, nosongs));

		}

	}

}
