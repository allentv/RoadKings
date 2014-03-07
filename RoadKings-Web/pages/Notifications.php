<?php
	$notification_file_path = "../data/notifications/notifications.json";
	
	// Merging GET & POST request arrays
	$req_array = array_merge($_GET,$_POST);
	$user = $req_array["id"];
	
	$file = json_decode(file_get_contents($notification_file_path),true);
	
	$result = array();
	
	foreach($file as $value) {
		if($value["user"] === $user) {
			array_push($result, $value);
		}
	}
	
	echo json_encode($result);
?>
