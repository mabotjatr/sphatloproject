package com.example.siphatloproject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.siphatloproject.POJO.DateTime;
import com.example.siphatloproject.POJO.Item;
import com.example.siphatloproject.POJO.Order;
import com.example.siphatloproject.POJO.RegisterUserClass;

public class OrderActivity extends Activity
{
	
private Button btnClear, btnSubmit;
private TextView txtMenu, txtOrder;
private String shopOwnerId="";
private Button btnOrder;

private MyService myService;

private static final String RETRIEVE_MENU_URL =MainActivity.URL+"allMenu.php";// "http://icep.net76.net/allMenu.php";
private ArrayList<Item> allItems = new ArrayList<Item>();
private ArrayList<Item> ordereItem = new ArrayList<Item>();
private ItemAdapter adapter;
private OrderAdapter adapters;
private ListView lv;
private List<Integer> itempositions = new ArrayList<Integer>();
String ord;
String result = "Selected Product are :";
double totalAmount =0.0;
public static  String user="";

ArrayList<Item> products = new ArrayList<Item>();
ListAdapter boxAdapter;
String[] token = {};

private Date date;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		
		Typeface fonts = Typeface.createFromAsset(getAssets(), "font/font.ttf");
		Typeface font = Typeface.createFromAsset(getAssets(), "font/kusha.otf");
		
		
		
		Intent intent=getIntent();
		shopOwnerId=intent.getStringExtra("shopId");
		user=intent.getStringExtra("user");
		
		
		
        lv = (ListView) findViewById(R.id.lvItems);
		getAllMenu(shopOwnerId);
	
		
	
	}
	public void statService()
	{
		startService(new Intent(this, MyService.class));
	}
	 private void getAllMenu(final String ownerID)
	 {
	        class UserLoginClass extends AsyncTask<String,Void,String>{
				ProgressDialog loading;
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(OrderActivity.this,"Please Wait",null,true,true);
	            }

	            @Override
	            protected void onPostExecute(String s) 
	            {
	                super.onPostExecute(s);
	                loading.dismiss();
	                
	                
	                if(s.equalsIgnoreCase("1") == false )
	                {
		                  String[] elements = s.split("@");
		                 
		                  
		                  Vector<String> data=new Vector<String>();
		                  
		                  for(int x = 0; x < elements.length; x++)
		                  {
		                	  String theData=elements[x];
		                	  if(theData.length()>1)
		                	  {
		                		  
		                		  String [] tempElemts=theData.split("#");
			                	
			                	  Item item = new Item(Integer.parseInt(tempElemts[0]), tempElemts[1], Double.parseDouble(tempElemts[2]));
			                	
			                	  allItems.add(item);
			                	  
		                		  
		                	  }
		                	  
		                	  boxAdapter = new ListAdapter(getBaseContext(), allItems);
		              	    lv.setAdapter(boxAdapter);
		                	  
		                	  
		                  }
		               
		                  
		                 for(int x = 0; x < token.length; x++)
		                  {
		                	  String[] data1 = token[x].split("#");
		                	  
		                	  Toast.makeText(OrderActivity.this,data1[x],Toast.LENGTH_LONG).show();
		                	  
		                	  Item item = new Item(Integer.parseInt(data1[0]), data1[1],Float.parseFloat(data1[2]));
		                	   
				             allItems.add(item);
		                  }
			             
		              
	          
	                }
	                else
	                {
	            
	                    Toast.makeText(OrderActivity.this,"No items to manage in your shop",Toast.LENGTH_LONG).show();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
		return true;
	}

	  int displayCount=0;
	 private void register(String custId, String itemID, String fk_owner_id, String date,String numItem,String status) 
	 {
		  	displayCount=0;
	        class RegisterUser extends AsyncTask<String, Void, String>
	        {
	            ProgressDialog loading;
	           
	            RegisterUserClass ruc=new RegisterUserClass();
	 
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(OrderActivity.this, "Please Wait...",null, true, true);
	            }
	 
	            @Override
	            protected void onPostExecute(String s)
             {
	                super.onPostExecute(s);
	                loading.dismiss();
	                if(displayCount<=0)
	                {
	                	 Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
	                	 displayCount++;
	                }
	               
	            }
	 
	            @Override
	            protected String doInBackground(String... params)
	            {
	 
	                HashMap<String, String> data = new HashMap<String,String>();
	                data.put("custId",params[0]);
	                data.put("itemID",params[1]);
	                data.put("fk_owner_id",params[2]);
	                data.put("date",params[3]);
	                data.put("numItem",params[4]);
	                data.put("status",params[5]);
	               
	 
	                String result = ruc.sendPostRequest(MainActivity.URL+"addOrder.php",data);
	 
	                return  result;
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(custId,itemID,fk_owner_id,date,numItem,status);
	 }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	
		int id = item.getItemId();
		if (id == R.id.action_order) 
		{
			 addOrderedItem();
			
			if(ordereItem.size()>0)
			{
				createDialog();
				
			}
			else
			{
				
				Toast.makeText(getBaseContext(), "Please select item from the list...!!",Toast.LENGTH_LONG).show();
			}
			
			
		}
		
		return super.onOptionsItemSelected(item);
	}
	public void createDialog()
	{
      
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		 alertDialogBuilder.setTitle("Submit Order(s)");
	     alertDialogBuilder.setMessage("Totat Price: R " +totalAmount+"\n"+" Are you sure you want to place an order for the selected item(s) ?");
	      
	      alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
	      {
	         @Override
	         public void onClick(DialogInterface arg0, int arg1)
	         {
	        	String[] userElements=user.split("#");
	 			DateTime dateTime=new DateTime();
	 			
	 			String currDate=dateTime.getDate();
	 			String custId=userElements[0];
	 			String status="Not Ready";
	 			
	 			
	 			for(int x=0;x<ordereItem.size();x++)
	 			{
	 				Item theItem=ordereItem.get(x);
	 				String itemId=String.valueOf(theItem.getId());
	 			    register( custId,  itemId,  shopOwnerId,currDate,"1", status) ;
	 			    
	 			}
	 			statService();
	 			totalAmount=0;
	 			ordereItem.clear();
	 			Intent intent = new Intent(OrderActivity.this,CustomerFunctionActivity.class);
           	    intent.putExtra("user", user);
           	    startActivity(intent); 
	        	
	         }
	      });
	      
	      alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface dialog, int which)
	         {
	        	totalAmount=0; 
	         }
	      });
	      
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
	    
    }
	  public void addOrderedItem()
	  {  
		    double totalAmount=0;

		    for (Item item : boxAdapter.getBox())
		    {
		      if (item.box)
		      {
		        ordereItem.add(item);
		        this.totalAmount +=item.getItemPrice();
		      }
		    }
		  
	  }
}
