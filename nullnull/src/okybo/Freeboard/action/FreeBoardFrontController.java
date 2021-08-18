package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.frcomment.action.FrCommentAdd;
import oky.frcomment.action.FrCommentDelete;
import oky.frcomment.action.FrCommentList;
import oky.frcomment.action.FrCommentReply;
import oky.frcomment.action.FrCommentUpdate;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;
import okybo.Freeboard.action.FreeBoardListAction;



@WebServlet("*.okybo")
public class FreeBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	    	
	    	String Request = request.getRemoteHost();
	    	System.out.println("접속 URI = " + Request);

	    	
	    	
	        String RequestURI = request.getRequestURI();
	        System.out.println("RequestURI = " + RequestURI);
	        

	        String contextPath = request.getContextPath();
	        System.out.println("contextPath = " + contextPath);
	        

	        String command = RequestURI.substring(contextPath.length());
	        System.out.println("command = " + command);
	        
	        // 초기화
	        ActionForward forward = null;
	        Action action = null;
	        
	        switch (command) {
	        case "/Freeboard.okybo":
	        	action = new FreeBoardListAction();
	        	break;	
	        case "/FreeboardWrite.okybo":
	        	action = new FreeBoardWriteAction();
	        	break;	  	 
	        case "/FreeboardAddAction.okybo":
	        	action = new FreeBoardAddAction();
	        	break;
	        case "/FreeboardDetailAction.okybo":
	        	action = new FreeBoardDetailAction();
	        	break;
	        case "/FreeboardReplyView.okybo":
	        	action = new FreeBoardReplyView();
	        	break;
	        case "/FreeboardReplyAction.okybo":
	        	action = new FreeBoardReplyAction();
	        	break;	        		        	
	        case "/FreeboardModifyView.okybo":
	        	action = new FreeBoardModifyView();
	        	break;
	        case "/FreeboardModifyAction.okybo":
	        	action = new FreeBoardModifyAction();
	        	break;
	        case "/FreeboardDeleteAction.okybo":
	        	action = new FreeBoardDeleteAction();
	        	break;
	        case "/FreeboardFileDown.okybo":
	        	action = new FreeBoardFileDownAction();
	        	break;
	        case "/FrcommentAdd.okybo":
	        	action = new FrCommentAdd();
	        	break;
	        case "/FrcommentList.okybo":
	        	action = new FrCommentList();
	        	break;	
	        case "/FrcommentUpdate.okybo":
	        	action = new FrCommentUpdate();
	        	break;
	        case "/FrcommentReply.okybo":
	        	action = new FrCommentReply();
	        	break;
	        case "/FrcommentDelete.okybo":
	        	action = new FrCommentDelete();
	        	break;
	        case "/FrList1.okybo":
	        	action = new FrList1();
	        	break;
	        case "/FrList2.okybo":
	        	action = new FrList2();
	        	break;
	        case "/FrList3.okybo":
	        	action = new FrList3();
	        	break;
	        case "/FrList4.okybo":
	        	action = new FrList4();
	        	break;
	        case "/FrList5.okybo":
	        	action = new FrList5();
	        	break;	        	
//	        case "/FrListEnr.okybo":
//	            action = new FrListActionEnr();
//	            break;
//	         case "/FrListRea.okybo":
//	            action = new FrListActionRea();
//	            break;
	        }// switch end
	        
	        forward = action.execute(request, response); //action 객체 생성 후 실행
	        
	        if (forward !=null) {
	        	if (forward.isRedirect()) { // 리다이렉트 됩니다.
	        		response.sendRedirect(forward.getPath());     		
	        	} else {// 포워딩 됩니다.
	        		RequestDispatcher dispatcher =
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
