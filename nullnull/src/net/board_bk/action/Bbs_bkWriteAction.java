package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Bbs_bkWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward =new ActionForward();
		forward.setRedirect(false); //������ ������� �ּҰ� �ٲ��� �ʾƿ�
		forward.setPath("Bbs_bk/bk_write.jsp");
		return forward;
	}

}
