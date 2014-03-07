package com.example.roadkings;

import android.support.v4.app.FragmentActivity;  

import com.example.googleKeyList.GooglePlacesList;
import com.example.googleKeyList.RepairShops;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;


public class SearchShopsActivity extends FragmentActivity{
    GoogleMap mGoogleMap;
    String address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_shops);
        // Get a handle to the Map Fragment
        GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        map.setMyLocationEnabled(true);
        Bundle bundle = getIntent().getExtras();
        LatLng position = new LatLng(bundle.getDouble("latitude"), bundle.getDouble("longitude"));
        GooglePlacesList googlePlacesList = (GooglePlacesList)getIntent().getSerializableExtra("GooglePlacesList");
        
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13));
        
        System.out.println("lenght size :"+googlePlacesList.results.size());
        for(RepairShops repairShops : googlePlacesList.results)
		{
        	System.out.println("formatthe adrres: "+repairShops.formatted_address);
        	System.out.println("latitude :"+repairShops.geometry.location.lat);
            map.addMarker(new MarkerOptions()
            .title(repairShops.name)
            .snippet(repairShops.vicinity)
            .position(new LatLng(repairShops.geometry.location.lat,repairShops.geometry.location.lng)));
		}
        
        

	    }
}
