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
		System.out.println("로그인 결과는" + result);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("여기는 로그인 프로세스");
		
		
		
		// 로그인 성공
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);  //세션 설정해놔서 로그인 성공시 id 값이 지속적으로 넘어오는 것
			
//			String IDStore = request.getParameter("remember");
//			Cookie cookie = new Cookie("id", id);
			out.println("<script>");
			out.println("alert('"+ id + " 님 접속을 확영합니다!');");
			out.println("location.href='http://localhost:8088/nullnull/';");
			out.println("</script>");
			out.close();	
//			if (IDStore != null && IDStore.equals("store")) {
//				// cookie.setMaxAge(60 * 60 * 24); //쿠키의 유효시간을 24시간으로 설정합니다.
//				cookie.setMaxAge(2 * 60);
//				// 클라이언트로 쿠키값을 전송합니다.
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
			out.println("alert('아이디 또는 비밀번호가 맞지 않습니다. \\n   로그인 정보를 다시 확인바랍니다. ');");
			out.println("location.href='http://localhost:8088/nullnull/';");			
			out.println("</script>");
			out.close();
		}
		return null;
	}

}
