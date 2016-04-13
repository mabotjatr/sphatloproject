package com.example.siphatloproject;


import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siphatloproject.POJO.RegisterUserClass;


public class MainActivity extends Activity implements OnClickListener {
	
	private TextView textName;
	private TextView txtMoto;
	
	private Button btnLogin;
	private Button btnRegister;
	private EditText edUser;
	private EditText edPass;
	//private static final String LOGIN_URL = "http://icep.net76.net/dbConnect.php";//C:\xampp\htdocs\PHP
	//public static final String URL = "http://168.172.186.71/SpathoFile/";//login.php";//C:\xampp\htdocs\PHP//httppost = new HttpPost("http://10.0.2.2/project/insert.php");
	public static final String URL = "http://168.172.188.103/SpathoFile/";
	//public static final String URL ="http://icep.net76.net/";
	private String LOGIN_URL=URL+"login.php";
	public static final String USER_NAME = "name";

	
	private Validation validation = new Validation();
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      //ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        
        setTitle("SPHATLHO");
        
		textName = (TextView) findViewById(R.id.txtName);
		Typeface font = Typeface.createFromAsset(this.getAssets(), "font/kusha.otf");
		textName.setTypeface(font);
		
		txtMoto = (TextView) findViewById(R.id.txtMoto);
		Typeface myFont = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		txtMoto.setTypeface(myFont,Typeface.BOLD);
		
		btnLogin =  (Button) findViewById(R.id.btnLogin);
		Typeface fonts = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		btnLogin.setTypeface(fonts);
		
		btnRegister =  (Button) findViewById(R.id.btnRegister);
		Typeface fon = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		btnRegister.setTypeface(fon);
		
		edUser =  (EditText) findViewById(R.id.edUser);
		Typeface custFont = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		edUser.setTypeface(custFont);
		
		edPass =  (EditText) findViewById(R.id.edPass);
		Typeface custF = Typeface.createFromAsset(this.getAssets(), "font/font.ttf");
		edPass.setTypeface(custF);
		// Toast.makeText(MainActivity.this,"BBBBBBBBBBBBBBBBBB",Toast.LENGTH_LONG).show();
		
		
    }
  
    private void login()
    {
        String username = edUser.getText().toString().trim();
        String password = edPass.getText().toString().trim();
        userLogin(username,password);
    }

    private void userLogin(final String username, final String password)
    {
        class UserLoginClass extends AsyncTask<String,Void,String>{
			ProgressDialog loading;
            @Override
            protected void onPreExecute() 
            {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                if(s.length()>2)
                {
                   
                    String[] element = s.split("#");
                    String role = element[element.length-1];
                    
                    if(role.length() == 9)
                    {
                    	Intent intent = new Intent(MainActivity.this,CustomerFunctionActivity.class);
                   	    intent.putExtra("user", s);
                   	    startActivity(intent); 
                    }
                    else 
                    {
	                	 Intent intent = new Intent(MainActivity.this,ShopOnwerActivity.class);
	                	 intent.putExtra("ownerID", element[0]);
	                	 startActivity(intent);
                    }
                    
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid login details, please rectify...!!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params)
            {
                HashMap<String,String> data = new HashMap<String, String>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
		Intent intent = null;
		
		if(((Button)v.findViewById(R.id.btnLogin) == btnLogin))
		{
			if(validation.textEmpty(edUser.getText().toString()) != true)
			{
				if(validation.textEmpty(edPass.getText().toString()) != true)
				{
					
					login();
				}
				else
				{
					Toast.makeText(getBaseContext(), "Password is Empty", Toast.LENGTH_LONG).show();

					edPass.setHintTextColor(Color.RED);
				}
			}
			else 
			{
				Toast.makeText(getBaseContext(), "Username is Empty", Toast.LENGTH_LONG).show();

				edUser.setHintTextColor(Color.RED);
			}
		
		}
		else if(((Button)v.findViewById(R.id.btnRegister) == btnRegister))
		{
			intent = new Intent(MainActivity.this, RegisterActivity.class);
			startActivity(intent);
		}
		
		
		
	}
	
	
}
