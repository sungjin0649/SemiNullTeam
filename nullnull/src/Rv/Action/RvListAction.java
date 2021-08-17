package Rv.Action;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Rv.db.RvBean;


public class RvListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RvDAO boarddao = new RvDAO();
		List<RvBean> boardlist = new ArrayList<RvBean>();
		
		//�α��� ������ �Ķ���� page�� �����. �׷��� �ʱⰪ�� �ʿ��մϴ�.
		int page = 1; 	//������ page
		int limit = 10;	//�� �������� ������ �Խ��� ����� ��
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ = " + page);
		
		//�߰� 
		if(request.getParameter("limit")!= null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("�Ѿ�� limit = " + limit);
		
		//�� ����Ʈ ���� �޾ƿɴϴ�.
		int listcount = boarddao.getListCount();
		
		//����Ʈ�� �޾ƿɴϴ�.
		boardlist = boarddao.getBoardList(page,limit);
		/*
		 * ����������
= (DB�� ����� �� ����Ʈ�� �� + ������������ �����ִ� ����Ʈ�Ǽ� -1) / ������������ �����ִ� ����Ʈ�� ��
		 * ���� ��� �� ���������� �����ִ� ����Ʈ�� ���� 10���� ��� 
		 * ��1) DB�� ����� �� ����Ʈ�� ���� 0�̸� �� ���������� 0������
		 * ��2) DB�� ����� �� ����Ʈ�� ���� ( 1~10)�̸� �� ���������� 1������
		 * ��3) DB�� ����� �� ����Ʈ�� ���� (11~20)�̸� �� ���������� 2������
		 * ��4) DB�� ����� �� ����Ʈ�� ���� (21~30)�̸� �� ���������� 3������
		 *
		 */
		int maxpage = (listcount + limit -1 )/limit;
		System.out.println("�� ��������=" + maxpage);
		/*
		 * startpage : ���� ������ �׷쿡�� �� ó���� ǥ�õ� ������ ���� �ǹ��մϴ�.
		 * ([1] , [11] , [21] �� ...) ������ �������� 30 ���� ��� 
		   [1][2][3]....[30] ���� �� ǥ���ϱ⿡�� �ʹ� ���� ������ ���� �� ����������
		   10������ �������� �̵��� �� �ְ� ǥ���մϴ�.
		 *  ��) ������ �׷��� �Ʒ��� �������
		       [1][2][3][4][5][6][7][8][9][10]
		 * �������׷��� ���� �������� startpage�� ������  �������� endpage�� ���մϴ�.
		 * 
		 * ���� 1~10 �������� ������ ��Ÿ������ ������ �׷��� [1][2][3]...[10]�� ǥ�õǰ�
		 * 11~20 �������� ������ ��Ÿ������ ������ �׷��� [11][12][13]...[20] ���� ǥ�õ˴ϴ�.
		 */
		
		int startpage= ((page-1) / 10 ) *10 +1;
		System.out.println("���� �������� ������ ���� ������ �� = " + startpage);
		
		//endpage: ���� ������ �׷쿡�� ������ ������ ������ �� ([10], [20], [30] ��...)
		int endpage = startpage +10 -1;
		System.out.println("���� �������� ������ ������ ������ �� = " + endpage);
		
		/*
		 * ������ �׷��� ������ ������ ���� �ִ� ������ ���Դϴ�.
		 * ���� ������ ������ �׷��� [21]~[30]�� ���
		 * ����������(startpage=21)�� ������ ������ (endpage=30) ������
		 * �ִ� ������(maxpage)�� 25��� [21]~[25] ������ ǥ�õǵ��� �մϴ�.
		 */
		if (endpage > maxpage)
			endpage = maxpage;
		//�߰�
		String state =request.getParameter("state");
		//�߰�
		if(state ==null) {
			
			System.out.println("state==null");
			request.setAttribute("page", page); //���� ������ �� 
			request.setAttribute("maxpage", maxpage);//�ִ� ������ ��
			
			//���� �������� ǥ���� ù ������ ��
			request.setAttribute("startpage", startpage);
			
			//���� �������� ǥ���� �� ������ �� 
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount);//�� ���� ��
			
			//�ش� �������� �� ����� ���� �ִ� ����Ʈ
			request.setAttribute("boardlist", boardlist);
			request.setAttribute("limit", limit);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			// �� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
			forward.setPath("Board/RvList.jsp");
			return forward;
		}else {
			System.out.println("state==ajax");
			
			//������ request�� ��Ҵ� ���� JsonObject�� ����ϴ�.
			JsonObject object = new JsonObject();
			object.addProperty("page",page);//{"page":���� page�� ��} �������� ����
			object.addProperty("maxpage",maxpage);
			object.addProperty("startpage",startpage);
			object.addProperty("endpage",endpage);
			object.addProperty("listcount",listcount);
			object.addProperty("limit",limit);
			
			//JsonObject�� List ������ ���� �� �ִ� addProperty() �������� �ʽ��ϴ�.
			//void com.goole.gson.JsonObject.add(String property, JsonElement value)
			//�޼��带 ���ؼ� �����մϴ�. 
			//List������ JsonElement�� �ٲپ� �־�� object�� ������ �� �ֽ��ϴ�.
			// List => JsonElement
			JsonElement je = new Gson().toJsonTree(boardlist);
			System.out.println("boardlist=" + je.toString());
			object.add("boardlist", je);
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(object.toString());
			System.out.println(object.toString());
			return null;
		}// if end
			
	}//execute end

}//class end
