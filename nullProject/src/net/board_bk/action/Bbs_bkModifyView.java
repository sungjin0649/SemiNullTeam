package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;

public class Bbs_bkModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		Bbs_bkDAO dao = new Bbs_bkDAO();
		Bbs_bk_bean Bbs_bk_data =new Bbs_bk_bean();
		//파라미터로 전달받은 답변할 글 번호를 num변수에 저장합니다.
		int num=Integer.parseInt(request.getParameter("num"));
		//글 번호 num에 해당하는 내용을 가져와서 boarddata 객체에 저장합니다.
		Bbs_bk_data=dao.bk_Detail(num);
		
		//글 내용이 없는 경우
		if(Bbs_bk_data ==null) {
			System.out.println("(수정) 상세보기 실패.");
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세 보기 실패 입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("(수정) 상세보기 성공");
		//답변 폼 페이지로 이동할 때 원본 글 내용을 보여주기 위해
		//boarddata객체를 Request 객체에 저장합니다.
		
		request.setAttribute("Bbs_bk_data", Bbs_bk_data);

		forward.setRedirect(false);
		//글 답변 페이지 경로 지정합니다.
		forward.setPath("Bbs_bk/bk_Modify.jsp");
		return forward;
	}

}
