<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Nucleus Application</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery-ui.js"></script>
<script>
	var index = 0;
	var clickNext;
	(function myFunction() {
		clickNext = setInterval(function() {
			$('#next').trigger('click');
		}, 5000);
	})();
	$(function() {
		$("#tabs").tabs();
		$("#next").click(function() {
			nextFunction('next');
		});
		$("#prev").click(function() {
			nextFunction('prev');
		});
	});

	function nextFunction(navigate) {
		$.ajax({
			url : "http://localhost:6064/getdata/" + index + "/" + navigate,
			success : function(result) {
				$("#movieDesc").html(result);
			},
			error : function() {
				alert("Ajax not working");
			}
		});
	}

	function validate() {
		if (document.getElementById("login").value == "") {
			alert("User name may not be blank");
		} else if (document.getElementById("password").value == "") {
			alert("Password may not be blank.");
		}
	}
</script>
</head>
<body onload="nextFunction('next');">

	<div id="tabs"
		class="ui-tabs ui-corner-all ui-widget ui-widget-content"
		style="float: left;">
		<ul role="tablist"
			class="ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header">
			<li role="tab" tabindex="0"
				class="ui-tabs-tab ui-corner-top ui-state-default ui-tab ui-tabs-active ui-state-active"
				aria-controls="tabs-1" aria-labelledby="ui-id-1"
				aria-selected="true" aria-expanded="true"><a href="#tabs-1"
				role="presentation" tabindex="-1" class="ui-tabs-anchor"
				id="ui-id-1">Movies</a></li>
			<li role="tab" tabindex="-1"
				class="ui-tabs-tab ui-corner-top ui-state-default ui-tab"
				aria-controls="tabs-2" aria-labelledby="ui-id-2"
				aria-selected="false" aria-expanded="false"><a href="#tabs-2"
				role="presentation" tabindex="-1" class="ui-tabs-anchor"
				id="ui-id-2">Manage</a></li>
		</ul>
		<div id="tabs-1"
			class="movies ui-tabs-panel ui-corner-bottom ui-widget-content"
			aria-labelledby="ui-id-1" role="tabpanel" aria-hidden="false"
			style="float: left;">
			<div class="left">
				<img src="img/prev.png" class="icon" id="prev">
			</div>
			<div class="left" style="width: 75%;" id="movieDesc">
				<div class="left" style="width: 49%;">
					<!-- <img src="img/image1.jpg"> -->
				</div>
				<div class="left"
					style="width: 35%; text-align: justify; padding-left: 4%;"></div>
			</div>
			<div class="left">
				<img src="img/next.png" class="icon" id="next">
			</div>
		</div>
		<div id="tabs-2" aria-labelledby="ui-id-2" role="tabpanel"
			class="ui-tabs-panel ui-corner-bottom ui-widget-content"
			aria-hidden="true" style="display: none;">

			<form action="/submit" method="post">
				<label>Username</label> <input type="text" name="username"
					id="login" , placeholder="Username" /> <br> <label>Password</label>
				<input type="password" name="password" id="password"
					placeholder="Password" /><br> <input type="submit"
					name="Submit" value="Submit" onclick="validate();">
			</form>
		</div>
	</div>
</body>
</html>