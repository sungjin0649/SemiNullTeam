function go(page) {
	var view = $("#view").val(); //최신순부터 값은 1~3
	var data = "view=" + view + "&state=ajax&page=" + page;
	ajax(data);
}


function setPaging(href, digit) {
	var output ="<li class=page-item>";
	gray="";
	if(href=="") {
		gray = " gray";
	}
	anchor = "<a class='page-link" + gray + "'" + href + ">" + digit + "</a></li>";
	output += anchor;
	return output;
}

function ajax(sdata) {
	var cate = $("#category").text(); //분류가 전체부터 값은 null~5
	var add ="";
	if (cate == null) {
		add = "Freeboard.okybo"
	}
	if (cate ==1) {
		add = "FrList1.okybo"
	}
	if (cate ==2) {
		add = "FrList2.okybo"
	}
	if (cate ==3) {
		add = "FrList3.okybo"
	}
	if (cate ==4) {
		add = "FrList4.okybo"
	}
	if (cate ==5) {
		add = "FrList5.okybo"
	}
	console.log(add)
	console.log(sdata)
	output = "";
	$.ajax({
		type : "POST",
		data : sdata,
		url : add,
		dataType : "json",
		cache : false,
		success : function(data) {
			$("#viewcount").val(data.limit);
			$("table").find("font").text("글 개수: " + data.listcount);
			
			if (data.listcount> 0) { //총 갯수가 0보다 큰 경우
				$("tbody").remove();
				var num = data.listcount - (data.page - 1) * data.limit;
				console.log(num)
				var output = "<tbody>";
				$(data.boardlist).each(
						function(index, item) {
						    output += '<tr><td>' + (num--) + '</td>'
						    blank_count = item.fr_re_lev * 2 + 1;
						    blank = '&nbsp;';
						    for (var i =0 ; i < blank_count; i++){
						    	blank += '&nbsp;&nbsp;';
						    }
						    img="";
						    if (item.fr_re_lev > 0) {
						    	img="<img src='image/line.gif'>";
						    }
						    if (item.fr_csfc == 1){
						    	output += '<td><div>잡담</div></td>'						    						    	
						    } else if (item.fr_csfc == 2){
						    	output += '<td><div>이벤트</div></td>'			
						    } else if (item.fr_csfc == 3){
						    	output += '<td><div>궁금해요</div></td>'			
						    } else if (item.fr_csfc == 4){
						    	output += '<td><div>정보</div></td>'			
						    } else if (item.fr_csfc == 5){
						    	output += '<td><div>기사</div></td>'			
						    }
						    output += "<td><div>" + blank + img
						    output += ' <a href="FreeboardDetailAction.okybo?num=' + item.fr_no + '">'
						    output += item.fr_subject.replace(/</g,'&lt;').replace(/>/g,'&gt;')
						              + '</a></div></td>'
						    output += '<td><div>' + item.id + '</div></td>'
						    output += '<td><div>' + item.fr_date + '</div></td>'
						    output += '<td><div>' + item.fr_read + '</div></td>'
						})
				   output += "</tbody>"
				   $('table').append(output) //table 완성
				   
				   $(".pagination").empty(); //페이징 처리 영역 내용 제거
				   output = "";
				   
				   digit = '이전&nbsp;'
				   href="";
				   if (data.page > 1) {
					   href = 'href=javascript:go(' + (data.page - 1) + ')';
				   }
				   output += setPaging(href, digit);
				   
				   for (var i = data.startpage; i <= data.endpage; i ++) {
					   digit = i;
					   href= "";
					   if (i != data.page) {
						   href = 'href=javascript:go(' + i + ')';
					   }
					   output += setPaging( href, digit);
				   }
				   digit = '&nbsp;다음&nbsp;';
				   href="";
				   if (data.page < data.maxpage) {
					   href = 'href=javascript:go(' + (data.page + 1) + ')';
				   }
				   output += setPaging( href, digit);
				   
				   $('.pagination').append(output)				
			}//if(data.listcount) end
			else { //카테고리에 글이 없는 경우
				$("tbody").remove();
				var output = "<tbody>";
				output += "<tr><td colspan=6><div>등록된 글이 없습니다.</div></td></tr>"
				 output += "</tbody>"
					   $('table').append(output)
				$(".pagination").empty();
			}
			
		}, //succes end
		error: function() {
			console.log('에러')
		}
	})// ajax end
}//function ajax end

$(function() {
	$("#view").change(function(){
		go(1); //보여줄 페이지를 1페이지로 설정합니다.
	}); // change end
		
	$("#write").click(function(){
	    	  location.href="FreeboardWrite.okybo";
	   });
});   
