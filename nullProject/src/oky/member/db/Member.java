package oky.member.db;
public class Member {
	private String  id       ;
	private String  pass ;
	private String  name     ;
	private int     birth     ;
	private String  phone   ;
	private String  email    ;
	private String  memberfile;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemberfile() {
		return memberfile;
	}
	public void setMemberfile(String memberfile) {
		this.memberfile = memberfile;
	}
	
	
}
