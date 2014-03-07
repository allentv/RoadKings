<?php
	$vendor		= $_POST["vendorID"];
	$message	= $_POST["message"];
	$user 		= $_POST["user"];
	$icon		= "www.scss.tcd.ie/~vargheat/rk/images/responses/img1.png";
	
	$file_url = "../data/responses/responses.json";
	$file = json_decode(file_get_contents($file_url), true);
	
	if(sizeof($file) > 0) {
		array_push($file, array(
				"name"	=>	$vendor
			,	"icon"	=>	$icon
			,	"text"	=>	$message
			,	"time"	=>	gmdate("Y-m-d H:i:s")
			,	"user"	=>	$user
			)
		);
	} else {
		$file = array(array(
				"name"	=>	$vendor
			,	"icon"	=>	$icon
			,	"text"	=>	$message
			,	"time"	=>	gmdate("Y-m-d H:i:s")
			,	"user"	=>	$user
			)
		);
	}
	
	file_put_contents($file_url, json_encode($file));
?>
