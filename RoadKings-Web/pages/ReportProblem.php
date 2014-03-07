<?php
	$request_array = array_merge($_POST,$_GET);

	$id 		= $request_array["id"];
	$name		= $request_array["name"];
	$comment 	= $request_array["comment"];
	$lat 		= $request_array["lat"];
	$lng 		= $request_array["lng"];
	$loc_name	= $request_array["location"];
	
	$prob_file_url = '../data/problems/problems.json';
	$img_file_path = "../data/pics/";
	
	$img_file_path = $img_file_path . $id . "/" . basename( $_FILES['uploaded_file']['name']);
	if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $img_file_path)) {
	    $dataWrite = array(
								"id"		=> $id
							,	"pid"		=> "p".date("YmdHis")
							,	"name"		=> $name
							,	"photo"		=> $img_file_path
							,	"comment"	=> $comment
							,	"location" => array(
										"lat" => $lat
									,	"lng" => $lng
									,	"name" => $loc_name
								)
							,	"datetime" => gmdate("Y-m-d H:i:s")
						);
	    
		// If file upload successful, then persist other data to disk
	    if(!file_exists($prob_file_url)) {
			$fp = fopen($prob_file_url,'w');
			
			$temp_array = array();
			array_push($temp_array, $dataWrite);
			
			fwrite($fp, json_encode($temp_array));
			fclose($fp);
		} else {
			$file = json_decode(file_get_contents($prob_file_url), true);
			
			array_push($file, $dataWrite);
			file_put_contents($prob_file_url, json_encode($file));
		}
		echo "success";
	} else {
	    echo "fail";
	}
?>
