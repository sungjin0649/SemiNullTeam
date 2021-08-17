drop table member;

create table MEMBER(
USER_ID		varchar2(20) not null,
USER_PASS	varchar2(20) not null,
USER_NAME	varchar2(20) not null,
USER_BIRTH	varchar2(20) not null,
USER_PHONE	varchar2(20) not null,
USER_EMAIL	varchar2(30) not null,
UESR_FILE	varchar2(50) not null,
primary key(USER_ID)
);

select * from member;