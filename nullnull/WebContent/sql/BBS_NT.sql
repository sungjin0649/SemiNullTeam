drop table bbs_nt;

create table bbs_nt(
nt_no		number			not null,
nt_pass		varchar2(20)	not null,
nt_subject 	varchar2(40)	not null,
user_id		varchar2(20)	not null,
nt_date		date			not null,
nt_read		number			not null,
nt_content	varchar2(4000),
nt_file		varchar2(50),
primary key(nt_no),
foreign key(user_id) references member(user_id)
);

select * from bbs_nt;

select count(*) from bbs_nt

delete from bbs_nt;

select rownum, t.*
	from(
		select nt_no, nt_subject,
		user_id, nt_content,
		nt_date, nt_file, nt_read
		from BBS_NT
		order by nt_date desc
		)t
		where rownum>=1 and rownum<=8
		order by rownum asc;
						
						
					

