 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>회원가입</title>
   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="js/validate.js"></script> <!-- 회원가입 자바스크립트 폼 -->

<script>
$(function () {	
	$("#searchck").click(function(){
		$("#idck").val($("#id").val());
		$("#okuse").hide();
	});
	
	$("#search").click(function(){
        $.ajax({
      	  url : "idCheck.oky",
      	  data : {"idck" : $("#idck").val()},
      	  success : function(resp) {
      		  if (resp == -1) {//db에 해당 id가 없는 경우
      			  $("#message").css('color', 'green').html(
      					  "사용 가능한 아이디 입니다.");
      			$("#okuse").show();
      		  } else {//db에 해당 id가 있는 경우(0)
      		    $("#message").css('color','red').html(
      		    		"사용중인 아이디 입니다.");
      		  $("#okuse").hide();
      		  }
      	  }
       	  
        });//ajax end
	});	

	$("#okuse").click(function(){
		$("#id").val($("#idck").val());

	});
})
</script>

 </head>
 
   <body> 
   <form name="joinform" action="joinProcess.oky" method="post">
    <div class="container">
    <fieldset>
     <legend>회원가입</legend>
     <label for="id"><b>아이디</b></label> <br>
     <div>                
     <input type="text" placeholder="아이디를 입력하세요" name="id"  id="id">
     <input type=button data-toggle="modal"  data-target="#idcheck" id="searchck" value="아이디 중복확인" ><br>
     </div>  
     <label for="pass"><b>비밀번호</b></label><br>
     <input type="password" placeholder="비밀번호를 입력하세요" name="pass" id="pass"><br>
     
     <label for="passck"><b>비밀번호 확인</b></label><br>
     <input type="password" placeholder="비밀번호를 입력하세요" name="passck" id="passck"><br>
      
     <label for="name"><b>이름</b></label><br>  
     <input type="text" placeholder="이름을 입력하세요" name="name" id="name"><br>
     
     <label for="birth"><b>생년월일</b></label><br>
     <input type="text" placeholder="년(4자)" name="year" maxLength="4" id="year">&nbsp;&nbsp;&nbsp;&nbsp;
     <select name="month" id="month"  style="width:170px;height:20px;">
                      <option value="월">월</option>
                      <option value="01">01</option>
                      <option value="02">02</option>
                      <option value="03">03</option>
                      <option value="04">04</option>
                      <option value="05">05</option>
                      <option value="06">06</option>
                      <option value="07">07</option>
                      <option value="08">08</option> 
                      <option value="09">09</option>
                      <option value="10">10</option>
                      <option value="11">11</option>
                      <option value="12">12</option>                                          
     </select>
     <input type="text" placeholder="일" maxLength="2" name="date" id="date">
     <br>      
     <label for="email"><b> 이메일 </b></label><br>
     <input type="text" placeholder="이메일 아이디를 입력하세요" name="email" id="email">@ 
     <input type="text" placeholder="이메일 주소를 입력하세요" name="domain" id="domain"> 
     <select name="sel" id="sel"  >
                      <option value="">직접입력</option>
                      <option value="naver.com">naver.com</option>
                      <option value="daum.net">daum.net</option>
                      <option value="nate.com">nate.com</option>
                      <option value="gmail.com">gmail.com</option>
    </select>
                  
   
     
     <br><label><b>휴대폰번호</b></label><br>
     <input type="text" size="11" maxLength="11" placeholder="01012345678" name="phone" id="phone" >
     
     <br><label><b>자동가입방지문자</b></label><br>
     <input type="text" size=6  name="che" id="che" readOnly>
     <input type="button" value="문자생성 " id="code">
     <input type="text" size=6 maxLength="6" name="output" id="output">
     <input type="button" value="문자확인" id="ckcode">

    
    <div class="clearfix">
     <button type="submit" class="signupbtn" >회원가입</button>
     <button type="button" id="button" class="cancelbtn">취소</button>

     
    </div>                 
     </fieldset>
     </div>
   </form>  
   
  <div class="modal fade" id="idcheck" role="dialog"> <!-- 사용자 지정 부분① : id명 -->

    <div class="modal-dialog">
   
      <!-- Modal content-->

      <div class="modal-content">

        <div class="modal-header">

          <button type="button" class="close" data-dismiss="modal" >×</button>

          <h4 class="modal-title" align="center">아이디 중복 확인</h4> <!-- 사용자 지정 부분② : 타이틀 -->

        </div>

        <div class="modal-body">
                   <form id="my-form">
                   <input type ="text" name="idck" id="idck" placeholder="아이디 입력">            
                   <button type="button" id="search" >검색</button><br>
                   <span id="message"></span>
                   </form>
        </div>
      <!-- 사용자 지정 부분③ : 텍스트 메시지 -->

        </div>

        <div class="modal-footer">
        <button type="button"  id="okuse"  data-dismiss="modal">사용하기</button><br>

        </div>

      </div>

    </div>
   
  </body>
</html>