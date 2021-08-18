<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>상세보기</title>

   <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/view.js"></script>
   <link rel="stylesheet" href="css/view.css">
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
    <nav class="navbar navbar-default navbar-static-top">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="image/logo.png"></div>
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav" >
            <li><a href="#">영화</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li class="active"><a href="#about">영화예매</a></li>
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
      	<input type="hidden" value="${id}" id="loginid">
		<table class="table">
			<tr><td>번호</td>
				<td>${Bbs_bkdata.BK_NO}</td>
				
				<td>분류</td>
				<td>${Bbs_bkdata.BK_CSFC}</td>
				
				<td>가격</td>
				<td>${Bbs_bkdata.BK_PRICE}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="5">${Bbs_bkdata.BK_SUBJECT}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${Bbs_bkdata.USER_ID}</td>
				
				<td>날짜</td>
				<td>${Bbs_bkdata.BK_DATE}</td>
				
				<td>조회수</td>
				<td>${Bbs_bkdata.BK_READ}</td>
			</tr>
			<tr>
				<td><div>내용	</div></td>
				<td style="padding-right:0px"><textarea class="form-control" rows="5"
					readOnly >${Bbs_bkdata.BK_CONTENT}</textarea></td>
			</tr>
			<c:if test="${Bbs_bkdata.BK_RE_LEV==0}"><%--원문글인 경우에만 첨부파일을 추가 할 수 있습니다. --%>
				<tr>
					<td><div>첨부파일</div></td>
					<c:if test="${!empty Bbs_bkdata.BK_FILE}"><%--파일첨부한 경우 --%>
					<td>
						<img src="image/down.png" width="10px">
						<a href="Bbs_bkFileDown.bk?filename=${Bbs_bkdata.BK_FILE}">
							${Bbs_bkdata.BK_FILE}</a></td>
					</c:if>
					<c:if test="${empty Bbs_bkdata.BK_FILE}"><%--파일첨부하지 않은 경우 --%>
					<td></td>
					</c:if>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2" class="center">
				<a href="Bbs_bkReplyView.bk?num=${Bbs_bkdata.BK_NO}">
					<button class="btn btn-primary">답변</button>
				</a>
				<c:if test="${Bbs_bkdata.USER_ID == id || id=='admin' }">
					<a href="Bbs_bkModifyView.bk?num=${Bbs_bkdata.BK_NO }">
						<button class="btn btn-info">수정</button>
					</a>
					<%-- href의 주소를 #으로 설정합니다. --%>
					<a href="#">
						<button class="btn btn-danger" data-toggle="modal"
						data-target="#myModal">삭제</button>
					</a>
				</c:if>
				
				<a href="Bbs_bkList.bk">
					<button class="btn btn-secondary">목록</button>
				</a>
				</td>
			</tr>
		</table>

		<%-- 게시판 view end --%>
			
			<%--modal 시작 --%>
			<div class="modal" id="myModal">
			 <div class="modal-dialog">
			  <div class="modal-content">
			   <%-- Modal body --%>
			   <div class="modal-body">
			   	<form name="deleteForm" action="Bbs_bkDelete.bk" method="post">
			   	<%-- http://localhost:8088/Board/BoardDetailAcion.bo?num=22
			   		주소를 보면 num을 파라미터로 넘기고 있습니다.
			   		이 값을 가져와서 ${param.num}를 사용
			   		또는 ${boarddata.board_num} 
			   	--%>
			   	<input type="hidden" name="num" value="${param.num}">
			   	<div class="form-group">
			   		<label for="pwd">비밀번호</label>
			   		<input type="password"
			   			class="form-control" placeholder="Enter password"
			   			name="BK_PASS" id="BK_PASS">
			   	</div>
			   	<button type="submit" class="btn btn-primary">전송</button>
			   	<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
			   	</form>
			   </div><!-- class="modal-body" -->
			  </div><!-- class="modal-content" -->
			 </div><!-- class="modal-dialog" -->
			</div><!-- class="modal end" -->
			
			<div class="CommentBox">
				<div class="comment_option">
					<h3 class="comment_title">
						댓글 <sup id="count"></sup>
					</h3>
					<div class="comment_tab">
						<ul class="comment_tab_list">
						</ul>
					</div>
				</div><!-- comment_option end -->
				<ul class="comment_list">
				</ul>
				<div class="CommentWriter">
					<div class="comment_inbox">
						<b class="comment_inbox_name">${id}</b><span
						 class="comment_inbox_count">0/200</span>
						<textarea placeholder="댓글을 남겨보세요" rows="1"
						 class="comment_inbox_text" maxlength="200"></textarea>
						 
						
						<input type="hidden" name="num" value="${param.num}"
						 id="BCM_COMMENT_BOARD_NUM">
						<!-- 또는 ${boarddata.BK_NO} -->
					</div>
					<div class="register_box">
						<div class="button btn_cancel">취소</div>
						<div class="button btn_register">등록</div>
					</div>
				</div><!-- CommentWriter end -->
			</div><!-- Commentbox end -->
      </div><!-- jumbotron -->
 </div>
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>