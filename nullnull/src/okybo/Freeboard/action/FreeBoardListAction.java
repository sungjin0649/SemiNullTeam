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
		
		//�ʱ� ���帮��Ʈ �� ����
		int page = 1; // ������ page
		int limit = 10; // �� �������� ������ �Խ��� ����
		
		
		String searchType ="";	//SEARCH 08-14 �˻���� �߰� 
		String search="";       //SEARCH 08-14 �˻���� �߰� 
		
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
			boardlist = boarddao.getfrBoardList(page, limit);
			listcount= boarddao.getfrListCount();
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
		
		
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	    
	    // �� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
	    forward.setPath("Bbs_fr/FreeboardList.jsp");
	    return forward;
	}

}
