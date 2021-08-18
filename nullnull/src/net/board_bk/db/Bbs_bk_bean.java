package net.board_bk.db;

public class Bbs_bk_bean {
	private int BK_NO;
	private String BK_PASS;
	private String BK_CSFC;
	private String BK_SUBJECT;
	private int BK_PRICE;
	private String USER_ID;
	private String BK_DATE;
	private int BK_READ;
	private String BK_CONTENT;
	private String BK_FILE;
	private int BK_RE_REF;
	private int BK_RE_LEV;
	private int BK_RE_SEQ;
	public int getBK_NO() {
		return BK_NO;
	}
	public void setBK_NO(int bK_NO) {
		BK_NO = bK_NO;
	}
	public String getBK_PASS() {
		return BK_PASS;
	}
	public void setBK_PASS(String bK_PASS) {
		BK_PASS = bK_PASS;
	}
	public String getBK_CSFC() {
		return BK_CSFC;
	}
	public void setBK_CSFC(String bK_CSFC) {
		BK_CSFC = bK_CSFC;
	}
	public String getBK_SUBJECT() {
		return BK_SUBJECT;
	}
	public void setBK_SUBJECT(String bK_SUBJECT) {
		BK_SUBJECT = bK_SUBJECT;
	}
	public int getBK_PRICE() {
		return BK_PRICE;
	}
	public void setBK_PRICE(int bK_PRICE) {
		BK_PRICE = bK_PRICE;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getBK_DATE() {
		return BK_DATE.substring(0,10);
	}
	public void setBK_DATE(String bK_DATE) {
		BK_DATE = bK_DATE;
	}
	public int getBK_READ() {
		return BK_READ;
	}
	public void setBK_READ(int bK_READ) {
		BK_READ = bK_READ;
	}
	public String getBK_CONTENT() {
		return BK_CONTENT;
	}
	public void setBK_CONTENT(String bK_CONTENT) {
		BK_CONTENT = bK_CONTENT;
	}
	public String getBK_FILE() {
		return BK_FILE;
	}
	public void setBK_FILE(String bK_FILE) {
		BK_FILE = bK_FILE;
	}
	public int getBK_RE_REF() {
		return BK_RE_REF;
	}
	public void setBK_RE_REF(int bK_RE_REF) {
		BK_RE_REF = bK_RE_REF;
	}
	public int getBK_RE_LEV() {
		return BK_RE_LEV;
	}
	public void setBK_RE_LEV(int bK_RE_LEV) {
		BK_RE_LEV = bK_RE_LEV;
	}
	public int getBK_RE_SEQ() {
		return BK_RE_SEQ;
	}
	public void setBK_RE_SEQ(int bK_RE_SEQ) {
		BK_RE_SEQ = bK_RE_SEQ;
	}
	
	
	
	
}
