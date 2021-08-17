package Rvcomment.Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Rv.Action.Action;
import Rv.Action.ActionForward;
import Rvcomment.db.Comment;
import Rvcomment.db.CommentDAO;

public class RvCommentAdd implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDAO dao =new CommentDAO();
		
		Comment co = new Comment();
		co.setId(request.getParameter("id"));
		co.setContent(request.getParameter("content"));
		System.out.println("content = " + co.getContent());
		co.setComment_board_num(Integer.parseInt(request.getParameter("comment_board_num")));
		co.setComment_re_lev(Integer.parseInt(request.getParameter("comment_re_lev")));
		co.setComment_re_seq(Integer.parseInt(request.getParameter("comment_re_seq")));
		int ok =dao.commentsInsert(co);
		response.getWriter().print(ok);
		return null;
		

		
	}
}
