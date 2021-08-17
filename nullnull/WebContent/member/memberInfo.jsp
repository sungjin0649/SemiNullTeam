 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>회원정보</title>
   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<style>
 tr>td:nth-child(odd){font-weigth: bold}
 td{text-align: center}
 .container{width:50%}
</style>
</head>
<body>
 <div class="container">
   <table class="table table-bordered"> 
   <tr>
       <td>아이디</td>
       <td>${memberinfo.id}</td><%-- Member 클래스의 getId()메서드 호출 --%>
  </tr>
  <tr>
       <td>비밀번호</td>
       <td>${memberinfo.pass}</td>
   </tr>
   <tr>
       <td>이름</td>
       <td>${memberinfo.name}</td>
   </tr>
   <tr>
       <td>생년월일</td>
       <td>${memberinfo.birth}</td>
   </tr>
   <tr>
       <td>전화번호</td>
       <td>${memberinfo.phone}</td>
   </tr>
   <tr>
       <td>이메일주소</td>
       <td>${memberinfo.email}</td>
  </tr>
  <tr>
        <td colspan="2" >
        <a href="memberList.oky">리스트로 돌아가기</a></td>
  </tr>
</table>
</div>
</body>
</html>