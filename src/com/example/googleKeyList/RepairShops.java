package com.example.googleKeyList;

import java.io.Serializable;
import com.google.api.client.util.Key;

public class RepairShops implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2776600446061936024L;
	@Key
    public String id;     
    @Key
    public String name;     
    @Key
    public String reference;    
    @Key
    public String icon;
    @Key
    public float rating;
    @Key
    public String vicinity;    
    @Key
    public Geometry geometry;    
    @Key
    public String formatted_address;    
    @Key
    public String formatted_phone_number;
    @Override
    public String toString() {
        return name + " - " + id + " - " + reference;
    }
     
	public RepairShops() {
		// TODO Auto-generated constructor stub
	}
	public static class Geometry implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1672117535330481217L;
		@Key
		public Location location;
	}
	public static class Location implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6072525111184974781L;

		@Key
		public double lat;
		
		@Key
		public double lng;
	}
	
}
