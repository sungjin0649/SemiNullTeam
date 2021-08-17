package oky.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import oky.member.db.Member;
import oky.member.db.MemberDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;



public class MemberUpdateProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String realFolder = "";
		
		// WebContent�Ʒ��� �� ���� �����ϼ���
		String saveFolder = "memberupload";
		
		int fileSize = 5 * 1024 * 1024; // ���ε��� ������ �ִ� ������ �Դϴ�. 5MB
		
		// ���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		try {
			MultipartRequest multi = null;			
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					 new DefaultFileRenamePolicy());
				
		String id = multi.getParameter("id");
		//String pass = request.getParameter("pass");
		String name = multi.getParameter("name");
		int birth = Integer.parseInt(multi.getParameter("birth"));
		String phone = multi.getParameter("phone");
		String email = multi.getParameter("email");
		
		Member m = new Member();
		m.setBirth(birth);    m.setEmail(email);    m.setPhone(phone);
		m.setId(id);      m.setName(name);      //m.setPassword(pass);
		
		String check = multi.getParameter("check");
		System.out.println("check=" + check);
		if(check != null) {
			m.setMemberfile(check);
		} else {
			String memberfile = multi.getFilesystemName("memberfile");
			m.setMemberfile(memberfile);
		}
		
		MemberDAO mdao = new MemberDAO();		
		int result = mdao.update(m);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.println("<script>");
		
		if (result == 1) { //������ �� ���
			out.println("alert('���������� ���� �Ǿ����ϴ�.');");
			out.println("location.href='http://localhost:8088/nullnull/';");
		} else {
			out.println("alert('ȸ�� ���� ������ �����߽��ϴ�.');");
			// ���ΰ�ħ�Ǿ� ������ �Է��� �����Ͱ� ��Ÿ���� �ʽ��ϴ�.
			// out.println("location.href='join.net';");
			out.println("history.back()");// ��й�ȣ�� ������ �ٸ� �����ʹ� �����Ǿ� �ֽ��ϴ�			
		}
		out.println("</script>");
		out.close();
		return null;
	} catch (IOException ex) {
		ActionForward forward = new ActionForward();
		forward.setPath("error/error.jsp");
		request.setAttribute("message", "������ ���� ���ε� �����Դϴ�.");
		forward.setRedirect(false);
		return forward;
	} // catch end
  }//execute end
}
