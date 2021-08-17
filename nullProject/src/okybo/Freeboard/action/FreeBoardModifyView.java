package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;



public class FreeBoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		FreeboardDAO  boarddao    = new FreeboardDAO();
		FreeboardBean boarddata   = new FreeboardBean();
		
		//�Ķ���ͷ� ���޹��� �亯�� �� ��ȣ�� num������ �����մϴ�.
		System.out.println("����� ���� ���μ���");
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("������ �� ��ȣ��" +num);
		//�� ��ȣ num �� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
		
		boarddata=boarddao.getfrDetail(num);
		//�� ������ ���� ���
		if(boarddata==null) {
			System.out.println("(����)�󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� ���� �� ���� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;			
		}
		
		System.out.println("(����)�󼼺��� ����");
		//�亯 �� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		//boarddata��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("boarddata", boarddata);
		
		forward.setRedirect(false);
		//�� �亯 ������ ��� �����մϴ�.
		forward.setPath("board/FreeboardModify.jsp");
		return forward;
	}//execute end

}
