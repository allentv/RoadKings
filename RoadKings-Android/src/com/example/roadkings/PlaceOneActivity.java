package com.example.roadkings;

import java.io.IOException;

import com.example.Computation.PlacesGoogle;
import com.example.googleKeyList.GooglePlacesDetails;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.TextView;

public class PlaceOneActivity extends Activity {
	
	public static String reference;
	PlacesGoogle placesGoogle;
	GooglePlacesDetails googlePlacesDetails;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_one);
		
		reference = getIntent().getStringExtra("reference");
		System.out.println("Reference :"+reference);
		new GetDetails().execute(reference);
	}
		
		 class GetDetails extends AsyncTask<String, String, String>
		 {

			@Override
			protected void onPreExecute() {
				
				progressDialog = new ProgressDialog(PlaceOneActivity.this);
				progressDialog.setCancelable(false);
				progressDialog.setMessage("Fetching Details");
				progressDialog.show();
			};
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				String reference  = params[0];
				System.out.println("reference in do :"+reference);
				try {
					System.out.println("yo yo");
					googlePlacesDetails = new PlacesGoogle().GooglePlaceDetailsFetch(reference);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				progressDialog.dismiss();
				super.onPostExecute(result);
				if(googlePlacesDetails!=null)
				{
					System.out.println("i am here");
					System.out.println("Status :"+googlePlacesDetails.status);
					
					if(googlePlacesDetails.result!=null)
					{
						String name = googlePlacesDetails.result.name.toString();
						String formatted_address = googlePlacesDetails.result.formatted_address;
						String phone_no = googlePlacesDetails.result.formatted_phone_number;
						String address= "";
						if(formatted_address !=null)
						{
							address = address+formatted_address;
						}
						if(phone_no == null)
						{
							phone_no = "Not Available";
						}
						TextView textViewName = (TextView) findViewById(R.id.name);
						TextView textViewAddress = (TextView) findViewById(R.id.address);
						TextView textViewPhone = (TextView)findViewById(R.id.phone);
						
						textViewAddress.setText(address);
						textViewName.setText(name);
						textViewPhone.setText(phone_no);
					}
				}
				
				
			}
			 
		 }
		
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.place_one, menu);
		return true;
	}

}
