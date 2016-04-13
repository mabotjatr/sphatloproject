package com.example.siphatloproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.siphatloproject.POJO.Item;

public class OrderAdapter extends ArrayAdapter
{
	private List<Item> shops = new ArrayList<Item>();
	private List<Integer> itemChecked;
	private Context context;
	
	private int count;
	
	public OrderAdapter(Context context, int resource,List shops)
	{
		super(context, resource,shops);
		context = context;
        this.itemChecked=new  ArrayList<Integer>();
		this.shops = shops;
		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		final CheckBox checkbox;
		View view = convertView;
		if(view == null)
		{
			 LayoutInflater vi;
	         vi = LayoutInflater.from(getContext());
	         view = vi.inflate(R.layout.row_item, null);
	         
	         Object order = shops.get(position);
	         
	         if(order!=null)
	         {
	        	 checkbox = (CheckBox) view.findViewById(R.id.checkBox1);
	        	 checkbox.setTag(position);
	        	 
	        	 checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        	 {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
					{
						 Object tag = checkbox.getTag();
	                       if ( isChecked ) 
	                       { 
	                           // perform logic 
	                           count ++;
	                           Log.d("Checkbox","added "+tag);
	                           itemChecked.add((Integer) tag);
	                       } 
	                       else
	                       {
	                           count--;
	                           itemChecked.remove(tag);
	                           Log.d("Checkbox","removed "+(Integer)tag);
	                       }
					}
				});
	         }
		}
		
		TextView tvName = (TextView) view.findViewById(R.id.itemDescr);
		TextView tvPrice = (TextView) view.findViewById(R.id.itemPrice);
		/**CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkBox1);
		checkbox.setFocusable(false);
		checkbox.setFocusableInTouchMode(false);**/
		
		Item order = shops.get(position);
		
		tvName.setText(order.getItemDescription());
		tvPrice.setText("R"+ order.getItemPrice());
		
		return view;
	}
	public List<Integer> getcheckeditemcount()
	{
	    return this.itemChecked;
	}
}
