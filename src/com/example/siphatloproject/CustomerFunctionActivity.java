package com.example.siphatloproject;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.MailTo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siphatloproject.POJO.RegisterUserClass;
import com.example.siphatloproject.POJO.Shop;

public class CustomerFunctionActivity extends Activity {

private static final String RETRIEVE_MENU_URL = MainActivity.URL+"getShopNames.php";
private ArrayList<Shop> shops = new ArrayList<Shop>();
private ShopAdapter adapter;
private ListView lv;
private String user="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_function);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		
		
		
		
		Intent intent=getIntent();
		
		user=intent.getStringExtra("user");
		lv = (ListView) findViewById(R.id.lvItems);
		displayShops();
		lv.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int poss,long arg3) 
			{
				Shop shop=shops.get(poss);
				
				String shopId=shop.getOwnerID();
				
				Intent intent=new Intent(getBaseContext(), OrderActivity.class);
				intent.putExtra("shopId", shopId);
				intent.putExtra("user", user);
				startActivity(intent);
				
			}
		});
	}
	 private void displayShops()
	 {
	        class UserLoginClass extends AsyncTask<String,Void,String>
	        {
				ProgressDialog loading;
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(CustomerFunctionActivity.this,"Please Wait...",null,true,true);
	            }

	            @Override
	            protected void onPostExecute(String s) 
	            {
	                super.onPostExecute(s);
	                loading.dismiss();

	                if(s.equalsIgnoreCase("1") == false )
	                {
		                  String[] elements = s.split("@");
		                  for(int x = 0; x < elements.length; x++)
		                  {
		                	
		                	  String theData=elements[x];
		                	  if(theData.length()>1)
		                	  {
		                		 
		                		  String [] tempElemts=theData.split("#");
		                		  String ownerID=tempElemts[0];
		                		  String shopName=tempElemts[1];
		                		  String address=tempElemts[2];
		                		  Shop shop=new Shop(ownerID, shopName, address);
			                	  shops.add(shop);
			                	  
		                		  
		                	  }
		                	  
		                  }
		                  adapter = new ShopAdapter(getBaseContext(), android.R.layout.simple_dropdown_item_1line, shops);
			              lv.setAdapter(adapter);
	          
	                }
	                else
	                {
	                	
	                    Toast.makeText(CustomerFunctionActivity.this,"No shops available...!!!",Toast.LENGTH_LONG).show();
	                }
	            }

	            @Override
	            protected String doInBackground(String... params)
	            {
	                HashMap<String,String> data = new HashMap<String, String>();
	                //data.put("shopID",params[0]);
	                
	                RegisterUserClass ruc = new RegisterUserClass();

	                String result = ruc.sendPostRequest(RETRIEVE_MENU_URL,data);

	                return result;
	            }
	        }
	        UserLoginClass ulc = new UserLoginClass();
	        ulc.execute();
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_function, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
