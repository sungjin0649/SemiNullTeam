package oky.contactus.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oky.member.db.Member;
import oky.member.db.MemberDAO;


public class ContactUsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("����� 1��1 ���� ���� ���μ���");
		MemberDAO mdao = new MemberDAO();
		String id =(String) session.getAttribute("id");
		Member m = mdao.member_info(id);
		
		System.out.println("������ id�� :" + id);
		
		//������� �̸��� ��������
		System.out.println("���� ���� email :" + m.getEmail());
		System.out.println("���� ���� phone :" + m.getPhone());
		request.setAttribute("memberinfo", m);
		
		
		 ActionForward forward = new ActionForward();
		 forward.setRedirect(false);
		 forward.setPath("Bbs_con/ContactUs.jsp");		
		return forward;
	}

}
