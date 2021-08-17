package okybo.Freeboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class FreeboardDAO {
	private DataSource ds;

	// �����ڿ��� JNDI ���ҽ��� �����Ͽ� Connection ��ü�� ���ɴϴ�.
	public FreeboardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}
	
	//�����Խ��� �Խñ� �ۼ�
	public boolean frboardInsert(FreeboardBean boarddata) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			con = ds.getConnection();
			
			String max_sql = "(select nvl(max(FR_NO),0)+1 from BBS_FR)";
			
			// ���� ���� RE_REF�� �ڽ��� �� ��ȣ
			String sql = "insert into BBS_FR "
					   + "(FR_NO, FR_PASS, FR_CSFC, FR_SUBJECT, "
					   + " ID, FR_READ, FR_CONTENT,  "
					   + " FR_FILE, FR_RE_REF, FR_RE_LEV, FR_RE_SEQ)"
					   + " values(" +max_sql +", ?, ?, ?,"
					   + "        ?, ?, ?, "
					   + "        ?, " +max_sql +", ?, ?)";
			// ���ο� ���� ����ϴ� �κ��Դϴ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getFr_pass());
			pstmt.setInt(2,boarddata.getFr_csfc());
			pstmt.setString(3, boarddata.getFr_subject());
			
			pstmt.setString(4, boarddata.getId());
			pstmt.setInt(5, 0); //READ �ʵ��ʱⰪ�� 0
			pstmt.setString(6, boarddata.getFr_content());
			
			pstmt.setString(7, boarddata.getFr_file());
			
			// �������� ��� BOARD_RE_LEV, BOARD_RE_SEQ �ʵ� ���� 0�Դϴ�.
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("������ ������ ��� �Ϸ�Ǿ����ϴ�.");
				return true;
			}
		} catch (Exception ex) {
			System.out.println("boardInsert() ����" + ex);
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
		return false;
	}// frboardInsert()�޼��� end
	
	//�����Խ��� �� ���� ���ϱ�
	public int getfrListCount() {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int x = 0; 
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement("select count(*) from BBS_FR");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getfrListCount() ����: " + ex);
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
	}// getfrListCount end

	
	//���õ� �������� �������� �Խñ۸���Ʈ 
	public List<FreeboardBean> getfrBoardList(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;		
		
		// page : ������
		// limit : ������ �� �Խñ� ����� �� (�⺻ 10��)
		// board_re_ref desc(�� �Խñ��� ��������), board_re_seq asc(����� ��������)�� ���� ������ ����
		// �������� �´� rnum�� ���� ��ŭ �������� �������Դϴ�.		
		
		String board_list_sql = "select * from "
				            + " (select rownum rnum, fr_no, fr_csfc, "
				            + "  fr_subject, id, fr_date, "
				            + "  fr_read, fr_pass, fr_content, "
				            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
				            + "  from (select * from bbs_fr "
				            + "        order by fr_re_ref desc,"
				            + "        fr_re_seq asc) "
				            + "  )"
				            + "where rnum>=? and rnum <=?";
		
		List<FreeboardBean> list = new ArrayList<FreeboardBean>();
		// �� �������� 10���� ����� ���                                                       1������, 2������, 3������ ... 10������
		int startrow = (page -1) * limit + 1; // �б� ������ row ��ȣ (1 11 21 31 ...
		int endrow = startrow + limit - 1;    // ���� ������ row ��ȣ (10 20 30 40 ...		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			// DB���� ������ �����͸� VO��ü�� ����ϴ�.
			while (rs.next()) {
				FreeboardBean board = new FreeboardBean();
				board.setFr_no(rs.getInt("fr_no"));
				board.setFr_csfc(rs.getInt("fr_csfc"));
				board.setFr_subject(rs.getString("fr_subject"));
				board.setId(rs.getString("id"));
				board.setFr_date(rs.getString("fr_date"));
				board.setFr_read(rs.getInt("fr_read"));
				board.setFr_pass(rs.getString("fr_pass"));
				board.setFr_content(rs.getString("fr_content"));
				board.setFr_file(rs.getString("fr_file"));
				board.setFr_re_ref(rs.getInt("fr_re_ref"));
				board.setFr_re_lev(rs.getInt("fr_re_lev"));
				board.setFr_re_seq(rs.getInt("fr_re_seq"));
				list.add(board);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getfrBoardList() ����: " + ex);
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
		return list;
	}//getfrBoardList end


	public void setfrReadCountUpdate(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update BBS_FR "
				   + "set FR_READ=FR_READ +1 "
				   + "where FR_NO  = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setfrReadCountUpdate() ����: " + ex);
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
		
	}// setfrReadCountUpdate end
	
	//�� ���� ����
	public FreeboardBean getfrDetail(int num) {
		FreeboardBean board = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from BBS_FR where FR_NO = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new FreeboardBean();
				board.setFr_no(rs.getInt("fr_no"));
				board.setFr_csfc(rs.getInt("fr_csfc"));
				board.setFr_subject(rs.getString("fr_subject"));
				board.setId(rs.getString("id"));
				board.setFr_date(rs.getString("fr_date"));
				board.setFr_read(rs.getInt("fr_read"));
				board.setFr_pass(rs.getString("fr_pass"));
				board.setFr_content(rs.getString("fr_content"));
				board.setFr_file(rs.getString("fr_file"));
				board.setFr_re_ref(rs.getInt("fr_re_ref"));
				board.setFr_re_lev(rs.getInt("fr_re_lev"));
				board.setFr_re_seq(rs.getInt("fr_re_seq"));
			}
		} catch (Exception ex) {
			System.out.println("getDeatail() ����: " + ex);
		} finally {
			if (rs !=null) {
				try {
					rs.close();					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
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
		return board;
	}//  getfrDetail end
	
	// �� �亯 ����
	public int frboardReply(FreeboardBean boarddata) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int num=0;
		
		String board_max_sql = "select max(fr_no)+1 from bbs_fr";
		/*
		 * �亯�� �� ���� �� �׷� ��ȣ�Դϴ�.
		 * �亯�� �ް� �Ǹ� �亯 ���� �̹�ȣ�� ���� ���ñ� ��ȣ�� ���� ó���Ǹ鼭 ���� �׷쿡 ���ϰ� �˴ϴ�.
		 * �� ��Ͽ��� �����ٶ� �ϳ��� �׷����� ������ ��µ˴ϴ�.
		 */
		int re_ref = boarddata.getFr_re_ref();
		
		/*
		 * ����� ���̸� �ǹ��մϴ�.
		 * ������ ���� ����� ��µ� �� �ѹ� �鿩���Ⱑ ó���� �ǰ� ��ۿ� ���� ����� �鿩���Ⱑ �� �� ó���ǰ� �մϴ�.
		 * ������ ��쿡�� �� ���� 0�̰� ������ ����� 1, ����� ����� 2�� �˴ϴ�.
		 */
		int re_lev = boarddata.getFr_re_lev();
		
		//���� ���� �� �߿��� �ش� ���� ��µǴ� �����Դϴ�.
		int re_seq = boarddata.getFr_re_seq();
		
		try {
			con = ds.getConnection();
			
			
			// Ʈ������� �̿��ϱ� ���ؼ� setAutoCommit�� false�� �����մϴ�.
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(board_max_sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
			}
			pstmt.close();
			
			// BOARD_RE_REF, BOARD_RE_SEQ ���� Ȯ���Ͽ� ���� �ۿ� �ٸ� ����� ������
			// �ٸ� ��۵��� BOARD_RE_SEQ���� 1�� ������ŵ�ϴ�.
			// ���� ���� �ٸ� ��ۺ��� �տ� ��µǰ� �ϱ� ���ؼ� �Դϴ�.
			String sql = "update bbs_fr "
					   + "set FR_RE_SEQ = FR_RE_SEQ +1 "
					   + "where FR_RE_REF = ? "
					   + "and FR_RE_SEQ > ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			// ����� �亯 ���� BOARD_RE_LEV, BOARD_RE_SEQ  ���� ���� �ۺ��� 1��
			// ������ŵ�ϴ�.
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			

			
			sql = "insert into BBS_FR "
					   + "(FR_NO, FR_PASS, FR_CSFC, FR_SUBJECT, "
					   + " ID, FR_READ, FR_CONTENT,  "
					   + " FR_FILE, FR_RE_REF, FR_RE_LEV, FR_RE_SEQ)"
					   + " values(" +num +", ?, ?, ?,"
					   + "        ?, ?, ?, "
					   + "      ?, ?, ?, ?)";
			// ���ο� ���� ����ϴ� �κ��Դϴ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getFr_pass());
			pstmt.setInt(2,boarddata.getFr_csfc());
			pstmt.setString(3, boarddata.getFr_subject());
			
			pstmt.setString(4, boarddata.getId());
			pstmt.setInt(5, 0); //READ �ʵ��ʱⰪ�� 0
			pstmt.setString(6, boarddata.getFr_content());
			
			pstmt.setString(7, "");//�亯���� ���� ���ε� ���� ����
			
			// �������� ��� BOARD_RE_LEV, BOARD_RE_SEQ �ʵ� ���� 0�Դϴ�.
			pstmt.setInt(8, re_ref);
			pstmt.setInt(9, re_lev);
			pstmt.setInt(10, re_seq);
			
			

			if (pstmt.executeUpdate() == 1) {
				con.commit(); // commit �մϴ�.
			} else {
				con.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("frboardReply() ����" + ex);
			if (con !=null) {
				try {
					con.rollback(); //rollback �մϴ�.
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt !=null)
				try {
					con.setAutoCommit(true);
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return num;
	}// frboardReply end
	
	// ������ ���� ���� �۾��� ���� Ȯ���ϱ�!
	public boolean isfrboardWriter(int num, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		String board_sql = "select FR_PASS from BBS_FR where FR_NO=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (pass.equals(rs.getString("FR_PASS"))) {
					result = true;
				}
			}
		} catch (SQLException ex) {
			System.out.println("isBoardWriter() ����: " + ex);
		} finally {
			if (rs !=null) {
				try {
					rs.close();					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
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
	}// isfrboardWriter end
	
	//�� ����
	public boolean frboardModify(FreeboardBean boarddata) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update BBS_FR "
				   + "set FR_SUBJECT=?, FR_CONTENT=?, FR_FILE=? ,FR_CSFC=? "
				   + "where FR_NO=? ";
		try {
			con  = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getFr_subject());
			pstmt.setString(2, boarddata.getFr_content());
			pstmt.setString(3, boarddata.getFr_file());
			pstmt.setInt(4, boarddata.getFr_csfc());
			pstmt.setInt(5, boarddata.getFr_no());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("������Ʈ ����");
				return true;
			}
		} catch(Exception ex) {
			System.out.println("frboardModify() ����: " + ex);
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
		} return false;
	}// frboardModify() end
	
	//�� ����
	public boolean frboardDelete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		String select_sql = "select FR_RE_REF, FR_RE_LEV, FR_RE_SEQ "
		         + " from BBS_FR" 
                + " where FR_NO=?";

        String board_delete_sql = "delete from bbs_fr" 
        + "			 where  FR_RE_REF = ?"
		+ "			 and    FR_RE_LEV >=?" 
        + "			 and    FR_RE_SEQ >=?"
		+ "			 and    FR_RE_SEQ <=("
		+ "			                      nvl((SELECT min(fr_re_seq)-1"
		+ "			                           FROM   BBS_FR  "
		+ "			                           WHERE  FR_RE_REF=?"
		+ "			                           AND    FR_RE_LEV=?"
		+ "			                           AND    FR_RE_SEQ>?) , "
		+ "			                           (SELECT max(fr_re_seq) "
		+ "			                            FROM   BBS_FR  "
		+ "			                            WHERE  FR_RE_REF=? ))"
		+ "				                   )";

		boolean result_check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt2 = con.prepareStatement(board_delete_sql);
				pstmt2.setInt(1, rs.getInt("FR_RE_REF"));
				pstmt2.setInt(2, rs.getInt("FR_RE_LEV"));
				pstmt2.setInt(3, rs.getInt("FR_RE_SEQ"));
				pstmt2.setInt(4, rs.getInt("FR_RE_REF"));
				pstmt2.setInt(5, rs.getInt("FR_RE_LEV"));
				pstmt2.setInt(6, rs.getInt("FR_RE_SEQ"));
				pstmt2.setInt(7, rs.getInt("FR_RE_REF"));
				
				int count=pstmt2.executeUpdate();
				
				if(count>=1)
					result_check = true; //������ �ȵ� ��쿡�� false�� ��ȯ�մϴ�.
			}
		} catch (Exception ex) {
			System.out.println("frboardDelete() ����: " + ex);
			ex.printStackTrace();
		} finally {
			if (rs !=null) {
				try {
					rs.close();					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
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
		return result_check;
	}//  frboardDelete end

	
}
