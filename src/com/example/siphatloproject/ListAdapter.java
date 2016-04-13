package com.example.siphatloproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.siphatloproject.POJO.Item;
import android.widget.TextView;




public class ListAdapter extends BaseAdapter 
{
	 Context ctx;
	    LayoutInflater lInflater;
	    ArrayList<Item> objects;

	    ListAdapter(Context context, ArrayList<Item> products) {
	        ctx = context;
	        objects = products;
	        lInflater = (LayoutInflater) ctx
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    @Override
	    public int getCount() {
	        return objects.size();
	    }

	    @Override
	    public Object getItem(int position) {
	        return objects.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View view = convertView;
	        if (view == null) {
	            view = lInflater.inflate(R.layout.row_item, parent, false);
	        }
	        
	       

	        Item p = getProduct(position);

	        ((TextView) view.findViewById(R.id.itemDescr)).setText(p.getItemDescription());
	        ((TextView) view.findViewById(R.id.itemPrice)).setText(p.getItemPrice() + "");
	       

	        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.checkBox1);
	        cbBuy.setOnCheckedChangeListener(myCheckChangList);
	        cbBuy.setTag(position);
	        cbBuy.setChecked(p.isBox());
	        return view;
	    }


	    	Item getProduct(int position) 
	    	{
	    		return ((Item) getItem(position));
	    	}

	    ArrayList<Item> getBox() {
	        ArrayList<Item> box = new ArrayList<Item>();
	        for (Item p : objects) {
	            if (p.isBox())
	                box.add(p);
	        }
	        return box;
	    }

	    OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() 
	    {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) 
			{
				getProduct((Integer) buttonView.getTag()).box = isChecked;

			}
	    };

}
