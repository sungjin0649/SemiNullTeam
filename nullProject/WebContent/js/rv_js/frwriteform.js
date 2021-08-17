$(document).ready(function () {
	
	// submit 버튼 클릭할 때 이벤트 부분
	$("form").submit(function () {
		
		if ($.trim($("#fr_pass").val()) == "") {
			alert("비밀번호를 입력하세요");
			$("#fr_pass").focus();
			return false;
		}
		
		if ($.trim($("#fr_subject").val()) == "") {
			alert("제목를 입력하세요");
			$("#fr_subject").focus();
			return false;
		}	
		
		if ($.trim($("textarea").val()) == "") {
			alert("내용을 입력하세요");
			$("textarea").focus();
			return false;
		}		
	});//submit end
	
	$("#fr_file").change(function () {
		console.log($(this).val())  //c:\fakepath\upload.png
		var inputfile = $(this).val().split('\\');
		$("#filevalue").text(inputfile[inputfile.length - 1]);
	});
	
	$("#cancel").click(function(){
		location.href="Freeboard.okybo";
});
});// ready()end