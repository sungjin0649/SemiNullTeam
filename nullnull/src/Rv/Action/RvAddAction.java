package Rv.Action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Rv.db.RvBean;


public class RvAddAction implements Action {

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
			
			//BoardBean ��ü�� �� ��� ������ �Է� ���� �������� �����մϴ�.
			boarddata.setUSER_ID(multi.getParameter("USER_ID"));
			boarddata.setRV_PASS(multi.getParameter("RV_PASS"));
			boarddata.setRV_SUBJECT(multi.getParameter("RV_SUBJECT"));
			boarddata.setRV_CONTENT(multi.getParameter("RV_CONTENT"));
			
			//�ý��� �� ���ε�� ���� ���ϸ��� ��� �ɴϴ�.
			String filename = multi.getFilesystemName("RV_FILE");
			boarddata.setRV_FILE(filename);
			
			//�� ��� ó���� ���� DAO�� boardInsert()�޼��带 ȣ���մϴ�.
			//�� ��� ������ �Է��� ������ ����Ǿ� �ִ� boarddata��ü�� �����մϴ�.
			result=boarddao.boardInsert(boarddata);
			
			//�� ��Ͽ� ������ ��� false�� ��ȯ�մϴ�.
			if(result == false) {
				System.out.println("�Խ��� ��� ���� ");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "�Խ��� ��� �����Դϴ�.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("�Խ��� ��� �Ϸ�");
			
			//�� ����� �Ϸ�Ǹ� �� ����� �����ֱ� ���� "BoardList.bo"�� �̵��մϴ�.
			//Redirect���θ� true�� �����մϴ�.
			forward.setRedirect(true);
			forward.setPath("RvList.rv");//�̵��� ��θ� �����մϴ�.
			return forward;
			
		}catch(IOException ex) {
			ex.printStackTrace();
			System.out.println("ioexcption");
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�Խ��� ���ε� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}//catch end
	}//execute end

}
