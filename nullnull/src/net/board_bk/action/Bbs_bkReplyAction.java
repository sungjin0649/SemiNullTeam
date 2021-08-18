package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;

public class Bbs_bkReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward =new ActionForward();
		Bbs_bkDAO dao = new Bbs_bkDAO();
		Bbs_bk_bean Bbs_bk_data =new Bbs_bk_bean();
		int result=0;
		
		//�Ķ���ͷ� �Ѿ�� ������ Bbs_bk_data ��ü�� �����մϴ�.
		Bbs_bk_data.setBK_CSFC(request.getParameter("BK_CSFC"));
		Bbs_bk_data.setUSER_ID(request.getParameter("USER_ID"));
		Bbs_bk_data.setBK_SUBJECT(request.getParameter("BK_SUBJECT"));
		Bbs_bk_data.setBK_CONTENT(request.getParameter("BK_CONTENT"));
		Bbs_bk_data.setBK_PASS(request.getParameter("BK_PASS"));
		Bbs_bk_data.setBK_RE_REF(Integer.parseInt(request.getParameter("BK_RE_REF")));
		Bbs_bk_data.setBK_RE_LEV(Integer.parseInt(request.getParameter("BK_RE_LEV")));
		Bbs_bk_data.setBK_RE_SEQ(Integer.parseInt(request.getParameter("BK_RE_SEQ")));
		
		//�亯�� DB�� �����ϱ����� boarddata ��ü�� �Ķ���ͷ� �����ϰ�
		//DAO�� �޼��� boardReply�� ȣ���մϴ�.
		result=dao.Bbs_bkReply(Bbs_bk_data);
		
		//�亯 ���忡 ������ ���
		if(result==0) {
			System.out.println("���� ���� ����");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "���� ���� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		//�亯 ������ ����� �� ���
		System.out.println("���� �Ϸ�");
		forward.setRedirect(true);
		//�亯 �� ������ Ȯ���ϱ� ���� �� ���� ���� �������� ��η� �����մϴ�.
		forward.setPath("Bbs_bkDetail.bk?num="+result);
		return forward;
	}

}
