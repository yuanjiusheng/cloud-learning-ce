--liquibase formatted sql

--changeset bill:20190930115001
create table t_private_letter
(
  id bigint auto_increment comment '主键id',
  sender_id varchar(100) not null comment '发送者id',
  receiver_id varchar(100) not null comment '接受者id',
  content text not null comment '内容',
  read_time timestamp comment '读信息时间',
  is_read tinyint not null default 0 comment '是否已读',
  status varchar(30) not null comment '状态',
  create_time timestamp default current_timestamp comment '创建时间',
  update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
  primary key (id)
) comment '私信';
