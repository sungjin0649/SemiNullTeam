package net.comm_bk.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class comm_bkDAO {
private DataSource ds;
	
	//�����ڿ��� JNDI ���ҽ��� �����Ͽ� Connection ��ü�� ���ɴϴ�.
	public comm_bkDAO() {
		try {
			Context init = new InitialContext();
			ds=(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : "+ ex);
			return;
		}
	}//comm_bkDAO end

	public int BBS_BCMListCount(int BCM_COMMENT_BOARD_NUM) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) "
						+ "from BBS_BCM where BCM_COMMENT_BOARD_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BCM_COMMENT_BOARD_NUM);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("BBS_BCMListCount() ���� :" +ex);
		}finally {
			if(rs !=null)
				try {
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(pstmt !=null)
				try {
					pstmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(conn !=null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return x;
	}//BBS_BCMListCount end

	public JsonArray BBS_BCMList(int BCM_COMMENT_BOARD_NUM, int state) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sort="asc";
		if(state == 2) {
			sort = "desc";
		}
		
		String comm_list_sql = 
				"select BCM_NO, BBS_BCM.BCM_ID, BCM_CONTENT, BCM_REG_DATE, BCM_COMMENT_RE_REF, "
				+ " BCM_COMMENT_RE_LEV, "
				+ " BCM_COMMENT_RE_SEQ, member.memberfile "
				+ " from BBS_BCM join member "
				+ " on BBS_BCM.BCM_ID=member.id "
				+ " where BCM_COMMENT_BOARD_NUM = ? "
				+ " order by BCM_COMMENT_RE_REF " + sort + ", "
				+ " BCM_COMMENT_RE_SEQ asc";
		JsonArray array = new JsonArray();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(comm_list_sql);
			pstmt.setInt(1, BCM_COMMENT_BOARD_NUM);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("BCM_NO", rs.getInt(1));
				object.addProperty("BCM_ID", rs.getString(2));
				object.addProperty("BCM_CONTENT", rs.getString(3));
				object.addProperty("BCM_REG_DATE", rs.getString(4));
				object.addProperty("BCM_COMMENT_RE_REF", rs.getInt(5));
				object.addProperty("BCM_COMMENT_RE_LEV", rs.getInt(6));
				object.addProperty("BCM_COMMENT_RE_SEQ", rs.getInt(7));
				object.addProperty("memberfile", rs.getString(8));
				array.add(object); //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
			}
			System.out.println(array.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("BBS_BCMList() ���� : " +ex);
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return array;
	}//BBS_BCMList end

	public int BBS_BCMInsert(comm_bk_bean cb) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql="insert into BBS_BCM "
					+ "(BCM_NO, BCM_ID, BCM_CONTENT, BCM_REG_DATE,"
					+ " BCM_COMMENT_BOARD_NUM,	BCM_COMMENT_RE_REF, BCM_COMMENT_RE_LEV,"
					+ " BCM_COMMENT_RE_SEQ)" //(num..���� ������� ©�󵵵�
					+ " values(BCM_seq.NEXTVAL,?,?,SYSDATE,"
					+ "			?,BCM_seq.NEXTVAL,?,"
					+ "			?)";
			
			//���ο� ���� ����ϴ� �κ��Դϴ�.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cb.getBCM_ID());
			pstmt.setString(2, cb.getBCM_CONTENT());
			pstmt.setInt(3, cb.getBCM_COMMENT_BOARD_NUM());
			pstmt.setInt(4, cb.getBCM_COMMENT_RE_LEV());
			pstmt.setInt(5, cb.getBCM_COMMENT_RE_SEQ());
			// ������ ��� BOARD_RE_LEV, BOARD_RE_SEQ �ʵ� ���� 0�Դϴ�.
			
			result = pstmt.executeUpdate();
			if(result ==1 ) 
				System.out.println("������ ������ ��� �Ϸ�Ǿ����ϴ�.");
		} catch (Exception ex) {
			System.out.println("BBS_BCMInsert() ���� : " +ex);
			ex.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}//BBS_BCMInsert end

	public int BBS_BCMUpdate(comm_bk_bean cb) {
		Connection conn =null;
		PreparedStatement pstmt= null;
		int result =0;
		
		try {
			conn = ds.getConnection();
			String sql = "update BBS_BCM set BCM_CONTENT=? "
							+"where BCM_NO = ? ";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, cb.getBCM_CONTENT());
			pstmt.setInt(2, cb.getBCM_NO());
			
			result = pstmt.executeUpdate();
			if(result ==1)
				System.out.println("������ ���� �Ǿ����ϴ�.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	} // BBS_BCMUpdate end

	public int BBS_BCMReply(comm_bk_bean cb) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			StringBuilder update_sql = new StringBuilder();
			update_sql.append("update BBS_BCM ");
			update_sql.append("set BCM_COMMENT_RE_SEQ=BCM_COMMENT_RE_SEQ +1 ");
			update_sql.append("where BCM_COMMENT_RE_REF=? ");
			update_sql.append("and BCM_COMMENT_RE_SEQ>? ");
			pstmt = conn.prepareStatement(update_sql.toString());
			pstmt.setInt(1, cb.getBCM_COMMENT_RE_REF());
			pstmt.setInt(2, cb.getBCM_COMMENT_RE_SEQ());
			pstmt.executeUpdate();
			pstmt.close();
			
			String sql = "insert into BBS_BCM "
					  +" values(BCM_seq.nextval, ?, ?, sysdate, ?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cb.getBCM_ID());
			pstmt.setString(2, cb.getBCM_CONTENT());
			pstmt.setInt(3, cb.getBCM_COMMENT_BOARD_NUM());
			pstmt.setInt(4, cb.getBCM_COMMENT_RE_REF());
			pstmt.setInt(5, cb.getBCM_COMMENT_RE_LEV()+1);
			pstmt.setInt(6, cb.getBCM_COMMENT_RE_SEQ()+1);

			result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("reply ���� �Ϸ�Ǿ����ϴ�.");
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(conn != null)
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}//BBS_BCMReply end

	public boolean BBS_BCMDelete(int bCM_NO) {
		Connection conn = null;
		PreparedStatement pstmt =null, pstmt2=null;
		ResultSet rs =null;
		String select_sql = "select BCM_COMMENT_RE_REF, BCM_COMMENT_RE_LEV, BCM_COMMENT_RE_SEQ "
						+ " from BBS_BCM"
						+ " where BCM_NO=?";
		
		String board_delete_sql = "delete from BBS_BCM"
				+ " 		where	BCM_COMMENT_RE_REF = ?"
				+ "			and		BCM_COMMENT_RE_LEV >=?"
				+ "			and		BCM_COMMENT_RE_SEQ >=?"
				+ "			and		BCM_COMMENT_RE_SEQ <=("
				+ "								nvl((SELECT min(BCM_COMMENT_RE_SEQ )-1"
				+ "									FROM	BBS_BCM	"
				+ "									WHERE	BCM_COMMENT_RE_REF=? "
				+ "									AND		BCM_COMMENT_RE_LEV=?"
				+ "									AND		BCM_COMMENT_RE_SEQ>?) , "
				+ "									(SELECT max(BCM_COMMENT_RE_SEQ) "
				+ "									 FROM BBS_BCM "
				+ "									 WHERE BCM_COMMENT_RE_REF=? ))"
				+ "									)";
		boolean result_check = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, bCM_NO);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt2 = conn.prepareStatement(board_delete_sql);
				pstmt2.setInt(1, rs.getInt("BCM_COMMENT_RE_REF"));
				pstmt2.setInt(2, rs.getInt("BCM_COMMENT_RE_LEV"));
				pstmt2.setInt(3, rs.getInt("BCM_COMMENT_RE_SEQ"));
				pstmt2.setInt(4, rs.getInt("BCM_COMMENT_RE_REF"));
				pstmt2.setInt(5, rs.getInt("BCM_COMMENT_RE_LEV"));
				pstmt2.setInt(6, rs.getInt("BCM_COMMENT_RE_SEQ"));
				pstmt2.setInt(7, rs.getInt("BCM_COMMENT_RE_REF"));
				
				int count=pstmt2.executeUpdate();
				
				if(count >=1)
					result_check=true;//������ �ȵ� ��쿡�� false�� ��ȯ�մϴ�.
			}
		} catch (Exception ex) {
			System.out.println("BBS_BCMDelete() ���� : " + ex);
		}finally {
			if(rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result_check;
	}
	
	
	
}
