package com.example.roadkings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.adapterRoadKings.ListAdapterClass;
import com.exmaple.modelRoadKings.SpinnerActionBar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class RoadKingsTabActivity extends FragmentActivity implements ActionBar.OnNavigationListener, ActionBar.TabListener {

	String emailId;
    private ArrayList<SpinnerActionBar> spinnerActionBarsList;
    ActionBar actionBar;
    private ListAdapterClass listAdapterClass;
    private String serverUrl;
    private String serverContent;
    
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_road_kings_tab);
		 actionBar = getActionBar();
		 

/*		 if(getIntent().getExtras().containsKey("emailID"))
		 {
			 emailId = getIntent().getExtras().getString("emailID");
			 System.out.println("emailID");
		 }*/
			
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			spinnerActionBarsList = new ArrayList<SpinnerActionBar>();
			spinnerActionBarsList.add(new SpinnerActionBar("Options", R.drawable.down));
			spinnerActionBarsList.add(new SpinnerActionBar("Gas Stations", R.drawable.cartoon_gas_station_clipart));
			spinnerActionBarsList.add(new SpinnerActionBar("Add Repair History", R.drawable.repair));
			spinnerActionBarsList.add(new SpinnerActionBar("Report Problem", R.drawable.report_a_problem));
			spinnerActionBarsList.add(new SpinnerActionBar("Repair Shops", R.drawable.handyman_clip_art));
			spinnerActionBarsList.add(new SpinnerActionBar("Repair History", R.drawable.repair_history));
			
			listAdapterClass = new ListAdapterClass(getApplicationContext(), spinnerActionBarsList);
			actionBar.setListNavigationCallbacks(listAdapterClass, this);
			
			 SharedPreferences preferences = getSharedPreferences("sharedData", 0);
			 System.out.println("pref :"+preferences.contains("Email"));
			 if(preferences.contains("Email"))
			 {
				 System.out.println("yes it contains");
				 emailId = preferences.getString("Email","");
				 System.out.println("emailId "+emailId);
			 }
			 
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.action_main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		serverUrl = "https://www.scss.tcd.ie/~vargheat/rk/pages/Notifications.php";
		 new VendorServerGet().execute(serverUrl);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
			case R.id.action_check_updates:
				return true;
			case R.id.action_help:
				return true;
			case R.id.action_location_found:
				LocationFound();
				return true;
			case R.id.action_refresh:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}	
	}
	
	public void LocationFound(){
		
		//Intent intent = new Intent(getApplicationContext(),LocationFound.class);
		//startActivity(intent);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		System.out.println("item Id :"+itemId);
		System.out.println("Item position :"+itemPosition);
		Intent intent =null;
		
		switch(itemPosition)
		{
		case 0:
			return false;
		case 1:
			intent = new Intent(this,SearchMapActivity.class).putExtra("gasStations", "gas_station");
			startActivity(intent);
			return true;
		case 2:
			intent = new Intent(this,RepairHistoryActivity.class).putExtra("Email", emailId);
			startActivity(intent);
			return true;
		case 3:
			intent = new Intent(this,ReportProblemActivity.class).putExtra("repairShops", "car_repair");
			startActivity(intent);
			return true;
		case 4:
			intent =new Intent(this,SearchMapActivity.class).putExtra("repairShops", "car_repair");
			startActivity(intent);
			return true;
		case 5:
			intent = new Intent(this,DisplayRepairHistory.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	private class VendorServerGet extends AsyncTask<String, Void, Void>
	{

		private final HttpClient  httpClient = new DefaultHttpClient();
		private String context;
		private String error = null;
		private ProgressDialog progressDialog = new ProgressDialog(RoadKingsTabActivity.this);
		String data = "";
		EditText serverText = (EditText) findViewById(R.id.serverText);
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			//progressDialog.setMessage("Please Wait..");
			//progressDialog.show();
			System.out.println("Yo yo");
			try {
				data +="&" + URLEncoder.encode("data", "UTF-8") + "="+serverText.getText();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			BufferedReader bufferedReader = null;
			System.out.println("in do back");
			URL url;
			try {
				url = new URL(params[0]);
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
				outputStreamWriter.write(data);
				outputStreamWriter.flush();
				System.out.println("in do background");
				bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String line =null;
				while((line = bufferedReader.readLine())!=null)
				{
					System.out.println("in while");
					builder.append(line +" ");
				}
			    serverContent = builder.toString();
			    
				
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				/*try {
					//bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//progressDialog.dismiss();
			
			JSONObject  jsonObject;
			System.out.println("in post execute");
			try {
				JSONArray array = new JSONArray(serverContent);
				System.out.println("hihi");
/*				jsonObject = new JSONObject(serverContent); 
				System.out.println("json Object: "+jsonObject);
				JSONArray jsonArray = jsonObject.optJSONArray("Value");*/
				int serverJsonDataLength = array.length();
				System.out.println("array length"+serverJsonDataLength);
				for(int j=0;j< serverJsonDataLength; j++)
				{
					
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
