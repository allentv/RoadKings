package com.example.roadkings;

import com.example.Computation.RegistartionEnum;
import com.example.Computation.RegistrationComputation;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class RegistrationActivity extends Activity {
	
	RegistrationComputation registrationComputation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		Button button = (Button)findViewById(R.id.registerationButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}
	
	
	@SuppressWarnings("deprecation")
	public void onClickRegistration(View view)
	{
		System.out.println("here");
		TextView registerationName = (TextView)findViewById(R.id.nameregistration);
		TextView registerationEmail = (TextView)findViewById(R.id.emailRegistration);
		TextView registerationPassword = (TextView)findViewById(R.id.passwordRegistration);
		TextView registerationPasswordConfirm = (TextView)findViewById(R.id.passwordRegistrationConfirm);
		String alertDialogMessage = null;
		
		registrationComputation = new RegistrationComputation();
		if(!(registrationComputation.verifyEmail(registerationEmail.toString())))
		{
			alertDialogMessage = RegistartionEnum.EmailIdAlreadyInUSe.getErrorMessage();
		}
		if(registrationComputation.verifyPassword(registerationPassword.toString(), registerationPasswordConfirm.toString()))
		{
			alertDialogMessage = RegistartionEnum.PasswordNotSame.getErrorMessage();
		}
		if(registrationComputation.emailFormat(registerationEmail.toString()))
		{
			alertDialogMessage = RegistartionEnum.IncorrectEmailIdFormat.getErrorMessage();
		}
		
		System.out.println("Alert Dialog"+ alertDialogMessage);
		
		if(alertDialogMessage !=null)
		{
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Error Message");
			alertDialog.setMessage(alertDialogMessage);
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			// here you can add functions
			}
			});
			alertDialog.show();
		}
	}
}
