package oky.contactus.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("*.okycon")
public class ContactUsFrontController extends HttpServlet {
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
	        case "/ContactUs.okycon":
	        	action = new ContactUsAction();
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
