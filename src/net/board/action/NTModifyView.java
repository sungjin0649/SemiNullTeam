package net.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.NTBean;
import net.board.db.NTDAO;

public class NTModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		NTDAO boarddao = new NTDAO();
		NTBean boarddata = new NTBean();
		
		//�Ķ���ͷ� ���޹��� ������ �� ��ȣ�� num ������ ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
		boarddata=boarddao.getDetail(num);
		
		//�� ������ ���� ���
		if(boarddata==null) {
			System.out.println("���� �󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message","�Խ��� ���� ����");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("���� �󼼺��� ����");
		//���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ������ boarddata ��ü��
		//request ��ü�� �����մϴ�.
		request.setAttribute("boarddata",boarddata);
		forward.setRedirect(false);
		//�� ���� �� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Bbs_nt/NTModify.jsp");
		return forward;
	}

}
