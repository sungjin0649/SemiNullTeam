package oky.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("여기는 ID/PW찾기 입니다.");
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("member/find.jsp");
		return forward;
	}

}
