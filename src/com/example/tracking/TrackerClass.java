package com.example.tracking;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class TrackerClass extends Service implements LocationListener{

	double latitude;
	double longitude;
	Context context;
	LocationManager locationManager;
	Location location;
	
    // The minimum distance to change Updates in meters
    private static final long distanceChange = 10; // 10 meters
 
    // The minimum time between updates in milliseconds
    private static final long timeChange = 1000 * 60 * 1;
	public TrackerClass(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public Location fetchLocation()
	{
		locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, timeChange,distanceChange, this);
		location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		return location;
	}
	
	public void stopFetchingLocation()
	{
		if(locationManager !=null)
		{
			locationManager.removeUpdates(TrackerClass.this);
		}
	}
	
	public double getLatitude()
	{
		latitude = location.getLatitude();
		System.out.println("fetch location"+ location);
		return 	latitude;
	}
	
	public double getLongitude()
	{
		longitude = location.getLongitude();
		return longitude;
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
