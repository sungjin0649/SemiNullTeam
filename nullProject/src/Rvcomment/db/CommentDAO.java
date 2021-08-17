package Rvcomment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;







public class CommentDAO {
	private DataSource ds;
	public CommentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}
	//��� ����ϱ�
	public int commentsInsert(Comment c) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0 ;
		try {
			con = ds.getConnection();
			


			// �������� BOARD_RE_REF �ʵ�� �ڽ��� �۹�ȣ �Դϴ�.
			String sql = "insert into comm " 
					    + " values(  comm_seq.nextval  ,?,?,sysdate," 
			            + "        ?,?,?,comm_seq.nextval)" ;

			// ���ο� ���� ����ϴ� �κ��Դϴ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,c.getId());
			pstmt.setString(2,c.getContent());
			pstmt.setInt(3,c.getComment_board_num());
			pstmt.setInt(4,c.getComment_re_lev());
			pstmt.setInt(5,c.getComment_re_seq());
	
			result = pstmt.executeUpdate();
			if(result == 1 ) {
				System.out.println("������ ���� �Ϸ�Ǿ����ϴ�.");
			}
			
		}catch (Exception  ex) {
			ex.printStackTrace();
		}finally {
			if (pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con !=null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

		}
		return result;
		
	}
	public int getListCount(int BOARD_RE_REF) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int x = 0; 
		String sql = "select count(*) "
				+ "		from comm where comment_board_num = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, BOARD_RE_REF);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch (Exception ex){
			ex.printStackTrace();
			System.out.println("getListCount() ����:" + ex);
		}finally {
			if (pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con !=null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if(rs !=null)
				try {
					rs.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}

		}
		return x ;
	}
	public JsonArray getCommentList(int comment_board_num, int state) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sort = "asc";
		if(state == 2) {
			sort="desc";
		}
		
		String sql = "select num, comm.id , content , reg_date, comment_re_lev, "
				+ "		comment_re_seq, "
				+ "		comment_re_ref, member.memberfile "
				+ "		from comm join member "
				+ "		on comm.id=member.id "
				+ "		where comment_board_num = ? "
				+ "		order by comment_re_ref " + sort + ", "
				+ "		comment_re_seq asc";
		JsonArray array = new JsonArray();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_board_num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("num", rs.getInt(1));
				object.addProperty("id",rs.getString(2));
				object.addProperty("content",rs.getString(3));
				object.addProperty("reg_date",rs.getString(4));
				object.addProperty("comment_re_lev",rs.getInt(5));
				object.addProperty("comment_re_seq",rs.getInt(6));
				object.addProperty("comment_re_ref",rs.getInt(7));
				object.addProperty("memberfile",rs.getString(8));
				array.add(object);
	
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getCommentList() ���� : " + ex);
		}finally {
			if (pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con !=null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if(rs !=null)
				try {
					rs.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}

		}
		return array;
	}
	public int commentsUpdate(Comment co) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0 ;
		
		try {
			con=ds.getConnection();
			String sql = "update comm set content=? "
					+ "		where num= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getContent());
			pstmt.setInt(2, co.getNum());
			
			result = pstmt.executeUpdate();
			if(result == 1)
				System.out.println("������ ���� �Ǿ����ϴ�.");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con !=null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

		}
		return result;
	}
	public int commentsReply(Comment c) {
		Connection con =null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			StringBuilder update_sql = new StringBuilder();
			update_sql.append("update comm ");
			update_sql.append( "set    comment_re_seq=comment_re_seq +1 ");
			update_sql.append("where   comment_re_ref = ? ");
			update_sql.append("and     comment_re_seq > ? ");
			pstmt = con.prepareStatement(update_sql.toString());
			pstmt.setInt(1, c.getComment_re_ref());
			pstmt.setInt(2, c.getComment_re_seq());
			pstmt.executeUpdate();
			pstmt.close();
			
			String sql = "insert into comm  "
					 + "values(com_seq.nextval, ? , ? ,sysdate, ?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getContent());
			pstmt.setInt(3, c.getComment_board_num());
			pstmt.setInt(4, c.getComment_re_lev()+1);
			pstmt.setInt(5, c.getComment_re_seq()+1);
			pstmt.setInt(6, c.getComment_re_ref());
			result = pstmt.executeUpdate();
			if(result ==1) {
				System.out.println("reply ���� �Ϸ�Ǿ����ϴ�.");
				con.commit();
			}else {
				con.rollback();
			}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			if (pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con !=null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

		}
		return result;
		
	}
	public int commentsDelete(int num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		int result =0 ;
		
		try {
			con = ds.getConnection();
			
			String sql ="delete comm where num =?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if(result ==1)
				System.out.println("������ ���� �Ǿ����ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con !=null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

		}
		return result;
	}

	
		



}
