package Rv.db;

public class RvBean {
	
	private int RV_NO;			//글번호
	private String USER_ID;		//글작성자
	private String RV_PASS;		//글 비밀번호
	private String RV_SUBJECT; 	//글 제목
	private String RV_CONTENT;	//글 내용
	private String RV_FILE;		//첨부될 파일의 이름
	private int	RV_RE_REF;		//답변 글 작성시 참조되는 글의번호
	private int RV_RE_LEV;		//답변 글의 깊이 
	private int RV_RE_SEQ;		//답변 글의 순서
	private int RV_READ;	//글의 조회수
	private String RV_DATE;
	
	
	public int getRV_NO() {
		return RV_NO;
	}
	public void setRV_NO(int rV_NO) {
		RV_NO = rV_NO;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getRV_PASS() {
		return RV_PASS;
	}
	public void setRV_PASS(String rV_PASS) {
		RV_PASS = rV_PASS;
	}
	public String getRV_SUBJECT() {
		return RV_SUBJECT;
	}
	public void setRV_SUBJECT(String rV_SUBJECT) {
		RV_SUBJECT = rV_SUBJECT;
	}
	public String getRV_CONTENT() {
		return RV_CONTENT;
	}
	public void setRV_CONTENT(String rV_CONTENT) {
		RV_CONTENT = rV_CONTENT;
	}
	public String getRV_FILE() {
		return RV_FILE;
	}
	public void setRV_FILE(String rV_FILE) {
		RV_FILE = rV_FILE;
	}
	public int getRV_RE_REF() {
		return RV_RE_REF;
	}
	public void setRV_RE_REF(int rV_RE_REF) {
		RV_RE_REF = rV_RE_REF;
	}
	public int getRV_RE_LEV() {
		return RV_RE_LEV;
	}
	public void setRV_RE_LEV(int rV_RE_LEV) {
		RV_RE_LEV = rV_RE_LEV;
	}
	public int getRV_RE_SEQ() {
		return RV_RE_SEQ;
	}
	public void setRV_RE_SEQ(int rV_RE_SEQ) {
		RV_RE_SEQ = rV_RE_SEQ;
	}
	public int getRV_READ() {
		return RV_READ;
	}
	public void setRV_READ(int rV_READ) {
		RV_READ = rV_READ;
	}
	public String getRV_DATE() {
		return RV_DATE;
	}
	public void setRV_DATE(String rV_DATE) {
		RV_DATE = rV_DATE;
	}

	

	

}
