<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>영화 예매</title>

   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 <script src="js/list.js"></script>
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
<script>
$(function(){

    $("#rec").click(function(){
       location.href="Bbs_bkList.bk";
    })
 })
$(function(){

    $("#enr").click(function(){
       location.href="Bbs_bkListEnr.bk";
    })
 })
$(function(){

    $("#rea").click(function(){
       location.href="Bbs_bkListRea.bk";
    })
 })
</script>
  </head>
  <body>
  	<!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><a href="main.jsp"><img style="text-align: center; width: 100px" src="image/logo.png"></a></div>
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav" >
            <li><a href="#">영화</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li class="active"><a href="#about">영화예매</a></li>
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
    
<div class="container">
  
<%-- 게시글이 있는 경우--%> 
<c:if test="${listcount > 0 }">
 <div class="rows">
  <span>줄보기</span>
  <select class="form-control" id ="viewcount">
     <option value="1">1</option>
   <option value="3">3</option>
   <option value="5">5</option>
   <option value="7">7</option>
   <option value="10" selected>10</option>  
  </select>
 </div>
   <div class="form-group">
       <button  type="button" class="btn btn-primary" id = "rec" name="rec" >최신순</button>
        <button type="button" class="btn btn-primary" id = "enr" name ="enr" >등록순</button>
        <button type="button" class="btn btn-primary" id= "rea" name = "rea" >조회순</button>
   </div>
  <table class="table table-striped">
   <thead>
   
   <tr>
      <th colspan="3">영화후기 게시판</th>
      <th colspan="2">
         <font size=3>글 개수 : ${listcount}</font>
      </th>
   </tr>
   <tr>
      <th><div>번호</div></th>
      <th><div>제목</div></th>
      <th><div>영화제목</div></th>
      <th><div>글쓴이</div></th>
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
      <c:set var="num" value="${num-1}"/>   <%-- num=num-1; 의미--%>   
     </td>
     <td><%--제목 --%>
        <div>         
         <%-- 답변글 제목앞에 여백 처리 부분 
               board_re_lev,  board_num, 
               board_subject, board_name, board_date, 
               board_readcount : property 이름 --%>
          <c:if test="${b.BK_RE_LEV != 0}">  <!--  답글인 경우 -->
            <c:forEach var="a" begin="0" end="${b.BK_RE_LEV*2}" step="1">
            &nbsp;    
            </c:forEach>      
            <img src='image/line.gif'>
         </c:if>  
          
         <c:if test="${b.BK_RE_LEV == 0}">  <%-- 원문인 경우 --%>
            &nbsp;  
         </c:if>          
         
         <a href="Bbs_bkDetail.bk?num=${b.BK_NO}">
             <c:out value="${b.BK_SUBJECT}" />  
            <%-- ${b.board_subject} --%>
            <%-- escapeXml="true" : HTML 태그를 화면에 그대로 보여줍니다. --%>   
         </a>
        </div>
            <%-- 분류 --%>

      </td>
       <td>
           ${b.BK_CSFC}
        </td> 
      <td><div>${b.USER_ID}</div></td>
      <td><div>${b.BK_DATE}</div></td>   
      <td><div>${b.BK_READ}</div></td>
      </tr>
     </c:forEach>
    </tbody>   
   </table>
      
   <div class="center-block">
        <ul class="pagination justify-content-center">      
          <c:if test="${page <= 1 }">
            <li class="page-item">
              <a class="page-link gray">이전&nbsp;</a>
            </li>
          </c:if>
          <c:if test="${page > 1 }">         
            <li class="page-item">
               <a href="Bbs_bkListEnr.bk?page=${page-1}" 
                  class="page-link">이전&nbsp;</a>
            </li> 
          </c:if>
               
         <c:forEach var="a" begin="${startpage}" end="${endpage}">
            <c:if test="${a == page }">
               <li class="page-item " >
                  <a class="page-link gray">${a}</a>
               </li>
            </c:if>
            <c:if test="${a != page }">
                <li class="page-item">
                  <a href="Bbs_bkListEnr.bk?page=${a}" 
                  class="page-link">${a}</a>
                </li>   
            </c:if>
         </c:forEach>
         
         <c:if test="${page >= maxpage }">
            <li class="page-item">
               <a class="page-link gray">&nbsp;다음</a> 
            </li>
         </c:if>
         <c:if test="${page < maxpage }">
           <li class="page-item">
            <a href="Bbs_bkListEnr.bk?page=${page+1}" 
               class="page-link">&nbsp;다음</a>
           </li>   
         </c:if>
       </ul>
      </div>
     </c:if><%-- <c:if test="${listcount > 0 }"> end --%>
  
</div>
 <%-- 게시글이 없는 경우--%>
<c:if test="${listcount == 0 }">
	<font size=5>등록된 글이 없습니다.</font>
</c:if>
<!-- 검색기능 -->

<form class="navbar-form navbar-right" method="get" action="Bbs_bkList.bk" name="search">
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
<button type="button" class="btn btn-info float-right" id="writebtn">글 쓰 기</button>
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>