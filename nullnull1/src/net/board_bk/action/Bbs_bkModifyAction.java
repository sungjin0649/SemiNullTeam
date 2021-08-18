package net.board_bk.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;


public class Bbs_bkModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bbs_bkDAO dao = new Bbs_bkDAO();
		Bbs_bk_bean Bbs_bkdata = new Bbs_bk_bean();
		ActionForward forward =new ActionForward();
		
		String realFolder="";
		
		//WebContent �Ʒ��� �� ���� �����ϼ���
		String saveFolder ="bbs_bkupload";
		int fileSize=5*1024*1024; //���ε��� ������ �ִ� ������ �Դϴ�. 5MB
		
		//���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder=" + realFolder);
		boolean result=false;
		try {
			MultipartRequest multi
			=new MultipartRequest(request,
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multi.getParameter("BK_NO"));
			String pass = multi.getParameter("BK_PASS");
			//�۾��� ���� Ȯ���ϱ� ���� ����� ��й�ȣ�� �Է��� ��й�ȣ�� ���մϴ�.
			boolean usercheck = dao.isBbs_bkWriter(num, pass);
			
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
			//BoardBean ��ü�� �� ��� ������ �Է� ���� �������� �����մϴ�.
			
			Bbs_bkdata.setBK_NO(num);
			Bbs_bkdata.setBK_SUBJECT(multi.getParameter("BK_SUBJECT"));
			Bbs_bkdata.setBK_CONTENT(multi.getParameter("BK_CONTENT"));
			Bbs_bkdata.setBK_PRICE(Integer.parseInt(multi.getParameter("BK_PRICE")));
			
			String check = multi.getParameter("check");
			System.out.println("check=" + check);
			if (check != null) {//�������� �״�� ����ϴ� ��쳪 ���� ������ ����Դϴ�.
				Bbs_bkdata.setBK_FILE(check);
			} else { //���� ÷�η� ���� ������ ���
				// ���ε�� ������ �ý��� �� ���ε�� ���� ���ϸ��� ��� �ɴϴ�.
				String filename = multi.getFilesystemName("BK_FILE");
				Bbs_bkdata.setBK_FILE(filename);
			}
			// DAO���� ���� �޼��� ȣ���Ͽ� �����մϴ�.
			result= dao.Bbs_bkModify(Bbs_bkdata);
			
			// ������ ������ ���
			if(result == false) {
				System.out.println("�Խ��� ���� ����");
				forward.setRedirect(false);
				request.setAttribute("message", "�Խ��� ��� �����Դϴ�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			// ���� ������ ���
			System.out.println("�Խ��� ���� �Ϸ�");
			
			forward.setRedirect(true);
			// ������ �� ������ �����ֱ� ���� �� ���� ���� �������� �̵��ϱ����� ��θ� �����մϴ�.
			forward.setPath("Bbs_bkDetail.bk?num=" +Bbs_bkdata.getBK_NO());
			return forward;
		} catch (IOException e) {
			e.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�Խ��� ���ε� �� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}//catch end
	}

}
