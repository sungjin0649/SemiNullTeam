package oky.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class MemberDAO {
	private DataSource ds;
	
	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	//회원가입
	public int insert(Member m) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			con = ds.getConnection();
			System.out.println("getConnection : 오근영 insert()");
			
			pstmt = con.prepareStatement(
					"INSERT INTO member(USER_ID, USER_PASS, USER_NAME, USER_BIRTH, USER_PHONE, USER_EMAIL)"
					+ "VALUES (?,?,?,?,?,?)");
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass());
			pstmt.setString(3, m.getName());
			pstmt.setInt(4, m.getBirth());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getEmail());
			result = pstmt.executeUpdate(); //삽입 성공시 result는 1
			
			System.out.println("삽입시 값은 1 :" +result);
			
		//primary key 제약조건 위반했을 경우 발생하는 에러	
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("멤버 아이디 중복 에러입니다.");
		}catch (Exception e) {
			e.printStackTrace();
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
	
	//로그인시 아이디 비밀번호 맞는지 확인
	public int ckidpw(String id, String pass) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;		
		int result=-1; //id 혹은 비밀번호 오류
		try {
			con = ds.getConnection();
			
			String sql = "select USER_ID, USER_PASS from member where USER_ID = ? and USER_PASS =? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ckidpw 에러: " + ex);
		} finally {
			if(rs !=null)
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
		return result;
	}// ckidpw 종료
	
	
	//id찾기
	public String findid(String name, String birth, String email) {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String id = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(
					"select USER_ID"
					+    " from member "
					+    " where USER_NAME = ? and"
					+    " USER_BIRTH = ? and"
					+    " USER_EMAIL = ? ");
		
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();
			
		while(rs.next()) {
         id = rs.getString(1);
		}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("findid 에러: " + ex);
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
		return id;
	}
	
	//비밀번호찾기
	public String findpass(String name, String birth, String email) {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String pass = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(
					"select USER_PASS"
					+    " from member "
					+    " where USER_NAME = ? and"
					+    " USER_BIRTH = ? and"
					+    " USER_EMAIL = ? ");
		
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();
			
		while(rs.next()) {
         pass = rs.getString(1);
		}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("findid 에러: " + ex);
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
		return pass;
	}

	public int isId(String id) {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		int result=-1;//DB에 해당 id가 없습니다
		try {
			con = ds.getConnection();
			
			String sql = "select * from member where USER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 0; //DB에 해당 id가 있습니다.
			}
	    }catch (Exception e) {
			e.printStackTrace();
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
		return result;
	}

	//회원정보 수정
	public Member member_info(String id) {
		Member m = null;
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * from member where USER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setId(rs.getString("USER_ID"));
				m.setPass(rs.getString("USER_PASS"));
				m.setName(rs.getString("USER_NAME"));
				m.setBirth(rs.getInt("USER_BIRTH"));
				m.setPhone(rs.getString("USER_PHONE"));
				m.setEmail(rs.getString("USER_EMAIL"));
				m.setMemberfile(rs.getString("USER_FILE"));				
			}
		}catch (Exception e) {
			e.printStackTrace();
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
		return m;
	}
	
	//회원정보 수정
	public int update(Member m) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			con = ds.getConnection();
			System.out.println("getConnection : update()");
			
			pstmt = con.prepareStatement(
					"UPDATE member SET "
					+ "USER_NAME =?, "
					+ "USER_BIRTH =?, "
					+ "USER_PHONE =?, "
					+ "USER_EMAIL =?, "
					+ "USER_FILE=? "
					+ " WHERE USER_ID =?");
			pstmt.setString(1, m.getName());
			pstmt.setInt(2, m.getBirth());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getMemberfile());
			pstmt.setString(6, m.getId());
			result = pstmt.executeUpdate(); //삽입 성공시 result는 1
			
		}catch (Exception e) {
			e.printStackTrace();
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

	public int getListCount() {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count (*) from member where USER_ID != 'admin'");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount()1 에러: " + ex);
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
	}// getListCount end

	public List<Member> getList(int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			con =ds.getConnection();
			
			String sql = "select *"
					+ "   from (select b.*, rownum rnum"
					+ "         from(select * from member"
					+ "              where USER_ID != 'admin'"
					+ "              order by USER_ID) b"
					+          ")"
					+ "  where rnum>=? and rnum <=? ";
			pstmt = con.prepareStatement(sql);
			 // 한 페이지당 3개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
			int startrow = (page - 1) * limit + 1;
			              // 읽기 시작할 row 번호 (1 4 7 10 ...
			int endrow = startrow + limit - 1;
			              // 읽을 마지막 row 번호 (3 6 9 12 ...
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("USER_ID"));
				m.setPass(rs.getString(2));
				m.setName(rs.getString(3));
				m.setBirth(rs.getInt(4));
				m.setPhone(rs.getString(5));
				m.setEmail(rs.getString(6));
				list.add(m);
			}
		    }catch (SQLException ex) {
		    	    ex.printStackTrace();
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
	}//getList end
	
	//검색클릭시
	public int getListCount(String field, String value) {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from member "
					+"    where USER_ID !='admin' "
					+"    and " + field + " like ? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount()2 에러: " + ex);
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
	}
	
	
	
	//검색클릭시
	public List<Member> getList(String field, String value, int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			con =ds.getConnection();
			
			String sql = "select * "
					+ "   from (select b.*, rownum rnum"
					+ "         from(select * from member "
					+ "              where USER_ID !='admin' "
					+ "              and " + field + " like ? "
					+ "              order by USER_ID) b"
					+          ")"
					+ "  where rnum between ? and ? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			 // 한 페이지당 3개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
			int startrow = (page - 1) * limit + 1;
			              // 읽기 시작할 row 번호 (1 4 7 10 ...
			int endrow = startrow + limit - 1;
			              // 읽을 마지막 row 번호 (3 6 9 12 ...
			pstmt.setInt(2, startrow);
			pstmt.setInt(3 , endrow);
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("USER_ID"));
				m.setPass(rs.getString(2));
				m.setName(rs.getString(3));
				m.setBirth(rs.getInt(4));
				m.setPhone(rs.getString(5));
				m.setEmail(rs.getString(6));
				list.add(m);
			}
		    }catch (SQLException ex) {
		    	    ex.printStackTrace();
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
	}//getList end
	
	//맴버삭제
	public int delete(String id) {
		Connection con = null;
		PreparedStatement pstmt= null;
		int result=0;
		try {
			con =ds.getConnection();
			String sql = "delete from member where USER_ID = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
	

}
