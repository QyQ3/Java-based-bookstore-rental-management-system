CREATE DATABASE SDZL

--���λ��
on
(
	name = SDZL_data,
	filename = 'D:\SDZL\SDZL.mdf',
	size = 10MB,
	filegrowth = 20%
)
log on
(
	name = SDZL_log,
	filename = 'D:\SDZL\SDZL.ldf',
	size = 3MB,
	maxsize = 10MB,
	filegrowth = 2MB
)

go
use SDZL

--����
CREATE TABLE HYXX(
	Hnum int NOT NULL PRIMARY KEY identity,
	Hname varchar(20) NOT NULL,
	sex char(2) NOT NULL CHECK (sex = '��' or sex = 'Ů') DEFAULT '��',
	zhanghu money NOT NULL,
	Ddate datetime NOT NULL,
	num varchar(50) NOT NULL UNIQUE
);
CREATE TABLE SJXX(
	Bnum int NOT NULL PRIMARY KEY identity,
	book varchar(20) NOT NULL,
	Bname varchar(20) NOT NULL,
	chubanshe varchar(50) NULL,
	jiage money NOT NULL,
	kucun int NOT NULL,
	Bbeizhu varchar(50) NULL
);
CREATE TABLE ZLXX(
	Znum int NOT NULL PRIMARY KEY identity,
	Hnum int NOT NULL references HYXX(Hnum),
	Hname varchar(20) NOT NULL,
	Bnum int NOT NULL references SJXX(Bnum),
	book varchar(20) NOT NULL,
	Bname varchar(20) NOT NULL,
	chubanshe varchar(50) NULL,
	Jdate datetime NOT NULL,
	Hdate datetime NULL,
	jiage money NOT NULL,
	Zbeizhu varchar(50) NULL,
);
CREATE TABLE GLYXX(
	Gnum int NOT NULL PRIMARY KEY identity,
	account varchar(40) NOT NULL,
	Gpassword varchar(40) NOT NULL
);
CREATE TABLE SRXX(
	Snum int NOT NULL PRIMARY KEY identity,
	Znum int NOT NULL references ZLXX(Znum),
	Hnum int NOT NULL references HYXX(Hnum),
	Bnum int NOT NULL references SJXX(Bnum),
	Sdate datetime NOT NULL,
	sr money NOT NULL
);

--������
create unique index HYXX_num on HYXX(Hnum);
create unique index SJXX_num on SJXX(Bnum);
create unique index ZLXX_num on ZLXX(Znum);
create unique index GLYXX_num on GLYXX(Gnum);
create unique index SRXX_num on SRXX(Snum);

--������
go
create trigger SR on ZLXX 
after update
as
begin
	declare @znum int
	declare @hnum int
	declare @bnum int
	declare @hdate datetime
	declare @sr money
	select @znum=Znum,@hnum=Hnum,@bnum=Bnum,@hdate=Hdate,@sr=jiage from inserted
	insert into SRXX values (@znum,@hnum,@bnum,@hdate,@sr)
	update HYXX set zhanghu=zhanghu-@sr where Hnum=@hnum
end

go
create trigger XG on ZLXX 
instead of delete
as
begin
	declare @znum int
	select @znum=Znum from deleted
	delete from SRXX where Znum=@znum
	delete from ZLXX where Znum=@znum
end

--��ͼ
go
create view XX
as 
	select ZLXX.Znum,HYXX.Hnum,HYXX.Hname,SJXX.Bnum,SJXX.book,ZLXX.Jdate,ZLXX.Hdate,ZLXX.jiage,ZLXX.Zbeizhu
	from HYXX,SJXX,ZLXX
	where HYXX.Hnum = ZLXX.Hnum AND SJXX.Bnum = ZLXX.Bnum

--�洢����
--��ӻ�Ա
go
CREATE procedure addh
	@Hname varchar(20),@sex char(2),@zhanghu money,@num varchar(50)
as
	begin
		INSERT INTO HYXX values (@Hname,@sex,@zhanghu,GETDATE(),@num);
	end

--�޸Ļ�Ա��Ϣ
go
CREATE procedure modh
	@Hnum int,@Hname varchar(20),@sex char(2),@zhanghu money,@num varchar(50)
as
	begin
		update HYXX set Hname=@Hname,sex=@sex,zhanghu=@zhanghu,num=@num
		where Hnum=@Hnum;
	end

--ɾ����Ա��Ϣ
go
CREATE procedure delh
	@Hnum int
as
	begin
		delete from HYXX where Hnum=@Hnum
	end

--����鼮
go
CREATE procedure addb
	@book varchar(20),@Bname varchar(20),@chubanshe varchar(50),@jiage money,@kucun int,@Bbeizhu varchar(50)
as
	begin
		INSERT INTO SJXX values (@book,@Bname,@chubanshe,@jiage,@kucun,@Bbeizhu)
	end

--�޸��鼮��Ϣ
go
CREATE procedure modb
	@Bnum int,@book varchar(20),@Bname varchar(20),@chubanshe varchar(50),@jiage money,@kucun int,@Bbeizhu varchar(50)
as
	begin
		update SJXX 
		set book=@book,Bname=@Bname,chubanshe=@chubanshe,jiage=@jiage,kucun=@kucun,Bbeizhu=@Bbeizhu
		where Bnum=@Bnum;
	end

--ɾ���鼮
go
CREATE procedure delb
	@Bnum int
as
	begin
		delete from SJXX where Bnum=@Bnum
	end

--����
go
CREATE procedure jbook
	@Hnum int,@Hname varchar(20),@Bnum int,@book varchar(20),@Zbeizhu varchar(50)
as
	begin
		INSERT INTO ZLXX values (@Hnum,@Hname,@Bnum,@book,
								(select Bname from SJXX where Bnum=@Bnum),
								(select chubanshe from SJXX where Bnum=@Bnum),
								GETDATE(),null,(select jiage from SJXX where Bnum=@Bnum),
								@Zbeizhu)
		update SJXX set kucun=kucun-1 where Bnum=@Bnum;
	end

--����
go
CREATE procedure hbook
	@Znum int,@Bnum int
as
	begin
		update ZLXX set Hdate=GETDATE() where Znum=@Znum;
		update SJXX set kucun=kucun+1 where Bnum=@Bnum;
	end

--ɾ��������Ϣ
go
CREATE procedure dbook
	@Znum int,@Bnum int
as
	begin
		if((select Hdate from ZLXX where Znum=@Znum) is null )
		begin
			update SJXX set kucun=kucun+1 where Bnum=@Bnum;
			delete from ZLXX where Znum=@Znum;
		end
		else
			delete from ZLXX where Znum=@Znum;
	end

go
--��ʼ��������
INSERT INTO HYXX values ('ϵͳ��Ա','��',99999,'2022-6-14','15028950491');
INSERT INTO HYXX values ('С��','��',99,'2022-6-14','12345678910');
INSERT INTO HYXX values ('С��','Ů',199,'2022-6-14','22345678910');

INSERT INTO SJXX values ('C����','̷��ǿ','������������',20,10,'');
INSERT INTO SJXX values ('����ԭ��','����ԭ','�廪��ѧ������',18,10,'');
INSERT INTO SJXX values ('���ݽṹ','����','������������',40,20,'');
INSERT INTO SJXX values ('�㷨����','����','���ӹ�ҵ������',30,20,'');
INSERT INTO SJXX values ('Android','����','������������',40,10,'');
INSERT INTO SJXX values ('�����ͼ��ѧ','����','���ӹ�ҵ������',30,20,'');

INSERT INTO ZLXX values (1,'ϵͳ��Ա',1,'C����','̷��ǿ','������������','2022-5-20','2022-5-21',20,'');
INSERT INTO ZLXX values (2,'С��',4,'�㷨����','����','���ӹ�ҵ������','2022-5-20',null,30,'');
INSERT INTO ZLXX values (3,'С��',3,'���ݽṹ','����','������������','2022-5-20',null,40,'');

INSERT INTO SRXX values (1,1,1,'2022-5-21',20)

INSERT INTO GLYXX values ('AYQ','123456');

--������¼�û�
create LOGIN AYQ
WITH password='123456',
DEFAULT_DATABASE=SDZL;

--�������ݿ��û�
create USER GLY
FROM LOGIN AYQ
WITH DEFAULT_SCHEMA = dbo;

--�������ݿ��û�GLY�����ݿ�������Ȩ��
exec sp_addrolemember 'db_owner','GLY'



