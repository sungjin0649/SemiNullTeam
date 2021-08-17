package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comm_bk.db.comm_bkDAO;

public class Bbs_bkCommentDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		comm_bkDAO dao = new comm_bkDAO();
		
		int BCM_NO = Integer.parseInt(request.getParameter("BCM_NO")) ;
		
		boolean ok= dao.BBS_BCMDelete(BCM_NO);
		response.getWriter().print(ok);
		return null;
	}

}
