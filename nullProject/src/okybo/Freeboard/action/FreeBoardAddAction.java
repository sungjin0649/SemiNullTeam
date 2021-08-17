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
			//카테고리 형변환
			String a= (multi.getParameter("fr_csfc"));
			int fr_csfc = Integer.parseInt(a);
			
			//값들이 잘 넘어왔나 콘솔로 테스트
			System.out.println(multi.getParameter("fr_csfc"));
			System.out.println(multi.getParameter("fr_subject"));
			System.out.println(multi.getParameter("id"));
			System.out.println(multi.getParameter("fr_pass"));
			System.out.println(multi.getParameter("fr_content"));
			System.out.println(multi.getFilesystemName("fr_file"));
			
			
			
			boarddata.setFr_csfc(fr_csfc); 	//카테고리		
			boarddata.setFr_subject(multi.getParameter("fr_subject"));  //제목
			boarddata.setId(multi.getParameter("id")); //글쓴이
			boarddata.setFr_pass(multi.getParameter("fr_pass")); //비밀번호
			boarddata.setFr_content(multi.getParameter("fr_content")); //내용
			
			//시스템 상 업로드된 실제 파일명
			String fr_file = multi.getFilesystemName("fr_file");
			boarddata.setFr_file(fr_file);
			
			//글 등록 처리 
			result = boarddao.frboardInsert(boarddata);
			
			//글 등록에 실패할 경우 false를 반환합니다.
			if(result==false) {
				System.out.println("게시판 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시판 등록 완료");
			
			//글 등록이 완료되면 글 목록을 보여주기 위해 "BoardList.bo"로 이동합니다.
			//Redirect 여부를 true로 설정합니다.
			forward.setRedirect(true);
			forward.setPath("Freeboard.okybo");//이동할 경로를 지정합니다.
			return forward;
		}catch(IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}//catch end	
	}//execute end

}
