CREATE DATABASE springboot_system;
USE springboot_system;
create table articles (
	id int(11) not null auto_increment,
	content varchar(255) default '' COMMENT '文章内容',
	create_time timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP not null default CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
	delete_status varchar(1) DEFAULT '1' COMMENT '此文章状态是否有效, 1 有效, 0 无效',
	primary key (id)
) ENGINE=INNODB auto_increment=1 DEFAULT charset=utf8 COMMENT '用户新增文章表';
show tables;
INSERT into articles values (null,'莎士比亚',null,null,'1');
INSERT into articles values (null,'亚里士多德',null,null,'1'),(null,'李白',null,null,'1');
SELECT * from articles;

create table permisson (
	id int(11) not null default '0' COMMENT '自定id,方便前端通过序号排序展示权限列表',
	menu_code varchar(255) default '' COMMENT '归属菜单,前端判断并展示菜单使用',
	menu_name varchar(255) default '' COMMENT '菜单中文释义',
	permission_code varchar(255) default '' COMMENT '权限代码',
	permission_name varchar(255) default '' COMMENT '权限中文名',
	required_permission TINYINT(1) default '2' COMMENT '本菜单是否是必须权限, 1 必选,2 非必选',
	PRIMARY key (id)
) ENGINE=INNODB default charset=utf8 row_format=compact COMMENT='后台权限表';

insert into permisson values (10,'article','文章管理权限','article:list','列表',1),(11,'article','文章管理权限','article:add','新增',2);
insert into permisson values (12,'article','文章管理权限','article:update','修改',2);
insert into permisson values (13,'user','用户管理权限','user:list','显示列表',1);
insert into permisson values (14,'user','用户管理权限','user:add','新增',2);
insert into permisson values (15,'user','用户管理权限','user:update','修改',2);
insert into permisson values (16,'role','角色权限','role:list','显示列表',1);
insert into permisson values (17,'role','角色权限','role:add','新增',2);
insert into permisson values (18,'role','角色权限','role:update','修改',2);
insert into permisson values (19,'role','角色权限','role:delete','删除',2);

create table roles (
	id int(11) not null auto_increment comment '角色id',
	role_name varchar(20) default null comment '角色名称',
	create_time timestamp null default CURRENT_TIMESTAMP,
	update_time timestamp null default current_timestamp on update CURRENT_TIMESTAMP,
	delete_status varchar(1) default '1' COMMENT '是否有效, 1 有效, 0无效',
	primary key (id)
)ENGINE=INNODB auto_increment=3 default charset=utf8 comment '系统角色表';

insert into roles values (1,'管理员',null,NULL, '1'), (2,'开发人员', '2020-3-12 09:10:30',null, '1');

CREATE TABLE `roles_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效 1有效     2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表';

INSERT INTO `roles_permission` VALUES (1,1,10,'2017-11-22 16:26:21','2017-11-22 16:26:32','1'),(2,1,11,'2017-11-22 16:26:21','2017-11-22 16:26:32','1');
INSERT INTO `roles_permission` VALUES (3,1,12,'2017-11-22 16:26:21','2017-11-22 16:26:32','1');
INSERT INTO `roles_permission` VALUES (4,1,13,'2017-11-22 16:26:21','2017-11-22 16:26:32','1');
INSERT INTO `roles_permission` VALUES (5,1,14,'2017-11-22 16:26:21','2017-11-22 16:26:32','1');
INSERT INTO `roles_permission` VALUES (6,1,15,'2017-11-22 16:26:21','2017-11-22 16:26:32','1');

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `role_id` int(11) DEFAULT '0' COMMENT '角色ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

INSERT INTO `users` VALUES (1000,'admin','123456','管理员',1,'2017-10-30 11:52:38','2017-11-17 23:51:40','1');
INSERT INTO `users` VALUES (1001,'developer','123456','网站开发人员',2,'2017-10-30 11:52:38','2017-11-17 23:51:40','1');