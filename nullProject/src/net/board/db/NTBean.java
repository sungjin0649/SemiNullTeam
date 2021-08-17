package net.board.db;


public class NTBean  {
	private int 		nt_no;
	private String 		nt_pass;
	private String 		nt_subject;
	private String 		user_id;
	private String 		nt_date;
	private int 		nt_read;
	private String		nt_content;
	private String 		nt_file;
	
	public int getNt_no() {
		return nt_no;
	}
	public void setNt_no(int nt_no) {
		this.nt_no = nt_no;
	}
	public String getNt_pass() {
		return nt_pass;
	}
	public void setNt_pass(String nt_pass) {
		this.nt_pass = nt_pass;
	}
	public String getNt_subject() {
		return nt_subject;
	}
	public void setNt_subject(String nt_subject) {
		this.nt_subject = nt_subject;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNt_date() {
		return nt_date;
	}
	public void setNt_date(String nt_date) {
		this.nt_date = nt_date;
				//.substring(0,10);
	}
	public int getNt_read() {
		return nt_read;
	}
	public void setNt_read(int nt_read) {
		this.nt_read = nt_read;
	}
	public String getNt_content() {
		return nt_content;
	}
	public void setNt_content(String nt_content) {
		this.nt_content = nt_content;
	}
	public String getNt_file() {
		return nt_file;
	}
	public void setNt_file(String nt_file) {
		this.nt_file = nt_file;
	}
	
}
       
 