<?php
	$user = $_POST["emailId"];
	$id = $_POST["regId"];
	$file_url = "../data/registration/GCMRegistrationUser.json";
	$file = json_decode(file_get_contents($file_url), true);
	
	$file[$user] = $id;
	
	file_put_contents($file_url, json_encode($file));
	
	echo "id:".$id;
	echo "success";
?>
