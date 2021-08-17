package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;

public class Bbs_bkModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		Bbs_bkDAO dao = new Bbs_bkDAO();
		Bbs_bk_bean Bbs_bk_data =new Bbs_bk_bean();
		//�Ķ���ͷ� ���޹��� �亯�� �� ��ȣ�� num������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
		Bbs_bk_data=dao.bk_Detail(num);
		
		//�� ������ ���� ���
		if(Bbs_bk_data ==null) {
			System.out.println("(����) �󼼺��� ����.");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� ���� �� ���� ���� �Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("(����) �󼼺��� ����");
		//�亯 �� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		//boarddata��ü�� Request ��ü�� �����մϴ�.
		
		request.setAttribute("Bbs_bk_data", Bbs_bk_data);

		forward.setRedirect(false);
		//�� �亯 ������ ��� �����մϴ�.
		forward.setPath("Bbs_bk/bk_Modify.jsp");
		return forward;
	}

}
