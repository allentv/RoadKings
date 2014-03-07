<?php
session_start();
?>
<html>
	<head>
		<title>Road Kings</title>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.min.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</head>
	<body>
		<table class="mainSection">
			<tr>
				<td>
					<img src="images/roadkings_bt.png" height="500" width="80">
				</td>
				<td>
					<table id="mainSection" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<div class="vendorSection" onclick="javascript:loadRepairSection();">
									<img src="images/transport.png" width="100" height="100">
									<br>
									<font size="5em"><b>Repair Shop</b></font>
									<br>
									<p>
										Are you someone looking to expand the reach of your automotive repair services? Register here and have your brand made known across the RoadKings community
									</p>
								</div>
							</td>
							<td>
								<div>
									&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div class="userSection" onclick="javascript:loadOwnerSection();">
									<img src="images/user.png" width="100" height="100">
									<br>
									<font size="5em"><b>Car Owner</b></font>
									<br>
									<p>
										Do you own a vehicle and have trouble in taking care of your automobile? Go no further, login and enjoy the benefits of being part of the RoadKings community
									</p>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div>
									&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div class="logoSection">
									<img src="images/logo.png" width="130" height="130">
								</div>
							</td>
							<td>
								<div>
									&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="helpSection"onclick="javascript:invokeDealerPage();">
									<br>
									<img src="images/dealer.png">
									<br>
									<font size="5em"><b>Dealer</b></font>
									<br>
									<p>
										Do you deal cars? Register here to improve your chances of new leads.
										<br>
									</p>
								</div>
							</td>
							<td>
								<div>
									&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div class="searchSection" onclick="javascript:loadSearchSection();">
									<img src="images/search.png" width="100" height="100">
									<br>
									<font size="5em"><b>Search</b></font>
									<br>
									<p>
										Search for offers, vendors and information about automobiles
										
									</p>
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<img src="images/roadkings_tb.png" height="500" width="80">
				</td>
			</tr>
		</table>
		<div id="login-dialog-vendor" title="Vendor Login">
			<p>
				<form id="vendorLoginForm" method="POST">
				Enter e-mail : <input type="input" id="txtEmail" name="txtEmail">
				<br>
				<br>
				Enter password : <input type="password" id="txtPassword" name="txtPassword">
				</form>
			</p>
		</div>
		<div id="login-dialog-user" title="User Login">
			<p>
				<form id="userLoginForm" method="POST">
				Enter e-mail : <input type="input" id="txtEmail" name="txtEmail">
				<br>
				<br>
				Enter password : <input type="password" id="txtPassword" name="txtPassword">
				</form>
			</p>
		</div>
		<div id="login-dialog-search" title="Search Login">
			<p>
				<form id="searchLoginForm" method="POST">
				Enter e-mail : <input type="input" id="txtEmail" name="txtEmail">
				<br>
				<br>
				Enter password : <input type="password" id="txtPassword" name="txtPassword">
				</form>
			</p>
		</div>
	</body>
</html>
