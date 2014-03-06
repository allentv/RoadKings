package com.example.Computation;

public enum RegistartionEnum {
	EmailIdAlreadyInUSe("Email Id Already In Use"), PasswordNotSame("Password and Confirm Password are not same"), 
	IncorrectEmailIdFormat("Incorrect Email Id Format");
	 
	private String statusCode;
 
	private RegistartionEnum(String s) {
		statusCode = s;
	}
 
	public String getErrorMessage() {
		return statusCode;
	}
}
