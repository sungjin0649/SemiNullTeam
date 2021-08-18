package Rv.Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Rv.db.RvBean;


public class RvReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		RvDAO boarddao = new RvDAO();
		RvBean boarddata = new RvBean();
		int result =0;
		
		//�Ķ���ͷ� �Ѿ�� ������ boarddata ��ü�� �����մϴ�.
		boarddata.setUSER_ID(request.getParameter("USER_ID"));
		boarddata.setRV_PASS(request.getParameter("RV_PASS"));
		boarddata.setRV_SUBJECT(request.getParameter("RV_SUBJECT"));
		boarddata.setRV_CONTENT(request.getParameter("RV_CONTENT"));
		boarddata.setRV_RE_REF(Integer.parseInt(request.getParameter("RV_RE_REF")));
		boarddata.setRV_RE_LEV(Integer.parseInt(request.getParameter("RV_RE_LEV")));
		boarddata.setRV_RE_SEQ(Integer.parseInt(request.getParameter("RV_RE_SEQ")));
		
		//�亯�� DB�� �����ϱ����� boarddata ��ü�� �Ķ���ͷ� �����ϰ� 
		//DAO�� �޼��� boardReply�� ȣ���մϴ�.
		result = boarddao.boardReply(boarddata);
		
		//�亯 ���忡 ������ ��� 
		if(result==0) {
			System.out.println("���� �������");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "���� ���� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//�亯 ������ ����ε� ��� 
		System.out.println("���� �Ϸ�");
		forward.setRedirect(true);
		//�亯 �� ������ Ȯ���ϱ� ���� �� ���� ���� �������� ��η� �����մϴ�.
		forward.setPath("RvDetailAction.rv?num=" + result);
		return forward;
	}

}
