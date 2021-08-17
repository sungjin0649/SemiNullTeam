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
    <title>수정 페이지</title>
   <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/frmodifyform.js"></script>
   
   
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


     <style>
     h1{font-size: 1.5rem; text-align:center; color:#1a92b9}
     .container{width:60%}
     label{font-weight: bold}
     #fr_file{display: none}
     img{width: 20px;}
     </style>
</head>
<body>
 <%-- 게시판 수정 --%>
<div class="container">
   <form action = "FreeboardModifyAction.okybo" method="post" enctype="multipart/form-data"
       name="boardform">
     <input type="hidden" name="fr_no" value="${boarddata.fr_no}">
     <h1>MVC 게시판-수정 페이지</h1>
       <div><select name="fr_csfc" id="fr_csfc"  style="width:80px;height:20px;">
        <c:if test="${boarddata.fr_csfc ==1 }">
         <option value="1" selected>잡담</option>
         <option value="2">이벤트</option>
         <option value="3">궁금해요</option>
         <option value="4">정보</option>
         <option value="5">기사</option>
		</c:if>
        <c:if test="${boarddata.fr_csfc ==2 }">
         <option value="1">잡담</option>
         <option value="2" selected>이벤트</option>
         <option value="3">궁금해요</option>
         <option value="4">정보</option>
         <option value="5">기사</option>
		</c:if>		
        <c:if test="${boarddata.fr_csfc ==3 }">
         <option value="1">잡담</option>
         <option value="2">이벤트</option>
         <option value="3" selected>궁금해요</option>
         <option value="4">정보</option>
         <option value="5">기사</option>
		</c:if>		
        <c:if test="${boarddata.fr_csfc ==4 }">
         <option value="1">잡담</option>
         <option value="2">이벤트</option>
         <option value="3">궁금해요</option>
         <option value="4" selected>정보</option>
         <option value="5">기사</option>         
		</c:if>
        <c:if test="${boarddata.fr_csfc ==5 }">
         <option value="1">잡담</option>
         <option value="2">이벤트</option>
         <option value="3">궁금해요</option>
         <option value="4">정보</option>
         <option value="5" selected>기사</option>         
		</c:if>		                                      
     </select></div><br>
      <div class="form-group">
       <label for="board_subject">제목</label>
     <input name="fr_subject" id="fr_subject"  type="text"  maxlength="100"
          class="form-control" value="${boarddata.fr_subject}"> 
       </div>
     <div class="form-group">
       <label for="board_name">글쓴이</label>
       <input name="id" id="id" value="${id}"   readOnly
              type="text"      class="form-control"
              placeholder="Enter board_name">           
      </div>
      
      <div class="form-group">
       <label for="board_pass">비밀번호</label>
       <input name="fr_pass" id="fr_pass"  type="password"  maxlength="30"
          class="form-control" placeholder="비밀번호를 입력하세요">                      
      </div>

      
      <div class="form-group">
       <label for="board_content">내용</label>
       <textarea name="fr_content" id="fr_content"  rows="10"  
          class="form-control" style="resize:none">${boarddata.fr_content}</textarea>                
      </div>       
      
         <%-- 원문글인 경우에만 파일 첨부 수정 가능합니다. --%>
     <c:if test="${boarddata.fr_re_lev==0}">
      <div class="form-group">
       <label for="board_file">파일 첨부</label>
       <label for="fr_file">
         <img src="image/attach.png" alt="파일첨부">
       </label>
       <input type="file" id="fr_file"  name="fr_file"> 
       <span id="filevalue">${boarddata.fr_file}</span>   
       <img src="image/remove.png" alt="파일삭제" width="10px" class="remove">             
      </div> 
           </c:if>  
      
      <div class="form-group">
        <button type=submit class="btn btn-primary">수정</button>
        <button type=reset class="btn btn-danger" 
        onClick="history.go(-1)">취소</button>
      </div>           
   </form>
 </div>
</body>
</html>