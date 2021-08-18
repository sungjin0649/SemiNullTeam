
function movieSelect(data, src){
	var name = $('#'+data).text();
	changename(name);
	$("#view").attr("src",src);
}

function changename(data){
	$('#moviename').text(data);
}

function changeplace(data){
	$('#movieplace').text(data);
}

function DateSelect(data){//onclick 부분 id값 받아옴
    var only = today.getFullYear()+""+(today.getMonth()+1)+"" //년도와 월
	var num = $('#'+data).text();	//선택한 id의 값가져오기 (일)
    if(num<10){
    	num = "0"+num;
    }
    var nonly =parseInt(only+num);	//숫자변환  => 년도 월 일 (선택한 년도)
    
    var d = new Date();
    var year	= d.getFullYear(); //연도
    var month	= d.getMonth()+1;	//월
    var date	= d.getDate();
    if(date<10){
    	date = "0"+date;
    }
    var dstring = year+""+month+""+date;
    var nnum =parseInt(dstring); //오늘 날짜 숫자로
    if(nonly>=nnum){
    	changedate(num);
    	$('.times').show();
    	$('.cal').css('background','');
    	$('#'+data).css('background','red');
    }
	
}
function changedate(data){
	YYNN = today.getFullYear() + "-"+ (today.getMonth() + 1) + "-";
	$('#moviedate').text(YYNN + data);	

}


function TimeSelect(data){
	name = $('#'+data).text().substring(2);
	$('#movietime').text(name);
}

$(function() {
	$('.times').hide();
	
	$('.button1').click(function(){
		var data = $(this).text();
		$('#moviepeoples').text(data+"명");
		$('.button1').css('background','');
		$(this).css('background','red');
	})
	$('.mplace').click(function(){
		var data = $(this).text();
		$('#movieplace').text(data);
	})

	$('#buttonsub').click(function(){
		$('#img').attr("value", $("#view").attr("src"));
		$('#name').attr("value", $("#moviename").text());
		$('#place').attr("value", $("#movieplace").text());
		$('#date').attr("value", $("#moviedate").text());
		$('#time').attr("value", $("#movietime").text());
		$('#count').attr("value", $("#moviepeoples").text());
		var formbtn = document.movieinfo
		
		if($.trim($("input").eq(1).val()) == ""){
			alert("영화를 선택하세요");
			return false;
		}
		if($.trim($("input").eq(2).val()) == ""){
			alert("극장을 선택하세요");
			return false;
		}
		if($.trim($("input").eq(3).val()) == ""){
			alert("날짜를 선택하세요");
			return false;
		}
		if($.trim($("input").eq(4).val()) == ""){
			alert("시간대를 선택하세요");
			return false;
		}
		if($.trim($("input").eq(5).val()) == ""){
			alert("사람수를 선택하세요");
			return false;
		}
		formbtn.submit();
	})

})


