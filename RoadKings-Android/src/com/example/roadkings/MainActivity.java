package com.example.roadkings;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	String emailId;
	String password;
	public static final String sharedData = "sharedData";
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedpreferences = getSharedPreferences(sharedData, Context.MODE_PRIVATE);
		

		Button login_button = (Button)findViewById(R.id.loginButton);
		TextView textView = (TextView)findViewById(R.id.registration_link);
		textView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),RegistrationActivity.class);
				startActivity(intent);
				
			}
		});
			
	}
	public void onClickLogin(View view) {
		// TODO Auto-generated method stub
		System.out.println("here");
		EditText email_text = (EditText)findViewById(R.id.email);
		emailId = email_text.getText().toString();
		System.out.println("email"+emailId);
		EditText password_text = (EditText)findViewById(R.id.password);
		password = password_text.getText().toString();
		System.out.println("pass"+password);
		if(emailId.equals("rajanverma"))
		{
			if(password.equals("rajan"))
			{
				System.out.println("here inside");
				
				SharedPreferences settings = getSharedPreferences("sharedData", 0);
			    SharedPreferences.Editor editor = settings.edit();
			    editor.putString("Email", emailId);
			    System.out.println("commit "+editor.commit());
				Intent intent = new Intent(this, RoadKingsTabActivity.class);
				intent.putExtra("emailId", emailId);
				startActivity(intent);
			}
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
