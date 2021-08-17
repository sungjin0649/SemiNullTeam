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
    <title>답변작성</title>
    
   <script src="js/jquery-3.6.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
     <script src="js/reply.js"></script>
     <style>
     h1{font-size: 1.5rem; text-align:center; color:#1a92b9}
     .container{width:60%}
     label{font-weight: bold}
     </style>
</head>
<body>
    넘어온 id는 ${id}  <br>
    
 <div class="container">
   <form action = "FreeboardReplyAction.okybo" method="post" name="boardform">
     <input type="hidden" name="fr_csfc" value="${boarddata.fr_csfc}">
     <input type="hidden" name="fr_re_ref" value="${boarddata.fr_re_ref}">
     <input type="hidden" name="fr_re_lev" value="${boarddata.fr_re_lev}">
     <input type="hidden" name="fr_re_seq" value="${boarddata.fr_re_seq}">
     <h1>MVC 게시판-Reply</h1>
     <div class="form-group">
       <label for="board_name">글쓴이</label>
       <input name="id" id="id" value="${id}"   readOnly
              type="text"      class="form-control">           
      </div>
      <div class="form-group">
       <label for="board_subject">제목</label>
       <textarea name="fr_subject" id="fr_subject" rows="1" 
        maxlength="100" class="form-control" readOnly >Re:${boarddata.fr_subject}</textarea>                 
      </div>       
      
      <div class="form-group">
       <label for="board_content">내용</label>
       <textarea name="fr_content" id="fr_content"  rows="10"  
          class="form-control" style="resize:none"></textarea>                
      </div>       
      
      <div class="form-group">
       <label for="board_pass">비밀번호</label>
       <input name="fr_pass" id="fr_pass"  
              type="password"   class="form-control">                      
      </div>
 
      <div class="form-group">
        <input type=submit class="btn btn-primary" value="등록">
        <input type=button class="btn btn-danger" value="취소"
                onClick="history.go(-1)">
      </div>           
   </form>
 </div>   
   
</body>
</html>
