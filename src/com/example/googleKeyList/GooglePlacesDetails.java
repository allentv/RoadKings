package com.example.googleKeyList;

import java.io.Serializable;

import com.google.api.client.util.Key;

public class GooglePlacesDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4055419219359818817L;
	@Key
	public String status;
	@Key
	public RepairShops result;
	public GooglePlacesDetails() {
		// TODO Auto-generated constructor stub
	}

}
