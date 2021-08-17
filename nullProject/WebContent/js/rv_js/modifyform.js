$(document).ready(function(){
	var check = 0 ;
	
	//submit 버튼 클릭할 때 이벤트 부분
	$("form").submit(function(){
		
		if($.trim($("#RV_SUBJECT").val())==""){
			alert("제목을 입력하세요");
			$("#RV_SUBJECT").focus();
			return false;
		}
		
		if($.trim($("#RV_CONTENT").val())==""){
			alert("내용을 입력하세요");
			$("#RV_CONTENT").focus();
			return false;
		}
		
		if($.trim($("#RV_PASS").val())==""){
			alert("비밀번호를 입력하세요 ")
			$("#RV_PASS").focus();
			return false;
		}
		
		/*
		 이곳이 적용되는 경우
		 1. 파일 첨부를 변경하지 않은 경우
		 	파일 첨부를 변경하지 않으면 $('#filevalue').text()의 파일명을 
		 	파라미터 'check'라는 이름으로 form에 추가하여 전송합니다.
		 
		 2. 파일첨부의 remove 이미지를 클릭해서 파일을 제거하고 파일첨부를 변경하지 않은 경우 
		 	이때 value의 값은 ''입니다.
		 * 
		 */
		if (check==0){
			value=$('#filevalue').text();
			html = "<input type='text' value='" + value + "' name = 'check'>";
			$(this).append(html);
		}
	}); //submit end 
	
	function show() {
		//파일 이름이 있는 경우 remove 이미지를 보이게 하고
		//파일 이름이 없는 경우 remove 이미지 보이지 않게 합니다.
		if($('#filevalue').text()==''){
			$(".remove").css('display','none');
		}else{
			$(".remove").css({'display': 'inline-block' , 
								'position' : 'relative','top':'-5px'});
		}
	}
	
	show();
	
	$("#upfile").change(function(){
		check++;
		var inputfile = $(this).val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length - 1]);
		show();
		console.log(check);
	});
	
	//remove 이미지를 클릭하면 파일명을 ''로 변경하고 remove 이미지를 보이지 않게 합니다.
	$(".remove").click(function(){
		$('#filevalue').text('');
		$(this).css('display','none');
	})

})// ready() end