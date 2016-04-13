package com.example.siphatloproject;

import java.util.ArrayList;
import java.util.List;

import com.example.siphatloproject.POJO.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter
{
	private List<Item> allItems = new ArrayList<Item>();
	
	public ItemAdapter(Context context, int resource, List objects) 
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
	         view = vi.inflate(R.layout.row_myitem, null);
		}
		
		TextView tvName = (TextView) view.findViewById(R.id.itemDescr);
		TextView tvPrice = (TextView) view.findViewById(R.id.itemPrice);
		
		Item item = allItems.get(position);
		
		tvName.setText(item.getItemDescription());
		tvPrice.setText("R"+ item.getItemPrice());
		
		return view;
	}

	

}
