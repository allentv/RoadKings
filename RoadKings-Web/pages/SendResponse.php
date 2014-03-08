<?php
	error_reporting(0);
	
	// GCM API Key
	$api_key = "AIzaSyDdC0-Vf1EigJYa9zUeUn9KRWWgZuI9GjU";
	
	// Reading the device id
	$file_url = "../data/registration/GCMRegistrationUser.json";
	$file = json_decode(file_get_contents($file_url), true);
	
	
	// Retrieve user name
	$user = $_POST["user"];
	// Retrieve response
	$message = $_POST["message"];
	
	$icon = $_POST["icon"];
	$vendor-id = $_POST["vendorID"];
	$vendor-ph = "(0)865132633";
	$vendor-loc = "Weston St, Near Harold Cross, Dublin 2, Ireland";
	
	// Get user device ID for pushing the response
	$device_id = $file[$user];
	
	//echo "User : $user\n";
	//echo "Message : $message\n";
	//echo "Device ID : $device_id\n";
	
	$url = 'https://android.googleapis.com/gcm/send';
	
	$fields = array (
		'registration_ids' => array($device_id)
	,	'data' => array(
			'message' => $message
		,	'icon' => $icon
		,	'vendor-id' => $vendor-id
		,	'vendor-ph' => $vendor-ph
		,	'vendor-location' => $vendor-loc
		)
	);
	
	$headers = array(
		'Authorization: key='.$api_key
	,	'Content-Type : application/json'
	);
	
	$ch = curl_init();
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
	
	$result = curl_exec($ch);
	curl_close($ch);
	
	echo $result;
?>
