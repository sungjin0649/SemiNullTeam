package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;



public class FreeBoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		FreeboardDAO  boarddao    = new FreeboardDAO();
		FreeboardBean boarddata   = new FreeboardBean();
		int result=0;
		
		//�Ķ���ͷ� �Ѿ�� ������ boarddata ��ü�� �����մϴ�.
		boarddata.setId(request.getParameter("id"));
		boarddata.setFr_pass(request.getParameter("fr_pass"));
		boarddata.setFr_subject(request.getParameter("fr_subject"));
		boarddata.setFr_content(request.getParameter("fr_content"));
		boarddata.setFr_csfc(Integer.parseInt(request.getParameter("fr_csfc")));
		boarddata.setFr_re_ref(Integer.parseInt(request.getParameter("fr_re_ref")));
		boarddata.setFr_re_lev(Integer.parseInt(request.getParameter("fr_re_lev")));
		boarddata.setFr_re_seq(Integer.parseInt(request.getParameter("fr_re_seq")));
		
		System.out.println("�Ѿ�� ��۾��� ������");
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("fr_pass"));
		System.out.println(request.getParameter("fr_subject"));
		System.out.println(request.getParameter("fr_content"));
		System.out.println(Integer.parseInt(request.getParameter("fr_re_ref")));
		System.out.println(Integer.parseInt(request.getParameter("fr_re_lev")));
		System.out.println(Integer.parseInt(request.getParameter("fr_re_seq")));
		
		
		//�亯�� DB�� �����ϱ����� boarddata ��ü�� �Ķ���ͷ� �����ϰ�
		//DAO�� �޼��� boardReply�� ȣ���մϴ�.
		result=boarddao.frboardReply(boarddata);
		
		//�亯 ���忡 ������ ���
		if (result==0) {
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
		forward.setPath("FreeboardDetailAction.okybo?num="+result);
		return forward;
	}

}
