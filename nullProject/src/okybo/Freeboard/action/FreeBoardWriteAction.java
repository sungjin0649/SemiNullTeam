package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class FreeBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����� �����Խ��� �۾���");
		 ActionForward forward = new ActionForward();
		 forward.setRedirect(false);
		 forward.setPath("board/FreeboardWrite.jsp");		
		return forward;
	}

}
