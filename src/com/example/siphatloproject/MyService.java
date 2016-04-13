package com.example.siphatloproject;

import java.util.ArrayList;
import java.util.HashMap;


import com.example.siphatloproject.POJO.Order;
import com.example.siphatloproject.POJO.RegisterUserClass;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service 
{
	private static final String TAG = "MyService";
	public String userID="";
	MediaPlayer player;
	Handler delayhandler = new Handler();
	private ArrayList<String> readyOrders = new ArrayList<String>();
	private static final String VIEW_READY_URL =MainActivity.URL+"checkReadyOrder.php";// "http://icep.net76.net/checkReadyOrder.php";
	private static final String VIEW_UNREADY_URL =MainActivity.URL+"checkUnReadyOrder.php";// "http://icep.net76.net/checkUnReadyOrder.php";
	private boolean stillHaveOders=false;
	
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	@Override
	public void onCreate() 
	{
		
		Log.d(TAG, "onCreate");
		delayhandler.postDelayed(mUpdateTimeTask, 1000);
		String user=OrderActivity.user;
		String[] element=user.split("#");
		userID=element[0];
		//Toast.makeText(this, "On create called ID:"+userID, Toast.LENGTH_LONG).show();
		//player = MediaPlayer.create(this, R.raw.braincandy);
		//player.setLooping(false); // Set looping
	}
	
	private Runnable mUpdateTimeTask = new Runnable()
	{   public void run()
	    {   
	    	//onCreate();
		    checkOrdder();
	        delayhandler.postDelayed(this, 1000);
	    }
	};
	
	public void  checkOrdder()
	{
		//Toast.makeText(this, "Chech Database...!!", Toast.LENGTH_LONG).show();
		//readyOrders.clear();
		getAllOrder(userID);
		getOrderThatNotready(userID);
		//stillHaveOders=true;
		delayhandler.postDelayed(mUpdateTimeTask, 1000);
		
	    //Log.i("Size ","Ready order size :"+readyOrders.size());
		if(readyOrders.size()>0)
		{
			ArrayList<String> mssg=new ArrayList<String>();
			
			
			for(String desc:readyOrders)
			{
				mssg.add("Order Desctription :"+desc);
			}
			
			notification(mssg);
			delayhandler.postDelayed(mUpdateTimeTask, 1000);
			getOrderThatNotready(userID);
		}
	}
	
	
	
	
	  public void notification(ArrayList<String> mssg)
	  {

			//NOTIFICATIONS
			
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
			 		.setPriority(NotificationCompat.PRIORITY_MAX)
			 		.setDefaults(Notification.DEFAULT_ALL)// for vibration\
			 		.setAutoCancel(true)
				    .setSmallIcon(R.drawable.images)
				    .setContentTitle("Sphatlho collection")
				    .setContentText("Your Sphatlho is ready")
				    .setTicker("Sphatlho Ready")
				    .setWhen(System.currentTimeMillis());
			
			Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
	         
	       
			
			long[] vibrate = { 0, 100, 200, 300 };
		    mBuilder.setVibrate(vibrate);
		    mBuilder.setSound(alarmSound);
			
			Intent resultIntent = new Intent(this, ViewReadyOrderActivity.class);
			resultIntent.putExtra("mssg", mssg);
			PendingIntent resultPendingIntent =
			PendingIntent.getActivity(this,0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			mBuilder.setContentIntent(resultPendingIntent);
			
			int mNotificationId = 001;
		
			NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
			mNotifyMgr.notify(mNotificationId, mBuilder.build());
		
			
			
	}
	@Override
	public void onDestroy() 
	{
		Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		stopService(new Intent(this, MyService.class));
		//Log.d(TAG, "onDestroy");
		//player.stop();
	}
	
	@Override
	public void onStart(Intent intent, int startid)
	{
		//Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		//Log.d(TAG, "onStart");
		//player.start();
	}
	 private void getOrderThatNotready(final String ownerID)
	 {
	        class UserLoginClass extends AsyncTask<String,Void,String>{
				//ProgressDialog loading;
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                //loading = ProgressDialog.show(MyService.this,"Please Wait... Orders loading",null,true,true);
	            }

	            @Override
	            protected void onPostExecute(String s) 
	            {
	                super.onPostExecute(s);
	                //loading.dismiss();
	               
	               Log.i("getOrderThatNotready :",s);
	               s=s.trim();
	               
	               if(s.length()>2)
	               {
		               //stillHaveOders=true;
		               onDestroy();
	               }
	               
	                
	            }

	            @Override
	            protected String doInBackground(String... params)
	            {
	                HashMap<String,String> data = new HashMap<String, String>();
	                data.put("userID",params[0]);
	                
	                RegisterUserClass ruc = new RegisterUserClass();

	                String result = ruc.sendPostRequest(VIEW_UNREADY_URL,data);

	                return result;
	            }
	        }
	        UserLoginClass ulc = new UserLoginClass();
	        ulc.execute(ownerID);
	}
	 private void getAllOrder(final String ownerID)
	 {
	        class UserLoginClass extends AsyncTask<String,Void,String>{
				//ProgressDialog loading;
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                //loading = ProgressDialog.show(MyService.this,"Please Wait... Orders loading",null,true,true);
	            }

	            @Override
	            protected void onPostExecute(String s) 
	            {
	                super.onPostExecute(s);
	                //loading.dismiss();
	               
	              // Log.i("getAllOrder :",s);
	               if(s.length()>1)
	               {
		                 String[] elements = s.split("@");
		                   
		                 
		                  for(int x = 0; x < elements.length; x++)
		                  {
		                	  String theData = elements[x];
		                	  
		                	  readyOrders.add(theData);
		                	  
		                  }
		                  
		               
		          
	                }
	                
	            }

	            @Override
	            protected String doInBackground(String... params)
	            {
	                HashMap<String,String> data = new HashMap<String, String>();
	                data.put("userID",params[0]);
	                
	                RegisterUserClass ruc = new RegisterUserClass();

	                String result = ruc.sendPostRequest(VIEW_READY_URL,data);

	                return result;
	            }
	        }
	        UserLoginClass ulc = new UserLoginClass();
	        ulc.execute(ownerID);
	}
}
