DROP TABLE BBS_FR
CREATE TABLE BBS_FR (
   FR_NO          NUMBER DEFAULT 0 PRIMARY KEY          NOT NULL,                --�۹�ȣ
   FR_PASS        VARCHAR2(20)                NOT NULL,                --��й�ȣ
   FR_CSFC        NUMBER                      NOT NULL,                --�з�
   FR_SUBJECT     VARCHAR2(300)               NOT NULL,                --����
   ID             VARCHAR2(20)   REFERENCES MEMBER(ID),                --���̵�
   FR_DATE        DATE   DEFAULT SYSDATE      NOT NULL,                --��¥
   FR_READ        NUMBER DEFAULT 0            NOT NULL,                --��ȸ��
   FR_CONTENT     VARCHAR2(4000)                      ,                --����                        
   FR_FILE        VARCHAR2(50)                        ,                --÷�����ϸ�
   FR_RE_REF      NUMBER DEFAULT 0                    ,                --�亯 �� ���� �۹�ȣ
   FR_RE_LEV      NUMBER DEFAULT 0                    ,                --�亯 ���� ����
   FR_RE_SEQ      NUMBER DEFAULT 0                                    --�亯 ���� ����
);

SELECT * FROM BBS_FR;

