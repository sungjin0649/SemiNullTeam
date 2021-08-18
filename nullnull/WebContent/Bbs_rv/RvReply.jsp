<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src = "http://code.jquery.com/jquery-latest.js"></script>
	<script>
	$(document).ready(function(){
		
		//submit 버튼 클릭할 때 이벤트 부분
		$("form").submit(function(){
			
			if($.trim($("#RV_SUBJECT").val())==""){
				alert("제목을 입력하세요 ");
				$("#RV_SUBJECT").focus();
				return false;
			}
			
			if($.trim($("#RV_CONTENT").val())==""){
				alert("내용을 입력하세요");
				$("#RV_CONTENT").focus();
				return false;
			}
			
			if($.trim($("input:password").val())==""){
				alert("비밀번호를 입력하세요");
				$("input:password").focus();
				return false;
			}
		})
	})
	</script>

	<style>
		h1{font-size:1.5rem; text-align:center;color:#1a92b9}
		.container{width:60%}
		label{font-weight:bold}
	</style>
<meta charset="EUC-KR">
<title>MVC 게시판</title>
</head>
<body>
<!--답변글쓰기페이지 -->
	<div class="container">
		<form action ="RvReplyAction.rv" method = "post" name = "boardform">
			<input type="hidden" name="RV_RE_REF" value="${boarddata.RV_RE_REF}">
			<input type="hidden" name="RV_RE_LEV" value="${boarddata.RV_RE_LEV}">
			<input type="hidden" name="RV_RE_SEQ" value="${boarddata.RV_RE_SEQ}">
			<h1>MVC 게시판- Reply 페이지</h1>
			<div class="form-group">
				<label for="USER_ID">글쓴이</label>
				<input name= "USER_ID" id="USER_ID" value="ADMIN" 
					type="text"	class="form-control">
			</div>		

			<div class="form-group">
				<label for="RV_SUBJECT">제목</label>
				<textarea name="RV_SUBJECT" id ="RV_SUBJECT" type="text" rows="1" 
					maxlength="100"
					class="form-control"> Re:${boarddata.RV_SUBJECT} </textarea> 
				
				
			</div>
			<div class="form-group">
				<label for="RV_CONTENT">내용</label>
				<textarea name="RV_CONTENT" id ="RV_CONTENT"
					rows="10" class="form-control"></textarea>
			</div>
			<div class="form-group">
				<label for="RV_PASS">비밀번호</label>
				<input name="RV_PASS" id ="RV_PASS" type = "password" 
					class="form-control">
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="등록">
				<input type=button class="btn btn-danger" value="취소" 
						onClick="history.go(-1)">
			</div>
		
		
		</form>
	</div>

</body>
</html>