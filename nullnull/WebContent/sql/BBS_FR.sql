DROP TABLE BBS_FR cascade constraints;
CREATE TABLE BBS_FR (
   FR_NO          NUMBER DEFAULT 0 PRIMARY KEY          NOT NULL,                --글번호
   FR_PASS        VARCHAR2(20)                NOT NULL,                --비밀번호
   FR_CSFC        NUMBER                      NOT NULL,                --분류
   FR_SUBJECT     VARCHAR2(300)               NOT NULL,                --제목
   USER_ID             VARCHAR2(20)   REFERENCES MEMBER(USER_ID) ON DELETE CASCADE,                --아이디
   FR_DATE        DATE   DEFAULT SYSDATE      NOT NULL,                --날짜
   FR_READ        NUMBER DEFAULT 0            NOT NULL,                --조회수
   FR_CONTENT     VARCHAR2(4000)                      ,                --내용                        
   FR_FILE        VARCHAR2(50)                        ,                --첨부파일명
   FR_RE_REF      NUMBER DEFAULT 0                    ,                --답변 글 참조 글번호
   FR_RE_LEV      NUMBER DEFAULT 0                    ,                --답변 글의 깊이
   FR_RE_SEQ      NUMBER DEFAULT 0                                    --답변 글의 순서
);


SELECT * FROM BBS_FR;

