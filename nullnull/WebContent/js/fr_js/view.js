function getList(state){
	    option=state;//현재 선택한 댓글 정렬방식을 저장합니다. 1=>등록순, 2=>최신순

	    
	    
	    
	    
	    
	    
		$.ajax({
			type:"post",
			url:"FrcommentList.okybo",
			data : {"comment_board_num" : $("#comment_board_num").val() , 
				    state:state},
			dataType:"json",
			success:function(rdata){
				//댓글의 총 갯수가 표시 됩니다.
				$('#count').text(rdata.listcount).css('font-family','arial,sans-serif')
				var red1='red';		var red2='red';
				if(option==1){  //등록순인 경우 등록순이 'red', 최신순이 'gray'로 글자색을 나타냅니다.
					red2='gray';
				}else if(option==2){ //최신순인 경우 등록순이 'gray', 최신순이 'red'로 글자색을 나타냅니다.
					red1='gray';
				}
				var output="";
				
			 if(rdata.boardlist.length>0){
				   output += '<li class="comment_tab_item ' +  red1 + '" >'
                          + '   <a href="javascript:getList(1)" class="comment_tab_button">등록순 </a>'
                          + '</li>'
                          + '<li class="comment_tab_item ' +  red2 + '" >'
                          + '   <a href="javascript:getList(2)" class="comment_tab_button">최신순</a>'
                          + '</li>';
                     $('.comment_tab_list').html(output);//댓글 수 등록순 최신순 출력
                     
				    output='';
					$(rdata.boardlist).each(function(){
						var lev = this.fcm_comment_re_lev;
						var comment_reply='';
						//레벨에 따라 왼쪽 여백줍니다.
						if(lev==1){
							comment_reply = ' CommentItem--reply lev1';//margin-left: 46px;
						}else if(lev==2){
							comment_reply = ' CommentItem--reply lev2';//margin-left: 92px;
						}
						
						//선택한 파일이 나타날지 기본 그림이 나타날지 결정합니다.
						var profile=this.fcm_memberfile;
						var src='image/profile.png';
						if(profile){
							src='memberupload/'+profile;
						}
						
						output += '<li id="' + this.fcm_no + '" class="CommentItem' + comment_reply + '">'
							   + '   <div class="comment_area">'
							   + '    <img  src="' + src +'" alt="프로필 사진" width="36" height="36">'
							   + '    <div class="comment_box">'
							   + '      <div class="comment_nick_box">'
							   + '            <div class="comment_nick_info">'
							   + '               <div class="comment_nickname">' + this.fcm_id  + '</div>'
							   + '            </div>' //comment_nick_info                  
							   + '       </div>'  // comment_nick_box
							   + '     </div>'   //comment_box
							   + '    <div class="comment_text_box">'
							   + '       <p class="comment_text_view">'
							   + '         <span class="text_comment">' + this.fcm_content + '</span>'
							   + '       </p>'
							   + '    </div>' //comment_text_box
							   + '    <div class="comment_info_box">'
							   + '      <span class="comment_info_date">' + this.fcm_reg_date + '</span>';
						if(lev<2){ //답글쓰기는 답글의 답글까지만 사용하도록 합니다.
						   	  output += '  <a href="javascript:replyform(' + this.fcm_no +',' 
						   	         + lev + ',' + this.fcm_comment_re_seq +',' 
						   	         + this.fcm_comment_re_ref +')"  class="comment_info_button">답글쓰기</a>'
						      }
						output += '   </div>' //comment_info_box;
							   
						//글쓴이가 로그인한 경우 나타나는 더보기입니다.
                        //수정과 삭제 기능이 있습니다.							
					    if($("#loginid").val()==this.fcm_id){  
						 output +=  '<div class="comment_tool">'
							   + '    <div title="더보기" class="comment_tool_button">'
							   + '       <div>&#46;&#46;&#46;</div>' 
							   + '    </div>'
							   + '    <div id="commentItem' +  this.fcm_no + '"  class="LayerMore">' //스타일에서 display:none; 설정함
							   + '     <ul class="layer_list">'							   
							   + '      <li class="layer_item">'
							   + '       <a href="javascript:updateForm(' + this.fcm_no + ')"'
							   + '          class="layer_button">수정</a>&nbsp;&nbsp;'
							   + '       <a href="javascript:del(' + this.fcm_no + ')"'
							   + '          class="layer_button">삭제</a></li></ul>'
							   + '     </div>'//LayerMore
							   + '   </div>'//comment_tool
					     }
							   
						output += '</div>'// comment_area
							   + '</li>'//li
					})//each
					 $('.comment_list').html(output);
					$('.comment_inbox_text').val('');//textarea 초기화
					$('.comment_inbox_count').text('');//입력한 글 카운트 초기화
			 }//if(rdata.boardlist.length>0)
			 else{ //댓글 1개가 있었는데 삭제하는 경우 갯수는 0이라  if문을 수행하지 않고 이곳으로 옵니다.
				   //이곳에서 아래의 두 영역을 없앱니다.
				 $('.comment_list').empty();  
				 $('.comment_tab_list').empty(); 
			 }
			}//success end
		});//ajax end
	}//function(getList) end









//더보기 클릭 후 -> 수정 클릭한 경우에 수정 폼을 보여줍니다.
function updateForm(num) { //num : 수정할 댓글 글번호
	
	//선택한 내용을 구합니다.
	var content=$("#"+num).find('.text_comment').text();
	
	var selector = '#' + num + ' .comment_area'
	$(selector).hide(); // selector 영역 숨겨요 - 수정에서 취소를 선택하면 보여줄 예정입니다.
	
	//$('comment_list+.CommentWriter').clone() : 기본 글쓰기 영역 복사합니다.
	//글이 있던 영역에 글을 수정할 수 있는 폼으로 바꿉니다.
	selector= $('#' + num);
	selector.append($('.comment_list + .CommentWriter').clone());
	
	//댓글쓰기 영역 숨깁니다.
	$('.comment_list + .CommentWriter').hide();
	
	//수정 폼의 <textarea>에 내용을 나타냅니다.
	selector.find('textarea').val(content);


	//'.btn_register'  영역에 수정할 글 번호를 속성 'data-id'에 나타내고 클래스 'update'를 추가하며 등록을 수정완료로
	selector.find('.btn_register').attr('data-id', num).addClass('update').text('수정완료');
	
	//폼에서 취소를 사용할 수 있도록 보이게 합니다.
	selector.find('.btn_cancel').css('display', 'block').addClass('update_cancel');
	
	selector.find('.comment_inbox_count').text(content.length+"/200");
}//function(updateForm) end










//더보기 -> 삭제 클릭한 경우 실행하는 함수
function del(num) {//num : 댓글 번호
	if(!confirm('정말 삭제하시겠습니까')){
		return;
	}
	
	   $.ajax({
		   url:'FrcommentDelete.okybo',
		   data: {num:num},
		   success:function(rdata){
			   if(rdata==1){
				   getList(option);
			   }
		   }
	   })
}//function(del) end









//답글 달기 폼
function replyform(num,lev,seq,ref) {
	//댓글달기 폼이 열려있다는 것은 다른 폼이 열려있지 않은 경우입니다.
	if($('.comment_list + .CommentWriter').css('display')=='block'){
		var output = '<li class="CommentItem Comment--reply lev'
			         + lev + ' CommentItem-form"></li>'
		var selector = $('#' + num);
		
		//선택한 글 뒤에 답글 폼을 추가합니다.
		selector.after(output);
		
		//글쓰기 영역 복사합니다.
		output = $('.comment_list + .CommentWriter').clone();
		
		//댓글쓰기 영역 숨깁니다.
		$('.comment_list + .CommentWriter').hide();
		
		
		//더보기를 누른 상태에서 답글 달기 폼을 연 경우 더보기의 영역 보이지 않게 합니다.
		$(".CommentBox .LayerMore").css('display','none');
		
		//선택한 글 뒤에 답글 폼 생성합니다.
		selector.next().html(output);
		
		//답글 폼의 <textarea>의 속성 'placehorder'를 '답글을 남겨보세요'로 바꾸어줍니다.
		selector.next().find('textarea').attr('placeholder','답글을 남겨보세요');
		
		//답글 폼의 '.btn_cancel'을 보여주고 클래스 'reply_cancel'를 추가합니다.
		selector.next().find('.btn_cancel').css('display', 'block')
		                 .addClass('reply_cancel');
		
		//답글 폼의 '.btn_register'에 클래스 'reply'추가합니다.
		//속성 'data-ref'에 ref, 'data-lev'에 lev, 'data-seq'에 seq 값을 설정합니다.
		selector.next().find('.btn_register').addClass('reply').text('답글완료')
		         .attr('data-ref', ref).attr('data-lev', lev).attr('data-seq', seq);
	}else {
		alert('다른 작업 완료 후 선택하세요');
	}
}//function (replyform) end








$(document).ready(function() {
	option=1;
	getList(option); //처음 로드 될때는 등록순 정렬
	
//   $("form").submit(function (){
//	   if ($("#board_pass").val() =='') {
//		   alert("비밀번호를 입력하세요");
//		   $("#board_pass").focus();
//		   return false;
//			  }
//	 })//form
	 
	//댓글쓰기 영역의 입력 글자 수를 표시합니다.
	$('.CommentBox').on('keyup', '.comment_inbox_text', function() {
		console.log("글자수")
		var length=$(this).val().length;
		$(this).prev().text(length+'/200');
	});//'keyup', '.comment_inbox_text', function() {
	
	//등록을 클릭하면 데이터베이스에 저장 -> 저장 성공 후에 리스트 불러옵니다.
	$('ul+.CommentWriter .btn_register').click(function(){
		var content=$('.comment_inbox_text').val();
		if(!content){//내용없이 등록 클릭한 경우
			alert("댓글을 입력하세요");
			return;
		}
		
		$.ajax({
			url : 'FrcommentAdd.okybo', //원문 등록
			data : {
				id :$("#loginid").val(),
				content : content,
				comment_board_num : $("#comment_board_num").val(),
				comment_re_lev : 0, //원문일 경우 comment_re_seq는 0,
				                    //comment_re_ref는 댓글의 원문 글번호
				comment_re_seq: 0
			},
			type : 'post',
			success : function(rdata) { // rdata = response.getWriter().print(ok); 
				if (rdata == 1) {
					getList(option);
				}
			}
		})//ajax
		
		$('.comment_inbox_text').val('');//textarea 초기화
		$('.comment_inbox_count').text(''); //입력한 글 카운트 초기화
	})//.btn_register').click(function(){
	
	//더보기를 클릭하면 수정과 삭제 영역이 나타나고 다시 클릭하면 사라져요-toggle() 이용
	$(".comment_list").on('click', '.comment_tool_button', function() {
		var selector = $(this).next();
		
		//댓글쓰기 폼이 나타난 경우에만 더보기를 선택할 수 있도록 합니다.
		if($('.comment_list + .CommentWriter').css('display')=='block'){
			selector.toggle();
			
			  //더보기를 여러개 선택하더라도 최종 선택한 더보기 한개만 보이도록 합니다.
			  if(selector.css('display')=='block'){ //현재 더보기가 열린경우
				  //$(".LayerMore") 중에서 selector가 아닌 객체들의 display속성을 none으로 설정합니다.
				  $(".LayerMore").not(selector).css('display','none');
			  }
		   }else {
			   //답글쓰기 폼이나 수정 폼이 열려 있는 상황에서 더보기를 클릭한 경우
			   alert('작업 완료 후 선택해 주세요')
		   }
		
	});//'.comment_tool_button' click end
	
	//더보기를 선택한 후 수정이나 삭제를 클릭한 경우 수정과 삭제 영역이 사라지게 합니다.
	$(".comment_list").on('click', '.LayerMore', function(){
		$(this).hide();
	});//'.LayerMore' click end
	
	//수정 후 수정완료 클릭한 경우
	$('.CommentBox').on('click','.update',function(){
		console.log("수정완료")
		
		//댓글쓰기 영역 보이도록 합니다.
		$('.comment_list + .CommentWriter').show();
		
		var content = $(this).parent().parent().find('textarea').val();
		if(!content){
			alert('수정할 내용을 입력하세요');
			return
		}
		$.ajax({
			url:'FrcommentUpdate.okybo',
			data: {num:$(this).attr('data-id'), content:content},
			success:function(rdata){
				if(rdata==1) {
					getList(option);
				}//if
			}//success
		});//ajax		
	})//수정 후 등록 버튼을 클릭한 경우
	
	//수정 후 취소 클릭한 경우
	$('.CommentBox').on('click','.update_cancel', function(){
		//댓글 번호를 구합니다.
		var num = $(this).next().attr('data-id');
		var selector='#' + num;
		
		//selector의 후손 중 .CommentWriter 영역 삭제 합니다.
		$(selector + ' .CommentWriter').remove();
		
		//댓글쓰기 영역 보이도록 합니다.
		$('.comment_list + .CommentWriter').show();
		
		//숨겨두었던 .comment_area 영역 보여줍니다.
		$(selector + '>.comment_area').css('display','block');		
	});//수정 후 취소 클릭한 경우
	
	
	//답글 달기 -> 답글완료 클릭한 경우
	$('.CommentBox').on('click','.reply', function(){
		
		var content=$(this).parent().parent().find('.comment_inbox_text').val();
		if(!content){
			alert('답변 내용을 입력하세요');
			return
		}
		
		//댓글쓰기 영역 보이도록 합니다.
		$('.comment_list + .CommentWriter').show();
		
		$.ajax({
			url : 'FrcommentReply.okybo',
			data : {
				id : $("#loginid").val(),
				content : content,
				comment_board_num : $("#comment_board_num").val(),
				comment_re_lev : $(this).attr('data-lev'),
				comment_re_ref : $(this).attr('data-ref'),
				comment_re_seq : $(this).attr('data-seq'),
			},
			type : 'post',
			success : function(rdata) {
				if (rdata == 1) {
					getList(option);
				}
			}
		})//ajax
	});//답변 달기 등록 버튼을 클릭한 경우
	
	
	
	
	//답변달기 후 취소 버튼을 클릭한 경우
	$('.CommentBox').on('click','.reply_cancel',function(){
		$(this).parent().parent().parent().remove();
		
		//댓글쓰기 영역 보이도록 합니다.
		$('.comment_list+.CommentWriter').show();
	})//수정 후 취소 버튼을 클릭한 경우

	
});