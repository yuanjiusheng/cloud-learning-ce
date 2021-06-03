--liquibase formatted sql

--changeset bill:2020070701
create table t_category
(
    id            bigint auto_increment comment '主键id',
    name          nvarchar(50)        not null comment '目录名称',
    sort_order    int       default 1 not null comment '排列序号，表示同级目录的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
    is_show       tinyint   default 1 not null comment '是否显示',
    is_show_index tinyint   default 1 not null comment '是否在首页显示',
    level         int                 not null comment '目录等级',
    image         varchar(500)        not null comment '目录图片',
    create_time   timestamp default current_timestamp comment '创建时间',
    update_time   timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '课程目录';

--changeset bill:2020070702
create table t_category_relation
(
    id                        bigint auto_increment comment '主键id',
    child_category_id         bigint  not null comment '子目录id',
    father_category_id        bigint  not null comment '父目录id',
    direct_father_category_id bigint  not null comment '直属父目录id',
    is_sub                    tinyint not null comment '是否属于子目录',
    create_time               timestamp default current_timestamp comment '创建时间',
    update_time               timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '目录关系';

--changeset bill:2020070703
create table t_lesson
(
    id           bigint auto_increment comment '主键id',
    name         nvarchar(100)  not null comment '名称（最大长度64个字符，只支持中文、字母、数字和下划线）',
    code         varchar(100)   not null comment '编号',
    start_time   timestamp      not null comment '开始时间',
    end_time     timestamp      not null comment '结束时间',
    image        varchar(1000)  not null comment '封面图片（海报）',
    status       varchar(50)    not null comment '状态',
    phrase       nvarchar(255)  not null default '' comment '短语介绍',
    introduction nvarchar(3000) not null default '' comment '详情',
    create_time  timestamp      not null default current_timestamp comment '创建时间',
    update_time  timestamp      not null default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '课程表';

--changeset bill:2020070707
create table t_lesson_chapter
(
    id          bigint auto_increment comment '主键id',
    lesson_id   bigint comment '课程id',
    title       nvarchar(100) not null comment '章标题',
    phrase      nvarchar(255) not null default '' comment '介绍',
    create_time timestamp     not null default current_timestamp comment '创建时间',
    update_time timestamp     not null default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '课程章表';

--changeset bill:2020070706
create table t_lesson_chapter_section
(
    id                bigint auto_increment comment '主键id',
    lesson_chapter_id bigint comment '课程章id',
    title             nvarchar(100) not null comment '章节标题',
    url               varchar(1000) not null comment '内容地址',
    phrase            nvarchar(255) not null default '' comment '介绍',
    create_time       timestamp     not null default current_timestamp comment '创建时间',
    update_time       timestamp     not null default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '课程章节表';

--changeset bill:2020070705
create table t_lesson_category_relation
(
    id          bigint auto_increment comment '主键id',
    category_id bigint not null comment '目录id',
    lesson_id   bigint not null comment '课程id',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '课程目录关系';

--changeset bill:202012121
create table t_sign_up
(
    id          bigint auto_increment comment '主键id',
    member_id   bigint      not null comment '会员id',
    lesson_id   bigint      not null comment '课程id',
    status      varchar(50) not null comment '状态',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '报名';

--changeset bill:202012122
create table t_record
(
    id                        bigint auto_increment comment '主键id',
    member_id                 bigint not null comment '会员id',
    lesson_id                 bigint not null comment '课程id',
    lesson_chapter_section_id bigint not null comment '章节id',
    sign_up_id                bigint not null comment '报名id',
    learn_time                bigint not null comment '学习时长',
    create_time               timestamp default current_timestamp comment '创建时间',
    update_time               timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '学习记录';

--changeset bill:202104281
alter table t_lesson
    add homework text not null comment '作业内容';
alter table t_lesson
    add homework_attachment varchar(3000) default '' not null comment '作业附件';

--changeset bill:202104282
create table t_homework_record
(
    id          bigint auto_increment comment '主键id',
    member_id   bigint        not null comment '会员id',
    lesson_id   bigint        not null comment '课程id',
    url         varchar(3000) not null comment '作业提交内容的地址',
    status      varchar(200)  not null comment '状态',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '作业提交内容记录';

--changeset bill:202104283
alter table t_record
    add max_progress_time bigint not null comment '最大的学习进度时间';
alter table t_record
    add status varchar(200) default 'progressing' not null comment '状态';

--changeset bill:202104284
alter table t_lesson_chapter_section
    add total_time bigint not null comment '内容总时长';

--changeset bill:202104285
alter table t_homework_record
    add sign_up_id bigint not null comment '报名id';

--changeset bill:202104286
alter table t_sign_up
    add completed_time timestamp comment '完成时间';

--changeset bill:202106011
create table t_lesson_target_member
(
    id          bigint auto_increment primary key comment '主键id',
    member_id   bigint not null comment '会员id',
    lesson_id   bigint not null comment '课程id',
    create_time timestamp default current_timestamp comment '创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '最后修改时间'
) comment '目标会员';

--changeset bill:202106012
create table t_lesson_target_department
(
    id            bigint auto_increment primary key comment '主键id',
    department_id bigint not null comment '部门id',
    lesson_id     bigint not null comment '课程id',
    create_time   timestamp default current_timestamp comment '创建时间',
    update_time   timestamp default current_timestamp on update current_timestamp comment '最后修改时间'
) comment '目标部门';

--changeset bill:202106013
alter table t_lesson
    add target_type varchar(100) default 'all' not null comment '目标类型';
