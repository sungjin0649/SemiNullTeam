$(document).ready(function () {
	var codeck= 0; 	 //자동가입방지문자확인

	
	$("#sel").change(function(){//이메일 도메인관련
		if($("#sel").val()== ""){ 
			$("#domain").val("").focus();
			$("#domain").prop("readOnly",false);
		}else { 
			$("#domain").val($("#sel").val());
			$("#domain").prop("readOnly",true);
		}
	}); 
	
	$("#phone").keyup(function () {
		if($("#phone").val().length == 11){
			pattern = /^[0][1][0][0-9]{8}$/;
			if(!pattern.test($("#phone").val())){
				alert("형식에 맞게 입력하세요");
				$("#phone").val('').focus();				
			}
		}
	}); 
	

    
	var random = Math.floor(Math.random() * 1000000) + 1; //자동가입방지문자 난수생성
	
	$("#code").click(function() {
	    $("#che").val(random);		
	})
	
	
	$("#ckcode").click(function() {
		if	( $("#che").val() == $("#output").val() && $("#che").val() !=""){
			 alert("정상적으로 확인되었습니다.");
			 codeck =1;
		}else {
			alert("자동가입방지문자를 다시입력하세요")
			$("#output").val('').focus();	
		}
	})
    
	

$("form").submit(function() {

   if ($.trim($("#id").val()) =="") {
	   alert("ID를 입력 하세요");
	   $("#id").focus();
	   return false;
   }
   if ($.trim($("#pass").val()) =="") {
	   alert("비밀번호를 입력 하세요");
	   $("#pass").focus();
	   return false;
   }
   
   if ($.trim($("#pass").val()) != $.trim($("#passck").val())) {
	   alert('비밀번호를 재확인을 다시 해주세요')
	   $("#passck").val('').focus();
	   return false;
	 }   
   
   if ($.trim($("#name").val()) =="") {
	   alert("이름을 입력 하세요");
	   $("#name").focus();
	   return false;
   }
	
   if ($.trim($("#year").val()) =="") {
	   alert("태어난 해를 입력하세요");
	   $("#year").focus();
	   return false;
   }
	
   if (isNaN($.trim($("#year").val())) ==true) {
	   alert('태어난 년도는 숫자만 입력 가능합니다.');
	   $("#year").val('').focus();
	   return false;
   }
   
   
   if ($.trim($("#month").val()) =="월") {
	   alert("태어난 월을 입력 하세요");
	   $("#month").focus();
	   return false;
   }
   
   pattern = /^0[1-9]|[12][0-9]|3[01]$/;
   if(!pattern.test($("#date").val())){
	   alert("태어난 날짜는 형식에 맞게 입력하세요(01~31)");
	   $("#date").val('').focus();	
	   return false;
   }
   




	if ($.trim($("#email").val()) =="") {
		   alert("E-mail 주소를 입력하세요");
		   $("#email").focus();
		   return false;
	   }
	

	if ($.trim($("#domain").val()) =="") {
		   alert("E-mail 도메인을 입력하세요");
		   $("#domain").focus();
		   return false;
	   }
	

	

	if ($.trim($("#phone").val()) =="" ) {
		alert("휴대폰번호를 입력하세요");
		$("#phone").focus();
		return false;
	}
	
	if ($("#phone").val().length != 11) {
		alert("휴대폰번호를  다시 입력하세요");
		$("#phone").val('').focus();
		return false;
	}
		

	if(codeck !=1) {
		alert("자동가입방지문자를 확인하세요");		
		$("#output").focus();
		return false;
	}
	
	if($("#id").val() != $("#idck").val()){
		alert("id 중복 확인을 해주세요");
		return false;
	}
			  
  });
	
	
	$('#button').click(function(){
		window.location ="index.jsp";
	})
	
}); 

