<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<!-- Load an icon library to show a hamburger menu (bars) on small screens -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.btn-lg {
	height: 200px;
	width: 100%;
	font-size: 70px;
	background-color: #0e1a40;
	border-color: #0e1a40;
}

.resultdiv {
	font-size: 80px;
	height: 100px;
}


/* Style the navigation menu */
.topnav {
  overflow: hidden;
  background-color: #333;
  position: relative;
}

/* Hide the links inside the navigation menu (except for logo/home) */
.topnav #myLinks {
  display: none;
}

/* Style navigation menu links */
.topnav a {
  color: white;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 60px;
  display: block;
}

/* Style the hamburger menu */
.topnav a.icon {
  background: black;
  display: block;
  position: absolute;
  right: 0;
  top: 0;
}

/* Add a grey background color on mouse-over */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

/* Style the active link (or home/logo) */
.active {
  background-color: #000000;
  color: white;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script>

	/* Toggle between showing and hiding the navigation menu links when the user clicks on the hamburger menu / bar icon */
	function myFunction() {
		var x = document.getElementById("myLinks");
		if (x.style.display === "block") {
			x.style.display = "none";
		} else {
			x.style.display = "block";
		}
	}

	$(document).ready(function() {
		$("#cookiebutton").click(function() {
			showLoading("cookie")
			setTimeout(function() {
				ajax("cookie");
			}, 500)
		});
		$("#dinnerbutton").click(function() {
			showLoading("dinner")
			setTimeout(function() {
				ajax("dinner");
			}, 500)
		});
		$("#restaurantbutton").click(function() {
			showLoading("restaurant")
			setTimeout(function() {
				ajax("restaurant");
			}, 500)
		});
		$("#datebutton").click(function() {
			showLoading("date")
			setTimeout(function() {
				ajax("date");
			}, 500)
		});
	});

	function showLoading(prefix) {
		$("#" + prefix + "resultdiv")
				.html(
						'<img src="${pageContext.request.contextPath}/loading-96.gif" alt="this slowpoke moves"  height="100px" />');
	}

	function ajax(prefix) {
		$.ajax({
			url : "${pageContext.request.contextPath}" + prefix,
		}).done(function(data) {
			$("#" + prefix + "resultdiv").html(data);
		});
	}
</script>
</head>
<body style="background-color: #946b2d">
	<!-- Top Navigation Menu -->
	<div class="topnav" style="font-size:70px">
		<a href="/" class="active">Home</a>
		<!-- Navigation links (hidden by default) -->
		<div id="myLinks">
			<a href="/manage">Manage</a> 
		</div>
		<!-- "Hamburger menu" / "Bar icon" to toggle the navigation links -->
		<a href="javascript:void(0);" class="icon" onclick="myFunction()">
			<i class="fa fa-bars"></i>
		</a>
	</div>
	<div>
		<br />
		<div class="container-fluid">
			<h1>Choose your fate!</h1>
		</div>
		<br />
		<div class="container-fluid">
			<!-- Cookies -->
			<div class="row">
				<div class="col-sm">
					<button id="cookiebutton" class="btn btn-primary btn-lg">Choose a cookie</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<div id="cookieresultdiv" class="resultdiv"></div>
				</div>
			</div>
			<br />
			<!-- Dinner -->
			<div class="row">
				<div class="col-sm">
					<button id="dinnerbutton" class="btn btn-primary btn-lg">Choose a dinner</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<div id="dinnerresultdiv" class="resultdiv"></div>
				</div>
			</div>
			<br />
			<!-- Restaurants -->
			<div class="row">
				<div class="col-sm">
					<button id="restaurantbutton" class="btn btn-primary btn-lg">Choose a restaurant</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<div id="restaurantresultdiv" class="resultdiv"></div>
				</div>
			</div>
			<br/>
			<!-- Dates -->
			<div class="row">
				<div class="col-sm">
					<button id="datebutton" class="btn btn-primary btn-lg">Choose a date</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<div id="dateresultdiv" class="resultdiv"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>