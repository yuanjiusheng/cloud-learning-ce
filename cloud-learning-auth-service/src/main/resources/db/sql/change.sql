--liquibase formatted sql

--changeset bill:201906181
create table t_role
(
  id bigint auto_increment comment '主键id',
  code varchar(30) not null comment '编号',
  name nvarchar(30) not null default '' comment '角色名',
  remark nvarchar(255) default '' comment '备注',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '角色表';

--changeset bill:201906182
create table t_authority
(
  id bigint auto_increment comment '主键id',
  name nvarchar(30) not null default '' comment '权限名',
  alias nvarchar(30) not null default '' comment '权限别名',
  pid bigint not null default 0 comment '上级权限id',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '权限表';

--changeset bill:201906183
create table t_role_authority
(
  id bigint auto_increment comment '主键id',
  role_id bigint not null comment '角色id',
  authority_id bigint not null comment '权限id',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '角色权限表';

--changeset bill:201906184
create table t_user_role
(
  id bigint auto_increment comment '主键id',
  user_id bigint not null comment '用户id',
  role_id bigint not null comment '角色id',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '用户角色表';

--changeset bill:201906185
INSERT INTO t_role (id, code, name, remark) VALUES (1, 'super_admin', '超级管理员', '超级管理员');
INSERT INTO t_user_role (id, user_id, role_id) VALUES (1, 0, 1);
INSERT INTO t_authority (id, name, alias, pid) VALUES (1,'organizational', '组织架构', 0);
INSERT INTO t_authority (id, name, alias, pid) VALUES (2,'organizational_user', '用户管理', 1);

--changeset bill:201906191
INSERT INTO t_authority (id, name, alias, pid) VALUES (3,'authority', '权限认证', 0);
INSERT INTO t_authority (id, name, alias, pid) VALUES (4,'authority_authority', '权限管理', 3);
INSERT INTO t_authority (id, name, alias, pid) VALUES (5,'authority_role', '角色管理', 3);

--changeset bill:201906192
INSERT INTO t_authority (id, name, alias, pid) VALUES (6,'setting', '系统设置', 0);
INSERT INTO t_authority (id, name, alias, pid) VALUES (7,'setting_carousel', '首页导航栏', 6);

--changeset bill:201906186
INSERT INTO t_authority (id, name, alias, pid) VALUES (8,'member', '会员管理', 0);
INSERT INTO t_authority (id, name, alias, pid) VALUES (9,'member_list', '会员列表', 8);
INSERT INTO t_authority (id, name, alias, pid) VALUES (10,'member_level', '会员等级', 8);

--changeset bill:201906188
INSERT INTO t_authority (id, name, alias, pid) VALUES (11,'learning', '学习培训', 0);
INSERT INTO t_authority (id, name, alias, pid) VALUES (12,'learning_list', '课程列表', 11);
INSERT INTO t_authority (id, name, alias, pid) VALUES (13,'learning_category', '课程分类', 11);

--changeset bill:201906187
INSERT INTO t_authority (id, name, alias, pid) VALUES (14,'live', '直播管理', 0);
INSERT INTO t_authority (id, name, alias, pid) VALUES (15,'live_channel', '频道列表', 14);
INSERT INTO t_authority (id, name, alias, pid) VALUES (16,'live_category', '频道分类', 14);

--changeset bill:2019061811
INSERT INTO t_authority (id, name, alias, pid) VALUES (17, 'setting_agreement', '协议管理', 6);

--changeset bill:20210201
INSERT INTO t_authority (id, name, alias, pid) VALUES (18,'comment', '评论管理', 0);
INSERT INTO t_authority (id, name, alias, pid) VALUES (19,'comment_list', '评论列表', 18);
INSERT INTO t_authority (id, name, alias, pid) VALUES (20,'comment_sensitive_setting', '敏感词管理', 18);

--changeset bill:2019061810
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 1);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 2);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 3);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 4);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 5);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 6);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 7);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 8);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 9);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 10);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 11);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 12);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 13);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 14);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 15);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 16);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 17);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 18);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 19);
INSERT INTO t_role_authority (role_id, authority_id) VALUES (1, 20);

--changeset bill:202104091
INSERT INTO t_user_role (user_id, role_id) VALUES (1, 1);

--changeset bill:202106011
create table t_sso_client
(
    id bigint auto_increment primary key comment '主键id',
    client_id varchar(500) not null comment '客户端id',
    client_secret varchar(500) not null comment '客户端秘钥',
    client_name nvarchar(500) not null comment '客户端名称',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间'
) comment '角色表';

--changeset bill:202106012
INSERT INTO t_sso_client (client_id, client_secret, client_name) VALUES ('de1a76b0e3a8478894ef614eab185f12', 'NTdkZGZkYjdiOTNkMTQ1NDE4ZGJiNzYxNWQxMTVhMDM=', 'OA')
