<?php
session_start();
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Road Kings - Repair Shop</title>
		<link rel="stylesheet" type="text/css" href="../css/vendor/jquery-ui-1.9.2.custom.min.css" />
		<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript" src="../js/Chart.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				loadReportProblem();
				
				$("#createNotificationSection").css("display","none");
				$("#greyBackground").css("display","none");
			});
		
			function sendResponseToUser() {
				$.post(
						"SaveResponses.php"
					,	$("#notificationForm").serialize()
					,	function(e) {
						//alert(e);
					}
				);
				$("#createResponseSection").css("display","none");
				$("#greyBackground").css("display","none");
			}
			
			function cancelResponseScreen() {
				$("#createResponseSection").css("display","none");
				$("#greyBackground").css("display","none");
			}
			
			function showNotificationMessageForm(userID) {
				$("#createResponseSection").css("display","block");
				$("#greyBackground").css("display","block");
				$("#txtChosenUser").val(userID);
			}
			
			// Reload the list of people that reports problem every 5 seconds
			setInterval(loadReportProblem, 5000);
			
			function loadReportProblem() {
				//alert("hi");
				$.get(
						"GetProblems.php"
					,	function(data) {
						//alert(data);
						var data = JSON.parse(data);
						//alert(data[0].photo);
						var newData = "";
						var counter=0;
						var userID = ["1111","2222","1111"];
						for(;counter < data.length;counter++) {
							newData += "<div id='user_reg' class='regUser' onclick='javascript:showNotificationMessageForm(\""+userID[counter]+"\");'>";
							newData += "	<table cellpadding='3' cellspacing='3'>";
							newData += "	  	<tr>";
							newData += "			<td width='10%'>";
							newData += "				<img src='../images/reg_user_male.png' width='30' height='40'>";
							newData += "			</td>";
							newData += "			<td tag='location' width='55%'>";
							newData += data[counter].name;
							newData += "			</td>";
							newData += "			<td>";
							newData += "				|";
							newData += "			</td>";
							newData += "			<td tag='timeago' wrap>";
							newData += data[counter].datetime;
							newData += "			</td>";
							newData += "		</tr>";
							newData += "	</table>";
							newData += "</div>";
						}
						
						$("#peopleSectionContent").html(newData);
					}
				);
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
			
			#peopleSection
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
			
			#contentSection
			{
				background : #FFFFFF;
			}
			
			body
			{
				margin-left : 15px;
			}
			
			.regUser
			{
				
				vertical-align : middle;
			}
			
			.unRegUser
			{
				
			}
			
			#createResponseSection
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
						<img src="../images/transport.png" width="80">&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						<h1>Repair Shop</h1>
					</td>
				</tr>
			</table>
		</center>
		<div id="contentSection">
			<div id="peopleSection">
				<div class="pageHeaderSection">People</div>
				<div id="peopleSectionContent" style="cursor:pointer;">
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
				<table cellpadding="0" cellspacing="2">
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
							MyRepairs
						</td>
					</tr>
					<tr>
						<td>
							<img src="../images/twitter.png" width="30" height="30">&nbsp;
						</td>
						<td>
							@MyRepairs
						</td>
						<td width="70">
							&nbsp;
						</td>
						<td>
							<img src="../images/google+.png" width="30" height="30">&nbsp;
						</td>
						<td>
							MyRepairs
						</td>
					</tr>
					<tr>
						<td>
							<img src="../images/pintrest.png" width="30" height="30">&nbsp;
						</td>
						<td>
							MyRepairs
						</td>
						<td width="70">
							&nbsp;
						</td>
						<td>
							<img src="../images/youtube.png" width="30" height="30">
						</td>
						<td>
							MyRepairs
						</td>
					</tr>
				</table>
			</div>
			<div id="responsesSection">
				<div class="pageHeaderSection">Responses Sent</div>
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
								fillColor : "rgba(0,125,0,0.5)",
								strokeColor : "rgba(0,125,0,1)",
								pointColor : "rgba(0,125,0,1)",
								pointStrokeColor : "#fff",
								data : [28,48,40,19,96,27,100,23,55,66,33,44]
							}
						]
					};
					var notificationsChart = new Chart(ctx).Line(data);
					
					function sortJSON(data, key) {
						return data.sort(function(a, b) {
							var x = a[key]; var y = b[key];
							return ((x < y) ? -1 : ((x > y) ? 1 : 0));
						});
					}
				</script>
			</div>
		</div>
		<div id="createResponseSection">
			<div class="pageHeaderSection">Create Response</div>
			<br>
			<center>
			<form id="notificationForm" action="" method="POST">
				<table cellpadding="5">
					<tr>
						<td>
							ID
						</td>
						<td>
							:
						</td>
						<td>
							<input type="text" name="vendorID" value="1111">
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
							<input type="text" name="message" size="35">
						</td>
					</tr>
					<tr>
						<td>
							User
						</td>
						<td>
							:
						</td>
						<td>
							<input type="text" name="user" id="txtChosenUser" readonly>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<input type="button" class="btnEnabled" onclick="javascript:sendResponseToUser();" value="Send Response">
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<input type="button" class="btnEnabled" value="Cancel" onclick="javascript:cancelResponseScreen();">
						</td>
					</tr>
				</table>
			</form>
			</center>
		</div>
		<div id="greyBackground"></div>
	</body>
</html>
