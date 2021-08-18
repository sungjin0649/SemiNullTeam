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
	        case "/ContactUs.okycon":
	        	action = new ContactUsAction();
	        	break;	
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
