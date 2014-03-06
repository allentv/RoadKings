package com.example.Database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseClass extends SQLiteOpenHelper{

	private static final int version = 1;
	private static final String databaseName = "repairHistoryManager";
	private static final String tableName = "repairHistory";
	private static final String key_car_model = "car_model";
	private static final String key_parts = "parts";
	private static final String key_price = "price";
	private static final String key_repair_details = "repair_details";
	private static final String key_date = "date";
	private static final String key_shop_name = "shop_name";
	private static final String key_emailId = "emailId";
	private static final String key_id = "id";
	private Integer id;
	private Random  random;
	
	 public DatabaseClass(Context context) {
	        super(context, databaseName, null, version);
	    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String createRepairHistoryTable = "CREATE TABLE "+tableName+" ("+key_id+" INTEGER PRIMARY KEY, "+key_emailId+" TEXT PRIMARY_KEY, "+ key_car_model
				+" TEXT, "+key_date+" datetime default current_timestamp, "+key_parts+" TEXT, "+key_repair_details+" TEXT, "+key_shop_name+" TEXT, "+
				key_price+" REAL	"+")";
		db.execSQL(createRepairHistoryTable);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("Drop table if exists"+tableName);
		onCreate(db);
		
	}
	
	public void addRepairHistory(RepairHistory repairHistory)
	{
		
		random = new  Random();
		id =random.nextInt(100000000);
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		System.out.println("shop name: "+repairHistory.getShop_name());
		System.out.println("repair price: "+repairHistory.getPrice());
		System.out.println("repair d :"+repairHistory.getRepair_details());
		ContentValues contentValues = new ContentValues();
		contentValues.put(key_id, id);
		contentValues.put(key_emailId,repairHistory.getEmailId());
		contentValues.put(key_car_model, repairHistory.getCar_model());
		contentValues.put(key_date, repairHistory.getDate().toString());
		contentValues.put(key_parts, repairHistory.getParts());
		contentValues.put(key_price, repairHistory.getPrice());
		contentValues.put(key_repair_details, repairHistory.getRepair_details());
		contentValues.put(key_shop_name,repairHistory.getShop_name());
		
		database.insert(tableName, null, contentValues);
		database.close();
	}
    // Getting All Contacts
    public List<RepairHistory> getAllRecords() {
        List<RepairHistory> repairHistoryList = new ArrayList<RepairHistory>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableName;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	RepairHistory repairHistory = new RepairHistory();
            	repairHistory.setId((Integer.parseInt(cursor.getString(0))));
            	repairHistory.setEmailId(cursor.getString(1));
            	repairHistory.setCar_model((cursor.getString(2)));
            	repairHistory.setDate(cursor.getString(3));
            	repairHistory.setParts(cursor.getString(4));
            	repairHistory.setRepair_details(cursor.getString(5));
            	repairHistory.setShop_name(cursor.getString(6));
            	repairHistory.setPrice(cursor.getString(7));
                // Adding contact to list
                repairHistoryList.add(repairHistory);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return repairHistoryList;
    }

}
