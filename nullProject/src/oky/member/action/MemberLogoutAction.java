package oky.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		session.invalidate();
//		forward.setPath("login.net");
//		forward.setRedirect(true);
		System.out.println("����� �α׾ƿ�");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('�α׾ƿ� �Ǿ����ϴ�.');");
		out.println("location.href='http://localhost:8088/nullnull/';");
		out.println("</script>");
		out.close();	
		return null;
//		return forward;
	}
}
