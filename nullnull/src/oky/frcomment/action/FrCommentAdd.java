package oky.frcomment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oky.frcomment.db.FrComment;
import oky.frcomment.db.FrCommentDAO;
import okybo.Freeboard.action.Action;
import okybo.Freeboard.action.ActionForward;

public class FrCommentAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FrCommentDAO dao = new FrCommentDAO();
		
		FrComment co = new FrComment();
		co.setId(request.getParameter("id"));
		co.setFcm_content(request.getParameter("content"));
		co.setFcm_comment_re_lev(Integer.parseInt(request.getParameter("comment_re_lev")));
		co.setFcm_comment_board_num(Integer.parseInt(request.getParameter("comment_board_num")));
		co.setFcm_comment_re_seq(Integer.parseInt(request.getParameter("comment_re_seq")));
		
		System.out.println("�Ѿ�� ����ۼ��� ���̵�=" + co.getId());
		System.out.println("�Ѿ�� ��۳���=" + co.getFcm_content());
		System.out.println("�Ѿ�� ���lev=" + co.getFcm_comment_re_lev());
		System.out.println("�Ѿ�� ��� ���Խñ� ��ȣ=" + co.getFcm_comment_board_num());
		System.out.println("�Ѿ�� ���seq=" + co.getFcm_comment_re_seq());
		
		int ok = dao.fcmcommentsInsert(co);
		System.out.println(ok);
		response.getWriter().print(ok);
		
		return null;
	}

}
