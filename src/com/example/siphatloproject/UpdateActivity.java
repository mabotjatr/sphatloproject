package com.example.siphatloproject;

import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siphatloproject.POJO.RegisterUserClass;

public class UpdateActivity extends Activity {

	private EditText edName, edPrice;
	private Button btnUpdate;
	private Validation validation = new Validation();
	private Intent intent = null;
	private static final String UPDATE_MENU_URL =MainActivity.URL+"updateMenu.php";// "http://icep.net76.net/updateMenu.php";
	
	private String menuID;
	private String ownerID;
	private String menuName;
	private String price;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		
		edName = (EditText) findViewById(R.id.edUpName);
		edPrice = (EditText) findViewById(R.id.edUpPrice);
		
		intent = getIntent();
		
		menuID = intent.getStringExtra("menuID");
		ownerID = intent.getStringExtra("ownerID");
		menuName = intent.getStringExtra("menuName");
		price = intent.getStringExtra("price");
		
		edName.setText(menuName);
		edPrice.setText(price);
		
		btnUpdate.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				if(validation.textEmpty(edName.getText().toString()) != true)
				{
					if(validation.isNameSurnameValid(edName.getText().toString()))
					{
						if(validation.textEmpty(edPrice.getText().toString()) != true)
						{
							if(validation.haveLetter(edPrice.getText().toString()))
							{
								updateMenu(menuID, ownerID, edName.getText().toString(), edPrice.getText().toString());
							
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
						edName.setTextColor(Color.RED);
					}
				}
				else
				{
					String mssg = "Plesea provide item name";
					Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
					edName.setHintTextColor(Color.RED);
				}
				
			}
		});
		
	}
	
	private void updateMenu(String menuID, final String ownerID, String menuName, String price) 
	 {
	        class RegisterUser extends AsyncTask<String, Void, String>
	        {
	            ProgressDialog loading;
	           
	            RegisterUserClass ruc=new RegisterUserClass();
	 
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(UpdateActivity.this, "Please Wait...",null, true, true);
	            }
	 
	            @Override
	            protected void onPostExecute(String s)
             {
	                super.onPostExecute(s);
	                loading.dismiss();
	                
	                Log.i("LOG",s);
	                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
	                Intent intent=new Intent(getBaseContext(), ManageItemActivity.class);
	                intent.putExtra("ownerID", ownerID);
	                startActivity(intent);
	            }
	 
	            @Override
	            protected String doInBackground(String... params)
	            {
	 
	                HashMap<String, String> data = new HashMap<String,String>();
	                
	                data.put("menuID",params[0]);
	                data.put("ownerID",params[1]);
	                data.put("menuName",params[2]);
	                data.put("price",params[3]);
	                
	                String result = ruc.sendPostRequest(UPDATE_MENU_URL,data);
	 
	                return  result;
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(menuID, ownerID, menuName, price);
	    }
	 

	
}
