package com.example.roadkings;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.Computation.PlacesGoogle;
import com.example.googleKeyList.GooglePlacesList;
import com.example.googleKeyList.RepairShops;
import com.example.tracking.TrackerClass;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchMapActivity extends Activity {


    GooglePlacesList googlePlacesList;
	TrackerClass trackerClass;
	Button buttonShowMap;
	String placesToSearch;
	Location location;
	Location locationB;
	double radius;
	ProgressDialog progressDialog;
	ListView listView;
	PlacesGoogle placesGoogle;
	int distance;
	String distanceInMeters;
	Bitmap bitmap= null;
	ArrayList<HashMap<String, String>> placeList = new ArrayList<HashMap<String,String>>();
	 // KEY Strings
    public static String referenceKey = "reference"; 
    public static String imageKey = "image";
    public static String nameKey = "name"; 
    public static String distanceKey = "distance"; 
    public static String vicinityKey = "vicinity"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_map);
		
		trackerClass = new TrackerClass(this);
		listView = (ListView) findViewById(R.id.listView);
		buttonShowMap = (Button)findViewById(R.id.map_view_btn);
		
		if(getIntent().getExtras().containsKey("repairShops"))
		{
			placesToSearch = getIntent().getExtras().getString("repairShops");
			radius = 2000;
			System.out.println("place to search "+placesToSearch);
			System.out.println("repair shops");
		}
		else if(getIntent().getExtras().containsKey("gasStations"))
		{
			placesToSearch = getIntent().getExtras().getString("gasStations");
			radius = 6000;
			System.out.println("place to search "+placesToSearch);
			System.out.println("gas stations");
		}
		
		if(trackerClass.fetchLocation()!=null)
		{
			location = trackerClass.fetchLocation();
			System.out.println("its working");
			if(location==null)
			{
				System.out.println("null location");
			}
		}
		new GetPlaces().execute();
		buttonShowMap.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(googlePlacesList ==null)
				{
					
					System.out.println("yp honey");
				}
				else
				{
					System.out.println("nahi h");
					 for(RepairShops repairShops : googlePlacesList.results)
					{
				        	System.out.println("formatthe adrres in: "+repairShops.formatted_address);
				        	System.out.println("latitude :"+repairShops.geometry.location.lat);
					}
				}
				Intent intent = new Intent(getApplicationContext(),SearchShopsActivity.class);
				intent.putExtra("latitude", location.getLatitude());
				intent.putExtra("longitude", location.getLongitude());
				intent.putExtra("GooglePlacesList", googlePlacesList);
				startActivity(intent);
			}
		});
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
	         @Override
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	                // getting values from selected ListItem
	                String reference = ((TextView) view.findViewById(R.id.reference)).getText().toString();
	                 
	                // Starting new intent
	                Intent in = new Intent(getApplicationContext(),
	                        PlaceOneActivity.class);

	                in.putExtra(referenceKey, reference);
	                startActivity(in);
	            }
		});
	}
	 class GetPlaces extends AsyncTask<String, String, String>{

		public GetPlaces() {
			// TODO Auto-generated constructor stub
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			placesGoogle = new PlacesGoogle();
			
			
			try {
				if(location!=null)
				{
					System.out.println("latitude :"+location.getLatitude());
					googlePlacesList = placesGoogle.GooglePlaceSearch(location.getLatitude(),location.getLongitude(), radius,placesToSearch );
					if(googlePlacesList.results !=null)
					{
						for(RepairShops repairShops : googlePlacesList.results)
						{
							HashMap<String, String> hashMap = new HashMap<String, String>();
							locationB = new Location(location);
							locationB.setLatitude(repairShops.geometry.location.lat);
							locationB.setLongitude(repairShops.geometry.location.lng);
							distance = (int) location.distanceTo(locationB);
							distanceInMeters = distance + " m";
							System.out.println("Bitmap in String :"+bitmap);
							hashMap.put(imageKey, Integer.toString(R.drawable.google_maps_th));
							hashMap.put(referenceKey,repairShops.reference);
							hashMap.put(nameKey, repairShops.name);
							hashMap.put(distanceKey, distanceInMeters);
							System.out.println("repair shops name :"+repairShops.name);
							System.out.println("repair shops latitude"+repairShops.geometry.location.lat);
							placeList.add(hashMap);
						}
					}
					else {
						System.out.println("aa nai");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		 /* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
	        protected void onPreExecute() {
			 super.onPreExecute();
			 progressDialog = new ProgressDialog(SearchMapActivity.this);
			 progressDialog.setCancelable(false);
			 progressDialog.setMessage("Loading Places Please Wait!");
			 progressDialog.show();
		 }
	    @Override
	    protected void onPostExecute(String result) {
	      progressDialog.dismiss();
	    	runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(googlePlacesList!=null)
					{
					String status = googlePlacesList.status;
					System.out.println("Status and Records "+googlePlacesList.status+" ");
					if(status.equals("OK"))
					{
						if(placeList !=null)
						{							
							ListAdapter listAdapter = new SimpleAdapter(SearchMapActivity.this, placeList, R.layout.list_item, new String[]{imageKey, referenceKey,nameKey,distanceKey}, new int[]{R.id.image, R.id.reference, R.id.name,R.id.distance});
							listView.setAdapter(listAdapter);
						}
						else
						{
							System.out.println("Lag gyi");
						}
					}
				}}
			});
	    }
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_map, menu);
		return true;
	}

}
