package oky.frcomment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.frcomment.db.FrComment;
import oky.frcomment.db.FrCommentDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class FrCommentUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FrCommentDAO dao = new FrCommentDAO();
		FrComment co = new FrComment();
		
		co.setFcm_content(request.getParameter("content"));
		System.out.println("content=" + co.getFcm_content());
		
		co.setFcm_no(Integer.parseInt(request.getParameter("num")));
		System.out.println("댓글 수정 성공");
		int ok = dao.fcmcommentsUpdate(co);
		response.getWriter().print(ok);
		return null;
	}

}
