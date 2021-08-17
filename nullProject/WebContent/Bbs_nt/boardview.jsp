<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>부트스트랩 101 템플릿</title>

   <script src="js/jquery-3.6.0.min.js"></script>
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
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="../image/logo.png"></div>
  	<input type="hidden" value="${id}" id="loginid" name="loginid">
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
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
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
<div class="container theme-showcase" role="main">
	<table class="table">
		<tr>
            	  <th><div>번호</div></th>
            	  <td><div>${boarddata.nt_num}</div></td>
            	  <th><div>분류</div></th>
            	  <th><div>제목</div></th>
            	  <th><div>글쓴이</div></th>
            	  <th><div>날짜</div></th>
            	  <th><div>조회수</div></th>
        </tr>
	</table>
      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <input type="hidden" class="form-control" placeholder="번호">
        <span>&nbsp;</span>
        <input type="text" class="form-control" placeholder="제목">
        <span>&nbsp;</span>
         <input type="hidden" class="form-control" placeholder="글쓴이">
        <span>&nbsp;</span>
        <textarea class="form-control" rows="15">내용</textarea>
      </div>
       <div class="board_bottom2">
			<form action="/sub/customer/notice.asp" method="post">
			<input type='hidden' name='boardName' value="b_notice">
			<select name="keyfield" class="input_select">
			<option value="title" >제목</option>
			<option value="content" >내용</option>
			<option value="user_id">글쓴이</option>
			<option value="comment">댓글</option>
			</select>
            <input name="key" value=""   placeholder="" class="input_texth18" height="18"> 
            <input type="image" src="../image/search.png" width="20" height="20" alt="검색" class="input_image">
			</form>			
			</div>
 		</div>
      <table class="table">
      		<tr>
				<td colspan="2" class="center">
				  <a href="BoardReplyView.bo?num=${boarddata.nt_num}">
				  	<button class="btn btn-primary">답변</button>
				  </a>
				  
				   <a href="BoardModifyView.bo?num=${boarddata.nt_num}">
				   	  <button class="btn btn-info" >수정</button>
				   </a>
				   <a href="#">
				   	  <button class="btn btn-danger" data-toggle="modal"
				   	  		  data-target="#myModal">삭제</button>
				   </a>
				   
				   <a href="BoardList.bo">
				   	 <button class="btn btn-secondary">목록</button>
				   </a>
				   </td>
			   </tr>
      </table>
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>