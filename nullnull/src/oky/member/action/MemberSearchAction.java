package oky.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.member.db.Member;
import oky.member.db.MemberDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class MemberSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		
		int page = 1;
		int limit= 3;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ =" + page);
		
		List<Member> list = null;
		int listcount = 0;
		int index =-1;//search_field�� �������� �ʴ� ������ �ʱ�ȭ
		
		String search_word="";
		//�޴�-������-ȸ������ Ŭ���� ���(member_list.net)
		//�Ǵ� �޴�-������-ȸ������ Ŭ�� �� ������ Ŭ���� ���
		//(memberList.net?=page=2&search_field=-1&search_word=)
		if (request.getParameter("search_word") == null
				|| request.getParameter("search_word").equals("")){
		     // �� ����Ʈ ���� �޾ƿɴϴ�.
			  listcount = mdao.getListCount();
			  list = mdao.getList(page,limit);		
			  System.out.println(list);
			} else { //�˻��� Ŭ���� ���
				index= Integer.parseInt(request.getParameter("search_field"));
				String[] search_field = new String[] { "USER_ID	", "USER_NAME", "USER_BIRTH", "USER_PHONE" };
				search_word = request.getParameter("search_word");
				listcount = mdao.getListCount(search_field[index], search_word);
				list = mdao.getList(search_field[index], search_word, page, limit);				
			}
			
			int maxpage = (listcount + limit - 1) /limit;
			System.out.println("�� ������ �� =" + maxpage);
			
			int startpage = ((page -1) / 10) * 10 + 1;
			int endpage = startpage + 10 -1;
			System.out.println("���� �������� ������ ������ ������ ��=" + endpage);
			System.out.println("���� �������� ������ ���� ������ ��=" + startpage);
			
			if (endpage > maxpage) endpage = maxpage;
			request.setAttribute("page", page); // ���� ������ ��
			request.setAttribute("maxpage", maxpage); //�ִ� ������ ��
			
			// ���� �������� ǥ���� ù ������ ��
			request.setAttribute("startpage", startpage);
			
			// ���� �������� ǥ���� �� ������ ��
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount); // �� ���� ��
			request.setAttribute("totallist", list);
			request.setAttribute("search_field",index);
			request.setAttribute("search_word", search_word);
			
			forward.setPath("member/memberList.jsp");
			forward.setRedirect(false);
		    return forward;
	}

}
