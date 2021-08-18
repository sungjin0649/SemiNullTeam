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
		
		//�α��� ������ �Ķ���� page�� �����. �׷��� �ʱⰪ�� �ʿ��մϴ�.
		
		int page = 1; //������ page
		int limit = 10; //�� �������� ������ �Խ��� ����� ��
		String searchType ="";
		String search="";
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ = " +page);
		
		//�߰�
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("�Ѿ�� limit = " + limit);
		
		//�߰�
		if(request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
		}
		System.out.println("�Ѿ�� searchType = " + searchType);
		//�߰�
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		System.out.println("�Ѿ�� search = " + search);
		//�� ����Ʈ ���� �޾ƿɴϴ�.
		int listcount;
		//����Ʈ�� �޾ƿɴϴ�.
		if(searchType=="" && search=="") {
			boardlist = dao.bbs_bkBoardList(page, limit);
			listcount = dao.bbs_bkListCount();
		}else {
			boardlist = dao.bbs_bkBoardSearchList(page, limit, searchType, search);
			listcount = dao.bbs_bkSearchListCount(searchType, search);
		}
		System.out.println("�Ѿ�� listcount"+ listcount);
		
		int maxpage =(listcount + limit -1)/limit;
		System.out.println("�� �������� =" +maxpage);
		
		int startpage =((page - 1) / 10) * 10 + 1;
		System.out.println("���� �������� ������ ���� ������ �� =" +startpage);
		
		int endpage = startpage +10 - 1;
		System.out.println("���� �������� ������ ������ ������ �� =" + endpage);
		
		if(endpage > maxpage)
			endpage = maxpage;
		
		String state = request.getParameter("state");
		//�߰�
		if(state == null) {
			System.out.println("state==null");
		//�߰�
		request.setAttribute("page", page); //���� ������ ��
		request.setAttribute("maxpage", maxpage); //�ִ� ������ ��
		
		//���� �������� ǥ���� ù ������ ��
		request.setAttribute("startpage", startpage);
		
		//���� �������� ǥ���� �� ������ ��
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount); //�� ���� ��
		
		request.setAttribute("searchType", searchType);
		request.setAttribute("search", search);
		
		//�ش� �������� �� ����� ���� �ִ� ����Ʈ
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("limit", limit);
		ActionForward forward =new ActionForward();
		forward.setRedirect(false);
		
		//�� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Bbs_bk/bk_list.jsp");
		return forward; //BoardFontController.java�� ���ϵ˴ϴ�.
		
	}else {//�����轺�� �Ѿ�°��
		System.out.println("state=ajax");
		
		//������ request�� ��Ҵ� ���� JsonObject�� ����ϴ�.
		JsonObject object =new JsonObject();
		object.addProperty("page", page);//{"page": ���� apge�� ��} �������� ����
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
