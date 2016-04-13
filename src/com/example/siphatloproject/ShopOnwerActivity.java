package com.example.siphatloproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShopOnwerActivity extends Activity {

	private Button btnAdd, btnManage, btnView;
	private TextView txtMo, txtSpa, txtMenu;
	private Intent intent = null;
	private String id = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_onwer);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		
		intent = getIntent();
		id = intent.getStringExtra("ownerID");
		
		btnAdd = (Button) findViewById(R.id.btnRegShop);
		btnManage = (Button) findViewById(R.id.btnManageMenu);
		
		btnAdd.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				intent = new Intent(ShopOnwerActivity.this, AddMenuActivity.class);
				intent.putExtra("ownerID", id);
				
				startActivity(intent);
				
			}
		});
		
		btnManage.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				intent = new Intent(ShopOnwerActivity.this, ManageItemActivity.class);
				intent.putExtra("ownerID", id);
				startActivity(intent);
				
			}
		});
		
		btnView = (Button) findViewById(R.id.btnViewOrder);
		
		btnView.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				intent = new Intent(ShopOnwerActivity.this, ViewCustomerOrderActivity.class);
				intent.putExtra("ownerID", id);
				startActivity(intent);
			
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_onwer, menu);
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
