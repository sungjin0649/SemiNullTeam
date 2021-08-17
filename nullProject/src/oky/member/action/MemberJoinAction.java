package oky.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("여기는 회원가입 입니다.");
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("member/joinForm.jsp");
		return forward;
	}

}
