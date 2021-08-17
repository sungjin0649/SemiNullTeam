<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>회원정보 수정페이지</title>
   <script src="js/jquery-3.6.0.min.js"></script>
<style>
h3 {
          text-align: center; color: #1a92b9;
}
input[type=file]{
  display:none;
}
</style>
<script>
$(document).ready(function () {   
	
$("#phone").keyup(function () {
	if($("#phone").val().length == 11){
		pattern = /^[0][1][0][0-9]{8}$/;
		if(!pattern.test($("#phone").val())){
			alert("형식에 맞게 입력하세요");
			$("#phone").val('').focus();				
		}
	}
}); 
var check=0;
 $('form').submit(function () {

	  	  
	   if ($.trim($("#name").val()) =="") {
		   alert("이름을 입력 하세요");
		   $("#name").focus();
		   return false;
	   }
	   
	   if ($.trim($("#birth").val()) =="") {
		   alert("생년월일을 입력 하세요");
		   $("#birth").focus();
		   return false;
	   }
	   
	   if ($.trim($("#birth").val()) =="") {
		   alert("생년월일을 입력 하세요");
		   $("#birth").focus();
		   return false;
	   }

	   if ($("#birth").val().length !=8 || isNaN($.trim($("#birth").val())) ==true) {
		   alert("생년월일을 형식에 맞게 입력 하세요");
		   $("#birth").val('').focus();
		   return false;
	   }
	   
		if ($.trim($("#phone").val()) =="" ) {
			alert("휴대폰번호를 입력하세요");
			$("#phone").focus();
			return false;
		}
		
		if ($("#phone").val().length != 11) {
			alert("휴대폰번호를  다시 입력하세요");
			$("#phone").val('').focus();
			return false;
		}
		
		if ($.trim($("#email").val()) =="") {
			   alert("E-mail 주소를 입력하세요");
			   $("#email").focus();
			   return false;
		   }
	  
	  if (check == 0) {
		  value = $('#filename').text();
		  html = "<input type='text' value='" + value + "' name='check'>";
		  $(this).append(html);
	  }
	  
 }); //submit
 
 
 $('input[type=file]').change(function(event) {
	      check++;
	      var inputfile = $(this).val().split('\\');
	      var filename=inputfile[inputfile.length - 1];
	      var pattern = /(gif|jpg|jpeg|png)$/i; //플래그 i는 대소문자 구분 없는 검색
	      if (pattern.test(filename)) {
	    	  $('#filename').text(filename); //inputfile.length - 1 =2
	    	  
	    	  var reader = new FileReader(); //파일을 읽기 위한 객체 생성(FileReader는 자바스크립트 자체서 제공)
	    	//DataURL 형식으로 파일을 읽어옵니다.
	    	//읽어온 결과는 reader객체의 result 속성에 저장됩니다.
	    	//event.target.files[0] : 선택한 그림의 파일객체에서 첫번째 객체를 가져옵니다.
	    	   reader.readAsDataURL(event.target.files[0]);
	
	           reader.onload = function (event) { //읽기에 성공했을 때 실행되는 이벤트 핸들러
	        	   $('#showImage').html('<img width="20px" src="'
	        			                + event.target.result + '">');
	           };  
	      } else {
	    	  alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
	    	  check=0;
	      }
 })// $('input[type=file]').change() end
 
	$('.cancelbtn').click(function(){
		window.location ="index.jsp";
	})
 
});
</script>   
</head>
<body>
<form name="joinform" action="updateProcess.oky" method="post"
      enctype="multipart/form-data">
       <h3>회원 정보 수정</h3>
       <hr>
       <b>아이디</b><br>
       <input type="text" name="id" value="${memberinfo.id}" readOnly><br>
       
       <b>비밀번호</b><br>
       <input type="password" name="pass" id="pass" value="${memberinfo.pass}" readOnly><br>
          
       <b>이름</b><br>
       <input type="text" name="name" id="name" value="${memberinfo.name}" placeholder="이름을 입력하세요" required><br>
       
       <b>생년월일</b><br>
       <input type="text" size="8" maxLength="8" name="birth" id="birth" value="${memberinfo.birth}" placeholder="생년월일 8자를 입력하세요"><br>
       
       <b>휴대폰번호</b><br>
       <input type="text" size="11" maxLength="11" placeholder="01012345678" name="phone" id="phone" value="${memberinfo.phone}"><br>
     
       
       <b>이메일 주소</b><br>
       <input type="text" id="email" name="email" value="${memberinfo.email}" placeholder="이메일을 입력하세요" required><br>
       
       <b>프로필 사진</b><br>
       <label>
          <img src="image/attach.png" width="10px">
          <span id="filename">${memberinfo.memberfile}</span>
          <%-- memberinfo.memberfile의 값이 없으면 기본 사진을 보여줍니다.
                              값이 존재하면 memberupload 폴더에 존재하는 파일명으로 경로를 설정합니다. --%>
          <span id="showImage">
            <c:if test='${empty memberinfo.memberfile}'>
             <c:set var='src' value='image/profile.png'/>
            </c:if>
           <c:if test='${!empty memberinfo.memberfile}'>
             <c:set var='src' value='${"memberupload/"}${memberinfo.memberfile}'/>
            </c:if>
            <%-- 위에서 memberinfo.memberfile의 값이 있는 경우와 없는 경우에 따라 src 속성값이 달라집니다. --%>
            <img src="${src}" width="20px" alt="profile">
          </span>
           <%-- accept: 업로드할 파일 타입을 설정합니다.
                <input type="file" accept="파일 확장자|audio/*|video/*|image/*|미디어 타입">
                                 파일 확장자는 .png, .jpg, .pdf, .hwp 처럼 (.)으로 시작되는 파일 확장자를 의미합니다.
                 audio/* : 모든 타입의 오디오 파일
                 image/* : 모든 타입의 이미지 파일
                 
                 --%>
          <input type="file" name="memberfile" accept="image/*">
       </label><br>
       
       <div class="clearfix">
          <button type="submit" class="submitbtn">수정</button>
          <button type="button" class="cancelbtn">취소</button>           
       </div>
</form> 
</body>
</html>
