package net.board_bk.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.bk")
public class Bbs_bkFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public Bbs_bkFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	String Request = request.getRemoteHost();
    	System.out.println("접속 URI =" +Request);
    	/*
   		요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
   		예) http://localhost:8088/JspProject/BoardList.bo인 경우 "/JspProject/BoardList.bo"
    	반환됩니다.
    	  */
    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);
    	
    	//getContextPath() = 컨텍스트 경로가 반환됩니다.
    	//contextPath는 "/JspProject"가 반환됩니다.
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);
    	
    	//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
    	//마지막 위치 문자까지 추출합니다.
    	// command는 "/login.net"반환됩니다.
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = "+ command);
    	
    	//초기화
    	ActionForward forward = null;
    	Action action =null;
    	
    	switch (command) {
    	case "/Bbs_bkList.bk" :
    		action = new Bbs_bkListAction();
    		break;
    	case "/Bbs_bkWrite.bk" :
    		action = new Bbs_bkWriteAction();
    		break;
    	case "/Bbs_bkAdd.bk" :
    		action = new Bbs_bkAddAction();
    		break;
    	case "/Bbs_bkDetail.bk" :
    		action = new Bbs_bkDetailAction();
    		break;
    	case "/Bbs_bkReplyView.bk" :
    		action = new Bbs_bkReplyViewAction();
    		break;
    	case "/Bbs_bkReply.bk" :
    		action = new Bbs_bkReplyAction();
    		break;
    	case "/Bbs_bkModifyView.bk" :
    		action = new Bbs_bkModifyView();
    		break;
    	case "/Bbs_bkModify.bk" :
    		action = new Bbs_bkModifyAction();
    		break;
    	case "/Bbs_bkDelete.bk" :
    		action = new Bbs_bkDeleteAction();
    		break;
    	case "/Bbs_bkFileDown.bk" :
    		action = new Bbs_bkFileDown();
    		break;
    	case "/Bbs_bkCommentAdd.bk" :
    		action = new Bbs_bkCommentAdd();
    		break;
    	case "/Bbs_bkCommentList.bk" :
    		action = new Bbs_bkCommentList();
    		break;
    	case "/Bbs_bkCommentDelete.bk" :
    		action = new Bbs_bkCommentDelete();
    		break;
    	case "/CommentUpdate.bk" :
    		action = new Bbs_bkCommentUpdate();
    		break;
    	case "/CommentReply.bk" :
    		action = new Bbs_bkCommentReply();
    		break;
    	}// switch end

    	forward =action.execute(request, response);
    	if(forward != null) {
    		if(forward.isRedirect()) { //리다이렉트 됩니다.
    			response.sendRedirect(forward.getPath());
    		}else {//포워딩됩니다.
    			RequestDispatcher dispatcher =
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}//else
    	}//forward
    	
    }//doProcess
    //doProcess(request,response)메서드를 구현하여 요청이 GET방식이든
    //POST방식으로 전송되어 오든 같은 요청을 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
