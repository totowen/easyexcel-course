/*==============================================================*/
/* Table: tb_user_info                                          */
/*==============================================================*/
drop table if exists tb_user_info;
create table tb_user_info
(
    id            bigint(20)     not null comment '主键标识',
    user_nickname varchar(50)    not null comment '用户昵称',
    user_phone    varchar(11) comment '用户手机',
    user_email    varchar(50) comment '用户邮箱',
    user_gender   int comment '用户性别',
    user_birth    datetime comment '用户生日',
    user_score    int            not null default 0 comment '用户积分',
    user_reward   decimal(12, 2) not null default 0 comment '用户佣金',
    primary key (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

alter table tb_user_info
    comment '用户信息';

/*==============================================================*/
/* Data: Mock Data                                              */
/*==============================================================*/
begin;
insert into tb_user_info(id, user_nickname, user_phone, user_email, user_gender, user_birth, user_score, user_reward)
values (1001, '郭德纲', '13800138001', 'guodegang@xxx.com', 1, '1973-01-18 00:00:00', 21, 100.21),
       (1002, '于谦', '13800138002', 'yuqian@xxx.com', 2, '1969-01-24 00:00:00', 24, 100.24),
       (1003, '栾云平', '13800138003', 'luanyunping@xxx.com', 0, '1984-03-20 00:00:00', 35, 100.35),
       (1004, '岳云鹏', '13800138004', 'yueyunpeng@xxx.com', 1, '1985-04-15 00:00:00', 20, 100.20),
       (1005, '孙越', '13800138005', 'sunyue@xxx.com', 2, '1979-10-13 00:00:00', 22, 100.22),
       (1006, '郭麒麟', '13800138006', 'guoqilin@xxx.com', 0, '1996-02-08 00:00:00', 24, 100.24),
       (1007, '阎鹤祥', '13800138007', 'yanhexiang@xxx.com', 1, '1981-09-14 00:00:00', 25, 100.25),
       (1008, '张云雷', '13800138008', 'zhangyunlei@xxx.com', 2, '1992-01-11 00:00:00', 29, 100.29),
       (1009, '杨九郎', '13800138009', 'yangjiulang@xxx.com', 0, '1989-07-17 00:00:00', 35, 100.35),
       (1010, '孟鹤堂', '13800138010', 'menghetang@xxx.com', 1, '1988-04-26 00:00:00', 30, 100.30),
       (1011, '周九良', '13800138011', 'zhoujiuliang@xxx.com', 2, '1994-09-14 00:00:00', 26, 100.26);
commit;