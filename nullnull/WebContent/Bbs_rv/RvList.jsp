<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<html>
<jsp:include page="/Main/header.jsp"/>
<head>


<style>
   select.form-control{
         width:auto;margin-bottom:2em;display:inline-block}
   .rows{text-align:right;}
   .gray{color:gray}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(1){width:8%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(2){width:50%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(3){width:14%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(4){width:17%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(5){width:11%}
 </style>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
 <script src="js/list.js"></script>
 <script>
 function go(page) {
		var limit = $('#viewcount').val();
		var data = "limit=" + limit + "&state=ajax&page=" + page;
		ajax(data);
	}

	function setPaging(href,digit){
		var output = "<li class=page-item>";
		gray ="";
		if(href==""){
			gray=" gray";
		}
		anchor = "<a class='page-link" + gray + "'" + href + ">" +digit + "</a></li>";
		output += anchor ;
		return output;
	}

	function ajax(sdata){
		console.log(sdata);
		output ="";
		$.ajax({
			type: "POST",
			data: sdata,
			url : "RvList.rv",
			dataType : "json" ,
			cache : false ,
			success : function(data){
				$("#viewcount").val(data.limit);
				$("table").find("font").text("글 개수 : " + data.listcount);
				
				if (data.listcount > 0){	// 총 갯수가 0보다 큰 경우
					$("tbody").remove();
					var num = data.listcount - (data.page-1) * data.limit;
					console.log(num);
					var output = "<tbody>"
					$(data.boardlist).each(
							function(index,item){
								output += '<tr><td>' + (num--) + '</td>'
								blank_count = item.RV_RE_LEV * 2 + 1;
								blank = '&nbsp;';
								for (var i =0;i<blank_count; i++){
									blank+= '&nbsp;&nbsp;';
								}
								img ="";
								if (item.RV_RE_LEV >0){
									img ="<img src='image/line.gif'>";
								}
								
								output += "<td><div>" + blank + img
								output += ' <a href="RvDetailAction.rv?num=' + item.RV_NO + '">'
								output += item.RV_SUBJECT.replace(/</g,'&lt;').replace(/>/g,'&gt;')
										+ '</a></div></td>'
								output += '<td><div>' + item.USER_ID + '</div></td>'
								output += '<td><div>' + item.RV_DATE + '</div></td>'
								output += '<td><div>' + item.RV_READ
										+ '</div></td></tr>'
												
							})
							output += "</tbody>"
							$('table').append(output)//table 완성
							
							$(".pagination").empty();//페이징 처리 영역 내용 제거
							output ="";
							
							digit ='이전&nbsp;'
							href="";
							if (data.page > 1){
								href='href=javascript:go(' + (data.page -1) + ')';
							}
							output += setPaging(href,digit);
							
							for(var i = data.startpage; i<= data.endpage;i++){
								digit = i;
								href ="";
								if(i != data.page){
									href ='href =javascript:go('+ i +')';
								}
								output += setPaging(href , digit);
							}
							
							digit ='&nbsp;다음&nbsp;';
							href="";
							if(data.page < data.maxpage){
								href = 'href=javascript:go(' + (data.page +1) + ')';
							}
							output += setPaging( href, digit);
								
							$('.pagination').append(output)
							}// if(data.listcount) end
				
			}, //success end
			error : function(){
				console.log('에러')
			}
		})// ajax end
	} //function ajax end
	
 $(function(){
		$("#viewcount").change(function(){
			go(1);//보여줄 페이지를 1페이지로 설정합니다.
		});// change end
		$("button").click(function(){
			location.href="RvWrite.rv";
		})
	})
	</script>
 
 <title>MVC 게시판</title>
</head>
<body>
<div class="container">
  
<%-- 게시글이 있는 경우--%> 
<c:if test="${listcount > 0 }">
 <div class="rows">
  <span>줄보기</span>
  <select class="form-control" id ="viewcount">
  	<option value="1">1</option>
	<option value="3">3</option>
	<option value="5">5</option>
	<option value="7">7</option>
	<option value="10" selected>10</option>  
  </select>
 </div>
  <table class="table table-striped">
   <thead>
	<tr>
	   <th colspan="3">MVC 게시판 - list</th>
	   <th colspan="2">
			<font size=3>글 개수 : ${listcount}</font>
	   </th>
	</tr>
	<tr>
		<th><div>번호</div></th>
		<th><div>제목</div></th>
		<th><div>작성자</div></th>
		<th><div>날짜</div></th>
		<th><div>조회수</div></th>
	</tr>	
   </thead>
   <tbody>
	<c:set var="num" value="${listcount-(page-1)*limit}"/>	
	<c:forEach var="b" items="${boardlist}">	
	<tr>
	  <td><%--번호 --%>
		<c:out value="${num}"/><%-- num 출력 --%>		
		<c:set var="num" value="${num-1}"/>	<%-- num=num-1; 의미--%>	
	  </td>
	  <td><%--제목 --%>
	     <div>			
			<%-- 답변글 제목앞에 여백 처리 부분 
			      board_re_lev,  board_num, 
			      board_subject, board_name, board_date, 
			      board_readcount : property 이름 --%>
		    <c:if test="${b.RV_RE_LEV != 0}">  <!--  답글인 경우 -->
				<c:forEach var="a" begin="0" end="${b.RV_RE_LEV*2}" step="1">
				&nbsp; 	
				</c:forEach>		
				<img src='image/line.gif'>
			</c:if>  
			 
			<c:if test="${b.RV_RE_LEV == 0}">  <%-- 원문인 경우 --%>
				&nbsp;  
			</c:if> 			
			
			<a href="RvDetailAction.rv?num=${b.RV_NO}">
				 <c:out value="${b.RV_SUBJECT}" />  
				<%-- ${b.board_subject} --%>
				<%-- escapeXml="true" : HTML 태그를 화면에 그대로 보여줍니다. --%>	
			</a>
		  </div>
		</td>
		<td><div>${b.USER_ID}</div></td>
		<td><div>${b.RV_DATE}</div></td>	
		<td><div>${b.RV_READ}</div></td>
	   </tr>
	  </c:forEach>
	 </tbody>	
	</table>
		
	<div class="center-block">
		  <ul class="pagination justify-content-center">		
			 <c:if test="${page <= 1 }">
				<li class="page-item">
				  <a class="page-link gray">이전&nbsp;</a>
				</li>
			 </c:if>
			 <c:if test="${page > 1 }">			
				<li class="page-item">
				   <a href="RvList.rv?page=${page-1}" 
				      class="page-link">이전&nbsp;</a>
				</li> 
			 </c:if>
					
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					<li class="page-item " >
					   <a class="page-link gray">${a}</a>
					</li>
				</c:if>
				<c:if test="${a != page }">
				    <li class="page-item">
					   <a href="RvList.rv?page=${a}" 
					      class="page-link">${a}</a>
				    </li>	
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
				<li class="page-item">
				   <a class="page-link gray">&nbsp;다음</a> 
				</li>
			</c:if>
			<c:if test="${page < maxpage }">
			  <li class="page-item">
				<a href="RvList.rv?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>
			  </li>	
			</c:if>
		 </ul>
		</div>
     </c:if><%-- <c:if test="${listcount > 0 }"> end --%>
	
<%-- 게시글이 없는 경우--%>
<c:if test="${listcount == 0 }">
	<font size=5>등록된 글이 없습니다.</font>
</c:if>

<button type="button" class="btn btn-info float-right" >글 쓰 기</button>
</div>
</body>
</html>