<?php
session_start();
?>
<html>
	<head>
		<title>Road Kings - Help</title>
		<link rel="stylesheet" type="text/css" href="../css/index.css" />
		<link rel="stylesheet" type="text/css" href="../css/help/jquery-ui-1.9.2.custom.min.css" />
		<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript">
			$(function() {
				
			});
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
	</body>
</html>
