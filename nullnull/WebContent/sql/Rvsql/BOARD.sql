drop table RVB cascade constraints;
CREATE TABLE RVB(
	RV_NO		NUMBER,			--�۹�ȣ
	USER_ID		VARCHAR2(30),	--�ۼ���
	RV_PASS		VARCHAR2(30),	--��й�ȣ
	RV_SUBJECT	VARCHAR2(300),	--����
	RV_CONTENT	VARCHAR2(4000),	--����
	RV_FILE		VARCHAR2(50),	--÷�ε� ���� ��
	RV_RE_REF	NUMBER,			--�亯 �� �ۼ��� �����Ǵ� ���� ��ȣ
	RV_RE_LEV	NUMBER,			--�亯 ���� ����
	RV_RE_SEQ	NUMBER,			--�亯 ���� ����
	RV_READ	NUMBER,			--���� ��ȸ��
	RV_DATE DATE default sysdate,			--���� �ۼ���¥ 
	PRIMARY KEY(RV_NO)
);

--Board_Ajax ������Ʈ���� �߰� -- 
alter table RVB
add (RV_CSFC VARCHAR2(50));

