$(function() {
	$( "#login-dialog-vendor" ).dialog({
		autoOpen : false,
		height: 300,
		width : 300,
		modal: true,
		buttons : {
			"Submit" : function() {
				doVendorLogin();
			},
			"Cancel" : function() {
				$("#login-dialog-vendor").dialog("close");
			}
		}
	});
	$( "#login-dialog-user" ).dialog({
		autoOpen : false,
		height: 300,
		width : 300,
		modal: true,
		buttons : {
			"Submit" : function() {
				doUserLogin();
			},
			"Cancel" : function() {
				$("#login-dialog-user").dialog("close");
			}
		}
	});
	$( "#login-dialog-search" ).dialog({
		autoOpen : false,
		height: 300,
		width : 300,
		modal: true,
		buttons : {
			"Submit" : function() {
				doSearchLogin();
			},
			"Cancel" : function() {
				$("#login-dialog-search").dialog("close");
			}
		}
	});
});

// This vaue is to be printed from PHP
var loggedIn = true;		
//var loggedIn = false;

function loadRepairSection() {
	// After user has logged in, clicking this link
	// should take the user directly to the page
	// without the login screen
	if(loggedIn) {
		invokeRepairPage();
	} else {
		$("#login-dialog-vendor").dialog("open");
	}
}

function loadOwnerSection() {
	// After user has logged in, clicking this link
	// should take the user directly to the page
	// without the login screen
	if(loggedIn) {
		invokeOwnerPage();
	} else {
		$("#login-dialog-user").dialog("open");
	}
}

function loadSearchSection() {
	// After user has logged in, clicking this link
	// should take the user directly to the page
	// without the login screen
	if(loggedIn) {
		invokeSearchPage();
	} else {
		$("#login-dialog-search").dialog("open");
	}
}

function doRepairLogin() {
	//alert("Login Successful!");
	$("#login-dialog-vendor").dialog("close");
	invokeRepairPage();
}

function doOwnerLogin() {
	$("#login-dialog-user").dialog("close");
	invokeOwnerPage();
}

function doSearchLogin() {
	$("#login-dialog-search").dialog("close");
	invokeSearchPage();
}

function invokeRepairPage() {
	//window.location.replace("pages/vendor.php");
	window.location.href = "pages/repair.php";
	//$(location).attr("href","roadkings/pages/vendor.php");
}

function invokeOwnerPage() {
	//window.location.replace("pages/vendor.php");
	window.location.href = "pages/owner.php";
	//$(location).attr("href","roadkings/pages/vendor.php");
}

function invokeSearchPage() {
	//window.location.replace("pages/vendor.php");
	window.location.href = "pages/search.php";
	//$(location).attr("href","roadkings/pages/vendor.php");
}

function invokeDealerPage() {
	//window.location.replace("pages/vendor.php");
	window.location.href = "pages/dealer.php";
	//$(location).attr("href","roadkings/pages/vendor.php");
}