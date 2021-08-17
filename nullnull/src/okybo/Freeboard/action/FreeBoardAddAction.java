package okybo.Freeboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;
import okybo.Freeboard.db.FreeboardBean;
import okybo.Freeboard.db.FreeboardDAO;
public class FreeBoardAddAction implements Action {

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
			//ī�װ� ����ȯ
			String a= (multi.getParameter("fr_csfc"));
			int fr_csfc = Integer.parseInt(a);
			
			//������ �� �Ѿ�Գ� �ַܼ� �׽�Ʈ
			System.out.println(multi.getParameter("fr_csfc"));
			System.out.println(multi.getParameter("fr_subject"));
			System.out.println(multi.getParameter("id"));
			System.out.println(multi.getParameter("fr_pass"));
			System.out.println(multi.getParameter("fr_content"));
			System.out.println(multi.getFilesystemName("fr_file"));
			
			
			
			boarddata.setFr_csfc(fr_csfc); 	//ī�װ�		
			boarddata.setFr_subject(multi.getParameter("fr_subject"));  //����
			boarddata.setId(multi.getParameter("id")); //�۾���
			boarddata.setFr_pass(multi.getParameter("fr_pass")); //��й�ȣ
			boarddata.setFr_content(multi.getParameter("fr_content")); //����
			
			//�ý��� �� ���ε�� ���� ���ϸ�
			String fr_file = multi.getFilesystemName("fr_file");
			boarddata.setFr_file(fr_file);
			
			//�� ��� ó�� 
			result = boarddao.frboardInsert(boarddata);
			
			//�� ��Ͽ� ������ ��� false�� ��ȯ�մϴ�.
			if(result==false) {
				System.out.println("�Խ��� ��� ����");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "�Խ��� ��� �����Դϴ�.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("�Խ��� ��� �Ϸ�");
			
			//�� ����� �Ϸ�Ǹ� �� ����� �����ֱ� ���� "BoardList.bo"�� �̵��մϴ�.
			//Redirect ���θ� true�� �����մϴ�.
			forward.setRedirect(true);
			forward.setPath("Freeboard.okybo");//�̵��� ��θ� �����մϴ�.
			return forward;
		}catch(IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�Խ��� ���ε� �����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		}//catch end	
	}//execute end

}
