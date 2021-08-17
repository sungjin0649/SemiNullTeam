<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>수정</title>

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
    <nav class="navbar navbar-default navbar-static-top"">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="image/logo.png"></div>
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
    
<div class="container theme-showcase" role="main">
      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
      	 <form action="Bbs_bkModify.bk" method="post" enctype="multipart/form-data" accept-charset="utf-8">
      	 <input type="hidden" name="BK_NO" value="${Bbs_bk_data.BK_NO}">
      	<h2>MVC 게시판_bk - 수정</h2>
      	<table>
      		<tr>
	      		<td style="width: 22%;" >
			      	<select class="form-control" name="BK_CSFC"> <!-- 이부분은 js스크립트로  -->
						  <option value="구매" selected>구매</option>
						  <option value="판매">판매</option>
						  <option value="무료나눔">무료나눔</option>
					</select>
	      		</td>
	      		<td  style="width: 5%;"><div>&nbsp;</div></td>
	      		<td  style="width: 73%;"><input type="text" name="BK_SUBJECT" class="form-control" value="${Bbs_bk_data.BK_SUBJECT}"></td>
      		</tr>
      		<tr >
      			<td style="width: 22%;"><input type="text" name="BK_PRICE" class="form-control" value="${Bbs_bk_data.BK_PRICE}"></td>
      			<td style="width: 5%;"></td>
      			<td style="width: 73%;"><input type="text" name="BK_PASS" class="form-control" placeholder="비밀번호"></td>
      		</tr>
      	</table>
        <span>&nbsp;</span>   
        <textarea class="form-control" name="BK_CONTENT" rows="25">${Bbs_bk_data.BK_CONTENT}</textarea>
        
        <div class="form-group">
			<label for="board_file">파일 첨부</label>
			<label for="upfile">
				<img src="image/attach.png" alt="파일첨부" width="20px">
			</label>
			<input  type="file" id="upfile" name="BK_FILE">
			 <span id="filevalue">${Bbs_bk_data.BK_FILE}</span>
		</div>
        <div style="text-align: right">
        	<button type="submit" class="btn btn-primary">수정</button>
			<button type="reset" class="btn btn-danger"
					onclick="history.go(-1)">취소</button>
        </div>
      </form>
      </div>
 </div>
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>