package oky.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.member.db.MemberDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO mdao = new MemberDAO();
		String id = request.getParameter("id");
		System.out.println("������ �޾ƿ��� id��:" + id);
	    response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		int result = mdao.delete(id);
		if (result == 1) {
			out.println("<script>");
			out.println("alert('���� �����Դϴ�.');");
			out.println("location.href='memberList.oky'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('ȸ�� ���� �����Դϴ�.');");
			out.println("history.back()");
			out.println("</script>");			
		}
		out.close();
		return null;
	}

}
