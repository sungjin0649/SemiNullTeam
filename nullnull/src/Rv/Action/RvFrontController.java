package Rv.Action;

//서블릿

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Rvcomment.Action.RvCommentReply;
import Rvcomment.Action.RvCommentAdd;
import Rvcomment.Action.RvCommentDelete;
import Rvcomment.Action.RvCommentList;
import Rvcomment.Action.RvCommentUpdate;





@WebServlet("*.rv")
public class RvFrontController extends javax.servlet.http.HttpServlet{
	
	private static final long serialVersionUID = 1L ; 
	
	protected void doProcess(HttpServletRequest request , HttpServletResponse response)
			throws ServletException, IOException{

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		// getContextPath() : 컨텍스트 경로가 반환됩니다.
		// contextPath는 "/JspProject"가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 
		//마지막 위치 문자까지 추출합니다.
		//command는 "/login.net" 반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		//초기화
		ActionForward forward = null;
		Action action = null;
		
		switch (command) {
		
		case "/RvList.rv":
			action = new RvListAction();
			break;
		case "/RvWrite.rv":
			action = new RvWriteAction();
			break;
		case "/RvAddAction.rv":
			action = new RvAddAction();
			break;	
		case "/RvDetailAction.rv":
			action = new RvDetailAction();
			break;
		case "/RvReplyView.rv" :			//답변 글쓰기 페이지
			action = new RvReplyView();
			break;
		case "/RvReplyAction.rv" :		//답변 등록
			action = new RvReplyAction();	
			break;
		case "/RvModifyView.rv":			//글 수정
			action = new RvModifyView();
			break;
		case "/RvModifyAction.rv":		//글 수정 창에서 수정
			action = new RvModifyAction();
			break;
		case "/RvDeleteAction.rv":		//글 삭제
			action = new RvDeleteAction();
			break;
		case "/RvFileDown.rv":			//파일 다운 
			action = new RvFileDownAction();
			break;
		case "/RvCommentAdd.rv":			
			action = new RvCommentAdd();
			break;
		case "/RvCommentList.rv":			
			action = new RvCommentList();
			break;
		case "/RvCommentUpdate.rv":			
			action = new RvCommentUpdate();
			break;
		case "/RvCommentReply.rv":			 
			action = new RvCommentReply();
			break;	
		case "/RvCommentDelete.rv":			
			action = new RvCommentDelete();
			break;
		}//switch end
		
		forward = action.execute(request, response);
		
		if(forward !=null) {
			if(forward.isRedirect()) {	// 리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());	
			}else { //포워딩됩니다.
				RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}//else
		}//forward
	}//doProcess
	
	
	
	
	//doProcess(request,response)메서드를 구현하여 요청이 GET방식이든
	//POST 방식으로 전송되어 오든 같은 메서드에서 요청을 처리할수 있또록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException , IOException {
		doProcess(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException , IOException {
			request.setCharacterEncoding("utf-8");
			doProcess(request,response);
		}
	
	

}
