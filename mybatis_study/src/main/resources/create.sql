create table user(
	id varchar(64) not null,
	password varchar(255) default null,
	username varchar(255) default null,
	primary key(id)
)ENGINE =InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf-8;

insert into user(id,password,username) values('1','123456','liangxp');
