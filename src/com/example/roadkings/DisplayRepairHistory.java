package com.example.roadkings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.Database.DatabaseClass;
import com.example.Database.RepairHistory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DisplayRepairHistory extends Activity {

	DatabaseClass databaseClass;
	ProgressDialog progressDialog = null;
	private static String imageRepairKey  = "imageRepair";
	private static String problemKey = "problem";
	private static String dateRepairKey = "dateRepair";
	private static String repairPartKey = "partsRepair";
	private ListView listView;
	ArrayList<HashMap<String, String>> repairList = new ArrayList<HashMap<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_repair_history);
		databaseClass  = new DatabaseClass(getApplicationContext());
		
		listView = (ListView)findViewById(R.id.listViewRepair);
		new GetRepairDetails().execute(databaseClass);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_repair_history, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		
	}
	
	class GetRepairDetails extends AsyncTask<DatabaseClass, Void, Void>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
/*			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("Please Wait..");
			progressDialog.setCancelable(false);
			progressDialog.show();*/
			
			
		}
		@Override
		protected Void doInBackground(DatabaseClass... params) {
			// TODO Auto-generated method stub
			
			List<RepairHistory> histories = databaseClass.getAllRecords();
			if(histories!=null)
			{

				for(RepairHistory history: histories)
				{
					HashMap<String, String> repairHistMap = new HashMap<String, String>();
					repairHistMap.put(imageRepairKey, Integer.toString(R.drawable.roadking));
					repairHistMap.put(problemKey, history.getRepair_details());
					repairHistMap.put(dateRepairKey, history.getDate());
					repairHistMap.put(repairPartKey, history.getParts());
					repairList.add(repairHistMap);
				}
				return null;
			}
			else
			{
				System.out.println("records not found");
			}
			return null;
			
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//progressDialog.dismiss();
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(!repairList.isEmpty())
					{
						ListAdapter listAdapter = new SimpleAdapter(DisplayRepairHistory.this, repairList, R.layout.repair_items, new String[]{imageRepairKey, problemKey,repairPartKey,dateRepairKey}, new int[]{R.id.imageRepair, R.id.problem,R.id.partsRepair, R.id.dateRepair});
						listView.setAdapter(listAdapter);
					}
					
				}
			});
			
		}
		
	}
}
