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
		System.out.println("여기는 아이디/비밀번호찾기 프로세스");
		
		String name = request.getParameter("name");
		System.out.println(name);
		String birth = request.getParameter("birth");
		System.out.println(birth);
		String email = request.getParameter("email");
		System.out.println(email);
		
		String pan = request.getParameter("find"); // 아이디찾는건지 비밀번호 찾는건지 판단
		System.out.println(pan);
		MemberDAO mdao = new MemberDAO(); 
		
		String id = mdao.findid(name, birth, email);
		System.out.println(id);
		
		String pass = mdao.findpass(name, birth, email);
		System.out.println(pass);
		
		out.println("<script>");
		if (pan.equals("findid") && id != null) {
			out.println("alert('요청하신 id 는" + id + "입니다');");
			out.println("history.back()");

		} else if (pan.equals("findpw") && pass != null) {
			out.println("alert('요청하신 비밀번호 는" + pass + "입니다');");
			out.println("history.back()");

		} else {
			out.println("alert('해당정보로 가입된 사용자를 찾을 수 없습니다.');");
			out.println("history.back()");//
			
		}

		out.println("</script>");
		out.close();			
		return null;
	}

}
