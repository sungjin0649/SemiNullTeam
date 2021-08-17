<%@ page language="java" contentType="text/html; charset=utf-8" %>
 
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "http://code.jquery.com/jquery-latest.js"></script>

	<script src = "js/writeform.js"></script>
	<style>
		h1{font-size:1.5rem; text-align:center;color:#1a92b9}
		.container{width:60%}
		label{font-weight:bold}
		#upfile{display:none}
		img{width:20px;}
	</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form action ="RvAddAction.rv" method = "post" enctype="multipart/form-data"
				name = "boardform">
			<h1>MVC 게시판- write 페이지</h1>
			<div class="form-group">
				<label for="USER_ID">글쓴이</label>
				<input name= "USER_ID" id="USER_ID" value="ADMIN" 
					type="text"	class="form-control"
					placeholder = "Enter board_name">
			</div>		
			<div class="form-group">
				<label for="RV_PASS">비밀번호</label>
				<input name="RV_PASS" id ="RV_PASS" type = "password" maxlength="30"
					class="form-control" placeholder="Enter board_pass">
			</div>
			<div class="form-group">
				<label for="RV_SUBJECT">제목</label>
				<input name="RV_SUBJECT" id ="RV_SUBJECT" type="text" maxlength="100"
					class="form-control" placeholder="Enter board_subject">
			</div>
			<div class="form-group">
				<label for="RV_CONTENT">내용</label>
				<textarea name="RV_CONTENT" id ="RV_CONTENT"
					rows="10" class="form-control"></textarea>
			</div>
			<div class="form-group">
				<label for="RV_FILE">파일 첨부</label>
				<label for="upfile">
					<img src="image/attach.png" alt="파일첨부">
				</label>
				<input type="file" id="upfile" name="RV_FILE">
				<span id="filevalue"></span>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">등록</button>
				<button type=reset class="btn btn-danger">취소</button>
			</div>
		
		
		</form>
	</div>

</body>
</html>