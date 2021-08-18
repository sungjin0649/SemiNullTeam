<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>공지사항 수정 페이지</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script src="NTjs/modifyform.js"></script>
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
#upfile{display:none}
</style>
</head>
<body>
<!-- Static navbar -->
<%--    <nav class="navbar navbar-default navbar-static-top">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div> --%>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="image/logo.png"></div>
   <div class="container">
      <div id="navbar" class="navbar-collapse collapse ">
        <div class="container">
   		  <div id="navbar" class="navbar-collapse collapse ">
       		<ul class="nav navbar-nav" >
       		  <li class="active"><a href="#">영화</a></li>
              <li style="width: 50px;">&nbsp;</li>
              <li><a href="#about">영화예매</a></li>
              <li style="width: 50px;">&nbsp;</li>
              <li><a href="#contact">커뮤니티</a></li>
              <li style="width: 50px;">&nbsp;</li>
              <li class="nav-item dropdown">
    			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">고객센터</a>
   			 	<div class="dropdown-menu">
      			<a class="dropdown-item" href="NTList.bo">공지사항</a>
      			<a class="dropdown-item" href="NTFAQ">자주묻는질문</a>
   				</div>
 			 </li>
          	</ul>
          </div><!--/.nav-collapse -->
      	</div>
     </div><!--/.nav-collapse -->
   </div>
  <!--   </nav> -->

 <%-- 게시판 수정 --%>
 <div class="container">
	 <form action="NTModifyAction.bo" method="post" enctype="multipart/form-data"
	 	name="modifyform">
	 	<input type="hidden" name="nt_no" value="${boarddata.nt_no}">
	 	<h1>공지사항 게시판 수정</h1>
	 	<div class="form-group">
	 		<label for="user_id">글쓴이</label>
	 		<input name="usesr_id" readOnly
	 				type="text"	class="form-control" value="${boarddata.user_id}">
	 	</div>
	 
	 	<div class="form-group">
	 		<label for="nt_subject">제목</label>
	 		<textarea name="nt_subject" id="nt_subject" maxlength="100"
	 				 class="form-control" rows="1">${boarddata.nt_subject}</textarea>
	 	</div>
	 	<div class="form-group">
	 		<label for="nt_content">내용</label>
	 		<textarea name="nt_content" id="nt_content"
	 					rows="15" class="form-control">${boarddata.nt_content}</textarea>
	 	</div>
	 	<%-- 파일 첨부 수정 가능합니다.--%>
	 	<div class="form-group">
	 		<label for="nt_file">파일 첨부</label>
	 		<label for="upfile">
	 			<img src="image/attach.png" alt="파일첨부"  width="20px">
	 		</label>
	 		<input name="nt_file" id="upfile" type="file">
	 		<span id="filevalue">${boarddata.nt_file}</span>
	 		<img src="image/remove.png" alt="파일삭제" width="10px" class="remove">
 		</div>
 		
 			<div class="form-group">
	 		<label for="nt_pass">비밀번호</label>
	 		<input name="nt_pass"
	 			   id="nt_pass" type="password" maxlength="30" size="10"
	 			   class="form-control" placeholder="Enter board_pass">${boarddata.nt_pass}
	 	</div>
	 	
 		<div class="form-group">
 			<button type="submit" class="btn btn-primary">수정</button>
 			<button type="reset" class="btn btn-danger"
 					onClick="history.go(-1)">취소</button>
 		</div>
	 </form>
 </div> 
</body>
</html>