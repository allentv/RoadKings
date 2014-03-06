package com.example.googleKeyList;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

public class GooglePlacesList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1251416985719944289L;
	@Key 
	public String status;
	@Key
	public List<RepairShops> results;
	public GooglePlacesList() {
		// TODO Auto-generated constructor stub
	}

}
