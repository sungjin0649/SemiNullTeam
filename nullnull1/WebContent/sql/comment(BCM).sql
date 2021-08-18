drop table BBS_BCM cascade constraints;
create table BBS_BCM(
	BCM_NO				number			primary key,
	BCM_ID				varchar2(30) references member(id),
	BCM_CONTENT			varchar2(200),
	BCM_REG_DATE		date,
	BCM_COMMENT_BOARD_NUM number	references BBS_BK(BK_NO) on delete cascade,
												--comm ���̺��� �����ϴ� ���� �� ��ȣ
	BCM_COMMENT_RE_REF	number, --�����̸� 0
	BCM_COMMENT_RE_LEV number(1) check(BCM_COMMENT_RE_LEV in (0, 1,2)), -- �����̸�0 ����̸� 1
	BCM_COMMENT_RE_SEQ number	--������ �ڽ� �۹�ȣ, ����̸� ���� �۹�ȣ
	);
-- �Խ��� ���� �����Ǹ� �����ϴ� ��۵� �����˴ϴ�. --
	
	drop sequence BCM_seq;
	
--�������� �����մϴ�.
	create sequence BCM_seq;
	
select * from BBS_BCM order by BCM_NO desc;
delete BBS_BCM;
insert into BBS_BCM values(1,'admin','�ȳ��ϼ���',SYSDATE,1,BCM_seq.NEXTVAL,0,0);
--member�� �ִ� memberfile���� ��ȸ�� ���ô�.

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