package Rv.Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Rv.db.RvBean;

public class RvModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RvBean boarddata = new RvBean();
		RvDAO boarddao = new RvDAO();
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		boarddata=boarddao.getDetail(num);
		
		if(boarddata==null) {
			System.out.println("(수정) 상세보기 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세보기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("(수정)상세보기 완료");
		//답변 폼 페이지로 이동할 때 원본 글 내용을 보여주기 위해 
		//boarddata객체를 Request 객체에 저장합니다.
		request.setAttribute("boarddata", boarddata);	
		
		forward.setRedirect(false);
		//글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("Board/RvModify.jsp");
		return forward;
		

	}//execute end

}
