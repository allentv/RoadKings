package com.example.Computation;

import java.io.IOException;
import com.example.googleKeyList.GooglePlacesDetails;
import com.example.googleKeyList.GooglePlacesList;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;

public class PlacesGoogle {
	
	public double latitude;
	public double longitude;
	public double radius;
	
	HttpTransport httpTransport = new NetHttpTransport();
	String apiKey = "AIzaSyCOIEoXeANDu5GT-1HNs0o1CKY1HRPJ-OQ";
	String placeSearchURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
	String placeTextSearchURL = "https://maps.googleapis.com/maps/api/place/textsearch/output?";
	String placeDetailsURL = "https://maps.googleapis.com/maps/api/place/details/json?";
	
	public GooglePlacesList GooglePlaceSearch(double latitude, double longitude,double radius, String types) throws IOException
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		
		System.out.println("latitude in Places Google "+latitude);
		System.out.println("radius "+radius);
		HttpRequestFactory httpRequestFactory = createRequestFactory(httpTransport);
		placeSearchURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius="+radius+"&types="+types+"&sensor=false&key="+apiKey+"";
		HttpRequest httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl(placeSearchURL));
		/*httpRequest.getUrl().put("key", apiKey);
		httpRequest.getUrl().put("location",latitude+ ""+longitude);
		httpRequest.getUrl().put("radius", radius);
		httpRequest.getUrl().put("sensor",false);
		if(types!=null)
		{
			httpRequest.getUrl().put("types",types);
		}*/
		System.out.println("hhtp request :"+httpRequest.toString());
		
		System.out.println("records :"+httpRequest.execute().parseAsString().toString());
		GooglePlacesList googlePlacesList = httpRequest.execute().parseAs(GooglePlacesList.class);
		System.out.println(" Place Status"+googlePlacesList.results.size());
		return googlePlacesList;
	}
	
	public GooglePlacesDetails GooglePlaceDetailsFetch(String details) throws IOException
	{
		
		HttpRequestFactory httpRequestFactory = createRequestFactory(httpTransport);
		placeDetailsURL = "https://maps.googleapis.com/maps/api/place/details/json?reference="+details+"&sensor=true&key="+apiKey+"";
		HttpRequest httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl(placeDetailsURL));
		/*httpRequest.getUrl().put("key",apiKey);
		httpRequest.getUrl().put("reference", details);
		httpRequest.getUrl().put("sensor", false);*/
		
		GooglePlacesDetails googlePlacesDetails = httpRequest.execute().parseAs(GooglePlacesDetails.class);
		return googlePlacesDetails;
	}
	
	public HttpRequestFactory createRequestFactory(HttpTransport transport)
	{
		return transport.createRequestFactory(new HttpRequestInitializer() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void initialize(HttpRequest httpRequest) throws IOException {
				// TODO Auto-generated method stub
				
				GoogleHeaders googleHeaders = new GoogleHeaders();
				googleHeaders.setApplicationName("Car-Repair-Places");
				httpRequest.setHeaders(googleHeaders);
				@SuppressWarnings("deprecation")
				JsonHttpParser httpParser = new JsonHttpParser(new JacksonFactory());
				httpRequest.addParser(httpParser);
			}
		});
	}
	
}
