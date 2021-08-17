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
	
	// �����ڿ��� JNDI ���ҽ��� �����Ͽ� Connection ��ü�� ���ɴϴ�.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}
	
	//ȸ������
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
			result = pstmt.executeUpdate(); //���� ������ result�� 1
			
		//primary key �������� �������� ��� �߻��ϴ� ����	
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("��� ���̵� �ߺ� �����Դϴ�.");
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
	
	//�α��ν� ���̵� ��й�ȣ �´��� Ȯ��
	public int ckidpw(String id, String pass) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;		
		int result=-1; //id Ȥ�� ��й�ȣ ����
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
			System.out.println("ckidpw ����: " + ex);
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
	}// ckidpw ����
	
//	// ���̵� ��й�ȣ ã��
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
//			System.out.println(rs.getString(1));//���̵�
//			System.out.println(rs.getString(2));//��й�ȣ
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
//			System.out.println("findidpw ����: " + ex);
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
//	}//findidpw ����  (�迭������ �޾ƿ��� ������ ���� ���� �ʾƼ� �ϴ� id ���� pass ���� �޾ƿԽ��ϴ�...)
	
	//idã��
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
			System.out.println("findid ����: " + ex);
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
	
	//��й�ȣã��
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
			System.out.println("findid ����: " + ex);
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
		int result=-1;//DB�� �ش� id�� �����ϴ�
		try {
			con = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 0; //DB�� �ش� id�� �ֽ��ϴ�.
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

	//ȸ������ ����
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
	
	//ȸ������ ����
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
			result = pstmt.executeUpdate(); //���� ������ result�� 1
			
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
