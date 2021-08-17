<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>MVD 게시판</title>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<script src="js/modifyform.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.container {width:60%}
h1 {font-size:1.5rem; text-align:center; color:#1a92b9;}
#upfile{display:none}
</style>
<!-- 글 수정 VIEW -->
</head>
<body>
	<%-- 게시판 수정 --%>
	<div class="container">
		<form action="RvModifyAction.rv"	method="post"	name="modifyform"
				enctype="multipart/form-data">
			<input type="hidden"	name="RV_NO"	value="${boarddata.RV_NO}">
			<h1>MVC 게시판 - 수정</h1>
			<div class="form-group">	
				<label for="USER_ID">글쓴이</label> <input type="text"
					class="form-control" value="${boarddata.USER_ID}" readOnly>
			</div>
			
			<div class="form-group">
				<label for="RV_SUBJECT">제목</label>
				<textarea name="RV_SUBJECT" id="RV_SUBJECT" rows="1"
				class="form-control"	maxlength="100" >${boarddata.RV_SUBJECT}</textarea>
			</div>
			
			<div class="form-group">
				<label for="RV_CONTENT">내용</label>
				<textarea name="RV_CONTENT" id="RV_CONTENT"
					class="form-control" rows="15">${boarddata.RV_CONTENT}</textarea>
			</div>
		<%--원문글인 경우에만 파일 첨부 수정 가능합니다. --%>
		<c:if test = "${boarddata.RV_RE_LEV==0}">
			<div class="form-group read">
			 <label for="RV_FILE">파일첨부</label>
			  <label for="upfile">
			  	<img src="image/attach.png" alt="파일첨부" width="20px">
			  </label>
			  <input type="file" id="upfile" name="RV_FILE">
			  <span id="filevalue">${boarddata.RV_FILE}</span>
			 <img src="image/remove.png" alt="파일삭제" width="10px" class="remove">
			</div>
		</c:if>
			
			<div class="form-group">
				<label for="RV_PASS">비밀번호</label>
				<input name="RV_PASS"
						id="RV_PASS" type="password" size="10" maxlength ="30"
						class="form-control" placeholder="Enter board_pass" value="">
			</div>
			<div class="form-group">
				<button type=submit class="btn btn-primary">수정</button>
				<button type=reset class="btn btn-danger"
						onClick="history.go(-1)">취소</button>
			</div>			
				
		</form>
	
	</div><%--class="container" end --%>

</body>
</html>