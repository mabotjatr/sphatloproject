package com.example.siphatloproject;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewReadyOrderActivity extends Activity {
	ListView listViewReadyOrders;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ready_order);
		
		//ACTION BAR COLOR
    	ActionBar actionBar = getActionBar();
    	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

		listViewReadyOrders =(ListView)findViewById(R.id.listViewReadyOrders);
		Intent intent=getIntent();
		ArrayList<String> mssg=intent.getStringArrayListExtra("mssg");
		
		ArrayAdapter<String> adapter =new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, mssg);
		listViewReadyOrders.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_ready_order, menu);
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
