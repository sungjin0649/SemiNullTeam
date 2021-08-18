package oky.frcomment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FrCommentDAO {
	private DataSource ds;
	
	// �����ڿ��� JNDI ���ҽ��� �����Ͽ� Connection ��ü�� ���ɴϴ�.
	public FrCommentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}
		
	//����ۼ�
	public int fcmcommentsInsert(FrComment co) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			con = ds.getConnection();
			
			String sql = "insert into BBS_FCM "
					 +   " values (fcm_seq.nextval, ?, ? , sysdate , ?, ?, ?, fcm_seq.nextval)";
			
			//���ο� ���� ����ϴ� �κ��Դϴ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getId());
			pstmt.setString(2, co.getFcm_content());
			pstmt.setInt(3, co.getFcm_comment_board_num());
			pstmt.setInt(4, co.getFcm_comment_re_lev());
			pstmt.setInt(5, co.getFcm_comment_re_seq());
			
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("������ ������ ��� �Ϸ�Ǿ����ϴ�.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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
	
	//��� ���� ���ϱ�
	public int getfcmListCount(int comment_board_num) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int x = 0; 
		String sql = "select count(*)"
				+    " from bbs_fcm where fcm_comment_board_num = ? ";
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, comment_board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getfcmListCount() ����: " + ex);
		} finally {
			if (rs !=null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
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
		return x;
	}// getfcmListCount end
	
	//��� ����Ʈ ���ϱ�
	public JsonArray getfcmCommentList(int comment_board_num, int state) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		String sort="asc";
		if(state==2) {
			sort ="desc";
		}
		
		String sql= "select fcm_no, bbs_fcm.fcm_id, fcm_content, fcm_reg_date, fcm_comment_re_lev, "
				+ "  fcm_comment_re_seq, "
				+ "  fcm_comment_re_ref, member.USER_FILE "
				+ "  from bbs_fcm join member "
				+ "  on bbs_fcm.fcm_id = member.USER_ID "
				+ "  where fcm_comment_board_num = ? "
				+ "  order by fcm_comment_re_ref " + sort + ","
				+ "  fcm_comment_re_seq asc";
		
		JsonArray array = new JsonArray();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_board_num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("fcm_no", rs.getInt(1));
				object.addProperty("fcm_id", rs.getString(2));
				object.addProperty("fcm_content", rs.getString(3));
				object.addProperty("fcm_reg_date", rs.getString(4));
				object.addProperty("fcm_comment_re_lev", rs.getInt(5));
				object.addProperty("fcm_comment_re_seq", rs.getInt(6));
				object.addProperty("fcm_comment_re_ref", rs.getInt(7));
				object.addProperty("fcm_memberfile", rs.getString(8));
				array.add(object);				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getfcmCommentList() ����: " + ex);
		} finally {
			if( rs !=null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
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
			return array;	
 }//getfcmCommentList end

	
	
	//��� ����
	public int fcmcommentsUpdate(FrComment co) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			String sql = "update bbs_fcm set fcm_content=? "
					   + "where fcm_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getFcm_content());
			pstmt.setInt(2, co.getFcm_no());
			
			result = pstmt.executeUpdate();
			if (result ==1) {
				System.out.println("������ ���� �Ǿ����ϴ�.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fcmcommentsUpdate() ����: " + e);
		} finally {
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
	
	//���� �ۼ�
	public int fcmcommentsReply(FrComment c) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			StringBuilder update_sql = new StringBuilder();
			update_sql.append("update bbs_fcm ");
			update_sql.append("set    fcm_comment_re_seq=fcm_comment_re_seq +1 ");
			update_sql.append("where  fcm_comment_re_ref=? ");
			update_sql.append("and    fcm_comment_re_seq> ? ");
			pstmt = con.prepareStatement(update_sql.toString());
			pstmt.setInt(1, c.getFcm_comment_re_ref());
			pstmt.setInt(2, c.getFcm_comment_re_seq());
			pstmt.executeUpdate();
			pstmt.close();
			
		
			String sql = "insert into bbs_fcm "
					  + " values(fcm_seq.nextval, ?, ?, sysdate, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getFcm_content());
			pstmt.setInt(3, c.getFcm_comment_board_num());
			pstmt.setInt(4, c.getFcm_comment_re_lev()+1);
			pstmt.setInt(5, c.getFcm_comment_re_seq()+1);
			pstmt.setInt(6, c.getFcm_comment_re_ref());
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("reply ���� �Ϸ�Ǿ����ϴ�.");
				con.commit();
			} else {
				con.rollback();
			}			
		} catch(Exception e){
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	} //fcmcommentsReply end


	public int fcmcommentsDelete(int num) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			
			String sql = "delete bbs_fcm where fcm_no = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 1) 
				System.out.println("������ ���� �Ǿ����ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;

	}//fcmcommentsDelete
	
	

}
