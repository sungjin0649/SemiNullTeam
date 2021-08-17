package oky.frcomment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.frcomment.db.FrComment;
import oky.frcomment.db.FrCommentDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class FrCommentReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FrCommentDAO dao = new FrCommentDAO();
		FrComment co = new FrComment();
		
		co.setId(request.getParameter("id"));
		co.setFcm_content(request.getParameter("content"));
		co.setFcm_comment_re_lev(Integer.parseInt(request.getParameter("comment_re_lev")));
		co.setFcm_comment_board_num(Integer.parseInt(request.getParameter("comment_board_num")));
		co.setFcm_comment_re_seq(Integer.parseInt(request.getParameter("comment_re_seq")));
		co.setFcm_comment_re_ref(Integer.parseInt(request.getParameter("comment_re_ref")));
		int ok = dao.fcmcommentsReply(co);
		response.getWriter().print(ok);
		return null;
	}

}
