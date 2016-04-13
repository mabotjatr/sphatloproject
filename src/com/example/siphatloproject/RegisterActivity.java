package com.example.siphatloproject;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	private Button btnNext;
	private EditText edUsername, edPassword, edConfirm;
	private Intent intent = null;
	private Spinner mySpinner;
	private String[] roles;
	private Validation validation = new Validation();

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		
		btnNext = (Button) findViewById(R.id.btnRegShop);
		edUsername = (EditText) findViewById(R.id.edOwnPhone);
		edPassword = (EditText) findViewById(R.id.edPas);
		edConfirm = (EditText) findViewById(R.id.edConfirm);
		
		
		roles = new String[3];
		roles[0]= "-------------------Choose Role-------------------";
		roles[1]= "Shop Owner";
		roles[2]= "Customer";
	
		mySpinner = (Spinner) findViewById(R.id.spinner1);
		MyCustomAdapter adapter = new MyCustomAdapter(getBaseContext(), R.layout.the_spin, roles);
		mySpinner.setAdapter(adapter);
		
		
		btnNext.setOnClickListener(this);
		Typeface myFont = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		
		btnNext.setTypeface(myFont);
		
		Typeface fonts = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		edUsername.setTypeface(fonts);
		edConfirm.setTypeface(fonts);
		
		Typeface fontse = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		edPassword.setTypeface(fontse);
		
		
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	public void onClick(View v) 
	{
		String role = (String) mySpinner.getSelectedItem();
		
		
		
		if(role.equalsIgnoreCase("Customer"))
		{
			intent = new Intent(RegisterActivity.this, RegisterCustomerActivity.class);
		}
		else if(role.equalsIgnoreCase("Shop Owner"))
		{
			intent = new Intent(RegisterActivity.this, RegisterShopActivity.class);
		}
		
		
	if(validation.textEmpty(edUsername.getText().toString()) != true)
		{
			if(validation.textEmpty(edPassword.getText().toString()) != true)
			{
				if(validation.textEmpty(edConfirm.getText().toString()) != true)
				{
					if((role.equalsIgnoreCase("--Choose role--")))
					{
						Toast.makeText(getBaseContext(), "Please choose role", Toast.LENGTH_LONG).show();
					}
					else
					{
						if(validation.isPasswordValid(edPassword.getText().toString()))
						{
							if(validation.isPasswdConfirmPasswdTheSame(edPassword.getText().toString(), edConfirm.getText().toString()))
							{
								
								Bundle bundle=new Bundle();
								
								String password=edPassword.getText().toString();
								String username=edUsername.getText().toString();
								
								bundle.putString("role", role);
								bundle.putString("password", password);
								bundle.putString("username", username);
								//Toast.makeText(getBaseContext(), "User :"+username+" Pass :"+password+" Role :"+role, Toast.LENGTH_LONG).show();
								
								intent.putExtra("bundle", bundle);
										
								startActivity(intent);
								
								
								
							}
							else
							{
								String mssg="Password and Confirm password must be the same";
								Toast.makeText(getBaseContext(),mssg, Toast.LENGTH_LONG).show();
								edPassword.setHintTextColor(Color.RED);
								edConfirm.setHintTextColor(Color.RED);
							}
							
						}
						else
						{
							String mssg="Please provide strong password\n" +
									"\n" +
									"Note :your password must be 5 or more character long and must contain both latters and numbers";
							Toast.makeText(getBaseContext(),mssg, Toast.LENGTH_LONG).show();
							edPassword.setHintTextColor(Color.RED);
						}
						
					}
				}
				else
				{
					Toast.makeText(getBaseContext(), "Password is Empty", Toast.LENGTH_LONG).show();

					edConfirm.setHintTextColor(Color.RED);
				}
			}
			else
			{
				Toast.makeText(getBaseContext(), "Password is Empty", Toast.LENGTH_LONG).show();

				edPassword.setHintTextColor(Color.RED);
			}
		}
		else
		{
			Toast.makeText(getBaseContext(), "Username is Empty", Toast.LENGTH_LONG).show();

			edUsername.setHintTextColor(Color.RED);
		}
		
	}
	public class MyCustomAdapter extends ArrayAdapter<String>
	{
		 public MyCustomAdapter(Context context, int textViewResourceId,
				    String[] objects) {
				   super(context, textViewResourceId, objects);
				   // TODO Auto-generated constructor stub
				  }

				  @Override
				  public View getDropDownView(int position, View convertView,
				    ViewGroup parent) {
				   // TODO Auto-generated method stub
				   return getCustomView(position, convertView, parent);
				  }

				  @Override
				  public View getView(int position, View convertView, ViewGroup parent) {
				   // TODO Auto-generated method stub
				   return getCustomView(position, convertView, parent);
				  }

				  public View getCustomView(int position, View convertView, ViewGroup parent) {
				   // TODO Auto-generated method stub
				   //return super.getView(position, convertView, parent);

				   LayoutInflater inflater = getLayoutInflater();
				   View row=inflater.inflate(R.layout.the_spin, parent, false);
				   TextView label=(TextView)row.findViewById(R.id.role);
				   label.setText(roles[position]); 
				   return row;
				  } 
	}
}
