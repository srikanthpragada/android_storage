package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

public class ListContactNamesActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listcontacts);

		TextView textList = (TextView) this.findViewById(R.id.textList);

		Log.d("Storage", ContactsContract.Contacts.CONTENT_URI.toString());

		Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
				null,null,null,null);


		textList.setText("");
		while (c.moveToNext()) {
			textList.append(c.getString(c.getColumnIndex( ContactsContract.Contacts.DISPLAY_NAME ) ) + "\n");
		}

	}

}