drop table BBS_FCM cascade constraints;

create table BBS_FCM (
 FCM_NO                NUMBER PRIMARY KEY NOT NULL,                        --��۹�ȣ
 FCM_ID                VARCHAR2(30)  REFERENCES MEMBER(ID),                --����ۼ��� ���̵�
 FCM_CONTENT           VARCHAR2(200) NOT NULL,                             --��۳���
 FCM_REG_DATE          DATE                 ,                              --����ۼ���¥
 FCM_COMMENT_BOARD_NUM NUMBER REFERENCES BBS_FR(FR_NO) ON DELETE CASCADE,  --�����۹�ȣ(���������Ǹ� ���̻�����)
 FCM_COMMENT_RE_LEV    NUMBER,                                             --������̸�0 , ������� ���� �̸� 1 2
 FCM_COMMENT_RE_SEQ    NUMBER,                                              --������̸� 0
 FCM_COMMENT_RE_REF    NUMBER                                             --������̸� �ڽ� ��۹�ȣ, �����̸� ����� ��ȣ
 );

SELECT * FROM BBS_FCM;

--������ ����
CREATE SEQUENCE FCM_SEQ;

-- member�� �ִ� memberfile���� ��ȸ�� ���ô�.
 select bbs_fcm.*, member.memberfile
 From bbs_fcm inner join MEMBER
 on bbs_fcm.fcm_id = member.id
 where fcm_comment_board_num = 27
 order by fcm_comment_re_ref desc,
 fcm_comment_re_seq asc;
 
 --
 select count(*)
 from bbs_fcm where fcm_comment_board_num = 27;