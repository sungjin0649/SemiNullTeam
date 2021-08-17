<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <script src="js/jquery-3.6.0.min.js"></script>
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>공지사항 상세보기 페이지</title>
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
      			<a class="dropdown-item" href="#">자주묻는질문</a>
   				</div>
 			 </li>
          	</ul>
          </div><!--/.nav-collapse -->
      	</div>
     </div><!--/.nav-collapse -->
   </div>
  <!--   </nav> -->
<div class="container theme-showcase" role="main">
		<input type="hidden" value="${id}" id="loginid" name="loginid">
			<table class="table">
			<tr><th colspan="2">공지사항 상세보기 페이지</th></tr>
			<tr>
				<td><div>글쓴이</div></td>
				<td><div>${boarddata.user_id}</div></td>
			</tr>
			<tr>
				<td><div>제목</div></td>
				<td><c:out value="${boarddata.nt_subject}"	/></td>
			</tr>
			<tr>
				<td><div>내용</div></td>
				<td style="padding-right:0px"><textarea class="form-control" rows="5"
					readOnly>${boarddata.nt_content}</textarea></td>
			</tr>
			<tr>
				<td><div>첨부파일</div></td>
				<c:if test="${!empty boarddata.nt_file}"><%-- 파일첨부한 경우 --%>
				 <td><img src="image/down.png" width="10px">
				 	<a href="NTFileDown.bo?filename=${boarddata.nt_file}">
				 		${boarddata.nt_file}</a></td>
				 </c:if>
				 <c:if test="${empty boarddata.nt_file}"><%--파일첨부하지 않은 경우 --%>
				 	<td></td>
				 </c:if>
			</tr>
			<tr>
				<td colspan="2" class="center">
				  <c:if test="${boarddata.user_id =='admin' }">
				   <a href="NTModifyView.bo?num=${boarddata.nt_no}">
				   	  <button class="btn btn-info" >수정</button>
				   </a>
				   <a href="#">
				   	  <button class="btn btn-danger" data-toggle="modal"
				   	  		  data-target="#myModal">삭제</button>
				   </a>
				   </c:if>
				   
				   <a href="NTList.bo">
				   	 <button class="btn btn-secondary">목록</button>
				   </a>
				 </td>
			</tr>
	 </table>    
	 <%-- 게시판view end --%>
	
		<%--modal 시작--%>
		<div class="modal" id="myModal">
		  <div class="modal-dialog">
			<div class="modal-content">
				<%--Modal body --%>
				<div class="modal-body">
				 <form name="deleteForm" action="NTDeleteAction.bo" method="post">
				 	<%--http://localhost:8088/Board/BoardDetailAction.bo?num=22 
				 		주소를 보면 num을 파라미터로 넘기고 있습니다.
				 		이 값을 가져와서 ${param.num}를 사용
				 		또는 ${boarddata.board_num}
				 	--%>
				 	<input type="hidden" name="num" value="${param.num}" id="comment_board_num">
				 	<div class="form-group">
				 		<label for ="pwd">비밀번호</label>
				 		<input type="password"
				 				class="form-control" placeholder="Enter password"
				 				name="nt_pass" id="nt_pass">
				 	</div>
				 	<button type="submit" class="btn btn-primary">전송</button>
				 	<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
				 </form>
				</div><!-- class="modal-body" -->
			</div><!-- class="modal-content -->
		</div><!-- class="modal-dialog" -->
		</div><!-- class="modal" end -->
		
</div><!-- class="container" -->
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>