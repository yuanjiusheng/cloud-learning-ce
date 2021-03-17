--liquibase formatted sql

--changeset bill:20190930115001
create table t_member
(
  id bigint auto_increment comment '主键id',
  username varchar(100) not null default '' comment '登陆账号',
  password varchar(100) not null default '' comment '密码',
  code varchar(30) not null default '' comment '员工编号',
  name nvarchar(30) not null default '' comment '姓名',
  status varchar(30) not null default '' comment '状态',
  gender varchar(2) not null default '' comment '性别',
  telephone varchar(20) not null default '' comment '办公电话',
  mobile varchar(20) not null default '' comment '移动电话',
  email varchar(30) not null default '' comment '电子邮件',
  birthday date comment '生日',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '会员表';

--changeset bill:201909301
alter table t_member add avatar varchar(1000) default '' not null comment '头像';

--changeset bill:20190930115002
create table t_member_level
(
    id          bigint auto_increment comment '主键id',
    name        nvarchar(100)  not null comment '名称',
    description nvarchar(2000) not null comment '描述',
    conditions  bigint         not null comment '状态',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '会员等级';

--changeset bill:202103121
create table t_member_level_relation
(
    id          bigint auto_increment comment '主键id',
    member_id   bigint not null comment '会员id',
    level_id    bigint not null comment '等级id',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '会员等级';
