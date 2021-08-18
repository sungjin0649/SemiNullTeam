package net.board_bk.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.comm_bk.db.comm_bkDAO;


public class Bbs_bkCommentList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		comm_bkDAO dao = new comm_bkDAO();
		

		
		int BCM_COMMENT_BOARD_NUM =
				Integer.parseInt(request.getParameter("BCM_COMMENT_BOARD_NUM"));
		System.out.println(BCM_COMMENT_BOARD_NUM);
		int state = Integer.parseInt(request.getParameter("state"));
		int listcount = dao.BBS_BCMListCount(BCM_COMMENT_BOARD_NUM);
		
		JsonObject object =new JsonObject();
		JsonArray jarray = dao.BBS_BCMList(BCM_COMMENT_BOARD_NUM, state);
		object.addProperty("listcount", listcount);
		JsonElement je = new Gson().toJsonTree(jarray);
		object.add("boardlist", je); //나올부분
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(object.toString());
		System.out.println(object.toString());
		//response.getWriter().append(object.toString())
		return null;
	}

}
