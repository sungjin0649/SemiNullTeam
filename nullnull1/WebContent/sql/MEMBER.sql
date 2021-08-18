create table member(
 id varchar2(20),
 pass varchar2(20) NOT NULL,
 name varchar2(20) NOT NULL,
 birth varchar2(20) NOT NULL,
 phone varchar2(20) NOT NULL,
 email varchar2(30) NOT NULL,
 memberfile varchar2(50) NOT NULL,
 PRIMARY KEY(id)
);
select * from member;
drop table member cascade constraints;
insert into member values('admin', '1234', '°ü¸®ÀÚ', '4/30','010-6322-4825','ja97324@hanmail.net','image')