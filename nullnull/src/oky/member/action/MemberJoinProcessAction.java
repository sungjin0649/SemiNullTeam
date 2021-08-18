package oky.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.member.db.Member;
import oky.member.db.MemberDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;


public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����� ȸ������ ���μ���");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = request.getParameter("date");	
		
		System.out.println(id);
		System.out.println(pass);
		System.out.println(name);
		
		String A= year.concat(month);
		A = A.concat(date);
		System.out.println(A);
		int birth = Integer.parseInt(A); //���� ��ȯ
		
		
		String e =  request.getParameter("email");
		String domain = request.getParameter("domain");
		String email= e.concat("@");
		email = email.concat(domain);
		System.out.println(email); //�̸��� ��ȯ
		
		
		String phone =request.getParameter("phone");
		System.out.println(phone); 

		Member m = new Member();
		m.setId(id); m.setPass(pass); m.setName(name);
		m.setBirth(birth); m.setPhone(phone); m.setEmail(email);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.insert(m);
		out.println("<script>");
		if (result ==1) {
			out.println("alert('ȸ�� ������ �����մϴ�!');");
			out.println("location.href='index.jsp';");
		} else if (result== -1) {
			out.println("alert('���̵� �ߺ��Ǿ����ϴ�. �ٽ� �Է��ϼ���');");
			// out.println("location.href='join.oky';");
			out.println("history.back()");// ��й�ȣ�� ������ �ٸ� �����ʹ� �����Ǿ� �ֽ��ϴ�			
		}
		out.println("</script>");
		out.close();		
		return null;
	}

}
