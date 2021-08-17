package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;



public class FreeBoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		FreeboardDAO  boarddao    = new FreeboardDAO();
		FreeboardBean boarddata   = new FreeboardBean();
		
		//파라미터로 전달받은 답변할 글 번호를 num변수에 저장합니다.
		System.out.println("여기는 수정 프로세스");
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("가져온 글 번호는" +num);
		//글 번호 num 에 해당하는 내용을 가져와서 boarddata 객체에 저장합니다.
		
		boarddata=boarddao.getfrDetail(num);
		//글 내용이 없는 경우
		if(boarddata==null) {
			System.out.println("(수정)상세보기 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세 보기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;			
		}
		
		System.out.println("(수정)상세보기 성공");
		//답변 폼 페이지로 이동할 때 원본 글 내용을 보여주기 위해
		//boarddata객체를 Request 객체에 저장합니다.
		request.setAttribute("boarddata", boarddata);
		
		forward.setRedirect(false);
		//글 답변 페이지 경로 지정합니다.
		forward.setPath("board/FreeboardModify.jsp");
		return forward;
	}//execute end

}
