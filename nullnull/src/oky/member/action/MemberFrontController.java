package oky.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;


@WebServlet("*.oky")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	     throws ServletException, IOException{
	/*
	 * 요청된 전체 URL 중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
	 예) http://http://localhost:8088/nullnull/join.com인 경우
	   "/nullnull/join.com" 반환답니다.
	 */	
	String RequestURI = request.getRequestURI();
    System.out.println("RequestURI = " + RequestURI);
    
    // getContextPath() : 컨텍스트 경로가 반환됩니다.
    // contextPath는 "/nullnull"가 반환됩니다.
    String contextPath = request.getContextPath();
    System.out.println("contextPath = " + contextPath);
    
    // RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
    // 마지막 위치 문자까지 추출합니다.
    // command는 "/join.oky" 반환됩니다.
    String command = RequestURI.substring(contextPath.length());
    System.out.println("command = " + command);
    
    // 초기화
    ActionForward forward = null;
    Action action = null;
    
    switch(command) {
    case "/join.oky":
    	action = new MemberJoinAction();
    	break;
    case "/find.oky":
    	action = new MemberFindAction();
    	break;   
    case "/joinProcess.oky":
    	action = new MemberJoinProcessAction();
    	break;
    case "/loginProcess.oky": //접속을 환영합니다 까지 처리
    	action = new MemberLoginProcessAction();
    	break;
    case "/findProcess.oky":
    	action = new MemberFindProcessAction();
    	break;
    case "/idCheck.oky":
    	action = new MemberIdCheckAction();
    	break;
    case "/logout.oky":
    	action = new MemberLogoutAction();
    	break;	
    case "/memberUpdate.oky":
    	action = new MemberUpdateAction();
    	break;    	
    case "/updateProcess.oky":
    	action = new MemberUpdateProcessAction();
    	break;    	
    case "/memberList.oky":
    	action = new MemberSearchAction();
    	break;
    case "/memberDelete.oky":
    	action = new MemberDeleteAction();
    	break;
    case "/memberInfo.oky":
    	action = new MemberInfoAction();
    	break;
    }//switch end
    
    forward = action.execute(request, response); //action 객체 생성 후 실행
    
    System.out.println(forward);
    if (forward != null) { // 리다이렉트 됩니다. (insert의 개념 시스템에 변화가 생기는 요청 ex.로그인, 회원가입, 글쓰기)
    	if (forward.isRedirect()) {
    		response.sendRedirect(forward.getPath());   		
    	} else {// 포워딩 됩니다. (select의 개념 시스템에 변화가 생기지 않는 단순조회(리스트보기,검색)
    		RequestDispatcher dispatcher=
    				request.getRequestDispatcher(forward.getPath());
    		dispatcher.forward(request, response);
    	}//else
    }//if (forward !=null)
    
	}//doProcess
	
    // doProcess(request,response)메서드를 구현하여 요청이 GET방식이든
    // POST방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
