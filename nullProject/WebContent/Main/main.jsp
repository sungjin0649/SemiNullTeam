<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
 <jsp:include page="header.jsp" />  
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

    
    <!-- carousel -->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div style="background: lightgray;" class="carousel-inner" role="listbox">
    <div class="item active">
    	<img style="margin: 0 auto; " src="image/sea.jpg" width="1000">
      <div class="carousel-caption">
      	첫번째 이미지
      </div>
    </div>
    <div class="item">
      	<img style="margin: 0 auto; " src="image/sea.jpg" width="1000">
      <div class="carousel-caption">
		두번째 이미지
      </div>
    </div>
    <div class="item">
      <img style="margin: 0 auto; " src="image/sea.jpg" width="1000">
      <div class="carousel-caption">
		세번째 이미지
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
    
    
<!-- bbs -->
<div class="aa">

</div>
<div class=" row">
        <div style="width: 410px" class="col-lg-4">
          <div style="margin-top: 50px ">
          	<a href="#">
          		<span>공지사항</span>
          		<span style="float: right">+더보기</span>
          	</a>
          	
          </div>
          <hr style="margin-top: 10px; text-align:left;">
          <table>
          		<tr>
          			<td><a href="#">1. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">2. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">3. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">4. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">5. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">6. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">7. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          </table>
        </div>
        <div style="width: 410px" class="col-lg-4">
          <div style="margin-top: 50px ">
          	<a href="#">
          		<span>예매대행</span>
          		<span style="float: right">+더보기</span>
          	</a>
          	
          </div>
          <hr style="margin-top: 10px">
          <table>
          		<tr>
          			<td><a href="#">1. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">2. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">3. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">4. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">5. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">6. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">7. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          </table>
       </div>
        <div style="width: 410px" class="col-lg-4">
          <div style="margin-top: 50px ">
          	<a href= "RvList.rv">
          		<span>영화 후기</span>
          		<span style="float: right">+더보기</span>
          	</a>
          	
          </div>
          <hr style="margin-top: 10px">
          <table>
          		<tr>
          			<td><a href="#">1. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">2. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">3. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">4. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">5. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">6. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          		<tr>
          			<td><a href="#">7. 공지사항의 최신 글 제목부분 입니다.</a> </td>
          		</tr>
          </table>
		</div>
      </div>
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>