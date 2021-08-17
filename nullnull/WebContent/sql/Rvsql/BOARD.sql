drop table RVB cascade constraints;
CREATE TABLE RVB(
	RV_NO		NUMBER,			--글번호
	USER_ID		VARCHAR2(30),	--작성자
	RV_PASS		VARCHAR2(30),	--비밀번호
	RV_SUBJECT	VARCHAR2(300),	--제목
	RV_CONTENT	VARCHAR2(4000),	--내용
	RV_FILE		VARCHAR2(50),	--첨부될 파일 명
	RV_RE_REF	NUMBER,			--답변 글 작성시 참조되는 글의 번호
	RV_RE_LEV	NUMBER,			--답변 글의 깊이
	RV_RE_SEQ	NUMBER,			--답변 글의 순서
	RV_READ	NUMBER,			--글의 조회수
	RV_DATE DATE default sysdate,			--글의 작성날짜 
	PRIMARY KEY(RV_NO)
);

--Board_Ajax 프로젝트에서 추가 -- 
alter table RVB
add (RV_CSFC VARCHAR2(50));

