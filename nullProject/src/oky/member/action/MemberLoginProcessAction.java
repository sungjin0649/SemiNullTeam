package oky.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oky.member.db.MemberDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberLoginProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String id = request.getParameter("id");
		System.out.println(id);
		String pass = request.getParameter("pass");
		System.out.println(pass);
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.ckidpw(id, pass);
		System.out.println("�α��� �����" + result);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("����� �α��� ���μ���");
		
		
		
		// �α��� ����
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);  //���� �����س��� �α��� ������ id ���� ���������� �Ѿ���� ��
			
//			String IDStore = request.getParameter("remember");
//			Cookie cookie = new Cookie("id", id);
			out.println("<script>");
			out.println("alert('"+ id + " �� ������ Ȯ���մϴ�!');");
			out.println("location.href='http://localhost:8088/nullnull/';");
			out.println("</script>");
			out.close();	
//			if (IDStore != null && IDStore.equals("store")) {
//				// cookie.setMaxAge(60 * 60 * 24); //��Ű�� ��ȿ�ð��� 24�ð����� �����մϴ�.
//				cookie.setMaxAge(2 * 60);
//				// Ŭ���̾�Ʈ�� ��Ű���� �����մϴ�.
//				response.addCookie(cookie);
//				
//			} else {
//				cookie.setMaxAge(0);
//				response.addCookie(cookie);
//			}
//			
//			forward.setRedirect(true);
//			forward.setPath("login.oky");
//			return forward;	
			return null;
		} else {
			out.println("<script>");
			out.println("alert('���̵� �Ǵ� ��й�ȣ�� ���� �ʽ��ϴ�. \\n   �α��� ������ �ٽ� Ȯ�ιٶ��ϴ�. ');");
			out.println("location.href='http://localhost:8088/nullnull/';");			
			out.println("</script>");
			out.close();
		}
		return null;
	}

}
