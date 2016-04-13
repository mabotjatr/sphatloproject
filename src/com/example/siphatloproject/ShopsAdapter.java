package com.example.siphatloproject;

import java.util.ArrayList;
import java.util.List;

import com.example.siphatloproject.POJO.Item;
import com.example.siphatloproject.POJO.Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShopsAdapter extends ArrayAdapter 
{
	
	private List<Shop> allItems = new ArrayList<Shop>();
	
	public ShopsAdapter(Context context, int resource, List objects) 
	{
		super(context, resource, objects);
		allItems = objects;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View view = convertView;
		if(view == null)
		{
			 LayoutInflater vi;
	         vi = LayoutInflater.from(getContext());
	         view = vi.inflate(R.layout.row_shops, null);
		}
		
		TextView tvName = (TextView) view.findViewById(R.id.txtNames);
		TextView tvPrice = (TextView) view.findViewById(R.id.txtAddress);
		
		Shop item = allItems.get(position);
		
		tvName.setText(item.getShopName());
		tvPrice.setText("R"+ item.getShopAddress());
		
		return view;
	}

}
