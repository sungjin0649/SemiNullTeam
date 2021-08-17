<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>부트스트랩 101 템플릿</title>

   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  
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

</style>
  </head>
  <body>
  	<!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top"">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="image/logo.png"></div>
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav" >
            <li class="active"><a href="#">영화</a></li>
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
    
<div class="container theme-showcase" role="main">
      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <table class="table">
        	<thead>
        		<th>번호</th><th>내용</th><th>날짜</th>
        	</thead>
        	<tbody>
        		<tr>
        			<td>1번글</td><td>1번글입니다.</td><td>2021-08-09</td>
        		</tr>
        		<tr>
        			<td>2번글</td><td>2번글입니다.</td><td>2021-08-09</td>
        		</tr>
        		<tr>
        			<td>3번글</td><td>3번글입니다.</td><td>2021-08-09</td>
        		</tr>
        	</tbody>
		</table>
      </div>
 </div>
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>