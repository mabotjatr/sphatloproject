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

public class ShopAdapter  extends ArrayAdapter
{
	private List<Shop> shops = new ArrayList<Shop>();
	public ShopAdapter(Context context, int resource,List shops)
	{
		super(context, resource,shops);
		this.shops = shops;
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View view = convertView;
		if(view == null)
		{
			 LayoutInflater vi;
	         vi = LayoutInflater.from(getContext());
	         view = vi.inflate(R.layout.row_shops, null);
		}
		
		TextView tvShopNames = (TextView) view.findViewById(R.id.txtNames);
		TextView tvShopAddress = (TextView) view.findViewById(R.id.txtAddress);
		
		Shop shop = shops.get(position);
		
		tvShopNames.setText("Shop Name : "+shop.getShopName());
		tvShopAddress.setText("Location : "+shop.getShopAddress());
		
		return view;
	}
}
