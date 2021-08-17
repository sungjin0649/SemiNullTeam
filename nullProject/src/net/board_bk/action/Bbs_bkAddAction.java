package net.board_bk.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board_bk.db.Bbs_bkDAO;
import net.board_bk.db.Bbs_bk_bean;



public class Bbs_bkAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bbs_bkDAO bbs_bkdao = new Bbs_bkDAO();
		Bbs_bk_bean bbs_bkbean = new Bbs_bk_bean();
		ActionForward forward =new ActionForward();
		
		String realFolder="";
		
		//WebContent �Ʒ��� �� ���� �����ϼ���
		String saveFolder ="bbs_bkupload";
		
		int fileSize=5*1024*1024; //���ε��� ������ �ִ� ������ �Դϴ�. 5MB
		
		//���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		int result=0;
		try {
			MultipartRequest multi
			=new MultipartRequest(request,
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			//BoardBean ��ü�� �� ��� ������ �Է� ���� �������� �����մϴ�.
			bbs_bkbean.setBK_PASS(multi.getParameter("BK_PASS"));
			bbs_bkbean.setBK_CSFC(multi.getParameter("BK_CSFC"));
			System.out.println(multi.getParameter("BK_CSFC"));
			bbs_bkbean.setBK_SUBJECT(multi.getParameter("BK_SUBJECT"));
			bbs_bkbean.setBK_PRICE(Integer.parseInt(multi.getParameter("BK_PRICE")));
			bbs_bkbean.setBK_CONTENT(multi.getParameter("BK_CONTENT"));

			String filename=multi.getFilesystemName("BK_FILE ");
			bbs_bkbean.setBK_FILE(filename);
			
			//�� ��� ó���� ���� DAO�� boardInsert()�޼��带 ȣ���մϴ�.
			//�� ��� ������ �Է��� ������ ����Ǿ� �ִ� boarddata��ü�� �����մϴ�.
			result= bbs_bkdao.bkInsert(bbs_bkbean);
			//�� ��Ͽ� ������ ��� false�� ��ȯ�մϴ�.
			if(result != 1) {
				System.out.println("�Խ��� ��� ����");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "�Խ��� ��� �����Դϴ�.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("�Խ��� ��� �Ϸ�");
			
			//�� ����� �Ϸ�Ǹ� �� ����� �����ֱ� ���� "BoardList.bo"�� �̵��մϴ�.
			//Redirect���θ� true�� �����մϴ�.
			forward.setRedirect(true);
			forward.setPath("Bbs_bkList.bk");//�̵��� ��θ� �����մϴ�.
			return forward;
		} catch (IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�Խ��� ���ε� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}//catch end
	}

}
