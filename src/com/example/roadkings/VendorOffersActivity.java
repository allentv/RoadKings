package com.example.roadkings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.internal.bu;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;

public class VendorOffersActivity extends Activity {

	String serverUrl;
	private String serverContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vendor_offers);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vendor_offers, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		serverUrl = "www.scss.tcd.ie/~vargheat/rk/pages/Notifications.php";
	}
	
	private class VendorServerGet extends AsyncTask<String, Void, Void>
	{

		private final HttpClient  httpClient = new DefaultHttpClient();
		private String context;
		private String error = null;
		private ProgressDialog progressDialog = new ProgressDialog(VendorOffersActivity.this);
		String data = "";
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.setMessage("Please Wait..");
			progressDialog.show();
		}
		
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			BufferedReader bufferedReader = null;
			
			URL url;
			try {
				url = new URL(params[0]);
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
				outputStreamWriter.write(data);
				outputStreamWriter.flush();
				
				bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String line =null;
				while((line = bufferedReader.readLine())!=null)
				{
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
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			
			JSONObject  jsonObject;
			
			try {
				jsonObject = new JSONObject(serverContent); 
				JSONArray jsonArray = jsonObject.optJSONArray("Android");
				int serverJsonDataLength = jsonArray.length();
				
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
