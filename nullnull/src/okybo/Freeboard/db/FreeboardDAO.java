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

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public FreeboardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	//자유게시판 게시글 작성
	public boolean frboardInsert(FreeboardBean boarddata) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			con = ds.getConnection();
			
			String max_sql = "(select nvl(max(FR_NO),0)+1 from BBS_FR)";
			
			// 원문 글의 RE_REF는 자신의 글 번호
			String sql = "insert into BBS_FR "
					   + "(FR_NO, FR_PASS, FR_CSFC, FR_SUBJECT, "
					   + " USER_ID, FR_READ, FR_CONTENT,  "
					   + " FR_FILE, FR_RE_REF, FR_RE_LEV, FR_RE_SEQ)"
					   + " values(" +max_sql +", ?, ?, ?,"
					   + "        ?, ?, ?, "
					   + "        ?, " +max_sql +", ?, ?)";
			// 새로운 글을 등록하는 부분입니다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getFr_pass());
			pstmt.setInt(2,boarddata.getFr_csfc());
			pstmt.setString(3, boarddata.getFr_subject());
			
			pstmt.setString(4, boarddata.getId());
			pstmt.setInt(5, 0); //READ 필드초기값은 0
			pstmt.setString(6, boarddata.getFr_content());
			
			pstmt.setString(7, boarddata.getFr_file());
			
			// 원문글일 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0입니다.
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
				return true;
			}
		} catch (Exception ex) {
			System.out.println("boardInsert() 에러" + ex);
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
	}// frboardInsert()메서드 end
	
	//자유게시판 글 갯수 구하기
	public int getfrListCount(String category) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int x = 0; 
		String board_count_sql="";
		
		try {
			if(category ==null) {
				board_count_sql="select count(*) from BBS_FR";
			} else {
				board_count_sql="select count(*) from BBS_FR where fr_csfc=? ";
			}
			con = ds.getConnection();			
			pstmt = con.prepareStatement(board_count_sql);
			if(category !=null) {
				pstmt.setString(1, category);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getfrListCount() 에러: " + ex);
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

	
	//선택된 페이지에 보여지는 게시글리스트 
	public List<FreeboardBean> getfrBoardList(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;		
		
		// page : 페이지
		// limit : 페이지 당 게시글 목록의 수 (기본 10개)
		// board_re_ref desc(원 게시글은 내림차순), board_re_seq asc(답글은 오름차순)에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.		
		
		String board_list_sql = "select * from "
				            + " (select rownum rnum, fr_no, fr_csfc, "
				            + "  fr_subject, USER_ID, fr_date, "
				            + "  fr_read, fr_pass, fr_content, "
				            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
				            + "  from (select * from bbs_fr "
				            + "        order by fr_re_ref desc,"
				            + "        fr_re_seq asc) "
				            + "  )"
				            + "where rnum>=? and rnum <=?";
		
		List<FreeboardBean> list = new ArrayList<FreeboardBean>();
		// 한 페이지당 10개씩 목록인 경우                                                       1페이지, 2페이지, 3페이지 ... 10페이지
		int startrow = (page -1) * limit + 1; // 읽기 시작할 row 번호 (1 11 21 31 ...
		int endrow = startrow + limit - 1;    // 읽을 마지막 row 번호 (10 20 30 40 ...		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while (rs.next()) {
				FreeboardBean board = new FreeboardBean();
				board.setFr_no(rs.getInt("fr_no"));
				board.setFr_csfc(rs.getInt("fr_csfc"));
				board.setFr_subject(rs.getString("fr_subject"));
				board.setId(rs.getString("USER_ID"));
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
			System.out.println("getfrBoardList() 에러: " + ex);
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
			System.out.println("setfrReadCountUpdate() 에러: " + ex);
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
	
	//글 내용 보기
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
				board.setId(rs.getString("USER_ID"));
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
			System.out.println("getDeatail() 에러: " + ex);
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
	
	// 글 답변 쓰기
	public int frboardReply(FreeboardBean boarddata) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int num=0;
		
		String board_max_sql = "select max(fr_no)+1 from bbs_fr";
		/*
		 * 답변을 달 원문 글 그룹 번호입니다.
		 * 답변을 달게 되면 답변 글은 이번호와 같은 관련글 번호를 갖게 처리되면서 같은 그룹에 속하게 됩니다.
		 * 글 목록에서 보여줄때 하나의 그룹으로 묶여서 출력됩니다.
		 */
		int re_ref = boarddata.getFr_re_ref();
		
		/*
		 * 답글의 깊이를 의미합니다.
		 * 원문에 대한 답글이 출력될 때 한번 들여쓰기가 처리가 되고 답글에 대한 답글은 들여쓰기가 두 번 처리되게 합니다.
		 * 원문인 경우에는 이 값이 0이고 원문의 답글은 1, 답글의 답글은 2가 됩니다.
		 */
		int re_lev = boarddata.getFr_re_lev();
		
		//같은 관련 글 중에서 해당 글이 출력되는 순서입니다.
		int re_seq = boarddata.getFr_re_seq();
		
		try {
			con = ds.getConnection();
			
			
			// 트랜잭션을 이용하기 위해서 setAutoCommit을 false로 설정합니다.
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(board_max_sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
			}
			pstmt.close();
			
			// BOARD_RE_REF, BOARD_RE_SEQ 값을 확인하여 원문 글에 다른 답글이 있으면
			// 다른 답글들의 BOARD_RE_SEQ값을 1씩 증가시킵니다.
			// 현재 글을 다른 답글보다 앞에 출력되게 하기 위해서 입니다.
			String sql = "update bbs_fr "
					   + "set FR_RE_SEQ = FR_RE_SEQ +1 "
					   + "where FR_RE_REF = ? "
					   + "and FR_RE_SEQ > ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			// 등록할 답변 글의 BOARD_RE_LEV, BOARD_RE_SEQ  값을 원문 글보다 1씩
			// 증가시킵니다.
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			

			
			sql = "insert into BBS_FR "
					   + "(FR_NO, FR_PASS, FR_CSFC, FR_SUBJECT, "
					   + " USER_ID, FR_READ, FR_CONTENT,  "
					   + " FR_FILE, FR_RE_REF, FR_RE_LEV, FR_RE_SEQ)"
					   + " values(" +num +", ?, ?, ?,"
					   + "        ?, ?, ?, "
					   + "      ?, ?, ?, ?)";
			// 새로운 글을 등록하는 부분입니다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getFr_pass());
			pstmt.setInt(2,boarddata.getFr_csfc());
			pstmt.setString(3, boarddata.getFr_subject());
			
			pstmt.setString(4, boarddata.getId());
			pstmt.setInt(5, 0); //READ 필드초기값은 0
			pstmt.setString(6, boarddata.getFr_content());
			
			pstmt.setString(7, "");//답변에는 파일 업로드 하지 않음
			
			// 원문글일 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0입니다.
			pstmt.setInt(8, re_ref);
			pstmt.setInt(9, re_lev);
			pstmt.setInt(10, re_seq);
			
			

			if (pstmt.executeUpdate() == 1) {
				con.commit(); // commit 합니다.
			} else {
				con.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("frboardReply() 에러" + ex);
			if (con !=null) {
				try {
					con.rollback(); //rollback 합니다.
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
	
	// 수정할 글이 본문 글쓴이 인지 확인하기!
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
			System.out.println("isBoardWriter() 에러: " + ex);
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
	
	//글 수정
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
				System.out.println("업데이트 성공");
				return true;
			}
		} catch(Exception ex) {
			System.out.println("frboardModify() 에러: " + ex);
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
	
	//글 삭제
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
					result_check = true; //삭제가 안된 경우에는 false를 반환합니다.
			}
		} catch (Exception ex) {
			System.out.println("frboardDelete() 에러: " + ex);
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
	
	//게시판 검색
	public List<FreeboardBean> getFrBoardSearchList(int page, int limit, String searchType, String search) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_search_list_sql= "";
		String searchs ="";
		
		
		List<FreeboardBean> list = new ArrayList<FreeboardBean>();
		int startrow = (page -1) *limit +1; //읽기 시작할 row 번호(1 11 21 31 ...)
		int endrow = startrow + limit -1 ;  //읽을 마지막 row 번호(10 20 30 40 ...)

		try {
			conn = ds.getConnection();
			if(searchType.equals("subAcon")) {
				searchs = "FR_SUBJECT like '%"+search+"%' or FR_CONTENT like '%"+search +"%' ";			
			}else if(searchType.equals("subject")) {
				searchs = "FR_SUBJECT like '%"+search+"%' ";
			}else if(searchType.equals("content")) {
				searchs = "FR_CONTENT like '%"+search+"%' ";
			}else if(searchType.equals("writer")) {
				searchs = "USER_ID like '%"+search+"%' ";
			}
			board_search_list_sql=
					"select * "
					+ "from "
					+ "		(select rownum rnum, FR_NO , FR_PASS,"
					+ " 	FR_CSFC, FR_SUBJECT, "
					+ " 	USER_ID, FR_DATE, FR_READ,"
					+ " 	FR_CONTENT, FR_FILE, FR_RE_REF,"
					+ "		FR_RE_LEV, FR_RE_SEQ FROM "
					+ " 							(select * from BBS_FR "
					+ "								where "+ searchs 
					+ "								order by FR_RE_REF desc, "
					+ "								FR_RE_SEQ asc)"
					+ ") "
					+ " where rnum >= ? and rnum<= ? ";
			pstmt = conn.prepareStatement(board_search_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs= pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while (rs.next()) {
				FreeboardBean board = new FreeboardBean();
				board.setFr_no(rs.getInt(2));
				board.setFr_pass(rs.getString(3));
				board.setFr_csfc(rs.getInt(4));
				board.setFr_subject(rs.getString(5));
				board.setId(rs.getString(6));
				board.setFr_date(rs.getString(7));
				board.setFr_read(rs.getInt(8));
				board.setFr_content(rs.getString(9));
				board.setFr_file(rs.getString(10));
				board.setFr_re_ref(rs.getInt(11));
				board.setFr_re_lev(rs.getInt(12));
				board.setFr_re_seq(rs.getInt(13));
				list.add(board); //값을 담은 객체를 리스트에 저장합니다.
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("board_search_list_sql() 에러 : " +ex);
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
	}//getFrBoardSearchList end
	
	
	//게시판 검색 2
	public int getFrSearchListCount(String searchType, String search) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchs="";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			if(searchType.equals("subAcon")) {
				searchs = "FR_SUBJECT like '%"+search+"%' or FR_CONTENT like '%"+search +"%' ";			
			}else if(searchType.equals("subject")) {
				searchs = "FR_SUBJECT like '%"+search+"%' ";
			}else if(searchType.equals("content")) {
				searchs = "FR_CONTENT like '%"+search+"%' ";
			}else if(searchType.equals("writer")) {
				searchs = "USER_ID like '%"+search+"%' ";
			}
			
			String sql = "select count(*) from BBS_FR where "+searchs+"";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("bbs_FRSearchListCount() 에러 :" +ex);
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
	}//getFrSearchListCoun end
	

	
	//view가 적용된 search
	public List<FreeboardBean> getfrBoardList(int page, int limit, String view, String category) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;				
		String board_list_sql ="";

		
		String pan = ""; //카테고리가 전체인지 그 외인지 판단하기 위함 (스위치 문에는 null 사용이 불가)	
		if (category == null) {
			pan = "null";
		} else {
			pan = "other";
		}
		System.out.println("dao의 카테고리는 = " +pan);
		// page : 페이지
		// limit : 페이지 당 게시글 목록의 수 (기본 10개)
		// board_re_ref desc(원 게시글은 내림차순), board_re_seq asc(답글은 오름차순)에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.	
		switch(pan) {
		case "null" : //분류 전체 선택시
			if (view == null || view.equals("1")) {
				board_list_sql = "select * from "
			            + " (select rownum rnum, fr_no, fr_csfc, "
			            + "  fr_subject, USER_ID, fr_date, "
			            + "  fr_read, fr_pass, fr_content, "
			            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
			            + "  from (select * from bbs_fr "
			            + "        order by fr_re_ref desc,"
			            + "        fr_re_seq asc) "
			            + "  )"
			            + "where rnum>=? and rnum <=?";
			} else if (view.equals("2"))  {//분류 전체 등록순
				
				board_list_sql = "select * from "
				            + " (select rownum rnum, fr_no, fr_csfc, "
				            + "  fr_subject, USER_ID, fr_date, "
				            + "  fr_read, fr_pass, fr_content, "
				            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
				            + "  from (select * from bbs_fr "
				            + "  order by fr_date asc, fr_re_ref desc,"
				            + "        fr_re_seq asc) "
				            + "  )"
				            + "where rnum>=? and rnum <=?";
				} else if (view.equals("3") ) {//분류 전체 조회순
					
					board_list_sql = "select * from "
			            + " (select rownum rnum, fr_no, fr_csfc, "
			            + "  fr_subject, USER_ID, fr_date, "
			            + "  fr_read, fr_pass, fr_content, "
			            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
			            + "  from (select * from bbs_fr "
			            + "  order by fr_read desc, fr_re_ref desc,"
			            + "        fr_re_seq asc) "
			            + "  )"
			            + "where rnum>=? and rnum <=?";	
					}
			break;
		case "other" : //전체게시판 외에 분류 선택시
			if (view == null || view.equals("1")) { //게시판 최신정렬순
				board_list_sql = "select * from "
			            + " (select rownum rnum, fr_no, fr_csfc, "
			            + "  fr_subject, USER_ID, fr_date, "
			            + "  fr_read, fr_pass, fr_content, "
			            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
			            + "  from (select * from bbs_fr "
			            + "  where fr_csfc=?   order by fr_re_ref desc,"
			            + "        fr_re_seq asc) "
			            + "  )"
			            + "where rnum>=? and rnum <=?";	
			
		} else if (view.equals("2"))  {//등록순
			
		board_list_sql = "select * from "
		            + " (select rownum rnum, fr_no, fr_csfc, "
		            + "  fr_subject, USER_ID, fr_date, "
		            + "  fr_read, fr_pass, fr_content, "
		            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
		            + "  from (select * from bbs_fr "
		            + "  where fr_csfc=? order by fr_date asc, fr_re_ref desc,"
		            + "        fr_re_seq asc) "
		            + "  )"
		            + "where rnum>=? and rnum <=?";
		}  else if (view.equals("3")) {//조회순			
			board_list_sql = "select * from "
		            + " (select rownum rnum, fr_no, fr_csfc, "
		            + "  fr_subject, USER_ID, fr_date, "
		            + "  fr_read, fr_pass, fr_content, "
		            + "  fr_file, fr_re_ref, fr_re_lev, fr_re_seq"
		            + "  from (select * from bbs_fr "
		            + "  where fr_csfc=? order by fr_read desc, fr_re_ref desc,"
		            + "        fr_re_seq asc) "
		            + "  )"
		            + "where rnum>=? and rnum <=?";	
				}
			break;
		}

		

		List<FreeboardBean> list = new ArrayList<FreeboardBean>();
		// 한 페이지당 10개씩 목록인 경우                                                       1페이지, 2페이지, 3페이지 ... 10페이지
		int startrow = (page -1) * limit + 1; // 읽기 시작할 row 번호 (1 11 21 31 ...
		int endrow = startrow + limit - 1;    // 읽을 마지막 row 번호 (10 20 30 40 ...		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			if(pan =="null"){
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			}else {
			pstmt.setString(1, category);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);	
			}
			rs = pstmt.executeQuery();
			
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while (rs.next()) {
				FreeboardBean board = new FreeboardBean();
				board.setFr_no(rs.getInt("fr_no"));
				board.setFr_csfc(rs.getInt("fr_csfc"));
				board.setFr_subject(rs.getString("fr_subject"));
				board.setId(rs.getString("USER_ID"));
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
			System.out.println("getfrBoardList() 에러: " + ex);
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
	}
	
}
