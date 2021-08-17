package okybo.Freeboard.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;


public class FreeBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FreeboardDAO  boarddao    = new FreeboardDAO();
		FreeboardBean boarddata   = new FreeboardBean();
		ActionForward forward = new ActionForward();		
		String realFolder="";
		
		//WebContent�Ʒ��� �� ���� �����ϼ���
		String saveFolder="frboardupload";
		
		int fileSize=5*1024*1024; //���ε��� ������ �ִ� ������ �Դϴ�. 5MB
		
		//���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		boolean result= false;
		try {
			MultipartRequest multi
			=new MultipartRequest(request,
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("fr_no"));
			String pass = multi.getParameter("fr_pass");
			System.out.println("�Խñ� �۹�ȣ�� :" + num);
			System.out.println("�Խñ� ��й�ȣ�� : " + pass);
			// �۾��� ���� Ȯ���ϱ� ���� ����� ��й�ȣ�� �Է��� ��й�ȣ�� ���մϴ�.
			boolean usercheck = boarddao.isfrboardWriter(num, pass);
			
			System.out.println("�� �۾��� �ΰ���?:" + usercheck);
			// ��й�ȣ�� �ٸ� ���
			if (usercheck ==false) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('��й�ȣ�� �ٸ��ϴ�.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			// ��й�ȣ�� ��ġ�ϴ� ��� ���� ������ �����մϴ�.
			// BoardBean ��ü�� �� ��� ������ �Է� ���� �������� �����մϴ�.
			boarddata.setFr_no(num);
			boarddata.setFr_subject(multi.getParameter("fr_subject"));
			boarddata.setFr_content(multi.getParameter("fr_content"));
			
			//ī�װ� ����ȯ 
			String a = multi.getParameter("fr_csfc");
			int fr_csfc =Integer.parseInt(a);
			
			
			boarddata.setFr_csfc(fr_csfc);
			
			String check = multi.getParameter("check");
			System.out.println("check=" + check);
			if (check != null) { // ���� ���� �״�� ����ϴ� ��쳪 ���� ������ ����Դϴ�.
				boarddata.setFr_file(check);				
			} else { // ���� ÷�η� ���� ������ ���
				// ���ε�� ������ �ý��� �� ���ε�� ���� ���ϸ��� ��� �ɴϴ�.
				String filename = multi.getFilesystemName("fr_file");
				boarddata.setFr_file(filename);
			}
			
			// DAO���� ���� �޼��� ȣ���Ͽ� �����մϴ�.
			result =boarddao.frboardModify(boarddata);
			
			// ������ ������ ���
			if (result == false) {
				System.out.println("�Խ��� ���� ����");
				forward.setRedirect(false);
				request.setAttribute("message", "�Խ��� ������ ���� �ʽ��ϴ�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			// ���� ������ ���
			System.out.println("�Խ��� ���� �Ϸ�");			
			forward.setRedirect(true);
			// ������ �� ������ �����ֱ� ���� �� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
			forward.setPath("FreeboardDetailAction.okybo?num=" + boarddata.getFr_no());
			return forward;
		}catch(IOException e) {
			e.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�Խ��� ���ε� �� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}//catch end	
	}
}
