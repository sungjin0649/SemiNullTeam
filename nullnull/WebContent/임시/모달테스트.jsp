<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

출처 :https://developer-joe.tistory.com/201

<html>

<head>

<meta charset="EUC-KR">

<title>Insert title here</title>

 

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>



<!-- 부트스트랩의 모달 창을 사용할려면 아래의 class 이름들을 그대로 사용해야 한다. 변경하면 모양이 달라진다.-->

  <!-- Modal -->

  <div class="modal fade" id="myModal" role="dialog"> <!-- 사용자 지정 부분① : id명 -->

    <div class="modal-dialog">

    

      <!-- Modal content-->

      <div class="modal-content">

        <div class="modal-header">

          <button type="button" class="close" data-dismiss="modal">×</button>

          <h4 class="modal-title">모달 창 타이틀</h4> <!-- 사용자 지정 부분② : 타이틀 -->

        </div>

        <div class="modal-body">

          <p>여기에 필요한 텍스트 메시지 넣기</p> <!-- 사용자 지정 부분③ : 텍스트 메시지 -->

        </div>

        <div class="modal-footer">

          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

        </div>

      </div>

    </div>

  </div>



<br/><br/>

<!-- 아래에서 data-toggle과 data-target 속성에서 data-toggle에는 modal 값을 data-target속성에는 모달 창 전체를 

감싸는 div의 id 이름을 지정하면 된다. -->


&nbsp;&nbsp;<a data-toggle="modal" href="#myModal">모달 창 열기</a>



</body>

</html>



출처: https://developer-joe.tistory.com/201 [코드 조각-Android, Java, Spring, JavaScript, C#, C, C++, PHP, HTML, CSS, Delphi]