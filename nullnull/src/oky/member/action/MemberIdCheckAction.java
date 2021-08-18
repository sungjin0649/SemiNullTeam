package oky.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.member.db.MemberDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO mdao = new MemberDAO();
		int result = mdao.isId(request.getParameter("idck"));
		response.getWriter().append(Integer.toString(result));//resp
		System.out.println(result);
		return null;
	}

}
