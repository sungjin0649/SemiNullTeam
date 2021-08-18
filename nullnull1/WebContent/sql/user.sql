drop table member;

create table member(
  id       varchar2(20),
  pass     varchar2(20),
  name     varchar2(20),
  birth    varchar2(20),
  phone    varchar2(20),
  email    varchar2(30),
  memberfile    varchar2(50),
  PRIMARY KEY(id)
);
 
select * from member;

create table member_copy
as
select * from member;

delete from member where id='admin';
