package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;



public class FreeBoardReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		FreeboardDAO  boarddao    = new FreeboardDAO();
		FreeboardBean boarddata   = new FreeboardBean();
		
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("답변할 글 번호는: " + num);
		
		boarddata = boarddao.getfrDetail(num);
		//글 내용이 없는 경우
				if(boarddata==null) {
					System.out.println("글이 존재하지 않습니다.");
					forward.setRedirect(false);
					request.setAttribute("message", "글이 존재하지 않습니다.");
					forward.setPath("error/error.jsp");
					return forward;			
				}
				
				System.out.println("답변 페이지 이동 완료");
				//답변 폼 페이지로 이동할 때 원본 글 내용을 보여주기 위해
				//boarddata객체를 Request 객체에 저장합니다.
				request.setAttribute("boarddata", boarddata);
				
				forward.setRedirect(false);
				//글 답변 페이지 경로 지정합니다.
				forward.setPath("board/FreeboardReply.jsp");
				return forward;
	}

}
