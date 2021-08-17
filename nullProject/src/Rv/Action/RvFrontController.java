package Rv.Action;

//����

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
		
		// getContextPath() : ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		// contextPath�� "/JspProject"�� ��ȯ�˴ϴ�.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں��� 
		//������ ��ġ ���ڱ��� �����մϴ�.
		//command�� "/login.net" ��ȯ�˴ϴ�.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		//�ʱ�ȭ
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
		case "/RvReplyView.rv" :			//�亯 �۾��� ������
			action = new RvReplyView();
			break;
		case "/RvReplyAction.rv" :		//�亯 ���
			action = new RvReplyAction();	
			break;
		case "/RvModifyView.rv":			//�� ����
			action = new RvModifyView();
			break;
		case "/RvModifyAction.rv":		//�� ���� â���� ����
			action = new RvModifyAction();
			break;
		case "/RvDeleteAction.rv":		//�� ����
			action = new RvDeleteAction();
			break;
		case "/RvFileDown.rv":			//���� �ٿ� 
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
			if(forward.isRedirect()) {	// �����̷�Ʈ �˴ϴ�.
				response.sendRedirect(forward.getPath());	
			}else { //�������˴ϴ�.
				RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}//else
		}//forward
	}//doProcess
	
	
	
	
	//doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵�
	//POST ������� ���۵Ǿ� ���� ���� �޼��忡�� ��û�� ó���Ҽ� �ֶǷ� �Ͽ����ϴ�.
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
