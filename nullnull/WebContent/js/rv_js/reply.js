$(document).ready(function(){
	
	//submit 버튼 클릭할 때 이벤트 부분
	$("form").submit(function(){
		
		if($.trim($("#board_subject").val())==""){
			alert("제목을 입력하세요 ");
			$("#board_subject").focus();
			return false;
		}
		
		if($.trim($("#board_content").val())==""){
			alert("내용을 입력하세요");
			$("#board_content").focus();
			return false;
		}
		
		if($.trim($("input:password").val())==""){
			alert("비밀번호를 입력하세요");
			$("input:password").focus();
			return false;
		}
	})
})