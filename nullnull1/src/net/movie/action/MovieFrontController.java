package net.movie.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.mo")
public class MovieFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public MovieFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	String Request = request.getRemoteHost();
    	System.out.println("���� URI =" +Request);
    	/*
   		��û�� ��ü URL�߿��� ��Ʈ ��ȣ ���� ���� ������ ���ڿ����� ��ȯ�˴ϴ�.
   		��) http://localhost:8088/JspProject/BoardList.bo�� ��� "/JspProject/BoardList.bo"
    	��ȯ�˴ϴ�.
    	  */
    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);
    	
    	//getContextPath() = ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
    	//contextPath�� "/JspProject"�� ��ȯ�˴ϴ�.
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);
    	
    	//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
    	//������ ��ġ ���ڱ��� �����մϴ�.
    	// command�� "/login.net"��ȯ�˴ϴ�.
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = "+ command);
    	
    	//�ʱ�ȭ
    	ActionForward forward = null;
    	Action action =null;
    	
    	switch (command) {
    	case "/bestmovie.mo" :
    		action = new bestmovieAction();
    		break;
    	case "/movieticketing.mo" :
    		action = new movieticketingAction();
    		break;
    	
    	}// switch end

    	forward =action.execute(request, response);
    	if(forward != null) {
    		if(forward.isRedirect()) { //�����̷�Ʈ �˴ϴ�.
    			response.sendRedirect(forward.getPath());
    		}else {//�������˴ϴ�.
    			RequestDispatcher dispatcher =
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}//else
    	}//forward
    	
    }//doProcess
    //doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵�
    //POST������� ���۵Ǿ� ���� ���� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
