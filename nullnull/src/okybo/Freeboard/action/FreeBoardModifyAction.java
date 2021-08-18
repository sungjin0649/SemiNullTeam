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
		
		//WebContent아래에 꼭 폴더 생성하세요
		String saveFolder="frboardupload";
		
		int fileSize=5*1024*1024; //업로드할 파일의 최대 사이즈 입니다. 5MB
		
		//실제 저장 경로를 지정합니다.
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
			System.out.println("게시글 글번호는 :" + num);
			System.out.println("게시글 비밀번호는 : " + pass);
			// 글쓴이 인지 확인하기 위해 저장된 비밀번호와 입력한 비밀번호를 비교합니다.
			boolean usercheck = boarddao.isfrboardWriter(num, pass);
			
			System.out.println("원 글쓴이 인가요?:" + usercheck);
			// 비밀번호가 다른 경우
			if (usercheck ==false) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			// 비밀번호가 일치하는 경우 수정 내용을 설정합니다.
			// BoardBean 객체에 글 등록 폼에서 입력 받은 정보들을 저장합니다.
			boarddata.setFr_no(num);
			boarddata.setFr_subject(multi.getParameter("fr_subject"));
			boarddata.setFr_content(multi.getParameter("fr_content"));
			
			//카테고리 형변환 
			String a = multi.getParameter("fr_csfc");
			int fr_csfc =Integer.parseInt(a);
			
			
			boarddata.setFr_csfc(fr_csfc);
			
			String check = multi.getParameter("check");
			System.out.println("check=" + check);
			if (check != null) { // 기존 파일 그대로 사용하는 경우나 파일 제거한 경우입니다.
				boarddata.setFr_file(check);				
			} else { // 파일 첨부로 파일 선택한 경우
				// 업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
				String filename = multi.getFilesystemName("fr_file");
				boarddata.setFr_file(filename);
			}
			
			// DAO에서 수정 메서드 호출하여 수정합니다.
			result =boarddao.frboardModify(boarddata);
			
			// 수정에 실패한 경우
			if (result == false) {
				System.out.println("게시판 수정 실패");
				forward.setRedirect(false);
				request.setAttribute("message", "게시판 수정이 되지 않습니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			// 수정 성공의 경우
			System.out.println("게시판 수정 완료");			
			forward.setRedirect(true);
			// 수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("FreeboardDetailAction.okybo?num=" + boarddata.getFr_no());
			return forward;
		}catch(IOException e) {
			e.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 중 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}//catch end	
	}
}
