package oky.frcomment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.frcomment.db.FrCommentDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class FrCommentDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        int num = Integer.parseInt(request.getParameter("num"));
		
		FrCommentDAO dao = new FrCommentDAO();
		
		int result = dao.fcmcommentsDelete(num);
		PrintWriter out = response.getWriter();
		out.print(result);
		
		
		return null;
	}

}
