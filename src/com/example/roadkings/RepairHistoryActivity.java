package com.example.roadkings;

import java.util.List;
import java.util.Random;

import com.example.Database.DatabaseClass;
import com.example.Database.RepairHistory;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RepairHistoryActivity extends Activity{

	Spinner spinner;
	Spinner spinnerPart;
	DatePicker datePicker;
	Button button;
	String car_model;
	String parts;
	String date;
	String price;
	String repair_details;
	String shop;
	Random random;
	String emailId;
	DatabaseClass databaseClass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_history);
		
		button = (Button)findViewById(R.id.sumbitRepair);
		datePicker = (DatePicker) findViewById(R.id.datePickerRepair);
		
		//emailId = getIntent().getExtras().getString("Email");
		
		 SharedPreferences preferences = getSharedPreferences("sharedData", 0);
		 System.out.println("pref :"+preferences.contains("Email"));
		 if(preferences.contains("Email"))
		 {
			 System.out.println("yes it contains");
			 emailId = preferences.getString("Email","");
			 System.out.println("emailId "+emailId);
		 }

		databaseClass = new DatabaseClass(this);
		/**
		 * Populating Car Model Spinner 
		 */
	    spinner = (Spinner) findViewById(R.id.car_models_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.car_models, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		
		/**
		 * Populating Car Parts Spinner 
		 */
		spinnerPart = (Spinner) findViewById(R.id.car_parts_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterPart = ArrayAdapter.createFromResource(this,
		        R.array.car_parts, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterPart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerPart.setAdapter(adapterPart);
		
		
		
		/**
		 * Get Value of spinner( Car Repair) on select
		 */
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
        	 
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                Toast.makeText(getApplicationContext(), "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                car_model = parent.getItemAtPosition(position).toString();
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
 
            }

        });
        
        
        /**
         * Get Value of spinner car parts
         */
        spinnerPart.setOnItemSelectedListener(new OnItemSelectedListener() {
       	 
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                Toast.makeText(getApplicationContext(), "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                parts = parent.getItemAtPosition(position).toString();
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
 
            }

        });
        
        // Reading all history
        List<RepairHistory> history = databaseClass.getAllRecords();       
         
        for (RepairHistory cn : history) {
            String log = "Email: "+cn.getEmailId()+" ,Name: " + cn.getCar_model() + " ,Parts: " + cn.getParts()
         		   +", repair details :"+cn.getRepair_details()+" , shop name :"+cn.getShop_name()+", Id :"+cn.getId()+",  Price:"+cn.getPrice()
         		   +", date "+cn.getDate();
                // Writing Contacts to lo
 	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	
	/**
	 * On click of submit button
	 * @param view
	 */
	public void onSubmit(View view)
	{
		TextView textViewRepairDetails = (TextView)findViewById(R.id.repairDetails);
		repair_details = textViewRepairDetails.getText().toString();
		
		TextView textViewShop = (TextView) findViewById(R.id.shopRepair);
		shop = textViewShop.getText().toString();
		
		TextView textViewPrice = (TextView)findViewById(R.id.repairPrice);
		price = textViewPrice.getText().toString();
		date = (datePicker.getMonth()+1)+"/"+datePicker.getDayOfMonth()+"/"+datePicker.getYear();
		Toast.makeText(getApplicationContext(), (datePicker.getMonth()+1)+"/"+datePicker.getDayOfMonth()+"/"+datePicker.getYear(), Toast.LENGTH_SHORT).show();
		System.out.println("car model"+car_model);
		System.out.println("parts"+parts);
		System.out.println("price"+price);
		System.out.println("repair details"+repair_details);
		System.out.println("date "+date);
		System.out.println("shop name"+shop);
		System.out.println("email "+emailId);
		databaseClass.addRepairHistory(new RepairHistory(car_model, parts, price, repair_details, date, shop, emailId));
		
		Intent intent = new Intent(this,DisplayRepairHistory.class);
		startActivity(intent);
		
		
        


}}
