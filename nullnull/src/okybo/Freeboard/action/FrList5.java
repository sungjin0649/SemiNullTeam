package okybo.Freeboard.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;

public class FrList5 implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FreeboardDAO boarddao = new FreeboardDAO();
		List<FreeboardBean> boardlist = new ArrayList<FreeboardBean>();
		
		//초기 보드리스트 값 설정
		int page = 1; // 보여줄 page
		int limit = 3; // 한 페이지에 보여줄 게시판 갯수
		
		
		String searchType ="";	//SEARCH 08-14 검색기능 추가 
		String search="";       //SEARCH 08-14 검색기능 추가 

		String category = "5";
		System.out.println("넘어온 cateogry는" + category);
		String view = request.getParameter("view");
		System.out.println("넘어온 view 는" + view); //정렬  적용할 ajax
		
		if(view == null) {
			System.out.println("정열이 널값으로  넘어옴");
		}

		
		if(request.getParameter("page") !=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 =" + page);
		
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit =" + limit);
		
		
		
		//SEARCH 08-14 검색 기능 추가
		if(request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
		}
		System.out.println("넘어온 searchType = " + searchType);	
		
		
		//SEARCH 08-14 검색 기능추가
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		System.out.println("넘어온 search = " + search);		
	
	
		// 총 리스트 수를 받아옵니다.
		int listcount;
		
		//SEARCH 08-14 검색 기능추가  트루일시 그냥 일반 리스트 , false 일시 게시글 검색해서 불러오는 리스트				
		if(searchType=="" && search=="") { //일반리스트
			boardlist = boarddao.getfrBoardList(page, limit, view, category);
			listcount= boarddao.getfrListCount(category);
		}else { //검색적용된리스트
			boardlist = boarddao.getFrBoardSearchList(page, limit, searchType, search);
			listcount = boarddao.getFrSearchListCount(searchType, search);
		}
		
	
		System.out.println("총 게시물 수: " +listcount);		
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
		
		String state = request.getParameter("state");
		System.out.println("넘어온 state 는" + state);
		
		
		
		if (state == null) {
			
		request.setAttribute("page", page); //현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수
		System.out.println("현재 페이지에 보여줄 최대 페이지 =" + maxpage);
		//현제 패이지에 표시할 첫 페이지
		request.setAttribute("startpage", startpage);
		
	    // 현재 페이지에 표시할 끝 페이지 수
	    request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount); // 총 글의 수
		
		
		//08-14 서치 검색 기능추가
		request.setAttribute("searchType", searchType);
		request.setAttribute("search", search);		
		
		// 해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("limit", limit);
		
		request.setAttribute("category", category); //카테고리 넘어가야 분류선택시 카테고리 넘어감
		
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	    
	    // 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
	    forward.setPath("Bbs_fr/FreeboardList.jsp");
	    return forward;
	    
	    
	} else {
		System.out.println("state=ajax");
		
		//위에서 request로 담았던 것을 JsonObject에 담습니다.
		JsonObject object = new JsonObject();
		object.addProperty("page" ,page);//{"page": 번수 page의 값} 형식으로 저장
		object.addProperty("maxpage", maxpage);
		object.addProperty("startpage", startpage);
		object.addProperty("endpage", endpage);
		object.addProperty("listcount", listcount);
		object.addProperty("limit", limit);
		object.addProperty("view", view);
		object.addProperty("category", category);
		//JsonObject에 List 형식을 담을 수 있는 addProperty() 존재하지 않습니다.
		//void com.google.gson.JsonObject.add(String property, JsonElemet value)
		//메서드를 통해서 저장합니다.
		//List형식을 JsonElement로 바꾸어 주어야 object에 저장할 수 있습니다.
		// List => JsonElement
		JsonElement je = new Gson().toJsonTree(boardlist);
		System.out.println("boardlist=" + je.toString());
		object.add("boardlist", je);
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append(object.toString());
		System.out.println(object.toString());
		return null;
	}
 }
}
