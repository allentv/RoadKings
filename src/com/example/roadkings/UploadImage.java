package com.example.roadkings;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UploadImage extends FragmentActivity implements OnClickListener,GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener{

    private TextView messageText;
    private Button uploadButton, btnselectpic;
    private ImageView imageview;
    private int serverResponseCode = 0;
    private ProgressDialog dialog = null;
    private String emailId = null;
    private String upLoadServerUri = null;
    private String imagepath=null;
    private String comment = null;
    private Geocoder geocoder;
    private LocationClient locationClient = null;
    private Location currentLocation = null;
    private List<Address> addresses = null;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private Address address = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_image);
		uploadButton = (Button)findViewById(R.id.uploadButton);
        messageText  = (TextView)findViewById(R.id.messageText);
        btnselectpic = (Button)findViewById(R.id.button_selectpic);
        imageview = (ImageView)findViewById(R.id.imageView_pic);
         
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        btnselectpic.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
        upLoadServerUri = "https://www.scss.tcd.ie/~vargheat/rk/pages/ReportProblem.php";
        locationClient = new LocationClient(this, this, this);
        
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		locationClient.connect();
		
		currentLocation = locationClient.getLastLocation();
		try {
			addresses = geocoder.getFromLocation(currentLocation.getLatitude(),
					currentLocation.getLongitude(), 1);
			if(addresses!=null && addresses.size()>0)
			{
				address = addresses.get(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		locationClient.disconnect();
		super.onStop();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upload_image, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        if(v==btnselectpic)
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
        }
        else if (v==uploadButton) {
             
             dialog = ProgressDialog.show(UploadImage.this, "", "Uploading file...", true);
             comment  = messageText.getText().toString();
             new Thread(new Runnable() {
                 public void run() {
                                      
                      uploadFile(imagepath);
                                               
                 }
               }).start();     
        }
		
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bitmap photo = (Bitmap) data.getData().getPath(); 
           
            Uri selectedImageUri = data.getData();
            imagepath = getPath(selectedImageUri);
            Bitmap bitmap=BitmapFactory.decodeFile(imagepath);
            imageview.setImageBitmap(bitmap);
            messageText.setText("Uploading file path:" +imagepath);
            
        }
    }
         public String getPath(Uri uri) {
                String[] projection = { MediaStore.Images.Media.DATA };
                Cursor cursor = managedQuery(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
         public int uploadFile(String sourceFileUri) {
             System.out.println("sourceFileUri :"+sourceFileUri);
             
             String fileName = sourceFileUri;
			 SharedPreferences preferences = getSharedPreferences("sharedData", 0);
			 System.out.println("pref :"+preferences.contains("Email"));
			 if(preferences.contains("Email"))
			 {
				 System.out.println("yes it contains");
				 emailId = preferences.getString("Email","");
				 System.out.println("emailId "+emailId);
			 }
			 System.out.println("yoyo");
             HttpURLConnection conn = null;
             DataOutputStream dos = null;  
             String lineEnd = "\r\n";
             String twoHyphens = "--";
             String boundary = "*****";
             int bytesRead, bytesAvailable, bufferSize;
             byte[] buffer;
             int maxBufferSize = 1 * 1024 * 1024; 
             File sourceFile = new File(sourceFileUri); 
              
             System.out.println("boolean :"+!sourceFile.isFile());
             System.out.println("hiiii");
             if (!sourceFile.isFile()) {
                  
                  dialog.dismiss(); 
                   
                  Log.e("uploadFile", "Source File not exist :"+imagepath);
                   
                  runOnUiThread(new Runnable() {
                      public void run() {
                          messageText.setText("Source File not exist :"+ imagepath);
                      }
                  }); 
                   
                  return 0;
               
             }
             else
             {
                  try { 
                       
                        // open a URL connection to the Servlet
                      FileInputStream fileInputStream = new FileInputStream(sourceFile);
                      URL url = new URL(upLoadServerUri);
                       System.out.println("here");
                      // Open a HTTP  connection to  the URL
                      conn = (HttpURLConnection) url.openConnection(); 
                      conn.setDoInput(true); // Allow Inputs
                      conn.setDoOutput(true); // Allow Outputs
                      conn.setUseCaches(false); // Don't use a Cached Copy
                      conn.setRequestMethod("POST");
                      conn.setRequestProperty("Connection", "Keep-Alive");
                      conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                      conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                      conn.setRequestProperty("id", emailId);
                      conn.setRequestProperty("uploaded_file", fileName); 
                      conn.setRequestProperty("name", emailId);
                      conn.setRequestProperty("comment", comment);
                      conn.addRequestProperty("lat", ""+currentLocation.getLatitude());
                      conn.addRequestProperty("lng", ""+currentLocation.getLongitude());
                      System.out.println("lat and Long "+currentLocation.getLatitude()+" "+currentLocation.getLongitude() );
                      conn.addRequestProperty("location", address.getLocality()+" "+address.getCountryName());
                      System.out.println("address :"+address.getLocality()+" "+address.getCountryName());
                       
                      dos = new DataOutputStream(conn.getOutputStream());
             
                      dos.writeBytes(twoHyphens + boundary + lineEnd); 
                      dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                                                + fileName + "\"" + lineEnd);
                       
                      dos.writeBytes(lineEnd);
                      System.out.println("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                                                + fileName + "\"" + lineEnd);
                      // create a buffer of  maximum size
                      bytesAvailable = fileInputStream.available(); 
             
                      bufferSize = Math.min(bytesAvailable, maxBufferSize);
                      buffer = new byte[bufferSize];
             
                      // read file and write it into form...
                      bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
                         
                      while (bytesRead > 0) {
                           
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        System.out.println("ByetsAvailable :"+bytesAvailable);
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);   
                         
                       }
             
                      // send multipart form data necesssary after file data...
                      dos.writeBytes(lineEnd);
                      dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
             
                      // Responses from the server (code and message)
                      serverResponseCode = conn.getResponseCode();
                      String serverResponseMessage = conn.getResponseMessage();
                        
                      Log.i("uploadFile", "HTTP Response is : "
                              + serverResponseMessage + ": " + serverResponseCode);
                       
                      if(serverResponseCode == 200){
                           
                          runOnUiThread(new Runnable() {
                               public void run() {
                                   String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                         +" F:/wamp/wamp/www/uploads";
                                   messageText.setText(msg);
                                   Toast.makeText(UploadImage.this, "File Upload Complete.", Toast.LENGTH_SHORT).show();
                               }
                           });                
                      }    
                       
                      //close the streams //
                      fileInputStream.close();
                      dos.flush();
                      dos.close();
                        
                 } catch (MalformedURLException ex) {
                      
                     dialog.dismiss();  
                     ex.printStackTrace();
                      
                     runOnUiThread(new Runnable() {
                         public void run() {
                             messageText.setText("MalformedURLException Exception : check script url.");
                             Toast.makeText(UploadImage.this, "MalformedURLException", Toast.LENGTH_SHORT).show();
                         }
                     });
                      
                     Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
                 } catch (Exception e) {
                      
                     dialog.dismiss();  
                     e.printStackTrace();
                      
                     runOnUiThread(new Runnable() {
                         public void run() {
                             messageText.setText("Got Exception : see logcat ");
                             Toast.makeText(UploadImage.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
                         }
                     });
                     Log.e("Upload file to server Exception", "Exception : "  + e.getMessage(), e);  
                 }
                 dialog.dismiss();       
                 return serverResponseCode; 
                  
              } // End else block 
            }

		@Override
		public void onConnectionFailed(ConnectionResult connectionResult) {
			// TODO Auto-generated method stub
			 /*
	         * Google Play services can resolve some errors it detects.
	         * If the error has a resolution, try sending an Intent to
	         * start a Google Play services activity that can resolve
	         * error.
	         */
	        if (connectionResult.hasResolution()) {
	            try {
	                // Start an Activity that tries to resolve the error
	                connectionResult.startResolutionForResult(
	                        this,
	                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
	                /*
	                 * Thrown if Google Play services canceled the original
	                 * PendingIntent
	                 */
	            } catch (IntentSender.SendIntentException e) {
	                // Log the error
	                e.printStackTrace();
	            }
	        } else {
	            /*
	             * If no resolution is available, display a dialog to the
	             * user with the error.
	             */
	            Toast.makeText(this, "Connected :"+connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
	        }
		}

		@Override
		public void onConnected(Bundle arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onDisconnected() {
			// TODO Auto-generated method stub
			Toast.makeText(this, "Disconnected. Please re-connect.",
	                Toast.LENGTH_SHORT).show();
			
		}

}
