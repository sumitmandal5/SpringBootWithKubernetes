use  test;

#display the name of current database
SELECT DATABASE()

create table subject_stream(id integer primary key,name varchar(150),description varchar(250))
select * from subject_stream 

create table sports(id integer primary key AUTO_INCREMENT,name varchar(150),description varchar(250))
select * from sports

create table student(id integer auto_increment  primary key, name varchar(150) NOT NULL,stream_id integer,sports_id integer,class integer,
CONSTRAINT FK_stream_id FOREIGN KEY(stream_id)  REFERENCES subject_stream(id), 
CONSTRAINT FK_sports_id FOREIGN KEY(sports_id)  REFERENCES sports(id))
select * from student

insert into subject_stream values(1001,'science','science stream');
insert into subject_stream values(1002,'humanities','humanities stream');
insert into subject_stream values(1003,'commerce','commerce stream');
select * from subject_stream 

insert into sports values(5001,'cricket','cricket');
insert into sports values(5002,'football','football');
insert into sports values(5003,'hockey','hockey');
select * from sports 

insert into student values(1,'Steve Smith',1001,5001,12)
insert into student values(2,'Brian Lara',1002,5001,12);
insert into student values(3,'Sachin Tendulkar',1001,5001,11);
insert into student values(4,'David Bekham',1003,5002,12);
insert into student values(5,'Dhanraj Pillai',1002,5003,10);
select * from student;
