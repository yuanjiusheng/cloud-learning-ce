--liquibase formatted sql

--changeset bill:2020070701
create table t_comment
(
    id            bigint auto_increment comment '主键id',
    topic_id      bigint              not null comment '主题ID，如课程评论、知识评论的ID等',
    topic_type    nvarchar(50)        not null comment '主题类型，如课程评论、知识评论等',
    content       nvarchar(4000)      not null comment '评论内容',
    member_id     bigint              not null comment '评论用户id',
    create_time   timestamp default current_timestamp comment '创建时间',
    update_time   timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '评论表';

--changeset bill:2020070702
create table t_reply_comment
(
    id               bigint auto_increment comment '主键id',
    comment_id       bigint         not null comment '评论id',
    reply_comment_id bigint         not null comment '回复评论id，也就是父ID，回复评论表的评论是，值跟评论id相等',
    content          nvarchar(4000) not null comment '回复内容',
    member_id        bigint         not null comment '当前评论的用户ID',
    to_member_id     bigint         not null comment '回复的评论的用户id',
    create_time      timestamp default current_timestamp comment '创建时间',
    update_time      timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '回复表';

--changeset bill:2020070703
create table t_like
(
    id            bigint auto_increment comment '主键id',
    topic_id      bigint              not null comment '主题ID，如课程评论、知识评论的ID等',
    topic_type    nvarchar(50)        not null comment '主题类型，如课程评论、知识评论等',
    member_id     bigint              not null comment '用户id',
    status        tinyint   default 1 not null comment '点赞状态,0=取消赞,1=有效赞',
    create_time   timestamp default current_timestamp comment '创建时间',
    update_time   timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '点赞';

--changeset bill:2020070704
create table t_favorite
(
    id            bigint auto_increment comment '主键id',
    topic_id      bigint              not null comment '主题ID，如课程评论、知识评论的ID等',
    topic_type    nvarchar(50)        not null comment '主题类型，如课程评论、知识评论等',
    member_id     bigint              not null comment '用户id',
    create_time   timestamp default current_timestamp comment '创建时间',
    update_time   timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '收藏';

--changeset bill:2020070708
create table t_sensitive_word
(
    id            bigint auto_increment comment '主键id',
    name          nvarchar(100)              not null comment '敏感词',
    create_time   timestamp default current_timestamp comment '创建时间',
    update_time   timestamp default current_timestamp on update current_timestamp comment '最后修改时间',
    primary key (id)
) comment '敏感词库';
