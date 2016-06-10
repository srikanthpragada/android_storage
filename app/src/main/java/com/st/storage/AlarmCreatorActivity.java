package com.st.storage;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmCreatorActivity extends Activity {

	 PendingIntent pi = null;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_alarmcreator);
	    }
	 
    
	    public  void setAlarm(View v) {
      	     AlarmManager  am = (AlarmManager) 
	    			    getSystemService(Context.ALARM_SERVICE);
	    	 
	    	 Calendar c = Calendar.getInstance();
	    	 c.setTimeInMillis( System.currentTimeMillis());
	    	 
	    	 c.add( Calendar.SECOND, 5);
	    	 
	    	 Intent intent = new Intent("com.st.alarm.CLASS_ALARM");
	    	 //Intent intent = new Intent(this, AlarmReceiver.class);
	    	 pi = PendingIntent.getBroadcast(this,0, intent,0);
	    	 
	    	 // am.set( AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pi);
	    	 am.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),10 * 1000, pi);
	    	 Toast.makeText(this,"Alram is set!",  Toast.LENGTH_LONG).show();
	    }


	public  void cancelAlarm(View v) {
		AlarmManager  am = (AlarmManager)
				getSystemService(Context.ALARM_SERVICE);
		if ( pi != null) {
			am.cancel(pi);
			Toast.makeText(this, "Alram is cancelled!", Toast.LENGTH_LONG).show();
		}
		else
			Toast.makeText(this, "Alram Not Yet Set!", Toast.LENGTH_LONG).show();


	}
	    

}
