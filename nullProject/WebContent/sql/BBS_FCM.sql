drop table BBS_FCM cascade constraints;

create table BBS_FCM (
 FCM_NO                NUMBER PRIMARY KEY NOT NULL,                        --댓글번호
 FCM_ID                VARCHAR2(30)  REFERENCES MEMBER(ID),                --댓글작성자 아이디
 FCM_CONTENT           VARCHAR2(200) NOT NULL,                             --댓글내용
 FCM_REG_DATE          DATE                 ,                              --댓글작성잘짜
 FCM_COMMENT_BOARD_NUM NUMBER REFERENCES BBS_FR(FR_NO) ON DELETE CASCADE,  --원문글번호(원문삭제되면 같이삭제됨)
 FCM_COMMENT_RE_LEV    NUMBER,                                             --원댓글이면0 , 윈댓글의 대댓글 이면 1 2
 FCM_COMMENT_RE_SEQ    NUMBER,                                              --원댓글이면 0
 FCM_COMMENT_RE_REF    NUMBER                                             --원댓글이면 자신 댓글번호, 대댓글이면 원댓글 번호
 );

SELECT * FROM BBS_FCM;

--시퀀스 생성
CREATE SEQUENCE FCM_SEQ;

-- member에 있는 memberfile까지 조회해 봅시다.
 select bbs_fcm.*, member.memberfile
 From bbs_fcm inner join MEMBER
 on bbs_fcm.fcm_id = member.id
 where fcm_comment_board_num = 27
 order by fcm_comment_re_ref desc,
 fcm_comment_re_seq asc;
 
 --
 select count(*)
 from bbs_fcm where fcm_comment_board_num = 27;