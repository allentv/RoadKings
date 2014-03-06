package com.example.roadkings;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracking.TrackerClass;

public class ReportProblemActivity extends FragmentActivity implements OnClickListener{

	private ImageView imageUplaod;
	private EditText editText;
	private ProgressDialog dialog  =null;
	private Button buttonSelect;
	private Button buttonUpload;
	private String imagePath;
	private int serverResCode = 0;
	private String emailId;
	private TextView textErrorMessage;
	private TrackerClass trackerClass;
	private String serverUri = "https://www.scss.tcd.ie/~vargheat/rk/pages/ReportProblem.php";
    private Geocoder geocoder;
    private Location currLocation;
    private List<Address> addresses = null;
    private String comment;
    private Address address = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_problem);
		
		editText = (EditText)findViewById(R.id.commentText);
		imageUplaod = (ImageView)findViewById(R.id.imageUpload);
		buttonSelect = (Button)findViewById(R.id.btnSelect);
		buttonUpload = (Button)findViewById(R.id.btnUpload);
		textErrorMessage = (TextView)findViewById(R.id.errorMessage);
		buttonSelect.setOnClickListener(this);
		buttonUpload.setOnClickListener(this);
		trackerClass = new TrackerClass(this);
		if(trackerClass.fetchLocation()!=null)
		{
			currLocation = trackerClass.fetchLocation();
			System.out.println("its working");
			if(currLocation==null)
			{
				System.out.println("null location");
			}
		}
		
		 geocoder = new Geocoder(this, Locale.getDefault());
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report_problem, menu);
		return true;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		try {
			if(trackerClass !=null)
			{
				addresses = geocoder.getFromLocation(currLocation.getLatitude(),
						currLocation.getLongitude(), 1);
				if(addresses!=null && addresses.size()>0)
				{
					address = addresses.get(0);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==buttonSelect)
		{
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "Choose your application..."), 1);
		}
		if(v==buttonUpload)
		{
			comment =  editText.getText().toString();
            dialog = ProgressDialog.show(ReportProblemActivity.this, "", "Wait Uploading", true);
            new Thread(new Runnable() {
                public void run() {
                                     
                     fileUpload(imagePath);
                                              
                }
              }).start(); 
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if (requestCode == 1 && resultCode == RESULT_OK) {
	            Uri imageUri = data.getData();
	            imagePath = foundPath(imageUri);
	            System.out.println("image path "+imagePath);
	            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
	            imageUplaod.setImageBitmap(bitmap);
	             
	        }
	}

	public String foundPath(Uri uri)
	{
		String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        System.out.println("mage path 1"+cursor.getString(column_index));
        return cursor.getString(column_index);
	}
	
	public int fileUpload (String imagePath)
	{
        System.out.println("sourceFileUri :"+imagePath);
        
        String fileName = imagePath;
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
        int bytes, bytesTotal, sizeBuffer;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024 * 20; 
        File sourceFile = new File(imagePath); 
         
        System.out.println("boolean :"+!sourceFile.isFile());
        if (!sourceFile.isFile()) {   
             dialog.dismiss(); 
             runOnUiThread(new Runnable() {
                 public void run() {
                     textErrorMessage.setText("Image does not exist :");
                 }
             }); 
              
             return 0;
          
        }
        else
        {
             try { 
                 FileInputStream fileInputStream = new FileInputStream(sourceFile);
                 URL url = new URL(serverUri);
                  System.out.println("here");
                 conn = (HttpURLConnection) url.openConnection(); 
                 conn.setDoInput(true); 
                 conn.setDoOutput(true); 
                 //conn.setUseCaches(false); 
                 conn.setRequestMethod("POST");
                 
                 conn.setRequestProperty("Connection", "Keep-Alive");
                 conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                 conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//                 conn.setRequestProperty("id", emailId);
//                 conn.setRequestProperty("uploaded_file", fileName); 
//                 conn.setRequestProperty("name", emailId);
//                 conn.setRequestProperty("comment", comment);
//                 conn.addRequestProperty("lat", ""+currLocation.getLatitude());
//                 conn.addRequestProperty("lng", ""+currLocation.getLongitude());
//                 System.out.println("lat and Long "+currLocation.getLatitude()+" "+currLocation.getLongitude() );
//                 conn.addRequestProperty("location", address.getLocality()+" "+address.getCountryName());
//                 System.out.println("address :"+address.getLocality()+" "+address.getCountryName());  
                 dos = new DataOutputStream(conn.getOutputStream());
        
                 dos.writeBytes(twoHyphens + boundary + lineEnd); 
                 dos.writeBytes("Content-Disposition: form-data; name=\"id\"\r\n\r\n"+emailId+"\r\n");
                 dos.writeBytes("Content-Disposition: form-data; name=\"name\"\r\n\r\n"+emailId+"\r\n");
                 dos.writeBytes("Content-Disposition: form-data; name=\"comment\"\r\n\r\n"+comment+"\r\n");
                 dos.writeBytes("Content-Disposition: form-data; name=\"lat\"\r\n\r\n"+currLocation.getLatitude()+"\r\n");
                 dos.writeBytes("Content-Disposition: form-data; name=\"lng\"\r\n\r\n"+currLocation.getLongitude()+"\r\n");
                 dos.writeBytes("Content-Disposition: form-data; name=\"location\"\r\n\r\n"+address.getLocality()+" "+address.getCountryName()+"\r\n");
                 dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"\r\n\r\n" + fileName + "\r\n");
                 //dos.writeBytes(lineEnd);
//                 List<NameValuePair> params = new ArrayList<NameValuePair>();
//                 params.add(new BasicNameValuePair("id", emailId));
//                 params.add(new BasicNameValuePair("uploaded_file", fileName));
//                 params.add(new BasicNameValuePair("name", emailId));
//                 params.add(new BasicNameValuePair("comment", comment));
//                 params.add(new BasicNameValuePair("lat", ""+currLocation.getLatitude()));
//                 params.add(new BasicNameValuePair("lng", ""+currLocation.getLongitude()));
//                 dos.writeBytes(getQueryString(params));
//                 dos.writeBytes(lineEnd);
                 dos.writeBytes(twoHyphens + boundary + lineEnd);
                 //dos.writeBytes("\r\n\r\n");
                 bytesTotal = fileInputStream.available(); 
        
                 sizeBuffer = Math.min(bytesTotal, maxBufferSize);
                 buffer = new byte[sizeBuffer];
                 bytes = fileInputStream.read(buffer, 0, sizeBuffer);  
                    
                 while (bytes > 0) {
                      
                   dos.write(buffer, 0, sizeBuffer);
                   bytesTotal = fileInputStream.available();
                   sizeBuffer = Math.min(bytesTotal, maxBufferSize);
                   bytes = fileInputStream.read(buffer, 0, sizeBuffer);   
                    
                  }
                 dos.writeBytes(lineEnd);
                 dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                 dos.flush();
                 dos.close();
                 fileInputStream.close();
                 
                 conn.connect();
                 
                 // Responses from the server (code and message)
                 serverResCode = conn.getResponseCode();
                 String serverResponseMessage = conn.getResponseMessage();
                   
                 Log.i("uploadFile", "HTTP Response is : "
                         + serverResponseMessage + ": " + serverResCode);
                  
                 if(serverResCode == 200){
                      
                     runOnUiThread(new Runnable() {
                          public void run() {
                              String successMessage = "File Successfully Uploaded";
                              textErrorMessage.setText(successMessage);
                              //textErrorMessage.setText(conn.get);
                              Toast.makeText(ReportProblemActivity.this, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
                          }
                      });                
                 }    
                 
                   
            } catch (MalformedURLException ex) {
                 
                dialog.dismiss();  
                ex.printStackTrace();
                 
                runOnUiThread(new Runnable() {
                    public void run() {
                        textErrorMessage.setText("check PHP url.");
                        Toast.makeText(ReportProblemActivity.this, "URLException", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
            } catch (Exception e) {
                 
                dialog.dismiss();  
                e.printStackTrace();
                 
                runOnUiThread(new Runnable() {
                    public void run() {
                        //messageText.setText("Got Exception : see logcat ");
                        Toast.makeText(ReportProblemActivity.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload file to server Exception", "Exception : "  + e.getMessage(), e);  
            }
            dialog.dismiss();       
            return serverResCode; 
             
         } // End else block 
	}

	private String getQueryString(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean firstValue = true;
		
		for(NameValuePair keyValue : params) {
			if(firstValue) {
				firstValue = false;
			} else {
				result.append("&");
			}
			
			result.append(URLEncoder.encode(keyValue.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(keyValue.getValue(), "UTF-8"));
		}
		
		return result.toString();
	}
	



	
}
