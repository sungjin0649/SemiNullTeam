package Rv.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Rv.db.RvBean;



public class RvModifyAction implements Action {

public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RvDAO boarddao = new RvDAO();
		RvBean boarddata = new RvBean();
		ActionForward forward = new ActionForward();
		String realFolder="";
		
		//WebContent �Ʒ��� �� ���� �����ϼ���
		String saveFolder="boardupload";
		
		int fileSize=5*1024*1024;	//���ε��� ������ �ִ� ������ �Դϴ�.5MB
		
		//���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder=" + realFolder);
		boolean result =false;
		try {
			MultipartRequest multi
			= new MultipartRequest(request,
						realFolder,
						fileSize,
						"utf-8",
						new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("RV_NO"));
			String pass = multi.getParameter("RV_PASS");
			
			//�۾��� ���� Ȯ���ϱ� ���� ����� ��й�ȣ�� �Է��� ��й�ȣ�� ���մϴ�.
			boolean usercheck = boarddao.isBoardWriter(num,pass);
			
			//��й�ȣ�� �ٸ���� 
			if(usercheck ==false) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('��й�ȣ�� �ٸ��ϴ�.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			//��й�ȣ�� ��ġ�ϴ� ��� ���� ������ �����մϴ�.
			//BoardBean ��ü�� �� ��� ������ �Է� ���� �������� �����մϴ�.
			boarddata.setRV_NO(num);
			boarddata.setRV_SUBJECT(multi.getParameter("RV_SUBJECT"));
			boarddata.setRV_CONTENT(multi.getParameter("RV_CONTENT"));
			
			String check =multi.getParameter("check");
			System.out.println("check= " + check);
			if(check!=null) {	//���� ���� �״�� ����ϴ� ��쳪 ���� ������ ����Դϴ�.
				boarddata.setRV_FILE(check);
			}else {	//���� ÷�η� ���ϼ����� ��� 
				// ���ε�� ������ �ý��� �� ���ε�� ���� ���ϸ��� ���ɴϴ�.
				String filename = multi.getFilesystemName("RV_FILE");
				boarddata.setRV_FILE(filename);
				
			}
			
			// DAO���� ���� �޼��� ȣ���Ͽ� �����մϴ�.
			result = boarddao.boardModify(boarddata);
			
			//������ ������ ��� 
			if(result == false) {
				System.out.println("�Խ��� ���� ����");
				forward.setRedirect(false);
				request.setAttribute("message", "�Խ��� ������ ���� �ʾҽ��ϴ�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			
			//���� ������ ��� 
			System.out.println("�Խ��� ���� �Ϸ�");
			
			forward.setRedirect(true);
			// ������ �� ������ �����ֱ� ���� �� ���� ���� �������� �̵��ϱ����� ��θ� �����մϴ�.
			forward.setPath("RvDetailAction.rv?num=" + boarddata.getRV_NO());
			return forward;
			

		}catch(IOException ex) {
			ex.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�Խ��� ���ε� �� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}//catch end
	}//execute end

}
