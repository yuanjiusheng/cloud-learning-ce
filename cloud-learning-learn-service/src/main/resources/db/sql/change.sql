--liquibase formatted sql

--changeset bill:2020070701
create table t_category
(
    id            bigint auto_increment comment '主键id',
    name          nvarchar(50)        not null comment '类目名称',
    sort_order    int       default 1 not null comment '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
    is_show       tinyint   default 1 not null comment '是否显示',
    is_show_index tinyint   default 1 not null comment '是否在首页显示',
    level         int                 not null comment '目录等级',
    image         varchar(500)        not null comment '分类图片',
    create_time   timestamp default current_timestamp comment '创建时间',
    update_time   timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    constraint t_category_pk primary key (id)
) comment '课程类目';

--changeset bill:2020070702
create table t_category_relation
(
    id                        bigint auto_increment comment '主键id',
    child_category_id         bigint  not null comment '子类目id',
    father_category_id        bigint  not null comment '父类目id',
    direct_father_category_id bigint  not null comment '直属父类目id',
    is_sub                    tinyint not null comment '是否属于子类目',
    create_time               timestamp default current_timestamp comment '创建时间',
    update_time               timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    constraint t_category_relation_pk primary key (id)
) comment '类目关系';

--changeset bill:2020070703
create table t_lesson
(
    id             bigint auto_increment comment '主键id',
    name           nvarchar(100) not null comment '频道名称（最大长度64个字符，只支持中文、字母、数字和下划线）',
    code   varchar(100) not null comment '编号',
    start_time     timestamp     not null comment '开始时间',
    end_time     timestamp     not null comment '结束时间',
    image          varchar(1000)  not null comment '封面图片（海报）',
    is_show    tinyint       not null default 1 comment '是否可见',
    status           varchar(50)  not null comment '状态',
    phrase nvarchar(255) not null default '' comment '短语介绍',
    introduction  nvarchar(3000) not null default '' comment '详情',
    create_time    timestamp     not null default current_timestamp comment '创建时间',
    update_time    timestamp     not null default current_timestamp on update current_timestamp comment '最后修改时间',
    constraint t_channel_pk primary key (id)
) comment '课程表';

--changeset bill:2020070707
create table t_lesson_chapter
(
    id             bigint auto_increment comment '主键id',
    lesson_id bigint comment '课程id',
    title           nvarchar(100) not null comment '章标题',
    phrase nvarchar(255) not null default '' comment '介绍',
    create_time    timestamp     not null default current_timestamp comment '创建时间',
    update_time    timestamp     not null default current_timestamp on update current_timestamp comment '最后修改时间',
    constraint t_channel_pk primary key (id)
) comment '课程章表';

--changeset bill:2020070706
create table t_lesson_chapter_section
(
    id             bigint auto_increment comment '主键id',
    lesson_chapter_id bigint comment '课程章id',
    title           nvarchar(100) not null comment '章节标题',
    url   varchar(1000) not null comment '内容地址',
    phrase nvarchar(255) not null default '' comment '介绍',
    create_time    timestamp     not null default current_timestamp comment '创建时间',
    update_time    timestamp     not null default current_timestamp on update current_timestamp comment '最后修改时间',
    constraint t_channel_pk primary key (id)
) comment '课程章节表';

--changeset bill:2020070705
create table t_lesson_category_relation
(
    id                        bigint auto_increment comment '主键id',
    category_id         bigint  not null comment '目录id',
    lesson_id        bigint  not null comment '频道id',
    create_time               timestamp default current_timestamp comment '创建时间',
    update_time               timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    constraint t_channel_category_relation_pk primary key (id)
) comment '频道类目关系';

--changeset bill:202012121
create table t_sign_up
(
    id                        bigint auto_increment comment '主键id',
    member_id          bigint  not null comment '会员id',
    lesson_id          bigint  not null comment '频道id',
    status        varchar(50)  not null comment '状态',
    create_time               timestamp default current_timestamp comment '创建时间',
    update_time               timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '报名';

--changeset bill:202012122
create table t_record
(
    id                        bigint auto_increment comment '主键id',
    member_id          bigint  not null comment '会员id',
    lesson_id          bigint  not null comment '频道id',
    lesson_chapter_section_id        bigint  not null comment '章节id',
    sign_up_id        bigint  not null comment '报名id',
    learn_time        bigint  not null comment '学习时长',
    create_time               timestamp default current_timestamp comment '创建时间',
    update_time               timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '学习记录';

--changeset bill:20210305
alter table t_lesson drop column is_show;
