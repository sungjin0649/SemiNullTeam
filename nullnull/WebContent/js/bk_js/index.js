$(document).ready(function () {
	var output="";
	
		$("#logi3n").click(function () {			
			alert("확인됨!");
			output +='<div><h1>환영합니다!</h1>'
                   +'<form id="my-form" action="success.html">'
                   +'<label for = "name">아이디</label><br>'
                   +'<input type ="text" name="name" id="name"><br>'              
                   +'<label for = "password">비밀번호</label><br>'
                   +'<input type ="password" name="password" id="password"><br>'
                   +'<input type="checkbox" id="remember" name="remember" value="store">'
                   +'<span>remember</span>'
                   +'<h4><a href="password.html">ID/PW찾기</a></h4><br>'
                   +'<input type ="submit" value="로그인">&nbsp;&nbsp;&nbsp;'
                   +'<input type="button" id="button" value="회원가입" >'
                   +'</form></div>'
                   $('.container1').html(output);       
                   
		})


});// ready()end