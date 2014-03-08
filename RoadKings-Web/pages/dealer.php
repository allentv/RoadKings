<?php
session_start();
//error_reporting(0);
?>
<html>
	<head>
		<title>Road Kings - Help</title>
		<link rel="stylesheet" type="text/css" href="../css/index.css" />
		<link rel="stylesheet" type="text/css" href="../css/help/jquery-ui-1.9.2.custom.min.css" />
		<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript" src="../js/Chart.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				loadNotifications();
				
				$("#createNotificationSection").css("display","none");
				$("#greyBackground").css("display","none");
			});
			
			// Reload the list of people that reports problem every 5 seconds
			setInterval(loadNotifications, 5000);
			
			function sendNotificationToUser() {
				$.post(
						"SaveNotifications.php"
					,	$("#notificationForm").serialize()
					,	function(e) {
							//alert(e);
						}
				);
				$("#createNotificationSection").css("display","none");
				$("#greyBackground").css("display","none");
			}
			
			function cancelNotificationScreen() {
				$("#createNotificationSection").css("display","none");
				$("#greyBackground").css("display","none");
			}
			
			function showNotificationMessageForm() {
				$("#createNotificationSection").css("display","block");
				$("#greyBackground").css("display","block");
			}
			
			function loadNotifications() {
				$.get(
						"GetNotifications.php"
					,	function (data) {
							var data = JSON.parse(data);
							var newData = "";
							var counter = 0;
							for(;counter < data.length;counter++) {
								newData += "<div>";
								newData += "	<table cellpadding='2' cellspacing='1'>";
								newData += "	  	<tr>";
								newData += "			<td width='10%'>";
								newData += "				<img src='"+data[counter].icon+"' width='30' height='40'>";
								newData += "			</td>";
								newData += "			<td width='60%'>";
								newData += data[counter].text;
								newData += "			</td>";
								newData += "			<td>";
								newData += "				|";
								newData += "			</td>";
								newData += "			<td tag='timeago'>";
								newData += getFormattedDate(data[counter].time);
								newData += "			</td>";
								newData += "		</tr>";
								newData += "	</table>";
								newData += "</div><hr>";
							}
							
							$("#notificationSectionContent").html(newData);
						}
				);
			}
			
			function getFormattedDate(d) {
				var date = new Date(d);
				var day = date.getDate();
				var month = date.getMonth() + 1;
				month = (month < 10) ? "0"+month : month;
				var year = date.getFullYear();
				return (day+"/"+month+"/"+year);
			}
		</script>
		<style type="text/css">
			.pageHeaderSection
			{
				background : #f1f1f1;
				color : #848484;
				border-bottom : 1px solid #d7d7d7;
				padding : 5px;
				text-align : left;
				font-size : 19px;
				font-weight : bold;
				vertical-align: text-top;
			}
			
			#notificationSection
			{
				width : 300px;
				border : 1px solid #d7d7d7;
				overflow-y : auto;
				height : 550px;
				position : relative;
				float : left;
				margin-top : 10px;
			}
			
			#profileSection
			{
				width : 550px;
				border : 1px solid #d7d7d7;
				overflow-y : auto;
				height : 550px;
				position : relative;
				float : left;
				margin-left : 10px;
				margin-top : 10px;
			}
			
			#contactSection
			{
				width : 450px;
				border : 1px solid #d7d7d7;
				height : 140px;
				position : relative;
				float : left;
				margin-left : 10px;
				margin-top : 10px;
			}
			
			#responsesSection
			{
				width : 450px;
				border : 1px solid #d7d7d7;
				overflow-y : auto;
				height : 400px;
				position : relative;
				float : left;
				margin-left : 10px;
				margin-top : 10px;
			}
			
			body
			{
				margin-left : 15px;
			}
			
			#createNotificationSection
			{
				position : absolute;
				z-index : 100;
				background : #FFFFFF;
				width : 400px;
				margin : 0px auto;
				left : 0px;
				right : 0px;
				border : 2px solid;
				display : none;
			}
			
			#greyBackground
			{
				position : absolute;
				z-index : 50;
				background : #999999;
				width : 100%;
				height : 100%;
				margin : 0px auto;
				left : 0px;
				right : 0px;
				top : 0px;
				opacity : 0.7;
				display : none;
			}
			
			.btnEnabled {
				background : #f1f1f1;
				color : #000000;
			}
			
			#peopleSectionContent div:hover {
				background : #e5e5ff;
			}
		</style>
	</head>
	<body>
		<center>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<img src="../images/dealer.png" width="120">&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						<h1>Dealer</h1>
					</td>
				</tr>
			</table>
		</center>
		<div id="notificationSection">
			<div class="pageHeaderSection">Notifications<div style="position:relative;float:right;"><!--<input type="button" size="5" value="N" onclick="javascript:showNotificationMessageForm();">--><img src="../images/new_item.png" style="cursor:hand;" title="Create new notification" onclick="javascript:showNotificationMessageForm();"></div></div>
			<div id="notificationSectionContent">
			<!--
			<div id="user" class="unRegUser">
				<table cellpadding="3" cellspacing="3">
					<tr>
						<td width="10%">
							<img src="../images/unreg_user.png" width="30" height="40">
						</td>
						<td tag="location" width="50%">
							Tallaghat
						</td>
						<td>
							|
						</td>
						<td tag="timeago">
							50 min
						</td>
						<td>
							ago
						</td>
					</tr>
				</table>
			</div>
			<div id="user" class="unRegUser">
				<table cellpadding="3" cellspacing="3">
					<tr>
						<td width="10%">
							<img src="../images/unreg_user.png" width="30" height="40">
						</td>
						<td tag="location" width="50%">
							Crumlin
						</td>
						<td>
							|
						</td>
						<td tag="timeago">
							50 min
						</td>
						<td>
							ago
						</td>
					</tr>
				</table>
			</div>
			-->
			</div>
		</div>
		<div id="profileSection">
			<div class="pageHeaderSection">Profile</div>
			<br>
			<form id="profileSectionForm">
				<div id="profileSectionContent">
					&nbsp;<b>About</b>
					<textarea id="about_content" name="about" rows="5" cols="65" style="display:none;">
						Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
					</textarea>
					<div id="about_display" style="text-align : justify;text-justify: inter-word;padding:5px;">
						Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
					</div>
					<br>
					&nbsp;<b>Description</b>
					<textarea id="description_content" name="description" rows="5" cols="65" style="display:none;">
						Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
					</textarea>
					<div id="description_display" style="text-align : justify;text-justify: inter-word;padding:5px;">
						Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
					</div>
					<br>
					&nbsp;<b>Tags</b>
					<br>
					<div id="tags_content" name="tags" style="display:none;">
						Engine Tuning; Power Enhancements; Torque Blaster; AC Upgrade;
					</div>
					<div id="tags_display" style="padding:5px;">
						Engine Tuning; Power Enhancements; Torque Blaster; AC Upgrade;
					</div>
				</div>
			</form>
		</div>
		<div id="contactSection">
			<div class="pageHeaderSection">Contact</div>
			<table cellpadding="0" cellspacing="1">
				<tr>
					<td>
						<img src="../images/phone.png" width="30" height="30">
					</td>
					<td>
						+353 899 657 732
					</td>
					<td width="70">
						&nbsp;
					</td>
					<td>
						<img src="../images/facebook.png" width="30" height="30">&nbsp;
					</td>
					<td>
						MyDealer
					</td>
				</tr>
				<tr>
					<td>
						<img src="../images/twitter.png" width="30" height="30">&nbsp;
					</td>
					<td>
						@MyDealer
					</td>
					<td width="70">
						&nbsp;
					</td>
					<td>
						<img src="../images/google+.png" width="30" height="30">&nbsp;
					</td>
					<td>
						MyDealer
					</td>
				</tr>
				<tr>
					<td>
						<img src="../images/pintrest.png" width="30" height="30">&nbsp;
					</td>
					<td>
						MyDealer
					</td>
					<td width="70">
						&nbsp;
					</td>
					<td>
						<img src="../images/youtube.png" width="30" height="30">
					</td>
					<td>
						MyDealer
					</td>
				</tr>
			</table>
		</div>
		<div id="responsesSection">
			<div class="pageHeaderSection">Notifications Sent</div>
			<br>
			<br>
			<canvas id="notificationsChart" width="420" height="300"></canvas>
			<script type="text/javascript">
				//Get the context of the canvas element we want to select
				var ctx = document.getElementById("notificationsChart").getContext("2d");
				var data = {
					labels : ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
					datasets : [
						{
							fillColor : "rgba(125,0,0,0.5)",
							strokeColor : "rgba(125,0,0,1)",
							data : [28,48,40,19,96,27,100,23,55,66,33,44]
						}
					]
				};
				
				var notificationsChart = new Chart(ctx).Bar(data);
				
				function sortJSON(data, key) {
					return data.sort(function(a, b) {
						var x = a[key]; var y = b[key];
						return ((x < y) ? -1 : ((x > y) ? 1 : 0));
					});
				}
			</script>
		</div>
		<div id="createNotificationSection">
			<div class="pageHeaderSection">Create Notification</div>
			<br>
			<center>
			<form id="notificationForm" action="" method="POST">
				<table cellpadding="5">
					<tr>
						<td>
							Dealer ID
						</td>
						<td>
							:
						</td>
						<td>
							<input type="text" name="vendorID" value="1111" readonly>
						</td>
					</tr>
					<tr>
						<td>
							Icon
						</td>
						<td>
							:
						</td>
						<td>
							<img src="../images/notifications/img1.png">
						</td>
					</tr>
					<tr>
						<td>
							Message
						</td>
						<td>
							:
						</td>
						<td>
							<textarea rows="8" cols="25" name="message"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							Expiry Date
						</td>
						<td>
							:
						</td>
						<td>
							<input type="text" name="expiry-date" size="25">
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<input type="button" class="btnEnabled" onclick="javascript:sendNotificationToUser();" value="Send Notification">
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<input type="button" class="btnEnabled" value="Cancel" onclick="javascript:cancelNotificationScreen();">
						</td>
					</tr>
				</table>
			</form>
			</center>
		</div>
		<div id="greyBackground"></div>
	</body>
</html>
