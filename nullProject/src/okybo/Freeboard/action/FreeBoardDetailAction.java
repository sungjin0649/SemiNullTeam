package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;

public class FreeBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FreeboardDAO boarddao = new FreeboardDAO();
		FreeboardBean boarddata = new FreeboardBean();
		
		//글번호 파라미터 값을 num 변수에 저장합니다.
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println("클릭한 글 번호: " + num);
		//해당 게시글 클릭 시 조회수 증가
		boarddao.setfrReadCountUpdate(num);
		
		//글의 내용을 DAO에서 가져와서 boardata 객체에 저장
		boarddata=boarddao.getfrDetail(num);
		
		//boarddata=null; //error테스트를 위한 값 설정
		//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
		if(boarddata==null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		
		//boarddata 객체를 request 객체에 저장합니다.
		request.setAttribute("boarddata", boarddata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("board/FreeboardView.jsp");
		return forward;
	}

}
