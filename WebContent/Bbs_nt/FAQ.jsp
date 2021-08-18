<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
<script src="js/jquery-3.6.0.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>무비마켓FAQ</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <style>
.navbar .navbar-nav {
  display: inline-block;
  float: none;
}
.navbar .navbar-collapse {
  text-align: center;
}
.navc{
	width:50px;
}
.row{
	display: flex;
	justify-content: center;
}
</style>
  </head>
  <body>
  	<!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
    	<div align="right"><a>로그인</a>&nbsp;&nbsp;&nbsp;<a>회원가입</a></div>
    	    	<div align="center"><img style="text-align: center; width: 100px" src="image/logo.png"></div>
      <div class="container">
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav" >
            <li class="active"><a href="#">영화</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#about">영화예매</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li><a href="#contact">커뮤니티</a></li>
            <li style="width: 50px;">&nbsp;</li>
            <li class="nav-item dropdown">
    			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">고객센터</a>
   			 	<div class="dropdown-menu">
      			<a class="dropdown-item" href="NTList.bo">공지사항</a>
      			<a class="dropdown-item" href="NTFAQ.bo">자주묻는질문</a>
   				</div>
 		   </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
         1. 보려는 영화관이 없어요.
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
      	 무비마켓에서는 일반관 / 2D 영화만 예약이 가능합니다.
		* 이용 불가 각 영화관 특별관
		① CGV - 여의도
		② 메가박스 - 분당, 상암, 센트럴, 일산벨라시타, 코엑스, 하남스타필드, 고양스타필드
		③ 롯데시네마 - 샤롯데, 수퍼플렉스 G, 수퍼S, 수퍼플렉스, 수퍼4D, 씨네패밀리, 씨네커플, 씨네비즈,
		프리미엄구미센트럴, 프리미엄만경, 프리미엄칠곡, 프리미엄진주(중안), 충장로
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
 		 2. 보려는 영화가 없어요.
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
    	① 상영 3시간 이내 영화는 예약이 불가능하므로 북티켓 상영 일정표에 나타나지 않습니다.
		② 상영일이 1주일 이상 남아 있는 경우 상영일 2~3일 전에 상영 일정에 등록되기도 합니다.
		③ 일부 영화관의 상영일정이 변경되는 경우가 있어 북티켓에서는 매일 심야시간에 상영일정표를 업데이트합니다. 
		따라서 오늘은 상영일정표에 나오지 않더라도 다음 날 오전에는 예약이 가능한 경우가 있습니다.
		④ 또한 영화선택 검색창에서 영화제목을 입력하시면 관람 가능한 영화관과 상영일자가 표시됩니다.
		만에 하나 다음 날 오전에도 상영시간이 없다면 관람하실 영화, 상영관, 날짜 및 시간, 요청 좌석 등을 <1:1 문의>에 올려주시면 확인 후 수동으로 예약을 진행해드립니다.
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          3. 예약자 이름이 달라요
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
               무비마켓은 고객님들께서 예약을 하시면 예매대행자가 고객님들을 대신해 실제 예매를 하는 예매대행시스템을 이용하고 있습니다.
		따라서 고객님께 전송된 예매번호에는 예매대행자의 이름이 표시됩니다.
		예매번호는 주로 좌석표을 발권하기 위한 것이므로 예약자 이름은 신경쓰지 않으셔도 됩니다.
		간혹 카운터 직원이 고객님 성함을 묻고 예약자 이름과 다르다고 말하면 신입 / 아르바이트 직원으로
 		예매대행시스템을 모르고 하는 것이기 때문에 예매대행업체인 무비마켓을 통해 예약했다고 말씀해주시거나 그냥 무인발권기에서 좌석표를 출력하시면 됩니다.
      </div>
    </div>
  </div>
    <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          4. 요청한 좌석으로 예약이 안 되었어요.
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
        ① 북티켓은 좌석지정예약제이므로 해당 영화관 홈페이지에서 좌석상황을 확인한 후 원하시는 좌석을 지정해 예약해주시면 그 좌석으로 예매가 진행됩니다.
		② 단, 예매가 진행되는 약 30~40 분 동안 누군가 그 좌석을 선점해 예약이 불가한 경우에는 최대한 근처 좌석으로 예약 해드립니다.
		    꼭 그 좌석만을 원하시는 경우에는 <기타요청사항>에서 (③요청 좌석 없으면 취소해주세요)를 선택하시면 됩니다.
		③ CGV는 원칙적으로 스탠다드 존 좌석만 예약이 가능하므로, 프라임존 좌석을 선택하실 경우 예매가 지연되거나 안 되는 경우도 있습니다.
		④ 혹시라도 요청하신 좌석으로부터 너무 멀리 떨어져 있어 관람을 원치 않으실 경우 상영 2시간 전까지 예약 취소수수료를 카드결제하시고 예약을 취소해주시면 다음 날 취소수수료 카드결제를 취소해드립니다.
		⑤ 특별한 사정이 없는 한 고객서비스 차원에서 앞 A, B, C열은 예약을 진행하지 않습니다.
      </div>
    </div>
  </div>
      <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
           5. 예매가 잘못 되었어요. 어떻게 하면 될까요?
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
      	 예매대행을 사람이 하는 일이다보니 약 2,000건 중 한 건 정도 예매오류가 일어나고 있습니다.
		따라서 예매번호를 전송 받으신 후 예매 내용이 고객님께서 예약하신 내용과 일치하는 지 사전에 꼭 확인하여 주시기 바랍니다.
		만일 예매 오류로 인해 예매 내용과 예약 내용이 다른 경우
		① 상영 2시간 전이라면 번거로우시겠지만 예약 취소수수료를 카드결제하시어 기존예약을 취소하고 다시 예약해주신 후 <1:1문의>에 올려주시면 다음 날 예약 취소 수수료 카드결제를 취소해드립니다.
		② 상영 2시간 이내라면 일단 정상가를 지불하고 영화를 관람하신 후 정상가 지불영수증을 <1:1문의>에 올려주시면 다음 날 정상가 전액을 보상해드립니다.
      </div>
    </div>
  </div>
</div>
  
    
  <footer style="text-align: center; background: lightgray; height: 50px; padding-top:15px; margin-top: 30px;" class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>

  </body>
</html>