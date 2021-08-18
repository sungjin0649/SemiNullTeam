<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>답글</title>
<script src="js/jquery-3.6.0.slim.js"></script>
<script src="js/reply.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
	h1{font-size: 1.5rem; text-align: center; color: #1a92b9}
	.container{width:60%}
	label{font-weight: bold}
	#upfile{display: none}
	imag{width: 20px;}
</style>
</head>
<body>
	<div class="container">
		<form action="Bbs_bkReply.bk" method="post" name="boardform">
		<input type="hidden" name="BK_CSFC" value="${Bbs_bk_data.BK_CSFC }">
		<input type="hidden" name="BK_RE_REF" value="${Bbs_bk_data.BK_RE_REF }">
		<input type="hidden" name="BK_RE_LEV" value="${Bbs_bk_data.BK_RE_LEV }">
		<input type="hidden" name="BK_RE_SEQ" value="${Bbs_bk_data.BK_RE_SEQ }">
		<h1>MVC 게시판-Reply</h1>
		<div class="form-group">
			<label for="USER_ID">글쓴이</label>
			<input name="USER_ID" id="USER_ID" value="admin" readOnly
			type="text" class="form-control"> <%-- value="${id}" --%>
		</div>
		<div class="form-group">
			<label for="board_subject">제목</label>
			<textarea name="BK_SUBJECT" id="BK_SUBJECT" rows="1" 
			 class="form-control" maxlength="100"> Re:${Bbs_bk_data.BK_SUBJECT}</textarea>
		</div>
		<div class="form-group">
			<label for="BK_CONTENT">내용</label>
			<textarea name="BK_CONTENT" id="BK_CONTENT"  rows="10"
			 class="form-control"></textarea>
		</div>
		<div class="form-group">
			<label for="BK_PASS">비밀번호</label>
			<input name="BK_PASS" id="BK_PASS"
			 type="password" class="form-control">
		</div>
		
		<div class ="form-group">
			<input type=submit class="btn btn-primary" value="등록">
			<input type=button class="btn btn-danger" value="취소"
				onClick="history.go(-1)">
		</div>
		</form>
	</div>
</body>
</html>