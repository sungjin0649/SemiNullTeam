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
    <title>자유게시판메인</title>
    
   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/fr_js/list.js"></script>
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
       
      $("#loginform").submit(function() {
    	   if ($.trim($("#id").val()) =="") {
    		   
    		   alert("ID를 입력 하세요");
    		   $("#id").focus();
    		   return false;
    	   } 
    	   if ($.trim($("#pass").val()) =="") {
    		 
    		   alert("비밀번호를 입력 하세요");
    		   $("#pass").focus();
    		   return false;
    	   }   	    	   
      });
      
      $("#all").click(function(){
          location.href="Freeboard.okybo";
       })

       $("#cate1").click(function(){
          location.href="FrList1.okybo";
       })
 
       $("#cate2").click(function(){
          location.href="FrList2.okybo";
       })
       
       $("#cate3").click(function(){
          location.href="FrList3.okybo";
       })

       $("#cate4").click(function(){
          location.href="FrList4.okybo";
       })       

       $("#cate5").click(function(){
          location.href="FrList5.okybo";
       })       
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

    
<div class="container1" role="main">
      
    <h1>게시판</h1>
      넘어온 id는 ${id} <br>
      넘어온 startpage는 ${startpage} <br>
     넘어온 endpage는 ${endpage} <br>
     넘어온 maxpage는 ${maxpage}<br>
      넘어온 page는 ${page}<br><br>
      
      
<div  align="left">분류&nbsp;&nbsp;&nbsp;
                     <button type="button" id="all" name="all" value="0">전체</button>&nbsp;&nbsp;&nbsp;
                     <button type="button" id="cate1" name="cate1" value="1">잡담</button>&nbsp;&nbsp;&nbsp;
                     <button type="button" id="cate2" name="cate2" value="2">이벤트</button>&nbsp;&nbsp;&nbsp;
                     <button type="button" id="cate3" name="cate3" value="3">궁금해요</button>&nbsp;&nbsp;&nbsp;
                     <button type="button" id="cate4" name="cate4" value="4">정보</button>&nbsp;&nbsp;&nbsp;
                     <button type="button" id="cate5" name="cate5" value="5">기사</button>
</div>
<br>


<%-- 게시글이 있는 경우--%> 
<c:if test="${listcount > 0 }">
  <div class="rows" >
   <span>정렬</span>
   <select class="form-control" id="view" style="width:100px;">
      <option value="1" selected>최신순</option>
      <option value="2">등록순</option>
      <option value="3">조회순</option>                     
   </select>
  </div>

    
  <table class="table table-striped">
   <thead>
	<tr>
	   <th colspan="3">MVC 게시판 - list</th>
	   <th colspan="2">
			<font size=3>글 개수 : ${listcount}</font>
	   </th>
	</tr>
	<tr>
		<th><div>번호</div></th>
		<th><div>분류</div></th>
		<th><div>제목</div></th>
		<th><div>작성자</div></th>
		<th><div>날짜</div></th>
		<th><div>조회수</div></th>
	</tr>	
   </thead>
   <tbody>
	<c:set var="num" value="${listcount-(page-1)*limit}"/>	
	<c:forEach var="b" items="${boardlist}">	
	<tr>
	  <td><%--번호 --%>
		<c:out value="${num}"/><%-- num 출력 --%>		
		<c:set var="num" value="${num-1}"/>	<%-- num=num-1; 의미--%>	
	  </td>
	  <td ><%--분류 --%>
		<%-- <c:out value="${b.fr_csfc}"/>해당분류 출력		 --%>
	    <c:if test="${b.fr_csfc ==1 }">
	    잡담
		</c:if>
	    <c:if test="${b.fr_csfc ==2 }">
	    이벤트
		</c:if>
	    <c:if test="${b.fr_csfc ==3 }">
	    궁금해요
		</c:if>		
	    <c:if test="${b.fr_csfc ==4 }">
	    정보
		</c:if>
	    <c:if test="${b.fr_csfc ==5 }">
	    기사
		</c:if>					
	  </td>	  
	  <td><%--제목 --%>
	     <div>			
			<%-- 답변글 제목앞에 여백 처리 부분 
			      board_re_lev,  board_num, 
			      board_subject, board_name, board_date, 
			      board_readcount : property 이름 --%>
		    <c:if test="${b.fr_re_lev != 0}">  <!--  답글인 경우 -->
				<c:forEach var="a" begin="0" end="${b.fr_re_lev*2}" step="1">
				&nbsp; 	
				</c:forEach>		
				<img src='image/line.gif'>
			</c:if>  
			 
			<c:if test="${b.fr_re_lev == 0}">  <%-- 원문인 경우 --%>
				&nbsp;  
			</c:if> 			
			
			<a href="FreeboardDetailAction.okybo?num=${b.fr_no}">
				 <c:out value="${b.fr_subject}" />  
				<%-- ${b.board_subject} --%>
				<%-- escapeXml="true" : HTML 태그를 화면에 그대로 보여줍니다. --%>	
			</a>
		  </div>
		</td>
		<td><div>${b.id}</div></td>
		<td><div>${b.fr_date}</div></td>	
		<td><div>${b.fr_read}</div></td>
	   </tr>
	  </c:forEach>
	 </tbody>	
	</table>
	<div id="category">카테고리는 ${category}</div>
	<div class="center-block">
		  <ul class="pagination justify-content-center">		
			 <c:if test="${page <= 1 }">
				<li class="page-item">
				  <a class="page-link" style="color:gray">이전&nbsp;</a>
				</li>
			 </c:if>
			 
			 
			 <c:if test="${page > 1 }">			
				<li class="page-item">
				<c:if test="${category ==null }">
				   <a href="Freeboard.okybo?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
		        </c:if>
				<c:if test="${category ==1 }">
				   <a href="FrList1.okybo?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
		        </c:if>
				<c:if test="${category ==2 }">
				   <a href="FrList2.okybo?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
		        </c:if>	
				<c:if test="${category ==3 }">
				   <a href="FrList3.okybo?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
		        </c:if>	
				<c:if test="${category ==4 }">
				   <a href="FrList4.okybo?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
		        </c:if>	
				<c:if test="${category ==5 }">
				   <a href="FrList5.okybo?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
		        </c:if>			        		        		        		        
				</li> 
			 </c:if>
					
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					<li class="page-item " >
					   <a class="page-link" style="color:gray">${a}</a>
					</li>
				</c:if>
				
				
				<c:if test="${a != page }">
				 <c:if test="${category ==null }">
				    <li class="page-item">
					   <a href="Freeboard.okybo?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				 </c:if>
				 <c:if test="${category ==1 }">
				    <li class="page-item">
					   <a href="FrList1.okybo?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				 </c:if>
				 <c:if test="${category ==2 }">
				    <li class="page-item">
					   <a href="FrList2.okybo?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				 </c:if>
				 <c:if test="${category ==3 }">
				    <li class="page-item">
					   <a href="FrList3.okybo?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				 </c:if>
				 <c:if test="${category ==4 }">
				    <li class="page-item">
					   <a href="FrList4.okybo?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				 </c:if>
				 <c:if test="${category ==5 }">
				    <li class="page-item">
					   <a href="FrList5.okybo?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				 </c:if>				 				 				 				 				 
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				<li class="page-item">
				   <a class="page-link" style="color:gray">&nbsp;다음</a> 
				</li>
			</c:if>
			
			
			<c:if test="${page < maxpage }">
			 <c:if test="${category ==null }">
			  <li class="page-item">
				<a href="Freeboard.okybo?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>		      
			  </li>	
			 </c:if>
			 <c:if test="${category ==1 }">
			  <li class="page-item">
				<a href="FrList1.okybo?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>		      
			  </li>	
			 </c:if>	
			 <c:if test="${category ==2 }">
			  <li class="page-item">
				<a href="FrList2.okybo?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>		      
			  </li>	
			 </c:if>
			 <c:if test="${category ==3 }">
			  <li class="page-item">
				<a href="FrList3.okybo?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>		      
			  </li>	
			 </c:if>
			 <c:if test="${category ==4 }">
			  <li class="page-item">
				<a href="FrList4.okybo?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>		      
			  </li>	
			 </c:if>
			 <c:if test="${category ==5 }">
			  <li class="page-item">
				<a href="FrList5.okybo?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>		      
			  </li>	
			 </c:if>			 			 			 			 		 
			</c:if>
		 </ul>
		</div>
     </c:if><%-- <c:if test="${listcount > 0 }"> end --%>
	
<%-- 게시글이 없는 경우--%>
<c:if test="${listcount == 0 }">
	<font size=5>등록된 글이 없습니다.</font>
</c:if>

<!-- 검색기능 -->

<form class="navbar-form navbar-right" method="get" action="Freeboard.okybo" name="search">
		<div class="form-group">
			<select name="searchType" id="searchType">
				<option value="subAcon"${(searchType =="subAcon")? "selected" : ""}>제목+내용</option>
				<option value="subject"${(searchType =="subject")? "selected" : ""}>제목</option>
				<option value="content"${(searchType =="content")? "selected" : ""}>내용</option>
				<option value="writer"${(searchType =="writer")? "selected" : ""}>글쓴이</option>
			</select>
			<c:if test="${search==''}">
				<input type="text" placeholder="내용을 입력하세요" class= "form-control" name="search" id="search">
			</c:if>
			<c:if test="${search !='' }">
				<input type="text" placeholder="내용을 입력하세요" class="form-control" name="search" id="search" value="${search}">
			</c:if>
		</div>
			<button  class="btn btn-success" id="searchbtn">검색</button>
</form>  

<!-- 검색기능 끝 --> 
 
 
   <c:if test="${!empty id}">
   <button type="button" id="write" class="btn btn-info float-right">글 쓰 기</button>  
   </c:if>
 </div>
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>
    
    
 <!-- 로그인 모달 -->   
 <div class="modal fade" id="login" role="dialog"> <!-- 사용자 지정 부분① : id명 -->

    <div class="modal-dialog">
   
      <!-- Modal content-->

      <div class="modal-content">

        <div class="modal-header">

          <button type="button" class="close" data-dismiss="modal">×</button>

          <h4 class="modal-title" align="center">로그인</h4> <!-- 사용자 지정 부분② : 타이틀 -->

        </div>

        <div class="modal-body">
                   <form name="loginform" id="loginform" action="loginProcess.oky" method="post">
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