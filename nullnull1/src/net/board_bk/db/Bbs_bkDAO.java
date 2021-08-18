package net.board_bk.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Bbs_bkDAO {
private DataSource ds;
	
	//�����ڿ��� JNDI ���ҽ��� �����Ͽ� Connection ��ü�� ���ɴϴ�.
	public Bbs_bkDAO() {
		try {
			Context init = new InitialContext();
			ds=(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : "+ ex);
			return;
		}
	}//Bbs_bkDAO end


	public int bkInsert(Bbs_bk_bean bbs_bkbean) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			
			String sql="insert into BBS_BK "
					+ "(BK_NO, BK_PASS, BK_CSFC, BK_SUBJECT,"
					+ " BK_PRICE, USER_ID, BK_DATE,"
					+ " BK_READ, BK_CONTENT, BK_FILE,"
					+ " BK_RE_REF, BK_RE_LEV, BK_RE_SEQ)"
					+ " values(bk_seq.NEXTVAL,?,?,?,"
					+ "			?,?,SYSDATE,"
					+ "			?,?,?,"
					+ "			bk_seq.NEXTVAL,?,?)";
			
			//���ο� ���� ����ϴ� �κ��Դϴ�.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs_bkbean.getBK_PASS());//��й�ȣ
			//pstmt.setString(2, bbs_bkbean.getBK_CSFC());//�з�
			pstmt.setString(2, "�˴ϴ�.");
			//System.out.println("bbs_csfc = " + bbs_bkbean.getBK_CSFC());
			pstmt.setString(3, bbs_bkbean.getBK_SUBJECT());
			System.out.println("bbs_subject =" + bbs_bkbean.getBK_SUBJECT());
			pstmt.setInt(4, bbs_bkbean.getBK_PRICE());//����
			//pstmt.setString(5, bbs_bkbean.getUSER_ID());//���̵�
			pstmt.setString(5, "admin");
			pstmt.setInt(6, 0);//��ȸ��
			pstmt.setString(7, bbs_bkbean.getBK_CONTENT());//����
			pstmt.setString(8, bbs_bkbean.getBK_FILE());//����
			pstmt.setInt(9, 0);//���� ����
			pstmt.setInt(10, 0);//���� ����
			// ������ ��� BOARD_RE_LEV, BOARD_RE_SEQ �ʵ� ���� 0�Դϴ�.
			
			result = pstmt.executeUpdate();
			if(result ==1 ) {
				System.out.println("������ ������ ��� �Ϸ�Ǿ����ϴ�.");
			}
		} catch (Exception ex) {
			System.out.println("BBS_BKInsert() ���� : " +ex);
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
	}//bkInsert


	public int bbs_bkListCount() {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from BBS_BK";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("bbs_bkListCount() ���� :" +ex);
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
	}


	public List<Bbs_bk_bean> bbs_bkBoardList(int page, int limit) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String board_list_sql = 
				"select * "
				+ "from "
				+ "		(select rownum rnum, BK_NO , BK_PASS,"
				+ " 	BK_CSFC, BK_SUBJECT, BK_PRICE,"
				+ " 	USER_ID, BK_DATE, BK_READ,"
				+ " 	BK_CONTENT, BK_FILE, BK_RE_REF,"
				+ "		BK_RE_LEV, BK_RE_SEQ FROM "
				+ " 							(select * from BBS_BK "
				+ "								order by BK_RE_REF desc, "
				+ "								BK_RE_SEQ asc)"
				+ ") "
				+ " where rnum >= ? and rnum<= ? ";
		
		List<Bbs_bk_bean> list = new ArrayList<Bbs_bk_bean>();
		//�� �������� 10���� ����� ���								1������, 2������, 3������, 4������...
		int startrow = (page -1) *limit +1; //�б� ������ row ��ȣ(1 11 21 31 ...)
		int endrow = startrow + limit -1 ;  //���� ������ row ��ȣ(10 20 30 40 ...)
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs= pstmt.executeQuery();
			
			//DB���� ������ �����͸� VO��ü�� ����ϴ�.
			while (rs.next()) {
				Bbs_bk_bean board = new Bbs_bk_bean();
				board.setBK_NO(rs.getInt(2));
				board.setBK_PASS(rs.getString(3));
				board.setBK_CSFC(rs.getString(4));
				board.setBK_SUBJECT(rs.getString(5));
				board.setBK_PRICE(rs.getInt(6));
				board.setUSER_ID(rs.getString(7));
				board.setBK_DATE(rs.getString(8));
				board.setBK_READ(rs.getInt(9));
				board.setBK_CONTENT(rs.getString(10));
				board.setBK_FILE(rs.getString(11));
				board.setBK_RE_REF(rs.getInt(12));
				board.setBK_RE_LEV(rs.getInt(13));
				board.setBK_RE_SEQ(rs.getInt(14));
				list.add(board); //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("bbs_bkBoardList() ���� : " +ex);
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
		return list;
	}//bbs_bkBoardList end


	public void bk_ReadCountUpdate(int num) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		
		String sql = "update BBS_BK "
				   + "set BK_READ = BK_READ+1 "
				   + "where BK_NO = ?";
		try {
			conn =ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate() ����: " + ex);
		}finally {
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
		
	}//setReadCountUpdate end


	public Bbs_bk_bean bk_Detail(int num) {
		Bbs_bk_bean board =null;
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			conn= ds.getConnection();
			pstmt = conn.prepareStatement("select * from BBS_BK where BK_NO =?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board= new Bbs_bk_bean();
				board.setBK_NO(rs.getInt(1));
				board.setBK_PASS(rs.getString(2));
				board.setBK_CSFC(rs.getString(3));
				board.setBK_SUBJECT(rs.getString(4));
				board.setBK_PRICE(rs.getInt(5));
				board.setUSER_ID(rs.getString(6));
				board.setBK_DATE(rs.getString(7));
				board.setBK_READ(rs.getInt(8));
				board.setBK_CONTENT(rs.getString(9));
				board.setBK_FILE(rs.getString(10));
				board.setBK_RE_REF(rs.getInt(11));
				board.setBK_RE_LEV(rs.getInt(12));
				board.setBK_RE_SEQ(rs.getInt(13));
			}
		} catch (Exception ex) {
			System.out.println("bk_Detail() ���� :" + ex);
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
		return board;
	}//bk_Detail end


	public int Bbs_bkReply(Bbs_bk_bean bbs_bk_data) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		int num=0;
		
		String board_max_sql = "select max(BK_NO)+1 from BBS_BK";
		/*
			�亯�� �� ���� �� �׷� ��ȣ�Դϴ�.
			�亯�� �ް� �Ǹ� �亯 ���� �� ��ȣȭ ���� ���ñ� ��ȣ�� ���� ó���Ǹ鼭 ���� �׷쿡 ���ϰ� �˴ϴ�.
			�� ��Ͽ��� �����ٶ� �ϳ��� �׷����� ������ ��µ˴ϴ�.
		 */
		int re_ref= bbs_bk_data.getBK_RE_REF();
		/*
			����� ���̸� �ǹ��մϴ�.
			������ ���� ����� ��µ� �� �� �� �鿩���� ó���� �ǰ� ��ۿ� ���� ����� �鿩���Ⱑ �� �� ó���ǰ� �մϴ�.
			������ ��쿡�� �� ���� 0�̰� ������ ����� 1, ����� ����� 2�� �˴ϴ�.
		 */
		int re_lev = bbs_bk_data.getBK_RE_LEV();
		
		//���� ���� �� �߿��� �ش� ���� ��µǴ� �����Դϴ�.
		int re_seq = bbs_bk_data.getBK_RE_SEQ();
		
		try {
			conn = ds.getConnection();
			//Ʈ������� �̿��ϱ� ���ؼ� setAutoCommit�� false�� �����մϴ�.
			conn.setAutoCommit(false);
			pstmt= conn.prepareStatement(board_max_sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
			}
			pstmt.close();
			//BAORD_RE_REF, BOARD_RE_SEQ ���� Ȯ���Ͽ� ���� �ۿ� �ٸ� ����� ������
			// �ٸ� ��۵��� BOARD_RE_SEQ���� 1�� ������ŵ�ϴ�.
			// ���� ���� �ٸ� ��ۺ��� �տ� ��µǰ� �ϱ� ���ؼ� �Դϴ�.
			String sql = "update BBS_BK "
						+ "set BK_RE_SEQ = BK_RE_SEQ + 1 "
						+ "where BK_RE_REF = ? "
						+ "and BK_RE_SEQ > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			// ����� �亯 ���� BOARD_RE_LEV, BOARD_RE_SEQ ���� ���� �ۺ��� 1��
			//���� ��ŵ�ϴ�.
			re_seq = re_seq +1;
			re_lev = re_lev +1;
			//BK_PRICE 0������
			sql="insert into BBS_BK "
				+ "(BK_NO,BK_PASS,BK_CSFC,BK_SUBJECT,"
				+ " BK_PRICE, USER_ID, BK_DATE,"
				+ " BK_READ, BK_CONTENT, BK_FILE,"
				+ " BK_RE_REF, BK_RE_LEV, BK_RE_SEQ)"
				+ " values(bk_seq.nextval, ?, ?, ?, "
				+ "		?,?,SYSDATE,"
				+ "		?,?,?,"
				+ "		?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs_bk_data.getBK_PASS());
			pstmt.setString(2, bbs_bk_data.getBK_CSFC());
			pstmt.setString(3, bbs_bk_data.getBK_SUBJECT());
			pstmt.setInt(4, bbs_bk_data.getBK_PRICE());
			pstmt.setString(5, bbs_bk_data.getUSER_ID());
			pstmt.setInt(6, 0); //��ȸ�� �⺻�� 0
			pstmt.setString(7, bbs_bk_data.getBK_CONTENT());
			pstmt.setString(8, ""); //���Ͼ��ε� x
			pstmt.setInt(9, re_ref);//BOARD_RE_SEQ �ʵ�
			pstmt.setInt(10, re_lev);
			pstmt.setInt(11, re_seq);
			
			if(pstmt.executeUpdate()==1) {
			}else {
				conn.rollback();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Bbs_bkReply() ���� : " +ex);
			if(conn !=null) {
				try {
					conn.rollback(); //rollback�մϴ�.
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {
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
					conn.setAutoCommit(true);
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return num;
	}//Bbs_bkReply end


	public boolean isBbs_bkWriter(int num, String pass) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		boolean result = false;
		String board_sql = "select BK_PASS from BBS_BK where BK_NO=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(pass.equals(rs.getString("BK_PASS"))) {
					result = true;
				}
			}
		} catch (SQLException ex) {
			System.out.println("isBbs_bkWriter() ����: " + ex);
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
		return result;
	}


	public boolean Bbs_bkModify(Bbs_bk_bean bbs_bkdata) {//csfc�� ������
		Connection conn = null;
		PreparedStatement pstmt =null;
		String sql = "update BBS_BK "
					+ "set BK_SUBJECT=?, BK_PRICE=?, BK_CONTENT=?, BK_FILE=? "
					+ "where BK_NO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs_bkdata.getBK_SUBJECT());
			pstmt.setInt(2, bbs_bkdata.getBK_PRICE());
			pstmt.setString(3, bbs_bkdata.getBK_CONTENT());
			pstmt.setString(4, bbs_bkdata.getBK_FILE());
			pstmt.setInt(5, bbs_bkdata.getBK_NO());
			int result = pstmt.executeUpdate();
			if(result ==1) {
				System.out.println("���� ������Ʈ");
				return true;
			}
		} catch (Exception ex) {
			System.out.println("Bbs_bkModify() ���� : " +ex);
		}finally {
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
		return false;
	}// Bbs_bkModify end


	public boolean Bbs_bkDelete(int num) {
		Connection conn = null;
		PreparedStatement pstmt =null, pstmt2=null;
		ResultSet rs =null;
		String select_sql = "select BK_RE_REF, BK_RE_LEV, BK_RE_SEQ "
						+ " from BBS_BK"
						+ " where BK_NO=?";
		
		String board_delete_sql = "delete from BBS_BK"
				+ " 		where	BK_RE_REF = ?"
				+ "			and		BK_RE_LEV >=?"
				+ "			and		BK_RE_SEQ >=?"
				+ "			and		BK_RE_SEQ <=("
				+ "								nvl((SELECT min(BK_RE_SEQ )-1"
				+ "									FROM	BBS_BK	"
				+ "									WHERE	BK_RE_REF=? "
				+ "									AND		BK_RE_LEV=?"
				+ "									AND		BK_RE_SEQ>?) , "
				+ "									(SELECT max(BK_RE_SEQ) "
				+ "									 FROM BBS_BK "
				+ "									 WHERE BK_RE_REF=? ))"
				+ "									)";
		boolean result_check = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt2 = conn.prepareStatement(board_delete_sql);
				pstmt2.setInt(1, rs.getInt("BK_RE_REF"));
				pstmt2.setInt(2, rs.getInt("BK_RE_LEV"));
				pstmt2.setInt(3, rs.getInt("BK_RE_SEQ"));
				pstmt2.setInt(4, rs.getInt("BK_RE_REF"));
				pstmt2.setInt(5, rs.getInt("BK_RE_LEV"));
				pstmt2.setInt(6, rs.getInt("BK_RE_SEQ"));
				pstmt2.setInt(7, rs.getInt("BK_RE_REF"));
				
				int count=pstmt2.executeUpdate();
				
				if(count >=1)
					result_check=true;//������ �ȵ� ��쿡�� false�� ��ȯ�մϴ�.
			}
		} catch (Exception ex) {
			System.out.println("Bbs_bkDelete() ���� : " + ex);
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
	}//Bbs_bkDelete end


	public int getcommListCount(int comment_board_num) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) "
						+ "from comm where comment_board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_board_num);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getcommListCount() ���� :" +ex);
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
	}//getcommListCount end
	//�˻�
	public List<Bbs_bk_bean> bbs_bkBoardSearchList(int page, int limit ,String searchType , String search) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_search_list_sql= "";
		String searchs ="";
		
		
		List<Bbs_bk_bean> list = new ArrayList<Bbs_bk_bean>();
		int startrow = (page -1) *limit +1; //�б� ������ row ��ȣ(1 11 21 31 ...)
		int endrow = startrow + limit -1 ;  //���� ������ row ��ȣ(10 20 30 40 ...)

		try {
			conn = ds.getConnection();
			if(searchType.equals("subAcon")) {
				searchs = "BK_SUBJECT like '%"+search+"%' or BK_CONTENT like '%"+search +"%' ";			
			}else if(searchType.equals("subject")) {
				searchs = "BK_SUBJECT like '%"+search+"%' ";
			}else if(searchType.equals("content")) {
				searchs = "BK_CONTENT like '%"+search+"%' ";
			}else if(searchType.equals("writer")) {
				searchs = "USER_ID like '%"+search+"%' ";
			}
			board_search_list_sql=
					"select * "
					+ "from "
					+ "		(select rownum rnum, BK_NO , BK_PASS,"
					+ " 	BK_CSFC, BK_SUBJECT, BK_PRICE,"
					+ " 	USER_ID, BK_DATE, BK_READ,"
					+ " 	BK_CONTENT, BK_FILE, BK_RE_REF,"
					+ "		BK_RE_LEV, BK_RE_SEQ FROM "
					+ " 							(select * from BBS_BK "
					+ "								where "+ searchs 
					+ "								order by BK_RE_REF desc, "
					+ "								BK_RE_SEQ asc)"
					+ ") "
					+ " where rnum >= ? and rnum<= ? ";
			pstmt = conn.prepareStatement(board_search_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs= pstmt.executeQuery();
			
			//DB���� ������ �����͸� VO��ü�� ����ϴ�.
			while (rs.next()) {
				Bbs_bk_bean board = new Bbs_bk_bean();
				board.setBK_NO(rs.getInt(2));
				board.setBK_PASS(rs.getString(3));
				board.setBK_CSFC(rs.getString(4));
				board.setBK_SUBJECT(rs.getString(5));
				board.setBK_PRICE(rs.getInt(6));
				board.setUSER_ID(rs.getString(7));
				board.setBK_DATE(rs.getString(8));
				board.setBK_READ(rs.getInt(9));
				board.setBK_CONTENT(rs.getString(10));
				board.setBK_FILE(rs.getString(11));
				board.setBK_RE_REF(rs.getInt(12));
				board.setBK_RE_LEV(rs.getInt(13));
				board.setBK_RE_SEQ(rs.getInt(14));
				list.add(board); //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("board_search_list_sql() ���� : " +ex);
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
		return list;
	}//board_search_list_sql end


	public int bbs_bkSearchListCount(String searchType , String search) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchs="";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			if(searchType.equals("subAcon")) {
				searchs = "BK_SUBJECT like '%"+search+"%' or BK_CONTENT like '%"+search +"%' ";			
			}else if(searchType.equals("subject")) {
				searchs = "BK_SUBJECT like '%"+search+"%' ";
			}else if(searchType.equals("content")) {
				searchs = "BK_CONTENT like '%"+search+"%' ";
			}else if(searchType.equals("writer")) {
				searchs = "USER_ID like '%"+search+"%' ";
			}
			
			String sql = "select count(*) from BBS_BK where "+searchs+"";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("bbs_bkSearchListCount() ���� :" +ex);
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
	}//bbs_bkSearchListCount end

	   public List<Bbs_bk_bean> getBoardEnrList(int page, int limit) {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs= null;
		      
		      // page : ������ 
		      // limit : ������ �� ����� �� 
		      // board_re_Ref desc, board_re_seq asc�� ���� ������ ����
		      // �������� �´� rnum�� ���� ��ŭ �������� �������Դϴ�.
		      
		      String board_list_sql = "select * from "
		                     + " (select rownum rnum, BK_NO, USER_ID, "
		                     + "      BK_SUBJECT, BK_CONTENT, BK_FILE, "
		                     + "      BK_RE_REF, BK_RE_LEV, BK_RE_SEQ ,"
		                     + "      BK_READ, BK_DATE , BK_CSFC"
		                     + "      from (select * from BBS_BK "
		                     + "            order by"
		                     + "            bk_date asc, "
		                     + "            BK_RE_REF desc, "
		                     + "            BK_RE_SEQ asc)"
		                     + "  ) "
		                     + "where rnum>=? and rnum<=? ";
		      List<Bbs_bk_bean> list = new ArrayList<Bbs_bk_bean>();
		      // �� �������� 10���� ����� ���             1������ ,2������ , 3������, 4������ ,
		      int startrow = (page-1) * limit +1;   //�б� ������ row��ȣ (1 11 21 31 .... 
		      int endrow = startrow + limit -1 ;   //���� ������ row ��ȣ (10 20 30 40 ... .
		      try {
		         con = ds.getConnection();
		         pstmt = con.prepareStatement(board_list_sql);
		         pstmt.setInt(1, startrow);
		         pstmt.setInt(2, endrow);
		         rs = pstmt.executeQuery();
		         
		         // DB���� ������ �����͸� VO ��ü�� ����ϴ�. 
		         while(rs.next()) {
		        	 Bbs_bk_bean board = new Bbs_bk_bean();
		        	 board.setBK_NO(rs.getInt("BK_NO"));
			            board.setUSER_ID(rs.getString("USER_ID"));
			            board.setBK_SUBJECT(rs.getString("BK_SUBJECT"));
			            board.setBK_CONTENT(rs.getString("BK_CONTENT"));
			            board.setBK_FILE(rs.getString("BK_FILE"));
			            board.setBK_RE_REF(rs.getInt("BK_RE_REF"));
			            board.setBK_RE_LEV(rs.getInt("BK_RE_LEV"));
			            board.setBK_RE_SEQ(rs.getInt("BK_RE_SEQ"));
			            board.setBK_READ(rs.getInt("BK_READ"));
			            board.setBK_DATE(rs.getString("BK_DATE"));
			            board.setBK_CSFC(rs.getString("BK_CSFC"));
		            list.add(board);   //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
		            
		         }
		      } catch (Exception ex) {
		         ex.printStackTrace();
		         System.out.println("getBoardList() ���� : " + ex);
		      }finally {
		         if(rs !=null)
		            try {
		               rs.close();
		            } catch (SQLException ex) {
		               ex.printStackTrace();
		            }
		         if(con !=null)
		            try {
		               con.close();
		            } catch (SQLException ex) {
		               ex.printStackTrace();
		            }
		         if(pstmt !=null)
		            try {
		               pstmt.close();
		            } catch (SQLException ex) {
		               ex.printStackTrace();
		            }
		      }
		      return list;
		   }
	
	   
	   public List<Bbs_bk_bean> getBoardReaList(int page, int limit) {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs= null;
		      
		      // page : ������ 
		      // limit : ������ �� ����� �� 
		      // board_re_Ref desc, board_re_seq asc�� ���� ������ ����
		      // �������� �´� rnum�� ���� ��ŭ �������� �������Դϴ�.
		      
		      String board_list_sql = "select * from "
		                     + " (select rownum rnum, BK_NO, USER_ID, "
		                     + "      BK_SUBJECT, BK_CONTENT, BK_FILE, "
		                     + "      BK_RE_REF, BK_RE_LEV, BK_RE_SEQ ,"
		                     + "      BK_READ, BK_DATE , BK_CSFC"
		                     + "      from (select * from BBS_BK "
		                     + "            order by"
		                     + "            bk_read desc, "
		                     + "            BK_RE_REF desc, "
		                     + "            BK_RE_SEQ asc)"
		                     + "  ) "
		                     + "where rnum>=? and rnum<=? ";
		      List<Bbs_bk_bean> list = new ArrayList<Bbs_bk_bean>();
		      // �� �������� 10���� ����� ���             1������ ,2������ , 3������, 4������ ,
		      int startrow = (page-1) * limit +1;   //�б� ������ row��ȣ (1 11 21 31 .... 
		      int endrow = startrow + limit -1 ;   //���� ������ row ��ȣ (10 20 30 40 ... .
		      try {
		         con = ds.getConnection();
		         pstmt = con.prepareStatement(board_list_sql);
		         pstmt.setInt(1, startrow);
		         pstmt.setInt(2, endrow);
		         rs = pstmt.executeQuery();
		         
		         // DB���� ������ �����͸� VO ��ü�� ����ϴ�. 
		         while(rs.next()) {
		        	 Bbs_bk_bean board = new Bbs_bk_bean();
		            board.setBK_NO(rs.getInt("BK_NO"));
		            board.setUSER_ID(rs.getString("USER_ID"));
		            board.setBK_SUBJECT(rs.getString("BK_SUBJECT"));
		            board.setBK_CONTENT(rs.getString("BK_CONTENT"));
		            board.setBK_FILE(rs.getString("BK_FILE"));
		            board.setBK_RE_REF(rs.getInt("BK_RE_REF"));
		            board.setBK_RE_LEV(rs.getInt("BK_RE_LEV"));
		            board.setBK_RE_SEQ(rs.getInt("BK_RE_SEQ"));
		            board.setBK_READ(rs.getInt("BK_READ"));
		            board.setBK_DATE(rs.getString("BK_DATE"));
		            board.setBK_CSFC(rs.getString("BK_CSFC"));
		            list.add(board);   //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
		            
		         }
		      } catch (Exception ex) {
		         ex.printStackTrace();
		         System.out.println("getBoardList() ���� : " + ex);
		      }finally {
		         if(rs !=null)
		            try {
		               rs.close();
		            } catch (SQLException ex) {
		               ex.printStackTrace();
		            }
		         if(con !=null)
		            try {
		               con.close();
		            } catch (SQLException ex) {
		               ex.printStackTrace();
		            }
		         if(pstmt !=null)
		            try {
		               pstmt.close();
		            } catch (SQLException ex) {
		               ex.printStackTrace();
		            }
		      }
		      return list;
		   }
}
