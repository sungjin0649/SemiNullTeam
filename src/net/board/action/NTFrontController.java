package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.bo")
public class NTFrontController extends javax.servlet.http.HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		/*
		 * ��û�� ��ü URL �߿��� ��Ʈ ��ȣ �������� ������ ���ڿ����� ��ȯ�˴ϴ�.
		      ��) http://localhost:8088/JspProject/login.net�� ���
		      "/JspProject/login.net" ��ȯ�˴ϴ�.
		 */
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		//getContextPath() : ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		//contextPath��"/JspProject"�� ��ȯ�˴ϴ�.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
		// ������ ��ġ ���ڱ��� �����մϴ�.
		//command��"/login.net" ��ȯ�˴ϴ�.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		
		//�ʱ�ȭ
		ActionForward forward = null;
		Action action = null;
		
		switch (command) {
			
			case "/NTList.bo":
				action = new NTListAction();
				break;
		    case "/NTListEnr.bo":
		        action = new NTListActionEnr();
		        break;
		    case "/NTListRea.bo":
		        action = new NTListActionRea();
		        break;
			case "/NTWrite.bo":
				action = new NTWriteAction();
				break;
			case "/NTAddAction.bo":
				action = new NTAddAction();
				break;
			case "/NTDetailAction.bo":
				action = new NTDetailAction();
				break;
			case "/NTModifyView.bo":
				action = new NTModifyView();
				break;
			case "/NTModifyAction.bo":
				action = new NTModifyAction();
				break;
			case "/NTDeleteAction.bo":
				action = new NTDeleteAction();
				break;
			case "/NTFAQ.bo":
				action = new NTFAQAction();
				break;
			case "/NTFileDown.bo":
				action = new NTFileDownAction();
				break;
		}
		
		forward = action.execute(request,response);
		if(forward!=null) {
			if(forward.isRedirect()) {//�����̷�Ʈ�˴ϴ�.
				response.sendRedirect(forward.getPath());
			}else { //�������˴ϴ�.
				RequestDispatcher dispatcher =
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}//else
		}//forward
	
	}//doProcess
	
	//doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵�
	//POST������� ���۵Ǿ� ���� ���� �޼��忡�� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
