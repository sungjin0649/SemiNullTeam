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
		
		// WebContent아래에 꼭 폴더 생성하세요
		String saveFolder = "memberupload";
		
		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈 입니다. 5MB
		
		// 실제 저장 경로를 지정합니다.
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
		
		if (result == 1) { //삽입이 된 경우
			out.println("alert('정상적으로 수정 되었습니다.');");
			out.println("location.href='http://localhost:8088/nullnull/';");
		} else {
			out.println("alert('회원 정보 수정에 실패했습니다.');");
			// 새로고침되어 이전에 입력한 데이터가 나타나지 않습니다.
			// out.println("location.href='join.net';");
			out.println("history.back()");// 비밀번호를 제외한 다른 데이터는 유지되어 있습니다			
		}
		out.println("</script>");
		out.close();
		return null;
	} catch (IOException ex) {
		ActionForward forward = new ActionForward();
		forward.setPath("error/error.jsp");
		request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
		forward.setRedirect(false);
		return forward;
	} // catch end
  }//execute end
}
