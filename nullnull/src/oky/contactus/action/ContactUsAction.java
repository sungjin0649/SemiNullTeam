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
		System.out.println("여기는 1대1 문의 사항 프로세스");
		MemberDAO mdao = new MemberDAO();
		String id =(String) session.getAttribute("id");
		Member m = mdao.member_info(id);
		
		System.out.println("가져온 id는 :" + id);
		
		//답장받을 이메일 가져오기
		System.out.println("답장 받을 email :" + m.getEmail());
		System.out.println("답장 받을 phone :" + m.getPhone());
		request.setAttribute("memberinfo", m);
		
		
		 ActionForward forward = new ActionForward();
		 forward.setRedirect(false);
		 forward.setPath("Bbs_con/ContactUs.jsp");		
		return forward;
	}

}
