package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;

public class Bbs_bkDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bbs_bkDAO dao = new Bbs_bkDAO();
		Bbs_bk_bean Bbs_bkdata =new Bbs_bk_bean();
		
		//http://localhost:8088/nullnull/Bbs_bkDetailAction.bk?num=1
		//�۹�ȣ �Ķ���� ���� num������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("���� ="+num);
		//������ Ȯ���� ���� ��ȸ���� ������ŵ�ϴ�.
		dao.bk_ReadCountUpdate(num);
		
		//���� ������ DAO���� ���� �� ���� ����� boarddata ��ü�� �����մϴ�.
		Bbs_bkdata=dao.bk_Detail(num);
		
		//boarddata=null;//error�׽�Ʈ�� ���� �� ����
		//DAO���� ���� ������ ���� ������ ��� null�� ��ȯ�մϴ�.
		if(Bbs_bkdata==null) {
			System.out.println("�󼼺��� ����");
			ActionForward forward =new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "�����͸� ���� ���߽��ϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("�󼼺��� ����");
		
		//boarddata ��ü�� request ��ü�� �����մϴ�.
		request.setAttribute("Bbs_bkdata", Bbs_bkdata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Bbs_bk/bk_view.jsp");
		return forward;
	}

}
