-- 存储引擎

-- 查看当前数据库支持的存储引擎
SHOW ENGINES;

-- 查看当前的默认存储引擎
SHOW VARIABLES LIKE '%storage_engine%';

-- 查看某个表用了什么引擎（在显示结果里参数engine后面的就表示该表当前用的存储引擎）
CREATE DATABASE mydb11_engine;
USE mydb11_engine;

CREATE TABLE stu1(id INT, name VARCHAR(20));

SHOW CREATE TABLE stu1;

-- 创建新表时指定存储引擎
CREATE TABLE stu2(id INT, name VARCHAR(20)) ENGINE = MyISAM;

SHOW CREATE TABLE stu2;

-- 修改数据库引擎
ALTER TABLE stu1 ENGINE = MyISAM;
ALTER TABLE stu2 ENGINE = INNODB;

-- 事务

-- 事务操作
create database if not exists mydb12_transcation;
use mydb12_transcation;
-- 创建账户表
create table account(
    id int primary key, -- 账户id
    name varchar(20), -- 账户名
    money double -- 金额
);
 
 
--  插入数据
insert into account values(1,'zhangsan',1000);
insert into account values(2,'lisi',1000);


-- 设置MySQL的事务为手动提交（关闭自动提交）
SELECT @@autocommit;
set autocommit = 0;

-- 模拟账户转账
-- 开启事务
BEGIN;
-- id为1的账户转账给id为2的账户
-- 由于关闭了事务的自动提交，所以在开启事务后，执行下面两条命令，数据库的值并没有发生改变
UPDATE account SET money = money - 200 WHERE name = 'zhangsan';
UPDATE account SET money = money + 200 WHERE name = 'lisi';
-- 提交事务
-- 提交事务后，上面两个update更改的数据才会体现在数据库中
COMMIT;

-- 回滚事务
-- 事务开启之后如果没有提交，回滚会让当前事务执行的结果回滚到最早执行的状态
ROLLBACK;

-- 即使是没有提交，查询之后得到的数据也是执行过后的
SELECT * FROM account;

-- 事务的隔离级别

-- 查看隔离级别
SHOW VARIABLES LIKE '%ISOLATION%';

-- 设置隔离级别
-- 读未提交
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

-- 不可重复读
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- 可重复读
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ; 

-- 串行化
SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;



-- MySQL的锁机制

-- MyISAM表锁
drop database if exists  mydb14_lock;
create database mydb14_lock ;
 
use mydb14_lock;
  
create table `tb_book` (
  `id` int(11) auto_increment,
  `name` varchar(50) default null,
  `publish_time` date default null,
  `status` char(1) default null,
  primary key (`id`)
) engine=myisam default charset=utf8 ;
 
insert into tb_book (id, name, publish_time, status) values(null,'java编程思想','2088-08-01','1');
insert into tb_book (id, name, publish_time, status) values(null,'solr编程思想','2088-08-08','0');

create table `tb_user` (
  `id` int(11) auto_increment,
  `name` varchar(50) default null,
  primary key (`id`)
) engine=myisam default charset=utf8 ;
 
insert into tb_user (id, name) values(null,'令狐冲');
insert into tb_user (id, name) values(null,'田伯光');


-- InnoDB行锁 
-- 行锁 
drop table if exists test_innodb_lock;
create table test_innodb_lock(
    id int(11),
    name varchar(16),
    sex varchar(1)
)engine = innodb ;
 
insert into test_innodb_lock values(1,'100','1');
insert into test_innodb_lock values(3,'3','1');
insert into test_innodb_lock values(4,'400','0');
insert into test_innodb_lock values(5,'500','1');
insert into test_innodb_lock values(6,'600','0');
insert into test_innodb_lock values(7,'700','0');
insert into test_innodb_lock values(8,'800','1');
insert into test_innodb_lock values(9,'900','1');
insert into test_innodb_lock values(1,'200','0');
 
create index idx_test_innodb_lock_id on test_innodb_lock(id);
create index idx_test_innodb_lock_name on test_innodb_lock(name);


-- 日志

-- 错误日志
SHOW VARIABLES LIKE 'log_error%';

-- 二进制日志-BINLOG

-- 查看MySQL是否开启了BINLOG日志
SHOW VARIABLES like 'log_bin';

-- 查看binlog日志的格式
SHOW VARIABLES LIKE 'binlog_format';

-- 查看所有日志
SHOW BINLOG EVENTS;

-- 查看最新的日志
SHOW MASTER STATUS;

-- 查询指定的binlog日志
SHOW BINLOG EVENTS in '高逸非-bin.000034';
SELECT * FROM mydb1.emp2;
UPDATE mydb1.emp2 SET salary = 8000;

-- 从指定的位置开始，查看指定的binlog日志
SHOW BINLOG EVENTS IN '高逸非-bin.000034' FROM 156;

-- 从指定位置开始，查看指定的binlog日志，限制查询的条数
SHOW BINLOG EVENTS IN '高逸非-bin.000034' FROM 156 limit 2;
-- 从指定位置开始，带有偏移，查看指定的binlog日志，限制查询的条数
SHOW BINLOG EVENTS IN '高逸非-bin.000034' FROM 156 limit 1, 2;


-- 清空所有的Binlog日志文件
RESET MASTER;

-- 查询日志

-- 查看MySQL是否开启了查询日志
SHOW VARIABLES LIKE 'general_log';

-- 开启查询日志（会话临时开启）
SET GLOBAL general_log = 1;

SELECT * FROM mydb1.emp2;
SELECT * FROM mydb6_view.emp;

SELECT COUNT(*) FROM mydb1.emp2;
SELECT COUNT(*) FROM mydb6_view.emp;
UPDATE mydb1.emp2 SET salary = 9000;


-- 慢查询日志

-- 查看慢日志查询是否开启
SHOW VARIABLES LIKE 'slow_query_log%';

-- 开启慢查询日志
SET GLOBAL slow_query_log = 1;

-- 查看慢查询的超时时间
SHOW VARIABLES LIKE 'long_query_time%';

SELECT SLEEP(10);







































