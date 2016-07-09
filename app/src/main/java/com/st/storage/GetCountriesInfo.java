package com.st.storage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class GetCountriesInfo extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_countriesinfo);
		
		//Cursor c =  this.managedQuery(CountryContentProvider.CONTENT_URI, null,null,null,null);
		Cursor c =  getContentResolver().query(
				 CountryContentProvider.CONTENT_URI, null,null,null,null);

		TextView textCountries = (TextView) this.findViewById( R.id.textCountries);
		textCountries.setText("");

        while (c.moveToNext()) {
           textCountries.append(  String.format("%s - %s \n",
        		   c.getString( c.getColumnIndex( CountryContentProvider.COUNTRY_NAME)),
        		   c.getString( c.getColumnIndex( CountryContentProvider.COUNTRY_CAPITAL))));
        		   
        }
        c.close();
	}

}
