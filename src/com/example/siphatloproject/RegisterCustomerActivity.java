package com.example.siphatloproject;

import java.util.HashMap;

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

import com.example.siphatloproject.POJO.Encryption;
import com.example.siphatloproject.POJO.Login;
import com.example.siphatloproject.POJO.Person;
import com.example.siphatloproject.POJO.RegisterUserClass;

public class RegisterCustomerActivity extends Activity {

	private Button btnRegCustomer;
	private Validation validation = new Validation();
	private EditText edName, edSurname, edPhoneNO,edEmail;
	private ProgressDialog progressDialog;
	
	
	
	
	private static final String REGISTER_URL = MainActivity.URL+"registerCustomer.php";//"http://icep.net76.net/registerCustomer.php";
	String role="";
	String username="";
	String password="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_customer);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		
		Typeface font = Typeface.createFromAsset(this.getAssets(), "font/kusha.otf");
		Typeface fonts = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		
		Intent intent=getIntent();
		
		Bundle bundle=new Bundle();
		bundle=intent.getBundleExtra("bundle");
		
		role=bundle.getString("role");
		password=bundle.getString("password");
		username=bundle.getString("username");
		
		//Toast.makeText(getBaseContext(), "User :"+username+" Pass :"+password+" Role :"+role, Toast.LENGTH_LONG).show();
		btnRegCustomer = (Button) findViewById(R.id.btnRegShop);
		btnRegCustomer.setTypeface(fonts);
		
		
		
		
		
		
		edName = (EditText) findViewById(R.id.edOwName);
		edName.setTypeface(fonts);
		edSurname = (EditText) findViewById(R.id.edOwnSurn);
		edSurname.setTypeface(fonts);
		edPhoneNO = (EditText) findViewById(R.id.edOwnPhone);
		edEmail = (EditText) findViewById(R.id.edCusEmail);
		edPhoneNO.setTypeface(fonts);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(true);
		progressDialog.setTitle("Processing data");
		progressDialog.setMessage("Please wait...");
		
		btnRegCustomer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(validation.textEmpty(edName.getText().toString()) != true )
				{
					if(validation.textEmpty(edSurname.getText().toString()) != true)
					{
						
						if(validation.textEmpty(edPhoneNO.getText().toString()) != true)
						{
							if(validation.isNameSurnameValid(edName.getText().toString()))
							{
								if(validation.isNameSurnameValid(edSurname.getText().toString()))
								{
									if(validation.isPhoneValid(edPhoneNO.getText().toString()))
									{
										if(validation.isEmailValid(edEmail.getText().toString()))
										{
											
											
											String name=edName.getText().toString();
											String lastname=edSurname.getText().toString();
											String phoneNum=edPhoneNO.getText().toString();
											String email=edEmail.getText().toString();
											// Person(int personID, String name, String lastname, String email,String phoneNO, String role, String username, String password)
											//Person(int personID, String name, String lastname, String email,String phoneNO)
											
											Person person=new Person(0, name, lastname, email, phoneNum);
											Encryption encryption=new Encryption();
											register(name, lastname, phoneNum, email,username,password,role);
											Login login =new Login(0, username, password, role);
											
											/////////////////////////////////////////////////
												//SAVE TO DB
											////////////////////////////////////////////////
											Toast.makeText(getBaseContext(), edName.getText().toString() + " successfully registered", Toast.LENGTH_LONG).show();
											
											startActivity(new Intent(RegisterCustomerActivity.this, MainActivity.class));
											
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
										String mssg="Please provide valid phone number\n" +
												"Note your phone number must start with zero(0) and must be 10 digits long";
												
										Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
										
										edPhoneNO.setHintTextColor(Color.RED);
										
									}
									
								}
								else
								{
									String mssg="Please provide valid name \n" +
											"Note : you surname must contain latters only and it must be 2 or more chararecter long";
									Toast.makeText(getBaseContext(), mssg, Toast.LENGTH_LONG).show();
									
									edSurname.setHintTextColor(Color.RED);
									
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
	
	   private void register(String name, String lastname, String phone, String email,String username,String password,String role) 
	   {
	        class RegisterUser extends AsyncTask<String, Void, String>
	        {
	            ProgressDialog loading;
	           
	            RegisterUserClass ruc=new RegisterUserClass();
	 
	            @Override
	            protected void onPreExecute() 
	            {
	                super.onPreExecute();
	                loading = ProgressDialog.show(RegisterCustomerActivity.this, "Please Wait...",null, true, true);
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
	                
	                data.put("username",params[4]);
	                data.put("password",params[5]);
	                data.put("role",params[6]);
	 
	                String result = ruc.sendPostRequest(REGISTER_URL,data);
	 
	                return  result;
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(name,lastname,phone,email,username,password,role);
	    }
	   
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_customer, menu);
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
