package net.board_bk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;


public class Bbs_bkListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bbs_bkDAO dao = new Bbs_bkDAO();
		List<Bbs_bk_bean> boardlist = new ArrayList<Bbs_bk_bean>();
		
		//로그인 성공시 파라미터 page가 없어요. 그래서 초기값이 필요합니다.
		
		int page = 1; //보여줄 page
		int limit = 10; //한 페이지에 보여줄 게시판 목록의 수
		String searchType ="";
		String search="";
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " +page);
		
		//추가
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit = " + limit);
		
		//추가
		if(request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
		}
		System.out.println("넘어온 searchType = " + searchType);
		//추가
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		System.out.println("넘어온 search = " + search);
		//총 리스트 수를 받아옵니다.
		int listcount;
		//리스트를 받아옵니다.
		if(searchType=="" && search=="") {
			boardlist = dao.bbs_bkBoardList(page, limit);
			listcount = dao.bbs_bkListCount();
		}else {
			boardlist = dao.bbs_bkBoardSearchList(page, limit, searchType, search);
			listcount = dao.bbs_bkSearchListCount(searchType, search);
		}
		System.out.println("넘어온 listcount"+ listcount);
		
		int maxpage =(listcount + limit -1)/limit;
		System.out.println("총 페이지수 =" +maxpage);
		
		int startpage =((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" +startpage);
		
		int endpage = startpage +10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 =" + endpage);
		
		if(endpage > maxpage)
			endpage = maxpage;
		
		String state = request.getParameter("state");
		//추가
		if(state == null) {
			System.out.println("state==null");
		//추가
		request.setAttribute("page", page); //현재 페이지 수
		request.setAttribute("maxpage", maxpage); //최대 페이지 수
		
		//현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		
		//현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount); //총 글의 수
		
		request.setAttribute("searchType", searchType);
		request.setAttribute("search", search);
		
		//해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("limit", limit);
		ActionForward forward =new ActionForward();
		forward.setRedirect(false);
		
		//글 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("Bbs_bk/bk_list.jsp");
		return forward; //BoardFontController.java로 리턴됩니다.
		
	}else {//에이잭스로 넘어온경우
		System.out.println("state=ajax");
		
		//위에서 request로 담았던 것을 JsonObject에 담습니다.
		JsonObject object =new JsonObject();
		object.addProperty("page", page);//{"page": 변수 apge의 값} 형식으로 저장
		object.addProperty("maxpage", maxpage);
		object.addProperty("startpage", startpage);
		object.addProperty("endpage", endpage);
		object.addProperty("listcount", listcount);
		object.addProperty("limit", limit);
		JsonElement je = new Gson().toJsonTree(boardlist);
		System.out.println("boardlist="+je.toString());
		object.add("boardlist", je);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(object.toString());
		System.out.println(object.toString());
		return null;
		//http://localhost:8088/Board/BoardList.bo?limit=3&state=ajax&page=1
	}//if end
	}

}
