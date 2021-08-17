package okybo.Freeboard.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;


public class FreeBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FreeboardDAO boarddao = new FreeboardDAO();
		List<FreeboardBean> boardlist = new ArrayList<FreeboardBean>();
		
		//초기 보드리스트 값 설정
		int page = 1; // 보여줄 page
		int limit = 10; // 한 페이지에 보여줄 게시판 갯수
		if(request.getParameter("page") !=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 =" + page);
		
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit =" + limit);
		
		// 총 리스트 수를 받아옵니다.
		int listcount = boarddao.getfrListCount();
		System.out.println("총 게시물 수: " +listcount);
		
		// 게시글 리스트를 받아옵니다.
		boardlist = boarddao.getfrBoardList(page, limit);
		System.out.println("페이지에 보여줄 게시글 리스트: " +boardlist);
		
		// 총 페이지 수
		int maxpage = (listcount + limit -1) /limit;
		System.out.println("총 페이지 수 = " + maxpage);
		
		// 현재 페이지 그룹에서 맨 처음에 표시될 페이지 
		int startpage = ((page -1) /10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 =" + startpage);
		
		// 현재 페이지 그룹에서 보여줄 마지막 페이지
		int endpage = startpage + 10 -1 ;
		
		// 페이지그룹에서 마지막 페이지가  설정한 maxpage(10페이지, 20페이지...)보다 작을 경우 maxpage를 end페이지로 사용해야함
		if (endpage > maxpage)
			endpage = maxpage;
		
		
		request.setAttribute("page", page); //현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수
		System.out.println("현재 페이지에 보여줄 최대 페이지 =" + maxpage);
		//현제 패이지에 표시할 첫 페이지
		request.setAttribute("startpage", startpage);
		
	    // 현재 페이지에 표시할 끝 페이지 수
	    request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount); // 총 글의 수
		
		// 해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("limit", limit);
		
		
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	    
	    // 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
	    forward.setPath("Bbs_fr/FreeboardList.jsp");
	    return forward;
	}

}
