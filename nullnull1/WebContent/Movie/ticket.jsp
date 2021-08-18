<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>영화 예매</title>

   <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/Cal2.js"></script>
   <script src="js/movie_select.js"></script>
   
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
</script>
    <style>
.navbar .navbar-nav {
  display: inline-block;
  float: none;
}
.navbar .navbar-collapse {
  text-align: center;
}
.navc{
	width:50px;
}
.row{
	display: flex;
	justify-content: center;
}
/* 여기는 body */
    * {
    font-size: 11px;
}

.div_title{
	font-size: 25px;
	background-color: #535353;
	color : white;
	text-align: center;
	border-bottom: 1px solid black;
}
.movie_form{
	margin: 0px auto;
	position : relative;
    display: block;
    justify-content: center;
    height: 600px;
    width : 943px;
    border: 1px solid black;
}
.choice_movie{
	position: absolute;
	width:220px;
	height : 400px;
	left : 10px;
	top : 10px;
	border: 1px solid black;
}
.choice_place{
	position: absolute;
	width: 220px;
	height : 400px;
	left : 240px;
	top : 10px;
	border: 1px solid black;
}
.choice_date{
	position: absolute;
	width: 220px;
	left : 470px;
	top : 10px;
	height : 250px;
	border: 1px solid black;
	 
}
.choice_time{
	position: absolute;
	width: 230px;
	height : 250px;
	left : 700px;
	top : 10px;
	border: 1px solid black;
}
.choice_peoples{
	position: absolute;
	width: 460px;
	height : 140px;
	left : 470px; 
	top : 270px;
	border: 1px solid black;
}
.choice_movieinfo{
	display:flex;
	position: absolute;
	width: 450px;
	height : 170px;
	left : 10px; 
	top : 420px;
	border: 1px solid black;
}
.choice_submit{
	position: absolute;
	width: 460px;
	height : 170px;
	left : 470px; 
	top : 420px;
	border: 1px solid black;
}
li{
	list-style: none;
}
#writeTextarea{
	overflow: auto;
}
</style>
<script>

</script>
  </head>
  <body onload="build();">
  	<!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="image/logo.png"></div>
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav" >
            <li class="active"><a href="">영화</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#about">영화예매</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#contact">커뮤니티</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#contact">고객센터</a></li>
            <li class="dropdown">
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
      
		<!-- 영화 정보 -->
	<div class="movie_form" style="height: 50px; border: none"><p style="font-size: 30pt;">예매하기</p></div>
    <div class="movie_form">
	    <div class="choice_movie">
	    	<div class="div_title">영화선택</div>
	    	<c:set var="num" value="1000"/>
	    	 <c:forEach var="b" items="${movielist}"> 
	    	 	    	 <c:set var="num" value="${num+1}"/>
	        		<ul>
	        			<li onclick="movieSelect(${num},'${b.src}')" id="${num}" value="${b.name}">${b.name}</li>
	        		</ul>
        		 </c:forEach> 
	    	
	    </div>
	    
	    <div class="choice_place" id="writeTextarea">
	    	<div class="div_title">극장 선택</div>
			    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div class="">
					    <div class="" role="tab" id="headingOne">
					      <h4 class="panel-title">
					        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
							서울(1)
					        </a>
					      </h4>
					    </div>
					    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					      <div class="">
					         <ul>
					         	<li style="background-color:gray;">강남구</li>
						         	<li class="mplace">CGV 압구정</li>
						         	<li class="mplace">CGV 강남</li>
						         	<li class="mplace">CGV 청담씨네시티</li>
					         </ul>
					         <ul>
					         	<li style="background-color:gray;">강동구</li>
						         	<li class="mplace">CGV 천호</li>
					         </ul>
					         <ul>
					         	<li style="background-color:gray;">강북구</li>
						         	<li class="mplace">CGV 미아</li>
						         	<li class="mplace">CGV 수유</li>
					         </ul>
					      </div>
					    </div>
					  </div>
					  <div class="">
					    <div class="" role="tab" id="headingTwo">
					      <h4 class="panel-title">
					        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							경기/인천
					        </a>
					      </h4>
					    </div>
					    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					      <div class="panel-body">
					        <ul>
					         	<li style="background-color:gray;">고양시</li>
						         	<li class="mplace">CGV 일산</li>
						         	<li class="mplace">CGV 화정</li>
						         	<li class="mplace">CGV 고양행신</li>
					         </ul>
					      </div>
					    </div>
					  </div>
					  <div class="">
					    <div class="" role="tab" id="headingThree">
					      <h4 class="panel-title">
					        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
					          부산/울산/경남
					        </a>
					      </h4>
					    </div>
					    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
					      <div class="panel-body">
					         <ul>
					         	<li style="background-color:gray;">거제시</li>
						         	<li class="mplace">CGV 거제</li>
					         </ul>
					      </div>
					    </div>
					  </div>
					</div>
	    </div>
	    <!-- 날짜선택 -->
	    <div class="choice_date">
	    <div class="div_title">날짜 선택</div>
			<table style="text-align: center;" id="calendar">
		<tr>
			<td>
			<a id="before" href="javascript:beforem()"><img src="image/leftArrow.png" width=20px;></a>
			</td>
			<td colspan="5" align="center">
			<div id="yearmonth"></div>
			</td>
			<td>
			<a id="next" href="javascript:nextm()"><img src="image/rightArrow.png" width=20px;></a>
			</td>
			<!-- <td>
			<a href="javascript:thisMonth()">오늘</a>
			</td> -->
		</tr>
		<tr>
			<td width="30px"><font color="#ed5353">일</font></td>
			<td width="30px"> 월 </td>
			<td width="30px"> 화 </td>
			<td width="30px"> 수 </td>
			<td width="30px"> 목 </td>
			<td width="30px"> 금 </td>
			<td width="30px"><font color="#009de0">토</font></td>
			
		</tr>
	</table>
	
	<div style="display: flex; margin-left: 50px; margin-top: 50px;">
		오늘날짜 
		<div style="background-color: #BCF1B1;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		&nbsp;&nbsp;&nbsp;&nbsp;
		선택
		<div style="background-color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
	
	<div></div>
	    </div>
	    
	    <div class="choice_time">
	    	<div class="div_title">시간 선택</div>
	    	<ul class="times">
	    		<li id="time1" onclick="TimeSelect('time1')">4관 10 :00</li>
	    		<li id="time2" onclick="TimeSelect('time2')">5관 11 :00</li>
	    		<li id="time3" onclick="TimeSelect('time3')">7관 12 :00</li>
	    	</ul>
	    </div>
	      <div class="choice_peoples">
	    	<div class="div_title">인원 선택</div>
	    	<div style="display: flex;">성인
	    	    	<button class="button1" value="1" >1</button>
	    	    	<button class="button1" value="2">2</button>
	    	    	<button class="button1" value="3">3</button>
	    	    	<button class="button1" value="4">4</button>
	    	    	<button class="button1" value="5">5</button>
	    	    	<button class="button1" value="6">6</button>
	    	</div>
			<hr>
			<p>여기에는 텍스트를 쓰세요</p>
	    </div>
	    <div class="choice_movieinfo">
	    	<div><!-- 영화 이미지 -->
	    		<img src="image/down.png" width="80px;" id="view">
	    	</div>
	    	<div>
	    	<ul>
	    	<li>
	    		<span>영화 이름 :</span>
	    		<span id="moviename"></span>
	    	</li>
	    	<li>
	    		<span>영화 장소 :</span>
	    		<span id="movieplace"></span>
	    	</li>
	    	<li>
	    		<span>영화 날짜 :</span>
	    		<span id="moviedate"></span>
	    	</li>
	    	<li>
	    		<span>시간 :</span>
	    		<span id="movietime"></span>
	    	</li>
	    	<li>
	    		<span>인원 :</span>
	    		<span id="moviepeoples"></span>
	    	</li>
	    	</ul>
	    	</div>
	    </div>
	    <div class="choice_submit">
	    	<div class="div_title">확인버튼</div>
	    	<form action="Movie/MovieSeatlist.jsp" method="post" name ="movieinfo">
	    		<input type="hidden" id="img" name="img" value="">
		    	<input type="hidden" id="name" name="name" value="">
		    	<input type="hidden" id="place" name="place" value="">
		    	<input type="hidden" id="date" name="date" value="">
		    	<input type="hidden" id="time" name="time" value="">
		    	<input type="hidden" id="count" name="count" value="">
	    	</form>
	    	<button id="buttonsub">다음 단계로</button>
	    </div>
    </div><!-- movie_form end -->

  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>