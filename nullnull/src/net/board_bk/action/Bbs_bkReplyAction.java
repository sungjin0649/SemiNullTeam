package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;

public class Bbs_bkReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward =new ActionForward();
		Bbs_bkDAO dao = new Bbs_bkDAO();
		Bbs_bk_bean Bbs_bk_data =new Bbs_bk_bean();
		int result=0;
		
		//파라미터로 넘어온 값들을 Bbs_bk_data 객체에 저장합니다.
		Bbs_bk_data.setBK_CSFC(request.getParameter("BK_CSFC"));
		Bbs_bk_data.setUSER_ID(request.getParameter("USER_ID"));
		Bbs_bk_data.setBK_SUBJECT(request.getParameter("BK_SUBJECT"));
		Bbs_bk_data.setBK_CONTENT(request.getParameter("BK_CONTENT"));
		Bbs_bk_data.setBK_PASS(request.getParameter("BK_PASS"));
		Bbs_bk_data.setBK_RE_REF(Integer.parseInt(request.getParameter("BK_RE_REF")));
		Bbs_bk_data.setBK_RE_LEV(Integer.parseInt(request.getParameter("BK_RE_LEV")));
		Bbs_bk_data.setBK_RE_SEQ(Integer.parseInt(request.getParameter("BK_RE_SEQ")));
		
		//답변을 DB에 저장하기위해 boarddata 객체를 파라미터로 전달하고
		//DAO의 메서드 boardReply를 호출합니다.
		result=dao.Bbs_bkReply(Bbs_bk_data);
		
		//답변 저장에 실패한 경우
		if(result==0) {
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
		forward.setPath("Bbs_bkDetail.bk?num="+result);
		return forward;
	}

}
