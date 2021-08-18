<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>1대1 문의 게시판</title>
    
   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
  table{margin:0 auto; text-align:center}
  td{height:30px}
  td:nth-child(2n){width:300px; background:green}
  td:nth-child(2n+1){width:150px; background:lightyellow}
  input[type=text]{height:30px; width:100%}
  textarea{width:100%}
</style>
<script>
$(document).ready(function () {
	$('.cancelbtn').click(function(){
		window.location ="index.jsp";
	})
	});
</script> 
</head>
<body>
회원 ID: ${id} <br>
회원 이름: ${memberinfo.name} <br>
회원 email: ${memberinfo.email} <br>
회원 전화번호: ${memberinfo.phone}

<form action="Bbs_con/mailSend.jsp" method="post">
<input type="hidden" name="id" value= "${id}" readOnly>
<input type="hidden" name="name" value= "${memberinfo.name}" readOnly>
<input type="hidden" name="email" value= "${memberinfo.email}" readOnly>
<input type="hidden" name="phone" value= "${memberinfo.phone}" readOnly>
<input type="hidden" name="sender" value= "okycontactus@gmail.com" readOnly>
<table>
    <tr>
          <td colspan=2>1대 1 문의</td>
    </tr>
    <tr>
        <td>받는 사람 메일 </td>
        <td><input type="text" name="receiver"value= "okycontactus@gmail.com" readOnly></td>
    </tr>
    <tr>
        <td>제목 </td>
        <td><input type="text" name="subject"></td>
    </tr>  
    <tr>
        <td>내용 </td>
        <td><textarea name="content" id="content"  cols=40 rows=10></textarea></td>
    </tr>        
    <tr>
       <td colspan=2><input type="submit" id="send" value="보내기"> <input type="button" class="cancelbtn" value="취소"></td>
       </tr>
</table>
</form>
</body>
</html>