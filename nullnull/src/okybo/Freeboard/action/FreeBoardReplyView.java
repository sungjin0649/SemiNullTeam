package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;



public class FreeBoardReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		FreeboardDAO  boarddao    = new FreeboardDAO();
		FreeboardBean boarddata   = new FreeboardBean();
		
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("�亯�� �� ��ȣ��: " + num);
		
		boarddata = boarddao.getfrDetail(num);
		//�� ������ ���� ���
				if(boarddata==null) {
					System.out.println("���� �������� �ʽ��ϴ�.");
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
				//�� �亯 ������ ��� �����մϴ�.
				forward.setPath("Bbs_fr/FreeboardReply.jsp");
				return forward;
	}

}
