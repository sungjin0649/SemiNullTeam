<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>영화 정보</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>


<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		var x = "";
		var num =0;	
		var count = $('#moviepeoples').text().substring(0,1);
		var arr=[];
<%-- 좌석클릭시 색상바꾸기 --%>
	$("input").click(function() {
		if(num==count){
			return;
		}
			$('input').removeClass("buttonactive");
			$(this).addClass("buttonactive");
			$('.aa').removeClass("aa");
			$(this).addClass("aa");

		})
<%-- 좌석 선택 클릭 (선택한 좌석 값 저장) --%>
	$("#seatcheck").click(function() {
		if(num==count){
			return;
		}
		if($('.buttonactive').eq('0').val().length < 4){
			x += $('.buttonactive').eq('0').val() + " ";
			$('#movieseat').html(x);
			$("input").removeClass("buttonactive");
			$(".aa").addClass("buttonactive2");
			num++;
		}

		})
		
		<%-- 다시선택 클릭 --%>
		$("#CancelButton").click(function(){
			x= "";
			$("input").removeClass("buttonactive2");
			$('input[name=seatResult]').attr('value',"");
			$('#movieseat').html("");
			
		})
		
		$('#PayButton').click(function(){
		$('#img').attr("value", $("#view").attr("src"));
		$('#name').attr("value", $("#moviename").text());
		$('#place').attr("value", $("#movieplace").text());
		$('#date').attr("value", $("#moviedate").text());
		$('#time').attr("value", $("#movietime").text());
		$('#count').attr("value", $("#moviepeoples").text());
		$('#seat').attr("value", $("#movieseat").text());
		var formbtn = document.movieinfo
		if($.trim($("#seat").val()) == ""){
			alert("좌석을 선택하세요");
			return false;
		}
		if(num+1 <=count){
			alert(count-num+"개의 좌석을 더고르세요");
			return false;
		}
		formbtn.submit();
	})
		
	})
</script>
<style>
.navbar .navbar-nav {
	display: inline-block;
	float: none;
}

.navbar .navbar-collapse {
	text-align: center;
}

.navc {
	width: 50px;
}

.row {
	display: flex;
	justify-content: center;
}
/* 여기는 body */
* {
	font-size: 11px;
}

.div_title {
	font-size: 25px;
	background-color: #535353;
	color: white;
	text-align: center;
	border-bottom: 1px solid black;
}

.movie_form {
	margin: 0px auto;
	position: relative;
	display: block;
	justify-content: center;
	height: 600px;
	width: 943px;
}

.info_movie {
	position: absolute;
	width: 220px;
	height: 400px;
	left: 10px;
	top: 10px;
	border: 1px solid black;
}

.seat_movie {
	position: absolute;
	width: 700px;
	height: 450px;
	left: 240px;
	top: 10px;
	border: 1px solid black;
}

li {
	list-style: none;
}

.seat-picker-label {
	background-color: #32a690;
	color: #ffffff;
	display: flex;
	justify-content: center;
	padding: 7px;
}

.buttonactive {
	background-color: blue;
}

.buttonactive2 {
	background-color: red;
}

.seat.selected {
	background: blue;
}

.seat.occupied {
	background: red;
}

.showcase {
	background: white;
	border-radius: 5px;
	padding: 5px 10px;
	list-style-type: none;
	display: flex;
}

.showcase li {
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0 10px;
}

.showcase li small {
	margin-left: 2px;
}

.seat {
	background: gray;
	height: 14px;
	width: 18px;
	margin: 4px;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border-bottom-left-radius: 3px;
	border-bottom-right-radius: 3px;
}
</style>
</head>
<body>
	<!-- Static navbar -->
	<nav class="navbar navbar-default navbar-static-top">
		<div align="right">
			<a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a>
		</div>
		<div align="center">
			<img style="text-align: center; width: 100px" src="../image/logo.png">
		</div>
		<div class="container">
			<div id="navbar" class="navbar-collapse collapse ">
				<ul class="nav navbar-nav">
					<li class="active"><a href="">영화</a></li>
					<li style="width: 50px;">&nbsp;</li>
					<li><a href="#about">영화예매</a></li>
					<li style="width: 50px;">&nbsp;</li>
					<li><a href="#contact">커뮤니티</a></li>
					<li style="width: 50px;">&nbsp;</li>
					<li><a href="#contact">고객센터</a></li>
					<li class="dropdown"></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>


	<!-- 영화 정보 -->
	<div class="container">
		<div class="movie_form" style="height: 50px; border: none">
			<p style="font-size: 30pt;">예매확인</p>
		</div>
		<div class="movie_form">
			<div class="info_movie">
				<div class="div_title">영화정보</div>
				<p style="text-align: center">
					<%request.setCharacterEncoding("UTF-8");%>
					<img style="text-align: center; width: 150px; height: 200px; margin-top: 20px;"
						src="<%=request.getParameter("img") %>" id="view">

				</p>
				<div class="movieinfo">
					<ul>
						<li><span style="font-size: 15px">영화 이름 : <%=request.getParameter("name") %></span> <span
							id="moviename"></span></li>
						<li><span>영화 장소 : </span><span id="movieplace"><%=request.getParameter("place") %></span></li>
						<li><span>영화 날짜 : </span> <span id="moviedate"><%=request.getParameter("date") %></span></li>
						<li><span>시간 : </span> <span id="movietime"><%=request.getParameter("time") %></span></li>
						<li><span>인원 : </span> <span id="moviepeoples"><%=request.getParameter("count") %></span></li>
						<li><span>좌석 : </span> <span id="movieseat"></span></li>
					</ul>
				</div>



			</div>

			<div class="seat_movie">
				<div class="div_title">좌석선택</div>
				<div class="seat-picker-label">Screen</div>
				<br>
				<ul class="showcase">
					<li>
						<div class="seat"></div> <small>빈 좌석</small>
					</li>
					<li>
						<div class="seat selected"></div> <small>선택한 좌석</small>
					</li>
					<li>
						<div class="seat occupied"></div> <small>예매된 좌석</small>
					</li>
				</ul>


				<div class="row">
					<input type="button" value="a1"> <input type="button"
						id="btn1" value="a2">&emsp; <input type="button"
						value="a3"> <input type="button" value="a4"> <input
						type="button" id="btn1" value="a5"> <input type="button"
						id="btn1" value="a6"> <input type="button" id="btn1"
						value="a7"> <input type="button" id="btn1" value="a8">
					<input type="button" id="btn1" value="a9"> <input
						type="button" id="btn1" value="a10">&emsp; <input
						type="button" id="btn1" value="b11"> <input type="button"
						id="btn1" value="a12">
				</div>
				<br>
				<div class="row">
					<input type="button" value="b1"> <input type="button"
						id="btn1" value="b2">&emsp; <input type="button"
						value="b3"> <input type="button" value="b4"> <input
						type="button" id="btn1" value="b5"> <input type="button"
						id="btn1" value="b6"> <input type="button" id="btn1"
						value="b7"> <input type="button" id="btn1" value="b8">
					<input type="button" id="btn1" value="b9"> <input
						type="button" id="btn1" value="b10">&emsp; <input
						type="button" id="btn1" value="b11"> <input type="button"
						id="btn1" value="b12">
				</div>
				<br>
				<div class="row">
					<input type="button" value="c1"> <input type="button"
						id="btn1" value="c2">&emsp; <input type="button"
						value="c3"> <input type="button" value="c4"> <input
						type="button" id="btn1" value="c5"> <input type="button"
						id="btn1" value="c6"> <input type="button" id="btn1"
						value="c7"> <input type="button" id="btn1" value="c8">
					<input type="button" id="btn1" value="c9"> <input
						type="button" id="btn1" value="c10">&emsp; <input
						type="button" id="btn1" value="c11"> <input type="button"
						id="btn1" value="c12">
				</div>
				<br>
				<div class="row">
					<input type="button" value="d1"> <input type="button"
						id="btn1" value="d2">&emsp; <input type="button"
						value="d3"> <input type="button" value="d4"> <input
						type="button" id="btn1" value="d5"> <input type="button"
						id="btn1" value="d6"> <input type="button" id="btn1"
						value="d7"> <input type="button" id="btn1" value="d8">
					<input type="button" id="btn1" value="d9"> <input
						type="button" id="btn1" value="d10">&emsp; <input
						type="button" id="btn1" value="d11"> <input type="button"
						id="btn1" value="d12">
				</div>
				<br>
				<div class="row">
					<input type="button" value="e1"> <input type="button"
						id="btn1" value="e2">&emsp; <input type="button"
						value="e3"> <input type="button" value="e4"> <input
						type="button" id="btn1" value="e5"> <input type="button"
						id="btn1" value="e6"> <input type="button" id="btn1"
						value="e7"> <input type="button" id="btn1" value="e8">
					<input type="button" id="btn1" value="e9"> <input
						type="button" id="btn1" value="e10">&emsp; <input
						type="button" id="btn1" value="e11"> <input type="button"
						id="btn1" value="e12">
				</div>
				<br>
				<div class="row">
					<input type="button" value="f1"> <input type="button"
						id="btn1" value="f2">&emsp; <input type="button"
						value="f3"> <input type="button" value="f4"> <input
						type="button" id="btn1" value="f5"> <input type="button"
						id="btn1" value="f6"> <input type="button" id="btn1"
						value="f7"> <input type="button" id="btn1" value="f8">
					<input type="button" id="btn1" value="f9"> <input
						type="button" id="btn1" value="f10">&emsp; <input
						type="button" id="btn1" value="f11"> <input type="button"
						id="btn1" value="f12">
				</div>
				<br>
				<div class="row">
					<input type="button" value="g1"> <input type="button"
						id="btn1" value="g2">&emsp; <input type="button"
						value="g3"> <input type="button" value="g4"> <input
						type="button" id="btn1" value="g5"> <input type="button"
						id="btn1" value="g6"> <input type="button" id="btn1"
						value="g7"> <input type="button" id="btn1" value="g8">
					<input type="button" id="btn1" value="g9"> <input
						type="button" id="btn1" value="g10">&emsp; <input
						type="button" id="btn1" value="g11"> <input type="button"
						id="btn1" value="g12">
				</div>
				<br>
				<div class="row">
					<input type="button" value="h1"> <input type="button"
						id="btn1" value="h2">&emsp; <input type="button"
						value="h3"> <input type="button" value="h4"> <input
						type="button" id="btn1" value="h5"> <input type="button"
						id="btn1" value="h6"> <input type="button" id="btn1"
						value="h7"> <input type="button" id="btn1" value="h8">
					<input type="button" id="btn1" value="h9"> <input
						type="button" id="btn1" value="h10">&emsp; <input
						type="button" id="btn1" value="h11"> <input type="button"
						id="btn1" value="h12">
				</div>
				<form action="MoviePay.jsp" method="post" name ="movieinfo">
		    		<input type="hidden" id="img" name="img" value="">
			    	<input type="hidden" id="name" name="name" value="">
			    	<input type="hidden" id="place" name="place" value="">
			    	<input type="hidden" id="date" name="date" value="">
			    	<input type="hidden" id="time" name="time" value="">
			    	<input type="hidden" id="count" name="count" value="">
			    	<input type="hidden" id="seat" name= "seat" value="">
		    	</form>
				<br>
				<button type="submit" class="btn btn-primary" name="seatcheck"
					id="seatcheck">좌석 선택</button>
				<button type="submit" class="btn btn-primary" id="CancelButton">다시 선택</button>
				<button type="submit" class="btn btn-primary" id="PayButton">결제</button>
				
			 <br>
				<br>
			</div>
		</div>



	</div>
	<!-- movie_form end -->

	<footer
		style="text-align: center; background: lightgray; height: 50px; padding-top: 15px; margin-top: 30px;"
		class="footer">
		<div class="container">
			<p class="text-muted">Place sticky footer content here.</p>
		</div>
	</footer>

</body>
</html>