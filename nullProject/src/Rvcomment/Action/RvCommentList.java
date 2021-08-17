package Rvcomment.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Rv.Action.Action;
import Rv.Action.ActionForward;
import Rvcomment.db.CommentDAO;



public class RvCommentList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDAO dao = new CommentDAO();
		
		int comment_board_num = 
					Integer.parseInt(request.getParameter("comment_board_num"));
		System.out.println(comment_board_num);
		int state = Integer.parseInt(request.getParameter("state"));
		int listcount = dao.getListCount(comment_board_num);
		
		JsonObject object = new JsonObject();
		JsonArray jarray = dao.getCommentList(comment_board_num,state);
		object.addProperty("listcount", listcount);
		JsonElement je = new Gson().toJsonTree(jarray);
		object.add("boardlist", je);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(object.toString());
		System.out.println(object.toString());
		return null;
	}
}
