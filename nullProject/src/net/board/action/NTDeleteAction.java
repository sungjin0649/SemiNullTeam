package net.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.NTDAO;

public class NTDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result = false;
		boolean usercheck = false;
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		NTDAO dao =new NTDAO();
		//�� ���� ����� ��û�� ����ڰ� �ۼ��� ��������� �Ǵ��ϱ� ����
		//�Է��� ��й�ȣ�� ����� ��й�ȣ�� ���Ͽ� ��ġ�ϸ� �����մϴ�.
		usercheck=dao.isNTWriter(num, request.getParameter("nt_pass"));
		
		//��й�ȣ ��ġ���� �ʴ� ���
		if(usercheck==false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��й�ȣ�� �ٸ��ϴ�.'");
			out.println("history.back();");
			out.println("</script>");
			return null;
		}
		//��й�ȣ ��ġ�ϴ� ��� ���� ó���մϴ�.
		result=dao.NTDelete(num);
		
		//���� ó�� ������ ���
		if(result==false) {
			System.out.println("�Խ��� ���� ����");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message","�����͸� �������� ���߽��ϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		//���� ó�� ������ ��� - �� ��� ���� ��û�� �����ϴ� �κ��Դϴ�.
		System.out.println("�Խ��� ���� ����");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('���� �Ǿ����ϴ�.');");
		out.println("location.href='NTList.bo';");
		out.println("</script>");
		out.close();
		return null;
	}

}
