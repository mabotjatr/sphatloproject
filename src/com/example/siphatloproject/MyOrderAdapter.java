package com.example.siphatloproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.siphatloproject.POJO.Item;
import com.example.siphatloproject.POJO.Order;

public class MyOrderAdapter extends ArrayAdapter
{
	private List<Order> allOrder = new ArrayList<Order>();
	
	public MyOrderAdapter(Context context, int resource, List objects) 
	{
		super(context, resource, objects);
		allOrder = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View view = convertView;
		
		if(view == null)
		{
			 LayoutInflater vi;
	         vi = LayoutInflater.from(getContext());
	         view = vi.inflate(R.layout.row_order, null);
		}
		
		TextView tvCusName = (TextView) view.findViewById(R.id.tvCustName);
		TextView tvPrice = (TextView) view.findViewById(R.id.tvItemPrice);
		TextView tvItemName = (TextView) view.findViewById(R.id.tvItemName);
		TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
		
	
		Order o = allOrder.get(position);
		
		tvCusName.setText("Customer Name : " + o.getCustName());
		tvPrice.setText("Price : R" + o.getUnitPrice());
		tvItemName.setText("Item : " + o.getItemName());
		tvDate.setText("Time : " + o.getTimeCreated());

		return view;
	}
}
