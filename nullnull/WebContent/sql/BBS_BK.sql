sys as sysdba 입력후 비밀번호입력

CREATE USER semi IDENTIFIED BY 1234
GRANT CONNECT, DBA, RESOURCE TO semi;
GRANT DROP ANY TABLE TO semi;

CREATE TABLE BBS_BK(
	BK_NO		NUMBER(20),
	BK_PASS		VARCHAR2(20) NOT NULL,
	BK_CSFC		VARCHAR2(14) NOT NULL,
	BK_SUBJECT 	VARCHAR2(40) NOT NULL,
	BK_PRICE	NUMBER,
	USER_ID		VARCHAR2(20) NOT NULL REFERENCES member(USER_ID) ON DELETE CASCADE,
	BK_DATE		DATE NOT NULL,
	BK_READ		NUMBER NOT NULL,
	BK_CONTENT	VARCHAR2(4000),
	BK_FILE		VARCHAR2(50),
	BK_RE_REF	NUMBER,
	BK_RE_LEV	NUMBER,
	BK_RE_SEQ	NUMBER,
	PRIMARY KEY(BK_NO)
);

	drop sequence bk_seq;
	
--시퀸스를 생성합니다.
	create sequence bk_seq;
	select *from bbs_bk
	delete from bbs_bk 
	drop table bbs_bk
insert into BBS_BK(BK_NO, BK_PASS, BK_CSFC, BK_SUBJECT,
BK_PRICE, USER_ID, BK_DATE, BK_READ, BK_CONTENT, BK_FILE, BK_RE_REF, BK_RE_LEV, BK_RE_SEQ)
					 values(bk_seq.NEXTVAL,'123','팔아요','제목',
					6000,'admin',SYSDATE,
							0,'내용','파일',
							bk_seq.NEXTVAL,0,0);
							
							
select * from 
(select rownum rnum, BK_NO , BK_PASS,
BK_CSFC, BK_SUBJECT, BK_PRICE,
USER_ID, BK_DATE, BK_READ,
BK_CONTENT, BK_FILE, BK_RE_REF,
BK_RE_LEV, BK_RE_SEQ FROM 
	(select * from BBS_BK 
	where BK_SUBJECT like '%무료%' or BK_CONTENT like '%1%' 
	order by BK_RE_REF desc, 
	BK_RE_SEQ asc)
	) 
	 where rnum >= 1 and rnum<= 10 ;
	 

	 