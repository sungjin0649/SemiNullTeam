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
	 * ��û�� ��ü URL �߿��� ��Ʈ ��ȣ ���� ���� ������ ���ڿ����� ��ȯ�˴ϴ�.
	 ��) http://http://localhost:8088/nullnull/join.com�� ���
	   "/nullnull/join.com" ��ȯ��ϴ�.
	 */	
	String RequestURI = request.getRequestURI();
    System.out.println("RequestURI = " + RequestURI);
    
    // getContextPath() : ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
    // contextPath�� "/nullnull"�� ��ȯ�˴ϴ�.
    String contextPath = request.getContextPath();
    System.out.println("contextPath = " + contextPath);
    
    // RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
    // ������ ��ġ ���ڱ��� �����մϴ�.
    // command�� "/join.oky" ��ȯ�˴ϴ�.
    String command = RequestURI.substring(contextPath.length());
    System.out.println("command = " + command);
    
    // �ʱ�ȭ
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
    case "/loginProcess.oky": //������ ȯ���մϴ� ���� ó��
    	action = new MemberLoginProcessAction();
    	break;
//    case "/login.oky": //�α����� ����Ǿ� ������������ ���°��� ó��
//    	action = new MemberLoginAction();
//    	break;
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
    }//switch end
    
    forward = action.execute(request, response); //action ��ü ���� �� ����
    
    System.out.println(forward);
    if (forward != null) { // �����̷�Ʈ �˴ϴ�. (insert�� ���� �ý��ۿ� ��ȭ�� ����� ��û ex.�α���, ȸ������, �۾���)
    	if (forward.isRedirect()) {
    		response.sendRedirect(forward.getPath());   		
    	} else {// ������ �˴ϴ�. (select�� ���� �ý��ۿ� ��ȭ�� ������ �ʴ� �ܼ���ȸ(����Ʈ����,�˻�)
    		RequestDispatcher dispatcher=
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
