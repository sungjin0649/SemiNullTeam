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
    <title>자유게시판 게시글 뷰페이지</title>
    
   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/view.css">
<script src="js/fr_js/view.js"></script>

<script>
      $(function(){ 
    	  var id = '${id}';
      	   $(".join1").click(function(){ //메인에서 회원가입버튼 클릭 시
    		   location.href="join.oky";
    	   });

      	   $(".join2").click(function(){ //로그인창에서 회원가입버튼 클릭 시
    		   location.href="join.oky";
    	   });

      	   $(".findidpw").click(function(){ //ID/PW찾기 클릭 시
    		   location.href="find.oky";
    	   });      	   
    	   $(".login").click(function(){//아이디 저장 기능
    	   var id = '${id}';
    	   if(id){
    		   $("#id").val(id);
    		   $("#remember").prop('checked',true);
    	   }
           });
    	   $("#contact").click(function(){ //1:1 문의 클릭시 아이디 없으면 안넘어가게하기
       		  if(!id){
       			  alert("로그인 후 이용해 주세요");
       			  return false;
       		  }
       	   });
      $("#loginaction").submit(function() {//자유게시판 뷰페이지에서 로그인 시도시
    	   if ($.trim($("#id").val()) =="") {
    		   
    		   alert("ID를 입력 하세요");
    		   $("#id").focus();
    		   return false;
    	   } 
    	   if ($.trim($("#pass").val()) =="") {
    		 
    		   alert("로그인시 비밀번호를 입력 하세요");
    		   $("#pass").focus();
    		   return false;
    	   }   	    	   
      });
      
      });

</script>   

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
      <c:if test="${empty id}">
    	<div align="right"><a class="login" data-toggle="modal" href="#login">로그인</a>&nbsp;&nbsp;&nbsp;<a class="join1" href="#">회원가입</a></div>
      </c:if>
     <c:if test="${!empty id&&id!='admin'}">
		<div align="right"><a class="logout" href="logout.oky">${id} 님(로그아웃)</a>&nbsp;&nbsp;&nbsp;<a class="nav-link" href="memberUpdate.oky">정보수정</a></div>			
      </c:if>
      <c:if test="${!empty id&&id=='admin'}">
		<div align="right"><a class="logout" href="logout.oky">${id} 님(로그아웃)</a>&nbsp;&nbsp;&nbsp;
		<a class="nav-link" href="memberUpdate.oky">정보수정</a>&nbsp;&nbsp;&nbsp;
		<a class="nav-link" href="memberList.oky">회원정보</a> </div>		
		</c:if>
   
    	    	<div align="center">
    	    	<a href="http://localhost:8088/nullnull/"><img style="text-align: center; width: 200px" src="image/logo.png" ></a></div>
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav" >
            <li class="active"><a href="#">영화</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#about">영화예매</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown">커뮤니티</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="Freeboard.okybo">자유게시판</a><br>
						<a class="dropdown-item" href="#">예매대행</a><br>
						<a class="dropdown-item" href="#">영화후기</a>
					</div></li>
            <li style="width: 50px;">&nbsp;</li>
            <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown">고객센터</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">공지사항</a><br>
						<a class="dropdown-item" href="#" >자주 묻는 질문</a><br>
						<a class="dropdown-item" href="ContactUs.okycon" id="contact">1:1문의</a>
					</div></li>					
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<!-- 상단 -->

넘어온 id는 ${id} <br>

<input type="hidden" id="loginid" value="${id}" name="loginid">
 <div class="container">
   <table class="table">
     <thead><tr><th colspan="4">MVC 게시판-view페이지</th></tr></thead>
     <tr>      
         <td><div>분류:
          	    <c:if test="${boarddata.fr_csfc ==1 }">
	                        잡담
		        </c:if>
	            <c:if test="${boarddata.fr_csfc ==2 }">
	                        이벤트
		        </c:if>
	            <c:if test="${boarddata.fr_csfc ==3 }">
	                        궁금해요
		        </c:if>		
	            <c:if test="${boarddata.fr_csfc ==4 }">
	                         정보
		        </c:if>
	             <c:if test="${boarddata.fr_csfc ==5 }">
	                         기사
		         </c:if>	             
          </div></td>
          
         <td><div>날짜 : ${boarddata.fr_date}</div> </td>
         
         <td><div>조회수 : ${boarddata.fr_read}</div>
         
      </tr>
     <tr>
         <td colspan="4"><div>글쓴이: ${boarddata.id}</div></td>
     </tr>
     <tr>
         <td colspan="4"><div>제목: <c:out value="${boarddata.fr_subject}" /></div></td>
     </tr>     
     <tr>
         <td colspan="4" style="padding-right:0px"><textarea class="form-control" rows="5"  style="resize: none;"
               readOnly >${boarddata.fr_content}</textarea></td>
     </tr>     
     
   <c:if test="${boarddata.fr_re_lev==0}"><%--원문글인 경우에만 첨부파일을 추가 할 수 있습니다. --%>
    <tr>
      <td colspan="4"><div>첨부파일
     <c:if test="${!empty boarddata.fr_file}"><%-- 파일첨부한 경우 --%>
      <img src="image/down.png" width="10px">
          <a href="FreeboardFileDown.okybo?filename=${boarddata.fr_file}">
            ${boarddata.fr_file}</a>
      </c:if>
      </div></td>
      <c:if test= "${empty boarddata.fr_file}"><%-- 파일첨부하지 않은 경우 --%>
          <td></td>
      </c:if>
      </tr>
   </c:if>
     
     <tr>
         <td colspan="4" class="center">
           <a href="FreeboardReplyView.okybo?num=${boarddata.fr_no}">
              <c:if test="${!empty id}">
             <button class="btn btn-primary">답변</button>
             </c:if>
           </a>
           
           <c:if test="${boarddata.id == id || id == 'admin' }">
            <a href="FreeboardModifyView.okybo?num=${boarddata.fr_no}">
              <button class="btn btn-info">수정</button>
            </a>
            <%-- href의 주소를 #으로 설정합니다. --%>
            <a href="#">
              <button class="btn btn-danger" data-toggle="modal"
                      data-target="#myModal">삭제</button>
            </a>
           </c:if>
           
           <a href="Freeboard.okybo">
              <button class="btn btn-secondary">목록</button>
           </a>
           </td>
           </tr>              
   </table>
<%-- 게시판 view end --%>

             <%-- modal 시작 --%>
             <div class="modal" id="myModal">
              <div class="modal-dialog">
               <div class="modal-content">
                 <%-- Modal body --%>
                 <div class="modal-body">
                  <form name="deleteForm" action="FreeboardDeleteAction.okybo" method="post">
                    <%-- http://localhost:8888/6Board/BoardDetailAction.bo?num=22
                                                 주소를 보면 num을 파라미터로 넘기고 있습니다.
                                                 이 값을 가져와서 ${param.num}를 사용
                                                 또는 ${boarddata.board_num}
                     --%>
                     <input type="hidden" name="num" value="${param.num}"
                         id="comment_board_num">
                     <div class="form-group">
                        <label for="pwd">비밀번호</label>
                        <input type="password"
                               class="form-control" placeholder="Enter password"
                               name="board_pass" id="board_pass">
                     </div>
                     <button type="submit" class="btn btn-primary">삭제</button>
                     <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                  </form>
                 </div><!-- class="modal-body" -->
               </div><!-- class="modal-content" -->
              </div><!-- class="modal-dialog" -->
             </div>
             <!-- class="modal" -->
             
             
             <!-- 댓글 작성 영역 -->
             <div class="CommentBox">
                 <div class="comment_option">
                    <h3 class="comment_title">
                                             댓글 <sup id="count"></sup> 
                    </h3>
                    <div class="comment_tab">
                        <ul class="comment_tab_list">
                        </ul>
                    </div>
                 </div><!-- coment option end -->
                 <ul class="comment_list">
                 </ul>
                 <div class="CommentWriter">
                     <div class="comment_inbox">
                         <b class="comment_inbox_name">${id}</b> <span                       
                              class="comment_inbox_count">0/200</span>
                         <textarea placeholder="댓글을 남겨보세요" rows="1"
                             class="comment_inbox_text" style="width:400px; height:80px; resize: none;" maxLength="200" ></textarea>
                             
                    </div>
                    <div class="register_box">
                        <div class="button btn_cancel">취소</div>
                         <div class="button btn_register">등록</div>                      
                    </div>
                 </div><!-- CommentWriter end -->
             </div><!-- CommentBox end -->
          </div><!-- container end -->
          
          
 <!-- 해드 로그인 부분 -->         
          <div class="modal fade" id="login" role="dialog"> <!-- 사용자 지정 부분① : id명 -->

    <div class="modal-dialog">
   
      <!-- Modal content-->

      <div class="modal-content">

        <div class="modal-header">

          <button type="button" class="close" data-dismiss="modal">×</button>

          <h4 class="modal-title" align="center">로그인</h4> <!-- 사용자 지정 부분② : 타이틀 -->

        </div>

        <div class="modal-body">
                   <form id="loginaction" name="loginform" action="loginProcess.oky" method="post">
                   <label for = "id">아이디</label><br>
                   <input type ="text" name="id" id="id"><br> 
                               
                   <label for = "pass">비밀번호</label><br>
                   <input type ="password" name="pass" id="pass"><br>
                   
                   <input type="checkbox" id="remember" name="remember" value="store">
                   <span>아이디 저장</span><br>
                   
                   <input type ="submit" value="로그인"><br>
                   
                   <a href="#" class="findidpw">ID/PW찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
                   <a href="#" class="join2">회원가입</a>
                   </form>
        </div>
      <!-- 사용자 지정 부분③ : 텍스트 메시지 -->

        </div>

        <div class="modal-footer">


        </div>

      </div>

    </div>
</body>
</html>