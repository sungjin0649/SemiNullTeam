drop table BBS_BCM cascade constraints;
create table BBS_BCM(
	BCM_NO				number			primary key,
	BCM_ID				varchar2(30) references member(id),
	BCM_CONTENT			varchar2(200),
	BCM_REG_DATE		date,
	BCM_COMMENT_BOARD_NUM number	references BBS_BK(BK_NO) on delete cascade,
												--comm 테이블이 참조하는 보드 글 번호
	BCM_COMMENT_RE_REF	number, --원문이면 0
	BCM_COMMENT_RE_LEV number(1) check(BCM_COMMENT_RE_LEV in (0, 1,2)), -- 원문이면0 답글이면 1
	BCM_COMMENT_RE_SEQ number	--원문은 자신 글번호, 답글이면 원문 글번호
	);
-- 게시판 글이 삭제되면 참조하는 댓글도 삭제됩니다. --
	
	drop sequence BCM_seq;
	
--시퀸스를 생성합니다.
	create sequence BCM_seq;
	
select * from BBS_BCM order by BCM_NO desc;
delete BBS_BCM;
insert into BBS_BCM values(1,'admin','안녕하세요',SYSDATE,1,BCM_seq.NEXTVAL,0,0);
--member에 있는 memberfile까지 조회해 봅시다.

select BCM_NO, BBS_BCM.BCM_ID, BCM_CONTENT, BCM_REG_DATE, BCM_COMMENT_RE_REF, 
				 BCM_COMMENT_RE_LEV, 
				 BCM_COMMENT_RE_SEQ, member.memberfile 
				 from BBS_BCM join member 
				 on BBS_BCM.BCM_ID=member.id 
				 where BCM_COMMENT_BOARD_NUM = 1 
				 order by BCM_COMMENT_RE_REF desc, 
				 BCM_COMMENT_RE_SEQ asc;
	
select * from 
	(select rownum rnum, num, id,
	content, reg_date, comment_board_num,
	comment_re_lev, comment_re_seq, comment_re_ref 
		FROM 
				(select * from comm where comment_board_num =9  
				order by comment_re_ref desc, 
				comment_re_seq asc)
				) 
				 where rnum >= 1 and rnum<= 10;