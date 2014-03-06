package com.example.adapterRoadKings;

import java.util.ArrayList;

import com.example.roadkings.R;
import com.exmaple.modelRoadKings.SpinnerActionBar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapterClass extends BaseAdapter{
	
	private ImageView imageView;
	private TextView textView;
	private  ArrayList<SpinnerActionBar> spinnerActionBars;
	private Context context;

	public ListAdapterClass(Context context, ArrayList<SpinnerActionBar> spinnerActionBars) {
		
		this.spinnerActionBars = spinnerActionBars;
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return spinnerActionBars.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return spinnerActionBars.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		if(view ==null)
		{
				LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.list_item2, null);
		}
		imageView = (ImageView) view.findViewById(R.id.iconImage);
		textView = (TextView) view.findViewById(R.id.txtTitle);
		
		imageView.setImageResource(spinnerActionBars.get(pos).getIcon());
		imageView.setVisibility(View.GONE);
		textView.setText(spinnerActionBars.get(pos).getTitle());
		return view;
		
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView ==null)
		{
			LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.list_item2, null);
		}
		
        imageView = (ImageView) convertView.findViewById(R.id.iconImage);
        textView = (TextView) convertView.findViewById(R.id.txtTitle);
         
        imageView.setImageResource(spinnerActionBars.get(position).getIcon());        
        textView.setText(spinnerActionBars.get(position).getTitle());
        return convertView;
		
	}
}
