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
		
		//�ʱ� ���帮��Ʈ �� ����
		int page = 1; // ������ page
		int limit = 3; // �� �������� ������ �Խ��� ����
		
		
		String searchType ="";	//SEARCH 08-14 �˻���� �߰� 
		String search="";       //SEARCH 08-14 �˻���� �߰� 

		String category = "5";
		System.out.println("�Ѿ�� cateogry��" + category);
		String view = request.getParameter("view");
		System.out.println("�Ѿ�� view ��" + view); //����  ������ ajax
		
		if(view == null) {
			System.out.println("������ �ΰ�����  �Ѿ��");
		}

		
		if(request.getParameter("page") !=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ =" + page);
		
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("�Ѿ�� limit =" + limit);
		
		
		
		//SEARCH 08-14 �˻� ��� �߰�
		if(request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
		}
		System.out.println("�Ѿ�� searchType = " + searchType);	
		
		
		//SEARCH 08-14 �˻� ����߰�
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		System.out.println("�Ѿ�� search = " + search);		
	
	
		// �� ����Ʈ ���� �޾ƿɴϴ�.
		int listcount;
		
		//SEARCH 08-14 �˻� ����߰�  Ʈ���Ͻ� �׳� �Ϲ� ����Ʈ , false �Ͻ� �Խñ� �˻��ؼ� �ҷ����� ����Ʈ				
		if(searchType=="" && search=="") { //�Ϲݸ���Ʈ
			boardlist = boarddao.getfrBoardList(page, limit, view, category);
			listcount= boarddao.getfrListCount(category);
		}else { //�˻�����ȸ���Ʈ
			boardlist = boarddao.getFrBoardSearchList(page, limit, searchType, search);
			listcount = boarddao.getFrSearchListCount(searchType, search);
		}
		
	
		System.out.println("�� �Խù� ��: " +listcount);		
		System.out.println("�������� ������ �Խñ� ����Ʈ: " +boardlist);
		
		// �� ������ ��
		int maxpage = (listcount + limit -1) /limit;
		System.out.println("�� ������ �� = " + maxpage);
		
		// ���� ������ �׷쿡�� �� ó���� ǥ�õ� ������ 
		int startpage = ((page -1) /10) * 10 + 1;
		System.out.println("���� �������� ������ ���� ������ =" + startpage);
		
		// ���� ������ �׷쿡�� ������ ������ ������
		int endpage = startpage + 10 -1 ;
		
		// �������׷쿡�� ������ ��������  ������ maxpage(10������, 20������...)���� ���� ��� maxpage�� end�������� ����ؾ���
		if (endpage > maxpage)
			endpage = maxpage;
		
		String state = request.getParameter("state");
		System.out.println("�Ѿ�� state ��" + state);
		
		
		
		if (state == null) {
			
		request.setAttribute("page", page); //���� ������ ��
		request.setAttribute("maxpage", maxpage); // �ִ� ������ ��
		System.out.println("���� �������� ������ �ִ� ������ =" + maxpage);
		//���� �������� ǥ���� ù ������
		request.setAttribute("startpage", startpage);
		
	    // ���� �������� ǥ���� �� ������ ��
	    request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount); // �� ���� ��
		
		
		//08-14 ��ġ �˻� ����߰�
		request.setAttribute("searchType", searchType);
		request.setAttribute("search", search);		
		
		// �ش� �������� �� ����� ���� �ִ� ����Ʈ
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("limit", limit);
		
		request.setAttribute("category", category); //ī�װ� �Ѿ�� �з����ý� ī�װ� �Ѿ
		
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	    
	    // �� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
	    forward.setPath("Bbs_fr/FreeboardList.jsp");
	    return forward;
	    
	    
	} else {
		System.out.println("state=ajax");
		
		//������ request�� ��Ҵ� ���� JsonObject�� ����ϴ�.
		JsonObject object = new JsonObject();
		object.addProperty("page" ,page);//{"page": ���� page�� ��} �������� ����
		object.addProperty("maxpage", maxpage);
		object.addProperty("startpage", startpage);
		object.addProperty("endpage", endpage);
		object.addProperty("listcount", listcount);
		object.addProperty("limit", limit);
		object.addProperty("view", view);
		object.addProperty("category", category);
		//JsonObject�� List ������ ���� �� �ִ� addProperty() �������� �ʽ��ϴ�.
		//void com.google.gson.JsonObject.add(String property, JsonElemet value)
		//�޼��带 ���ؼ� �����մϴ�.
		//List������ JsonElement�� �ٲپ� �־�� object�� ������ �� �ֽ��ϴ�.
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
