package net.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.NTBean;
import net.board.db.NTDAO;

public class NTModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NTDAO boarddao = new NTDAO();
		NTBean boarddata = new NTBean();
		ActionForward forward = new ActionForward();
		String realFolder="";
		
		//WebContent�Ʒ��� �� ���� �����ϼ���
		String saveFolder="NTupload";
		
		int fileSize = 5 * 1024 * 1024; //���ε� �� ���� �ִ� ������ 5MB
		
		//���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		boolean result = false;
		try {
			MultipartRequest multi
			= new MultipartRequest(request, realFolder, fileSize, "utf-8",
			  new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("nt_no"));
			String pass = multi.getParameter("nt_pass");
			System.out.println("nt_pass"+ pass);
			//�۾������� Ȯ���ϱ� ���� ����� ��й�ȣ�� �Է��� ��й�ȣ�� ���մϴ�.
			boolean usercheck = boarddao.isNTWriter(num, pass);
			System.out.println("usercheck=" + usercheck);
			//��й�ȣ�� �ٸ� ���
			if(usercheck == false) {
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
			//NTBean ��ü�� �� ��� ������ �Է� ���� �������� �����մϴ�.
			boarddata.setNt_no(num);
			boarddata.setNt_subject(multi.getParameter("nt_subject"));
			boarddata.setNt_content(multi.getParameter("nt_content"));
			
			String check = multi.getParameter("check");
			System.out.println("check= " + check);
			if(check != null) {//���� ���� �״�� ����� ��쳪 ���� ������ ����Դϴ�.
				boarddata.setNt_file(check);
			}else {//���� ÷�η� ���� ������ ���
				//���ε� �� ������ �ý��� �� ���ε� �� ���� ���ϸ��� ���ɴϴ�.
				String filename = multi.getFilesystemName("nt_file");
				boarddata.setNt_file(filename);
			}
			//DAO���� ���� �޼��� ȣ���Ͽ� �����մϴ�.
			result = boarddao.boardModify(boarddata);
			
			//������ ������ ���
			if(result==false) {
				System.out.println("�������� ���� ����");
				forward.setRedirect(false);
				request.setAttribute("message","�������� ������ ���� �ʾҽ��ϴ�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			//���� ������ ���
			System.out.println("�Խ��� ���� �Ϸ�");
			
			forward.setRedirect(true);
			//������ �� ������ �����ֱ� ���� �� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
			forward.setPath("NTDetailAction.bo?num=" + boarddata.getNt_no());//�̵��� ��� ����
			return forward;
			
		}catch(IOException e) {
			e.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�������� ���ε� �� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}//catch end
	}//execute end
}
