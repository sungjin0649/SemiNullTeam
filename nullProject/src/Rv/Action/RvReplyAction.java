package Rv.Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Rv.db.RvBean;


public class RvReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		RvDAO boarddao = new RvDAO();
		RvBean boarddata = new RvBean();
		int result =0;
		
		//파라미터로 넘어온 값들을 boarddata 객체에 저장합니다.
		boarddata.setUSER_ID(request.getParameter("USER_ID"));
		boarddata.setRV_PASS(request.getParameter("RV_PASS"));
		boarddata.setRV_SUBJECT(request.getParameter("RV_SUBJECT"));
		boarddata.setRV_CONTENT(request.getParameter("RV_CONTENT"));
		boarddata.setRV_RE_REF(Integer.parseInt(request.getParameter("RV_RE_REF")));
		boarddata.setRV_RE_LEV(Integer.parseInt(request.getParameter("RV_RE_LEV")));
		boarddata.setRV_RE_SEQ(Integer.parseInt(request.getParameter("RV_RE_SEQ")));
		
		//답변을 DB에 저장하기위해 boarddata 객체를 파라미터로 전달하고 
		//DAO의 메서드 boardReply를 호출합니다.
		result = boarddao.boardReply(boarddata);
		
		//답변 저장에 실패한 경우 
		if(result==0) {
			System.out.println("답장 저장실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "답장 저장 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//답변 저장이 제대로된 경우 
		System.out.println("답장 완료");
		forward.setRedirect(true);
		//답변 글 내용을 확인하기 위해 글 내용 보기 페이지를 경로로 설정합니다.
		forward.setPath("RvDetailAction.rv?num=" + result);
		return forward;
	}

}
