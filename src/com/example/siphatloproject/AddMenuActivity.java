package com.example.siphatloproject;

import java.util.HashMap;

import com.example.siphatloproject.POJO.RegisterUserClass;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData.Item;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMenuActivity extends Activity {

	private Button btnaddItem;
	private EditText edItem, edPrice;
	private Validation validation = new Validation();
	
	private static final String ADD_MENU_URL = MainActivity.URL+"addMenu.php";
	
	private Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_menu);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		
		Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
		Typeface fonts = Typeface.createFromAsset(getAssets(), "font/kusha.otf");
		
		edItem = (EditText) findViewById(R.id.edOwName);
		edItem.setTypeface(font);
		
		edPrice = (EditText) findViewById(R.id.edOwnSurn);
		edPrice.setTypeface(font);
		
		btnaddItem = (Button) findViewById(R.id.btnRegShop);
		btnaddItem.setTypeface(font);
		
		
	
		intent = getIntent();
		
	
		
		btnaddItem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				if(validation.textEmpty(edItem.getText().toString()) != true)
				{
					if(validation.isNameSurnameValid(edItem.getText().toString()))
					{
						if(validation.textEmpty(edPrice.getText().toString()) != true)
						{
							if(validation.haveLetter(edPrice.getText().toString()))
							{
								String ownerID = intent.getStringExtra("ownerID");
								
								addMenu(edItem.getText().toString(), edPrice.getText().toString(), ownerID);
								edItem.setText("");
								edPrice.setText("");
								
							}
							else
							{
								String mssg = "Plesea provide valid item price";
								Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
								
								edPrice.setTextColor(Color.RED);
							}
						}
						else
						{
							String mssg = "Plesea enter price of item";
							Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
							edPrice.setHintTextColor(Color.RED);
						}
					}
					else
					{
						String mssg = "Plesea provide valid item name, your item name contains numbers";
						Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
						edItem.setTextColor(Color.RED);
					}
				}
				else
				{
					String mssg = "Plesea provide item name";
					Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
					edItem.setHintTextColor(Color.RED);
				}
				
				
			}
		});
	}

	private void addMenu(String itemName, String price, String ownerID) 
	   {
	        class RegisterUser extends AsyncTask<String, Void, String>
	        {
	            ProgressDialog loading;
	           
	            RegisterUserClass ruc=new RegisterUserClass();
	 
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(AddMenuActivity.this, "Please Wait...",null, true, true);
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
	                
	                data.put("itemName",params[0]);
	                data.put("price",params[1]);
	                data.put("ownerID",params[2]);
	             
	                String result = ruc.sendPostRequest(ADD_MENU_URL,data);
	 
	                return  result;
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(itemName, price, ownerID);
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_menu, menu);
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
