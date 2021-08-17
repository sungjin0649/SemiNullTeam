<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>세미메인</title>



   <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/index.js"></script>
   
<script>
      $(function(){ 
      	   $(".join1").click(function(){ //메인에서 회원가입버튼 클릭 시
    		   location.href="join.net";
    	   });

      	   $(".join2").click(function(){ //로그인창에서 회원가입버튼 클릭 시
    		   location.href="join.net";
    	   });
    	   $(".login").click(function(){//아이디 저장 기능
    		   alert("클릭!");
    	   var id = '${id}';
    	   if(id){
    		   $("#id").val(id);
    		   $("#remember").prop('checked',true);
    	   }
       })
      });
</script>   

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
            <!--     <a href="#">
              <button class="btn btn-danger" data-toggle="modal"
                      data-target="#myModal">삭제</button>
            </a> -->
    	<div align="right"><a class="login" data-toggle="modal" href="#login">로그인</a>&nbsp;&nbsp;&nbsp;<a class="join1" href="#">회원가입</a></div>
    	   <div align="center">
    	     <a href="http://localhost:8088/nullnull/index.jsp"><img style="text-align: center; width: 200px" src="image/logo.png"></a></div>
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav" >
            <li class="active"><a href="#">영화</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#about">영화예매</a></li>
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
   <form name="myform" method="get" action="send.html"
          id="myform" >
    <div class="container">
    <fieldset>
     <legend>회원가입</legend>
     <label for="id"><b>아이디</b></label> <br>
     <div>                
     <input type="text" placeholder="아이디를 입력하세요" name="id"  id="id">
     <input type=button value="ID중복검사" id="idcheck"><br>
     </div>  
     <label for="pass"><b>비밀번호</b></label><br>
     <input type="password" placeholder="비밀번호를 입력하세요" name="pass" id="pass">
     
     <label for="jumin1"><b>주민번호</b></label><br>
     <input type="text" placeholder="주민번호 앞자리" size="6" maxLength="6" 
             name="jumin1" id="jumin1" > 
     <span>-</span>
     <label for="jumin2"><b></b></label>
     <input type="text" placeholder="주민번호 뒷자리" size="7" maxLength="7" 
             name="jumin2" id="jumin2" >
             
     <label for="email"><b> 이메일 </b></label><br>
     <input type="text" placeholder="이메일 아이디를 입력하세요" name="email" id="email">@ 
     <input type="text" placeholder="이메일 주소를 입력하세요" name="domain" id="domain"> 
     <select name=sel id="sel"  >
                      <option value="">직접입력</option>
                      <option value="naver.com">naver.com</option>
                      <option value="daum.net">daum.net</option>
                      <option value="nate.com">nate.com</option>
                      <option value="gmail.com">gmail.com</option>
    </select>
                  
     <label><b>성별</b></label><br>             
     <div class="container2">
      <input type="radio" name="gender" value="m" id="gender1">남자&nbsp;&nbsp;&nbsp;
      <input type="radio" name="gender" value="f" id="gender2">여자     
     </div>
        
     <label><b>가입경로</b></label><br>    
      <div class="container2">
        <input type="checkbox" name="sulmun" id="sulmun1" value="검색">검색&nbsp;&nbsp;&nbsp;
        <input type="checkbox" name="sulmun" id="sulmun2" value="SNS">SNS&nbsp;&nbsp;&nbsp;
        <input type="checkbox" name="sulmun" id="sulmun3" value="광고">광고&nbsp;&nbsp;&nbsp;
        <input type="checkbox" name="sulmun" id="sulmun4" value="방문">방문&nbsp;&nbsp;&nbsp;
        <input type="checkbox" name="sulmun" id="sulmun5" value="추천">추천&nbsp;&nbsp;&nbsp;     
      </div>       
      <br>
     <label><b>우편번호</b></label><br>
     <input type="text" size="5" maxLength="5" name="post1" id="post1">                    
     <input type="button" value="우편검색" id=postcode><br>
     
     <label><b>주소</b></label><br>
     <input type="text" size="50" name="address" id="address">  
     
     <label><b>휴대폰번호</b></label><br>
     <input type="text" size="50" maxLength="13" placeholder="010-1234-5678" name="tel" id="tel" >  
     
     <label><b>자동가입방지문자</b></label><br>
     <input type="text" size=5 maxLength="5" name="output" id="output">
     <input type="button" value="문자확인" id="ckcode"><br>
     <input type="text" size=5  name="che" id="che">
     <input type="button" value="문자생성 " id="code"><br>
     
     
     <label><b>자기소개</b></label><br>
     
     <textarea rows="10" name="intro" id="intro"></textarea>
    
    <div class="clearfix">
     <button type="submit" class="signupbtn" >회원가입</button>
     <button type="button" id="button" class="cancelbtn">취소</button>

     
    </div>                 
     </fieldset>
     </div>
   </form>  
  </body>
</html>