--liquibase formatted sql

--changeset bill.lai:202008211
create table t_carousel(
    id                       bigint auto_increment comment '主键id',
    carousel_json            json default null comment '轮播图设置json字符串',
    create_time              timestamp default current_timestamp comment '创建时间',
    update_time              timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '首页轮播图';

--changeset bill.lai:202008212
create table t_agreement(
    id                       bigint auto_increment comment '主键id',
    name                     nvarchar(50)  not null comment '协议名称',
    type                     varchar(50)  not null comment '协议类型',
    content                  text         not null comment '协议内容',
    create_time              timestamp default current_timestamp comment '创建时间',
    update_time              timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '协议';
