<?php
	session_start();
	error_reporting(0);

	$vendor			= $_POST["vendorID"];
	$message		= $_POST["message"];
	$expiry_date	= $_POST["expiry-date"];
	$icon			= "../images/notifications/img1.png";
	
	$file_url = "../data/notifications/notifications.json";
	$file = json_decode(file_get_contents($file_url), true);
	
	if(sizeof($file) > 0) {
		array_push($file, array(
				"name"	=>	$vendor
			,	"icon"	=>	$icon
			,	"text"	=>	$message
			,	"time"	=>	gmdate("Y-m-d H:i:s")
			,	"expiry-date"	=>	$expiry_date
			)
		);
	} else {
		$file = array(array(
				"name"	=>	$vendor
			,	"icon"	=>	$icon
			,	"text"	=>	$message
			,	"time"	=>	gmdate("Y-m-d H:i:s")
			,	"expiry-date"	=>	$expiry_date
			)
		);
	}
	
	file_put_contents($file_url, json_encode($file));
	
	echo "success";
?>
