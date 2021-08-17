package oky.member.action;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.member.db.MemberDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberFindProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("����� ���̵�/��й�ȣã�� ���μ���");
		
		String name = request.getParameter("name");
		System.out.println(name);
		String birth = request.getParameter("birth");
		System.out.println(birth);
		String email = request.getParameter("email");
		System.out.println(email);
		
		String pan = request.getParameter("find"); // ���̵�ã�°��� ��й�ȣ ã�°��� �Ǵ�
		System.out.println(pan);
		MemberDAO mdao = new MemberDAO(); 
		
		String id = mdao.findid(name, birth, email);
		System.out.println(id);
		
		String pass = mdao.findpass(name, birth, email);
		System.out.println(pass);
		
		out.println("<script>");
		if (pan.equals("findid") && id != null) {
			out.println("alert('��û�Ͻ� id ��" + id + "�Դϴ�');");
			out.println("history.back()");

		} else if (pan.equals("findpw") && pass != null) {
			out.println("alert('��û�Ͻ� ��й�ȣ ��" + pass + "�Դϴ�');");
			out.println("history.back()");

		} else {
			out.println("alert('�ش������� ���Ե� ����ڸ� ã�� �� �����ϴ�.');");
			out.println("history.back()");//
			
		}

		out.println("</script>");
		out.close();			
		return null;
	}

}
