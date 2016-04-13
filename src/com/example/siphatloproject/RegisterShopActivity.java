package com.example.siphatloproject;

import java.util.HashMap;

import com.example.siphatloproject.POJO.Login;
import com.example.siphatloproject.POJO.Person;
import com.example.siphatloproject.POJO.RegisterUserClass;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
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

public class RegisterShopActivity extends Activity {

	private Button btnRegShop;
	private Validation validation = new Validation();
	private EditText edName, edSurname, edPhoneNO, edShopName, edAddress,edEmail;
	private TextView txtSelect;
	private ProgressDialog progressDialog;
	String role="";
	String username="";
	String password="";
	private static final String REGISTER_URL =MainActivity.URL+"registerShopOwner.php";// "http://icep.net76.net/registerShopOwner.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_shop);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		
		Typeface font = Typeface.createFromAsset(this.getAssets(),"font/font.ttf");
		Typeface fonts = Typeface.createFromAsset(this.getAssets(),"font/kusha.otf");
		
		Intent intent=getIntent();
		
		Bundle bundle=new Bundle();
		bundle=intent.getBundleExtra("bundle");
		
		role=bundle.getString("role");
		password=bundle.getString("password");
		username=bundle.getString("username");
		
		//Toast.makeText(getBaseContext(), "User :"+username+" Pass :"+password+" Role :"+role, Toast.LENGTH_LONG).show();
		
		txtSelect = (TextView) findViewById(R.id.txtWelcom);
		
		
		btnRegShop = (Button) findViewById(R.id.btnRegShop);
		btnRegShop.setTypeface(font);
		
		edName = (EditText) findViewById(R.id.edOwName);
		edName.setTypeface(font);
		
		edSurname = (EditText) findViewById(R.id.edOwnSurn);
		edSurname.setTypeface(font);
		
		edPhoneNO = (EditText) findViewById(R.id.edOwnPhone);
		edPhoneNO.setTypeface(font);
		
		edEmail = (EditText) findViewById(R.id.edShopEmail);
		edEmail.setTypeface(font);
		
		edShopName = (EditText) findViewById(R.id.edShopName);
		edShopName.setTypeface(font);
		
		edAddress = (EditText) findViewById(R.id.edAddress);
		edAddress.setTypeface(font);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setTitle("Processing data");
		progressDialog.setMessage("Please wait...");
		
		btnRegShop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(validation.textEmpty(edName.getText().toString()) != true)
				{
					if(validation.textEmpty(edSurname.getText().toString()) != true)
					{
						if(validation.textEmpty(edPhoneNO.getText().toString()) != true)
						{
							if(validation.isPhoneValid(edPhoneNO.getText().toString()) == true)
							{
								if(validation.textEmpty(edShopName.getText().toString()) != true)
								{
									
									if(validation.textEmpty(edAddress.getText().toString()) != true)
									{
										if(validation.isNameSurnameValid(edName.getText().toString()))
										{
											if(validation.isNameSurnameValid(edSurname.getText().toString()))
											{
												if((edShopName.getText().toString()).length()>1)
												{
													if((edAddress.getText().toString()).length()>1)
													{
														if(validation.isEmailValid(edEmail.getText().toString()))
														{
															progressDialog.show();
															
															String name=edName.getText().toString();
															String lastname=edSurname.getText().toString();
															String phone=edPhoneNO.getText().toString();
															String email=edEmail.getText().toString();
															
															String shopName=edShopName.getText().toString();
															String address=edAddress.getText().toString();
															
															// Person(int personID, String name, String lastname, String email,String phoneNO, String role, String username, String password)
															//Person(int personID, String name, String lastname, String email,String phoneNO)
															register( name,  lastname,  phone,  email,shopName, address, username, password, role) ;
															//Person person=new Person(0, name, surname, email, phoneNum);
															Login login =new Login(0, username, password, role);
															/////////////////////////////////////////////////
																//SAVE TO DB
															////////////////////////////////////////////////
															Toast.makeText(getBaseContext(), edShopName.getText().toString() + " shop successfully registered", Toast.LENGTH_LONG).show();
															startActivity(new Intent(RegisterShopActivity.this, MainActivity.class));
															
															
														}
														else
														{
															
															String mssg="Please provide valid email address\n";
																	
															Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
															
															edEmail.setHintTextColor(Color.RED);
														}
														
														
														
													}
													else
													{
														String mssg="Plesea provide valid address";
														Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
														
														edAddress.setHintTextColor(Color.RED);
														
													}
													
												}
												else
												{
													String mssg="Please provide valid shop name";
													Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
													
													edShopName.setHintTextColor(Color.RED);
													
												}
												
											}
											else
											{
												String mssg="Please provide valid surname \n" +
														"Note : you surname must contain latters only and it must be 2 or more chararecter long";
												Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
												
												edSurname.setHintTextColor(Color.RED);
												edSurname.setBackgroundColor(Color.RED);
												
											}
											
										}
										else
										{
											String mssg="Please provide valid name \n" +
													"Note : you name must contain latters only and it must be 2 or more chararecter long";
											Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
											
											edName.setHintTextColor(Color.RED);
											
										}
										
									}
									else
									{
										Toast.makeText(getBaseContext(), "Address is Empty", Toast.LENGTH_LONG).show();
	
										edAddress.setHintTextColor(Color.RED);
									}
								}
								else
								{
									Toast.makeText(getBaseContext(), "Shop name is empty", Toast.LENGTH_LONG).show();

									edPhoneNO.setHintTextColor(Color.RED);
								}
							}
							else
							{
								Toast.makeText(getBaseContext(), "Phone number is invalid", Toast.LENGTH_LONG).show();

								edShopName.setHintTextColor(Color.RED);
							}
						}
						else
						{
							Toast.makeText(getBaseContext(), "Phone number is Empty", Toast.LENGTH_LONG).show();

							edPhoneNO.setHintTextColor(Color.RED);
						}
					}
					else
					{
						Toast.makeText(getBaseContext(), "Surname is Empty", Toast.LENGTH_LONG).show();

						edSurname.setHintTextColor(Color.RED);
					}
				}
				else
				{
					Toast.makeText(getBaseContext(), "Name is Empty", Toast.LENGTH_LONG).show();

					edName.setHintTextColor(Color.RED);
				}
				
				
			}
			
		});
	}
	   private void register(String name, String lastname, String phone, String email,String shopName ,String address,String username,String password,String role) 
	   {
	        class RegisterUser extends AsyncTask<String, Void, String>
	        {
	            ProgressDialog loading;
	           
	            RegisterUserClass ruc=new RegisterUserClass();
	 
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(RegisterShopActivity.this, "Please Wait...",null, true, true);
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
	                data.put("name",params[0]);
	                data.put("lastname",params[1]);
	                data.put("phone",params[2]);
	                data.put("email",params[3]);
	                data.put("shopName",params[4]);
	                data.put("address",params[5]);
	                
	                data.put("username",params[6]);
	                data.put("password",params[7]);
	                data.put("role",params[8]);
	 
	                String result = ruc.sendPostRequest(REGISTER_URL,data);
	 
	                return  result;
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(name,lastname,phone,email,shopName,address,username,password,role);
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_shop, menu);
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
