<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- Load an icon library to show a hamburger menu (bars) on small screens -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.btn-lg {
	height: 133px;
	width: 100%;
	font-size: 70px;
/* 	background-color: #0e1a40; */
/* 	border-color: #0e1a40; */
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

.item-select {
	font-size: 95px;
}

.select-label {
	font-size: 95px;
}

.item {
	font-size: 95px;
}

.item-input {
	height: 133px;
	font-size: 65px;
	width: 100%;
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
	
	function refreshList(){
		var selected = $("#myItemChoice option:selected").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/manage/list/" + selected,
		}).done(function(data) {
			var myArr = data.split("|");
			var html = "";
			for (var i = 0; i < myArr.length; i++) {
				var entry = myArr[i];
				html += "<div class='row'>"
						+ "<div class='col-sm-9'>"
							+ "<span class='item'>"+entry+ "</span>"
						+ "</div>"
						+ "<div class='col-sm-2'>"
							+ "<button id='"+entry+ "remove-item-button' class='remove-item-button btn btn-danger btn-lg'>-</button>"
						+ "</div>"
						+ "</div>";
			}
			$("#item-container").html(html);
			addListeners();
		});
	}
	
	function addListeners(){
		$(".remove-item-button").click(function(){
			var selected = $("#myItemChoice option:selected").val()
			var removeItem = $(this).parent().parent().children(".col-sm-9").children().text()
			if (confirm('Are you sure?')) {
				$.ajax({
					url : "${pageContext.request.contextPath}/manage/remove/" + selected + "/" + removeItem,
				}).done(function(data){
					refreshList();
				});
			}
		});
	}

	$(document).ready(function() {
		$("#myItemChoice").change(function() {
			refreshList();
		});
		$("#add-item-button").click(function(){
			var selected = $("#myItemChoice option:selected").val()
			var newItem = $("#add-item-input").val()
			$.ajax({
				url : "${pageContext.request.contextPath}/manage/add/" + selected + "/" + newItem,
			}).done(function(data){
				$("#add-item-input").val("");
				refreshList();
			});
		});
		
		addListeners();
	});
</script>
</head>
<body style="background-color: #946b2d">
	<!-- Top Navigation Menu -->
	<div class="topnav" style="font-size: 70px">
		<a href="/" class="active">Manage</a>
		<!-- Navigation links (hidden by default) -->
		<div id="myLinks">
			<a href="/">Home</a>
		</div>
		<!-- "Hamburger menu" / "Bar icon" to toggle the navigation links -->
		<a href="javascript:void(0);" class="icon" onclick="myFunction()">
			<i class="fa fa-bars"></i>
		</a>
	</div>
	<div>
		<br />
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-4">
					<span class="select-label">List of</span>
				</div>
				<div class="col-sm">
					<select id="myItemChoice" class="item-select">
						<option value="cookie">Cookies</option>
						<option value="dinner">Dinners</option>
						<option value="restaurant">Restaurants</option>
						<option value="date">Dates</option>
					</select>
				</div>
			</div>
		</div>
		<br />
		<div id="item-container" class="container-fluid">
			<c:forEach var="item" items="${list}">
				<div class="row">
					<div class="col-sm-9">
						<span class="item">${item}</span>
					</div>
					<div class="col-sm-2">
						<button id="${item}-remove-item-button" class="remove-item-button btn btn-danger btn-lg">-</button>
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="add-item-container" class="container-fluid">
			<div class="row">
				<div class="col-sm-9">
					<input class="item-input" type="text" id="add-item-input" />
				</div>
				<div class="col-sm-2">
					<button id="add-item-button" class="btn btn-primary btn-lg">+</button>
				</div>
			</div>
		</div>
	</div>
</body>