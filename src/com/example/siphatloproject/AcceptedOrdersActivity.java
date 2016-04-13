package com.example.siphatloproject;

import com.example.siphatloproject.POJO.Order;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AcceptedOrdersActivity extends Activity {

	private ListView listAcceptedOrders;
	private ArrayAdapter<Order> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accepted_orders);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		setTitle("SPHATLHO");
		listAcceptedOrders  = (ListView) findViewById(R.id.listOfAcceptedOrders);
		
		adapter = new MyOrderAdapter(getBaseContext(), android.R.layout.simple_dropdown_item_1line, ViewCustomerOrderActivity.acceptedOrders);
		listAcceptedOrders.setAdapter(adapter);
		
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accepted_orders, menu);
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
