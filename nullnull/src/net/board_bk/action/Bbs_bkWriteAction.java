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
		forward.setRedirect(false); //포워딩 방식으로 주소가 바뀌지 않아요
		forward.setPath("Bbs_bk/bk_write.jsp");
		return forward;
	}

}
