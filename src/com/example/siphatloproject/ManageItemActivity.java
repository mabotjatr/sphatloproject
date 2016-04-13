package com.example.siphatloproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.example.siphatloproject.POJO.Item;
import com.example.siphatloproject.POJO.RegisterUserClass;

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
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ManageItemActivity extends Activity {

private static final String RETRIEVE_MENU_URL =MainActivity.URL+"allMenu.php";// "http://icep.net76.net/allMenu.php";
private static final String DELETE_MENU_URL = MainActivity.URL+"deleteMenu.php";//"http://icep.net76.net/deleteMenu.php";
private ArrayList<Item> allItems = new ArrayList<Item>();
private ItemAdapter adapter;
private ListView lv;
private Intent intent;
private String ownerID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_item);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		Typeface font = Typeface.createFromAsset(getAssets(), "font/kusha.otf");
		
		lv = (ListView) findViewById(R.id.lvViewItems);
		
		intent = getIntent();
		
		ownerID = intent.getStringExtra("ownerID");
		
		getAllMenu(ownerID);
		
		
		registerForContextMenu(lv);
		
	}

	 private void getAllMenu(final String ownerID)
	 {
	        class UserLoginClass extends AsyncTask<String,Void,String>{
				ProgressDialog loading;
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(ManageItemActivity.this,"Please Wait",null,true,true);
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
		                		  String [] tempElemts = theData.split("#");
			                
			                	  Item item = new Item(Integer.parseInt(tempElemts[0]), tempElemts[1], Double.parseDouble(tempElemts[2]));
			                	  
			                	  allItems.add(item);
			                	  
		                		  
		                	  }
		                	  
		                  }
		                  
		                  adapter = new ItemAdapter(getBaseContext(), android.R.layout.simple_dropdown_item_1line, allItems);
		   	          		
			              lv.setAdapter(adapter);
		                  
			             lv.setOnLongClickListener(new OnLongClickListener() 
			     		 {
			     			    public boolean onLongClick(View arg0)
			     			    {
			     			    	Toast.makeText(getApplicationContext(), "Long Clicked " ,Toast.LENGTH_SHORT).show();

			     			        return true;    
			     			    }
			     		});
		          
	                }
	                else
	                {
	         
	                    Toast.makeText(ManageItemActivity.this,"No items to manage in your shop",Toast.LENGTH_LONG).show();
	                }
	            }

	            @Override
	            protected String doInBackground(String... params)
	            {
	                HashMap<String,String> data = new HashMap<String, String>();
	                data.put("shopID",params[0]);
	                
	                RegisterUserClass ruc = new RegisterUserClass();

	                String result = ruc.sendPostRequest(RETRIEVE_MENU_URL,data);

	                return result;
	            }
	        }
	        UserLoginClass ulc = new UserLoginClass();
	        ulc.execute(ownerID);
	    }
	 
	 private void deleteMenu(String menuID, String ownerID) 
	 {
	        class RegisterUser extends AsyncTask<String, Void, String>
	        {
	            ProgressDialog loading;
	           
	            RegisterUserClass ruc=new RegisterUserClass();
	 
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(ManageItemActivity.this, "Please Wait...",null, true, true);
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
	                
	                data.put("menuID",params[0]);
	                data.put("ownerID",params[1]);
	                
	                String result = ruc.sendPostRequest(DELETE_MENU_URL,data);
	 
	                return  result;
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(menuID, ownerID);
	    }
	 
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_item, menu);
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
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) 
	{
		getMenuInflater().inflate(R.menu.manage_item, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		int position = 0;
		
    	AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
    	position = menuInfo.position;
        int id = item.getItemId();
        
        Item i = allItems.get(position);
        
        String menuID = String.valueOf(i.getId());
        String ownerID = intent.getStringExtra("ownerID");
        String menuName = String.valueOf(i.getItemDescription());
        String price = String.valueOf(i.getItemPrice());
        
    	if(id == R.id.action_delete)
    	{
    		deleteMenu(menuID,ownerID);
    		allItems.remove(position);
    		adapter.notifyDataSetChanged();
    	}
    	else if(id == R.id.action_edit)
    	{
    		intent = new Intent(ManageItemActivity.this, UpdateActivity.class);
    		
    		
    		intent.putExtra("ownerID", ownerID);
    		intent.putExtra("menuID", menuID);
    		intent.putExtra("menuName", menuName);
    		intent.putExtra("price", price);
    		
    		startActivity(intent);
    		
    	}
    	
		return super.onContextItemSelected(item);
	}
	
	
}
