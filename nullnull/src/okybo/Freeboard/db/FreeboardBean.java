package okybo.Freeboard.db;

public class FreeboardBean {
	private int fr_no; //�۹�ȣ
	private String fr_pass; //�� ��й�ȣ
	private int fr_csfc;  //�з�
	private String fr_subject; //����
	private String id;   //���̵�
	private String fr_date; //��¥
	private int fr_read;  //��ȸ��
	private String fr_content;  //����
	private String fr_file;  //÷�����ϸ�
	private int fr_re_ref;    //�亯 �� ���� �۹�ȣ
	private int fr_re_lev;    //�亯 ���� ����	
	private int fr_re_seq;    //�亯 ���� ����
	
	
	public int getFr_no() {
		return fr_no;
	}
	public void setFr_no(int fr_no) {
		this.fr_no = fr_no;
	}
	public String getFr_pass() {
		return fr_pass;
	}
	public void setFr_pass(String fr_pass) {
		this.fr_pass = fr_pass;
	}
	public int getFr_csfc() {
		return fr_csfc;
	}
	public void setFr_csfc(int fr_csfc) {
		this.fr_csfc = fr_csfc;
	}
	public String getFr_subject() {
		return fr_subject;
	}
	public void setFr_subject(String fr_subject) {
		this.fr_subject = fr_subject;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFr_date() {
		return fr_date;
	}
	public void setFr_date(String fr_date) {
		this.fr_date = fr_date.substring(0,10);
	}
	public int getFr_read() {
		return fr_read;
	}
	public void setFr_read(int fr_read) {
		this.fr_read = fr_read;
	}
	public String getFr_content() {
		return fr_content;
	}
	public void setFr_content(String fr_content) {
		this.fr_content = fr_content;
	}
	public String getFr_file() {
		return fr_file;
	}
	public void setFr_file(String fr_file) {
		this.fr_file = fr_file;
	}
	public int getFr_re_ref() {
		return fr_re_ref;
	}
	public void setFr_re_ref(int fr_re_ref) {
		this.fr_re_ref = fr_re_ref;
	}
	public int getFr_re_lev() {
		return fr_re_lev;
	}
	public void setFr_re_lev(int fr_re_lev) {
		this.fr_re_lev = fr_re_lev;
	}
	public int getFr_re_seq() {
		return fr_re_seq;
	}
	public void setFr_re_seq(int fr_re_seq) {
		this.fr_re_seq = fr_re_seq;
	}
	
	
}
