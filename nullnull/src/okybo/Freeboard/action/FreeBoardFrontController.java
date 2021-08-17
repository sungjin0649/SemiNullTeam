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
	    	System.out.println("���� URI = " + Request);

	    	
	    	
	        String RequestURI = request.getRequestURI();
	        System.out.println("RequestURI = " + RequestURI);
	        

	        String contextPath = request.getContextPath();
	        System.out.println("contextPath = " + contextPath);
	        

	        String command = RequestURI.substring(contextPath.length());
	        System.out.println("command = " + command);
	        
	        // �ʱ�ȭ
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
	        }// switch end
	        
	        forward = action.execute(request, response); //action ��ü ���� �� ����
	        
	        if (forward !=null) {
	        	if (forward.isRedirect()) { // �����̷�Ʈ �˴ϴ�.
	        		response.sendRedirect(forward.getPath());     		
	        	} else {// ������ �˴ϴ�.
	        		RequestDispatcher dispatcher =
	        				request.getRequestDispatcher(forward.getPath());
	        		dispatcher.forward(request, response);      		
	        	}//else
	        }//if (forward !=null)
	        
	    }//doProcess
	        
	    // doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵�
	    // POST������� ���۵Ǿ� ���� ���� �޼��忡�� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�.
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			doProcess(request, response);
		}

}
