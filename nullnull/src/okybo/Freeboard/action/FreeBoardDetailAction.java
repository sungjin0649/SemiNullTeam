package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;

public class FreeBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FreeboardDAO boarddao = new FreeboardDAO();
		FreeboardBean boarddata = new FreeboardBean();
		
		//�۹�ȣ �Ķ���� ���� num ������ �����մϴ�.
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println("Ŭ���� �� ��ȣ: " + num);
		//�ش� �Խñ� Ŭ�� �� ��ȸ�� ����
		boarddao.setfrReadCountUpdate(num);
		
		//���� ������ DAO���� �����ͼ� boardata ��ü�� ����
		boarddata=boarddao.getfrDetail(num);
		
		//boarddata=null; //error�׽�Ʈ�� ���� �� ����
		//DAO���� ���� ������ ���� ������ ��� null�� ��ȯ�մϴ�.
		if(boarddata==null) {
			System.out.println("�󼼺��� ����");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "�����͸� ���� ���߽��ϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("�󼼺��� ����");
		
		//boarddata ��ü�� request ��ü�� �����մϴ�.
		request.setAttribute("boarddata", boarddata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Bbs_fr/FreeboardView.jsp");
		return forward;
	}

}
