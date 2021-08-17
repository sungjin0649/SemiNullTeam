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
			System.out.println("getConnection : insert()");
			
			pstmt = con.prepareStatement(
					"INSERT INTO member(id, pass, name, birth, phone, email)"
					+ "VALUES (?,?,?,?,?,?)");
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass());
			pstmt.setString(3, m.getName());
			pstmt.setInt(4, m.getBirth());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getEmail());
			result = pstmt.executeUpdate(); //삽입 성공시 result는 1
			
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
			
			String sql = "select id, pass from member where id = ? and pass =? ";
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
	
//	// 아이디 비밀번호 찾기
//	public List<Member> findidpw(String name, String birth, String email) {
//		List<Member> list = new ArrayList<Member>();
//		Connection con = null;
//		PreparedStatement pstmt= null;
//		ResultSet rs = null;
//		try {
//			con = ds.getConnection();
//			
//			pstmt = con.prepareStatement(
//					"select *"
//					+    " from member "
//					+    " where name = ? and"
//					+    " birth = ? and"
//					+    " email = ? ");
//		
//			pstmt.setString(1, name);
//			pstmt.setString(2, birth);
//			pstmt.setString(3, email);
//			rs = pstmt.executeQuery();
//			
//		while(rs.next()) {
//			Member m = new Member();
//			
//			System.out.println(rs.getString(1));//아이디
//			System.out.println(rs.getString(2));//비밀번호
//			String id = rs.getString(1);
//			String pass = rs.getString(2);
//			m.setId(id);
//			System.out.println(m);
//			m.setPass(pass);
//			list.add(m);
//			System.out.println(m);
//		}
//		} catch(Exception ex) {
//			ex.printStackTrace();
//			System.out.println("findidpw 에러: " + ex);
//		} finally {
//	    	if (rs !=null)
//				try {
//					rs.close();
//				} catch (SQLException ex) {
//					ex.printStackTrace();
//				}
//			if (pstmt !=null)
//				try {
//					pstmt.close();
//				} catch (SQLException ex) {
//					ex.printStackTrace();
//				}
//			if (con !=null)
//				try {
//					con.close();
//				} catch (SQLException ex) {
//					ex.printStackTrace();
//				}
//		}
//		return list;
//	}//findidpw 종료  (배열값으로 받아오려 했으나 뜻대로 되지 않아서 일단 id 따로 pass 따로 받아왔습니다...)
	
	//id찾기
	public String findid(String name, String birth, String email) {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String id = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(
					"select id"
					+    " from member "
					+    " where name = ? and"
					+    " birth = ? and"
					+    " email = ? ");
		
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
					"select pass"
					+    " from member "
					+    " where name = ? and"
					+    " birth = ? and"
					+    " email = ? ");
		
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
			
			String sql = "select * from member where id = ?";
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
			
			String sql = "select * from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setId(rs.getString("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setBirth(rs.getInt("birth"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setMemberfile(rs.getString("memberfile"));				
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
					+ "NAME =?, "
					+ "BIRTH =?, "
					+ "PHONE =?, "
					+ "EMAIL =?, "
					+ "MEMBERFILE=? "
					+ " WHERE ID =?");
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

}
