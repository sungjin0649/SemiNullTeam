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
