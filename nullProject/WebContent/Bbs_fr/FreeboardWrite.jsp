 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>자유게시판 글쓰기 페이지</title>
   <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/frwriteform.js"></script>
   
   
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
 <div class="container">
   <form action = "FreeboardAddAction.okybo" method="post" enctype="multipart/form-data"
       name="boardform">
     <h1>MVC 게시판-write 페이지</h1>
       <div><select name="fr_csfc" id="fr_csfc"  style="width:80px;height:20px;">
                      <option value="1">잡담</option>
                      <option value="2">이벤트</option>
                      <option value="3">궁금해요</option>
                      <option value="4">정보</option>
                      <option value="5">기사</option>                                        
     </select></div><br>
      <div class="form-group">
       <label for="board_subject">제목</label>
     <input name="fr_subject" id="fr_subject"  type="text"  maxlength="100"
          class="form-control" placeholder="제목을 입력하세요">                      
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
          class="form-control" style="resize:none"></textarea>                
      </div>       
      
      <div class="form-group">
       <label for="board_file">파일 첨부</label>
       <label for="fr_file">
         <img src="image/attach.png" alt="파일첨부">
       </label>
       <input type="file" id="fr_file"  name="fr_file"> 
       <span id="filevalue"></span>                
      </div> 
      <div class="form-group">
        <button type=submit class="btn btn-primary">등록</button>
        <button type=button id="cancel" class="btn btn-danger">취소</button>
      </div>           
   </form>
 </div>
</body>
</html>