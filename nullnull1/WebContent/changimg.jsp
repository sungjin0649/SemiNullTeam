<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
   <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/Cal.js"></script>
   <script src="js/movie_select.js"></script>
</head>
 <style>
 	div{
 		width: 100px;
 	}
</style>
</head>
<body>
	    <div class="choice_movieinfo">
	    	<button>버튼</button>
	    	<div class="cal">zz</div>
	    	<div class="cal">dfdf</div>
	    </div>

    <script>
    	$(document).ready(function(){
    		$('img').click(function(){
    			$("button").text('로딩완료').css('color','red');	
    	  	  	  $("#view").attr("src","https://img.cgv.co.kr/Movie/Thumbnail/Poster/000083/83847/83847_320.jpg");
    		})
    			$('.cal').click(function(){
					$(this).css('background','red');
				})
    	})

    </script>
	
</body>
</html>