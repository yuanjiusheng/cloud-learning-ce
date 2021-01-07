--liquibase formatted sql

--changeset bill:20190620180001
create table t_department (
  id bigint not null default 0 comment'主键id',
  code nvarchar(50) not null comment '编号',
  name nvarchar(50) not null comment '名称',
  short_name nvarchar(50) not null default '' comment '简称',
  enabled tinyint not null default 1 comment '0：弃用，1：启用',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '部门表';
--rollback drop table t_department;

--changeset bill:20190930115001
create table t_user
(
  id bigint not null default 0 comment '主键id',
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
  id_card varchar(20) not null default '' comment '身份证',
  nation nvarchar(20) not null default '' comment '民族',
  native_place nvarchar(1000) not null default '' comment '籍贯',
  id_card_address nvarchar(100) not null default '' comment '户口地址',
  current_address nvarchar(1000) not null default '' comment '居住地',
  marital_status nvarchar(10) not null default '' comment '婚姻状况',
  contract_start_date date comment '合同开始日期',
  contract_end_date date comment '合同结束日期',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '用户表';
--rollback drop table t_user;

--changeset bill:20190930114401
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
--rollback drop table t_role;

--changeset bill:20190930114402
create table t_user_role
(
  id bigint auto_increment comment '主键id',
  user_id bigint not null comment '用户id',
  role_id bigint not null comment '角色id',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '用户角色表';
--rollback drop table t_user_role;

--changeset bill:20190930143003
create table t_department_department(
  id bigint auto_increment comment '主键id',
  child_department_id bigint not null comment '子部门id',
  father_department_id bigint not null comment '部门id',
  direct_father_department_id bigint not null comment '直属父部门id',
  is_sub tinyint not null default 0 comment '是否属于子类',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key(id)
) comment '部门之间的关系表';
--rollback drop table t_department_department;

--changeset bill:20190930114404
create table t_user_department(
  id bigint auto_increment comment '主键id',
  user_id bigint not null comment '用户id',
  department_id bigint not null comment '部门id',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key(id)
) comment '用户与部门关系表';
--rollback drop table t_user_department;

--changeset bill:20190930114405
create table t_job(
  id bigint comment '主键id',
  short_name nvarchar(30) not null default '' comment '简称',
  full_name nvarchar(30) not null default '' comment '全称',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key(id)
) comment '职务表';
--rollback drop table t_job;

--changeset bill:20190930114406
create table t_user_job(
  id bigint auto_increment comment '主键id',
  user_id bigint not null comment '用户id',
  job_id bigint not null comment '职务id',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key(id)
) comment '用户与职务关系表';
--rollback drop table t_user_job;

--changeset bill20190930114407
create table t_post (
  id bigint not null comment '主键id',
  short_name nvarchar(30) not null comment '简称',
  full_name nvarchar(30) not null comment'全称',
  remark nvarchar(255) default '' comment '备注',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '岗位表';
--rollback drop table t_post;

--changeset bill: 201909301144018
create table t_user_post(
  id bigint auto_increment comment '主键id',
  user_id bigint not null comment '用户id',
  post_id bigint not null comment '岗位id',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改日期',
  primary key(id)
) comment '用户与岗位关系表';
--rollback drop table t_user_post;

--changeset bill:20190930114409
create table t_manager (
  id bigint comment'主键id',
  short_name nvarchar(30) not null comment '简称',
  full_name nvarchar(30) not null comment '全称',
  remark nvarchar(255) not null default '' comment '备注',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '上级领导表';
--rollback drop table t_manager;

--changeset bill:201909301144010
create table t_user_manager(
  id bigint auto_increment comment '主键id',
  user_id bigint not null comment '用户id',
  manager_id bigint not null comment '上级领导id' ,
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key(id)
) comment '用户与上级领导关系表';
--rollback drop table t_user_manager;

