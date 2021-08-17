<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.6.0.js"></script>
<script src="js/write2.js"></script>
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>공지사항 글쓰기</title>
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
#nt_subject{
	width:80%;
}
#upfile{
display:none;
}

</style>
  </head>
  <body>
  	<!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="image/logo.png"></div>
  	<input type="hidden" value="${id}" id="loginid" name="loginid">
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
         
          <ul class="nav navbar-nav" >
            <li><a href="#contact">영화</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#about">영화예매</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#contact">커뮤니티</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li class="nav-item dropdown">
    			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">고객센터</a>
   			 	<div class="dropdown-menu">
      			<a class="dropdown-item" href="NTList.bo">공지사항</a>
      			<a class="dropdown-item" href="#">자주묻는질문</a>
   				</div>
 			 </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
<div class="container theme-showcase" role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
     <form action="NTAddAction.bo" method="post" enctype="multipart/form-data"
	 	name="boardform">
	 	<div class="form-group">
	 		<label for="nt_subject">제목</label><br>
	 		<input name="nt_subject" id="nt_subject" type="text">
<%-- 	 	<input type="hidden" value="${id}" id="writer"> --%>
	 		<input type="hidden" name="writer" value="admin" id="writer">
	 	</div>
       <div class="form-group">
	 		<label for="nt_pass">비밀번호</label><br>
	 		<input name="nt_pass" id="nt_pass"
	 		 type="password" placeholder="Enter Password">
	 	</div>
       <div class="form-group">
	 		<label for="nt_content">내용</label>
	 		<textarea name="nt_content" id="nt_content"
	 					rows="15" class="form-control"></textarea>
	 	</div>
        <div class="form-group">
	 		<label for="nt_file">파일 첨부</label>
	 		<label for="upfile">
	 			<img src="image/attach.png" width="20" height="20" alt="파일첨부">
	 		</label>
	 		<input name="nt_file" id="upfile" type="file">
	 		<span id="filevalue"></span>
 		</div>
       <div class="form-group">
 			<button type="submit" class="btn btn-primary">등록</button>
 			<button type="reset" class="btn btn-danger">취소</button>
 		</div>
	 	</form>
      </div>
 </div>
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>