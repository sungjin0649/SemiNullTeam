package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comm_bk.db.comm_bkDAO;
import net.comm_bk.db.comm_bk_bean;

public class Bbs_bkCommentUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		comm_bkDAO dao = new comm_bkDAO();
		comm_bk_bean cb = new comm_bk_bean();
		cb.setBCM_CONTENT(request.getParameter("BCM_CONTENT"));
		
		cb.setBCM_NO(Integer.parseInt(request.getParameter("BCM_NO")));
		
		int ok = dao.BBS_BCMUpdate(cb);
		response.getWriter().print(ok);
		return null;
	}

}
