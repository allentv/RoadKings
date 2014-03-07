package com.example.roadkings;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePageActivity extends Activity {

	Button buttonShowGasStation;
	String emailId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_page);
		
		TextView customerName = (TextView)findViewById(R.id.customerName);
		emailId = getIntent().getExtras().getString("emailId");
		customerName.setText("Welcome "+getIntent().getExtras().getString("emailId"));
		buttonShowGasStation = (Button)findViewById(R.id.searchGasStation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome_page, menu);
		return true;
	}
	public void onClickWelcome(View view) {
		// TODO Auto-generated method stub
			String button = ((Button)view).getText().toString();
			System.out.println("string button:"+button);
			if(button.equalsIgnoreCase("Search Shops"))
			{
				Intent intent = new Intent(this, SearchMapActivity.class);
				intent.putExtra("repairShops", "car_repair");
				startActivity(intent);
			}
			if(button.equalsIgnoreCase("Search Gas Stations"))
			{
				System.out.println("here in gas statiosn");
				Intent intent = new Intent(this,SearchMapActivity.class);
				intent.putExtra("gasStations", "gas_station");
				startActivity(intent);
			}
			if(button.equalsIgnoreCase("Repair History"))
			{
				System.out.println("here in gas statiosn");
				Intent intent = new Intent(this,RepairHistoryActivity.class).putExtra("Email", emailId);
				//intent.putExtra("gasStations", "gas_station");
				startActivity(intent);
			}
			if(button.equalsIgnoreCase("Report Problem"))
			{
				System.out.println("here in gas statiosn");
				Intent intent = new Intent(this,ReportProblemActivity.class);
				//intent.putExtra("gasStations", "gas_station");
				startActivity(intent);
			}
		}
}
