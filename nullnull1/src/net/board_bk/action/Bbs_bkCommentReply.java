package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comm_bk.db.comm_bkDAO;
import net.comm_bk.db.comm_bk_bean;

public class Bbs_bkCommentReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		comm_bkDAO dao = new comm_bkDAO();
		comm_bk_bean cb = new comm_bk_bean();
		
		cb.setBCM_ID(request.getParameter("BCM_ID"));
		cb.setBCM_CONTENT(request.getParameter("BCM_CONTENT"));
		cb.setBCM_COMMENT_RE_LEV(Integer.parseInt(request.getParameter("BCM_COMMENT_RE_LEV")));
		cb.setBCM_COMMENT_BOARD_NUM(Integer.parseInt(request.getParameter("BCM_COMMENT_BOARD_NUM")));
		cb.setBCM_COMMENT_RE_SEQ(Integer.parseInt(request.getParameter("BCM_COMMENT_RE_SEQ")));
		cb.setBCM_COMMENT_RE_REF(Integer.parseInt(request.getParameter("BCM_COMMENT_RE_REF")));
		
		int ok = dao.BBS_BCMReply(cb);
		response.getWriter().print(ok);
		return null;
	}

}
