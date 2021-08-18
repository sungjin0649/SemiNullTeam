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
		
		//WebContent 아래에 꼭 폴더 생성하세요
		String saveFolder ="bbs_bkupload";
		
		int fileSize=5*1024*1024; //업로드할 파일의 최대 사이즈 입니다. 5MB
		
		//실제 저장 경로를 지정합니다.
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
			
			//BoardBean 객체에 글 등록 폼에서 입력 받은 정보들을 저장합니다.
			bbs_bkbean.setBK_PASS(multi.getParameter("BK_PASS"));
			bbs_bkbean.setBK_CSFC(multi.getParameter("BK_CSFC"));
			System.out.println(multi.getParameter("BK_CSFC"));
			bbs_bkbean.setBK_SUBJECT(multi.getParameter("BK_SUBJECT"));
			bbs_bkbean.setBK_PRICE(Integer.parseInt(multi.getParameter("BK_PRICE")));
			bbs_bkbean.setBK_CONTENT(multi.getParameter("BK_CONTENT"));

			String filename=multi.getFilesystemName("BK_FILE ");
			bbs_bkbean.setBK_FILE(filename);
			
			//글 등록 처리를 위해 DAO의 boardInsert()메서드를 호출합니다.
			//글 등록 폼에서 입력한 정보가 저장되어 있는 boarddata객체를 전달합니다.
			result= bbs_bkdao.bkInsert(bbs_bkbean);
			//글 등록에 실패할 경우 false를 반환합니다.
			if(result != 1) {
				System.out.println("게시판 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시판 등록 완료");
			
			//글 등록이 완료되면 글 목록을 보여주기 위해 "BoardList.bo"로 이동합니다.
			//Redirect여부를 true로 설정합니다.
			forward.setRedirect(true);
			forward.setPath("Bbs_bkList.bk");//이동할 경로를 지정합니다.
			return forward;
		} catch (IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}//catch end
	}

}
