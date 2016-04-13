package com.example.siphatloproject;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siphatloproject.R.menu;
import com.example.siphatloproject.POJO.Order;
import com.example.siphatloproject.POJO.RegisterUserClass;

public class ViewCustomerOrderActivity extends Activity {
	
	private static final String VIEW_ORDER_URL =MainActivity.URL+"allOrder.php";// "http://icep.net76.net/allOrder.php";
	private static final String UPDATE_ORDER_URL =MainActivity.URL+"orderReady.php";// "http://icep.net76.net/orderReady.php";
	
	private ArrayList<Order> allOders = new ArrayList<Order>();
	private ListView lv;
	private MyOrderAdapter adapter;
	public static ArrayList<Order> acceptedOrders  = new ArrayList<Order>();
	private Intent i;
	
	Session session = null;
	ProgressDialog pdialog = null;
	Context context = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_customer_order);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("");
		
		Typeface face = Typeface.createFromAsset(getAssets(), "font/kusha.otf");
		
		context = this;
		
		i = getIntent();
		
		final String ownerID = i.getStringExtra("ownerID");
		
		getAllOrder(ownerID);
		
		
		
		lv = (ListView) findViewById(R.id.lvViewItems);
		registerForContextMenu(lv);
		
		/*lv.setOnItemClickListener(new OnItemClickListener()
		{
		    @Override 
		    public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    { 
		    	Order order = allOders.get(position);
		    	createDialog(order, ownerID);
		    }
		});*/
		
		
	}
	
	/*public void createDialog(final Order order, final String ownerID)
	{
        
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		 alertDialogBuilder.setTitle("Make an order");
	     alertDialogBuilder.setMessage("Are you done with " + order.getItemName());
	      
	      alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
	      {
	         @Override
	         public void onClick(DialogInterface arg0, int arg1)
	         {
	 
	        	 String orderID = String.valueOf(order.getOrderId());
	        	 String status = "Accepted";
	        	 
	        	 prepareOrder(ownerID, orderID, status);
	        	 
	        	 acceptedOrders.add(order);
	        	 allOders.remove(order);
	        	 adapter.notifyDataSetChanged();
	       }
	      });
	      
	      alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface dialog, int which)
	         {
	        	 String orderID = String.valueOf(order.getOrderId());
	        	 String status = "Not ready";
	        	 
	        	 prepareOrder(ownerID, orderID, status);
	         }
	      });
	      
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
    }*/

	 private void getAllOrder(final String ownerID)
	 {
	        class UserLoginClass extends AsyncTask<String,Void,String>{
				ProgressDialog loading;
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(ViewCustomerOrderActivity.this,"Please Wait... Orders loading",null,true,true);
	            }

	            @Override
	            protected void onPostExecute(String s) 
	            {
	                super.onPostExecute(s);
	                loading.dismiss();
	               
	               Log.i("",s);
	               if(s.equalsIgnoreCase("1") == false )
	                {
		                 String[] elements = s.split("@");
		                   
		                 
		                  for(int x = 0; x < elements.length; x++)
		                  {
		                	  String theData = elements[x];
		                	  
		                	  if(theData.length() > 1)
		                	  {
		                		 String[] tempElemts = theData.split("#");
		                
			                	 Order order = new Order(Integer.parseInt(tempElemts[0]),Integer.parseInt(tempElemts[1]),Integer.parseInt(tempElemts[2]), tempElemts[3],Integer.parseInt(tempElemts[4]),tempElemts[5], tempElemts[6], tempElemts[7],  Double.parseDouble(tempElemts[8]));
			                	  
			                	 if(order.getStatus().equalsIgnoreCase("Not ready"))
			                	 {
			                		 allOders.add(order);
			                		 
			                	 }
		                	  }
		                	  
		                  }
		                  
		                 adapter = new MyOrderAdapter(getBaseContext(), android.R.layout.simple_dropdown_item_1line, allOders);
		   	          		
			             lv.setAdapter(adapter);
		          
	                }
	                else
	                {
	         
	                    Toast.makeText(ViewCustomerOrderActivity.this,"No items to manage in your shop",Toast.LENGTH_LONG).show();
	                }
	            }

	            @Override
	            protected String doInBackground(String... params)
	            {
	                HashMap<String,String> data = new HashMap<String, String>();
	                data.put("shopID",params[0]);
	                
	                RegisterUserClass ruc = new RegisterUserClass();

	                String result = ruc.sendPostRequest(VIEW_ORDER_URL,data);

	                return result;
	            }
	        }
	        UserLoginClass ulc = new UserLoginClass();
	        ulc.execute(ownerID);
	}
	 
	 private void prepareOrder(String ownerID, String orderID, String status) 
	 {
	        class RegisterUser extends AsyncTask<String, Void, String>
	        {
	            ProgressDialog loading;
	           
	            RegisterUserClass ruc = new RegisterUserClass();
	 
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(ViewCustomerOrderActivity.this, "Please Wait...",null, true, true);
	            }
	 
	            @Override
	            protected void onPostExecute(String s)
                {
	                super.onPostExecute(s);
	                loading.dismiss();
	                
	                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
	                
	            }
	 
	            @Override
	            protected String doInBackground(String... params)
	            {
	 
	                HashMap<String, String> data = new HashMap<String,String>();
	                
	    
	                data.put("ownerID",params[0]);
	                data.put("orderID",params[1]);
	                data.put("status",params[2]);
	                
	                String result = ruc.sendPostRequest(UPDATE_ORDER_URL,data);
	 
	                return  result;
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(ownerID, orderID, status);
	    }
	 
//-------- send email method ------------------------//

	 private void sendAutoEmail()
	 {
		 	Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("mabotjatr@gmail.com", "Action@me");
				}
			});
			
			pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);
			
			RetreiveFeedTask task = new RetreiveFeedTask();
			task.execute();
	 }
	 
	 class RetreiveFeedTask extends AsyncTask<String, Void, String> {

			@Override
			protected String doInBackground(String... params) {
				
				try{
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("mabotjatr@gmail.com"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mabotjatr@gmail.com"));
					message.setSubject("subject");
					message.setContent("textMessage", "text/html; charset=utf-8");
					Transport.send(message);
				} catch(MessagingException e) {
					e.printStackTrace();
				} catch(Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			protected void onPostExecute(String result) {
				pdialog.dismiss();
				//reciep.setText("");
				//msg.setText("");
				//sub.setText("");
				Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
			}
		}
	
	 //-------- send email method end here ------------------------//


	//------------ Context menu thing ---------------------------
	
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		getMenuInflater().inflate(R.menu.accept_reject_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		AdapterView.AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		
		if(R.id.action_accept_order == item.getItemId())
		{

			int index  = menuInfo.position;
			Order order = allOders.get(menuInfo.position);
			
			
       	 	
			i = getIntent();
       	 	final String ownerID = i.getStringExtra("ownerID");
 
       	 	
       	 	
       	 	sendAutoEmail();
       	 	//acceptedOrders.add(menuInfo.position,order);
       	 	//allOders.remove(order);
			//adapter.notifyDataSetChanged();
			
		}
		else if(R.id.action_reject_order == item.getItemId())
		{
			int index  = menuInfo.position;
			Order order = allOders.get(index);
		
			String orderID = String.valueOf(order.getOrderId());
       	 	String status = "Order rejected";
       	 	Intent i = getIntent();
       	 	final String ownerID = i.getStringExtra("ownerID");
       	 	//prepareOrder(ownerID, orderID, status);
       	 	
       	 	//createDialog(order, ownerID);
       	 	sendAutoEmail();
       	 	
			allOders.remove(order);
			adapter.notifyDataSetChanged();
		}
		
		return super.onContextItemSelected(item);
	}

	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_customer_order, menu);
		return true;
	}
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_view_accepted_orders) 
		{
			Intent intent  = new Intent(this, AcceptedOrdersActivity.class);
			startActivity(intent);
			
			return true;
		
		}
		return super.onOptionsItemSelected(item);
	}
}
