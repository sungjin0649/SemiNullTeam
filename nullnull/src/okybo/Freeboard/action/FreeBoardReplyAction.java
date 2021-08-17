package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;



public class FreeBoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		FreeboardDAO  boarddao    = new FreeboardDAO();
		FreeboardBean boarddata   = new FreeboardBean();
		int result=0;
		
		//파라미터로 넘어온 값들을 boarddata 객체에 저장합니다.
		boarddata.setId(request.getParameter("id"));
		boarddata.setFr_pass(request.getParameter("fr_pass"));
		boarddata.setFr_subject(request.getParameter("fr_subject"));
		boarddata.setFr_content(request.getParameter("fr_content"));
		boarddata.setFr_csfc(Integer.parseInt(request.getParameter("fr_csfc")));
		boarddata.setFr_re_ref(Integer.parseInt(request.getParameter("fr_re_ref")));
		boarddata.setFr_re_lev(Integer.parseInt(request.getParameter("fr_re_lev")));
		boarddata.setFr_re_seq(Integer.parseInt(request.getParameter("fr_re_seq")));
		
		System.out.println("넘어온 댓글쓰기 값들은");
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("fr_pass"));
		System.out.println(request.getParameter("fr_subject"));
		System.out.println(request.getParameter("fr_content"));
		System.out.println(Integer.parseInt(request.getParameter("fr_re_ref")));
		System.out.println(Integer.parseInt(request.getParameter("fr_re_lev")));
		System.out.println(Integer.parseInt(request.getParameter("fr_re_seq")));
		
		
		//답변을 DB에 저장하기위해 boarddata 객체를 파라미터로 전달하고
		//DAO의 메서드 boardReply를 호출합니다.
		result=boarddao.frboardReply(boarddata);
		
		//답변 저장에 실패한 경우
		if (result==0) {
			System.out.println("답장 저장 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "답장 저장 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//답변 저장이 제대로 된 경우
		System.out.println("답장 완료");
		forward.setRedirect(true);
		//답변 글 내용을 확인하기 위해 글 내용 보기 페이지를 경로로 설정합니다.
		forward.setPath("FreeboardDetailAction.okybo?num="+result);
		return forward;
	}

}
