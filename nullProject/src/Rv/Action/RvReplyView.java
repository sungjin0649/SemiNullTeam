package Rv.Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Rv.db.RvBean;


public class RvReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RvBean boarddata = new RvBean();
		RvDAO boarddao = new RvDAO();
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		boarddata=boarddao.getDetail(num);
		
		if(boarddata==null) {
			System.out.println("���� ���������ʽ��ϴ�. ");
			forward.setRedirect(false);
			request.setAttribute("message", "���� �������� �ʽ��ϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("�亯 ������ �̵� �Ϸ�");
		//�亯 �� �������� �̵��� �� ���� �� ������ �����ֱ� ���� 
		//boarddata��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("boarddata", boarddata);	
		
		forward.setRedirect(false);
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Board/RvReply.jsp");
		return forward;
		

	}//execute end
}
