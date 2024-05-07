-- SQL优化



INSERT INTO account VALUES(3, 'wangwu', 1000);
-- 查看当前会话SQL执行类型的统计信息
SHOW SESSION STATUS LIKE 'Com_______';

-- 查看全局（自从上次MySQL服务器启动至今）执行类型的统计信息
SHOW SESSION STATUS LIKE 'Com_______';

-- 查看针对InnoDB引擎的统计信息
SHOW STATUS LIKE 'Innodb_rows_%';


-- 定位低效率执行SQL

-- 慢日志查询

-- 查看慢日志配置信息
SHOW VARIABLES LIKE '%slow_query_log%';

-- 开启慢日志查询
SET GLOBAL slow_query_log = 1;

-- 查看慢日志记录SQL的最低阈值时间，默认如果SQL的执行时间>=10秒，则算慢查询，则会将该操作记录到日志中去
SHOW VARIABLES LIKE '%long_query_time%';
SELECT SLEEP(12);
SELECT SLEEP(10);

-- 修改慢日志记录SQL的最低阈值时间
SET GLOBAL long_query_time = 5;

-- 通过SHOW PROCESSLIST产看当前客户端连接服务器的线程执行状态信息
SHOW PROCESSLIST;

SELECT SLEEP(50);

-- EXPLAIN分析执行计划

-- 准备测试数据
CREATE DATABASE mydb13_optimize;
USE mydb13_optimize;

CREATE TABLE user(
	uid INT PRIMARY KEY,
	uname VARCHAR(20)
);

CREATE TABLE role(
	rid INT PRIMARY KEY,
	rname VARCHAR(20)
);

CREATE TABLE user_role(
	uid INT,
	rid INT,
	CONSTRAINT uid_fk FOREIGN KEY(uid) REFERENCES user(uid),
	CONSTRAINT rid_fk FOREIGN KEY(rid) REFERENCES role(rid)
);

CREATE TABLE privilege(
	pid INT PRIMARY KEY,
	pname VARCHAR(30)
);

CREATE TABLE role_privilege(
	rid INT,
	pid INT,
	CONSTRAINT rid_role_fk FOREIGN KEY(rid) REFERENCES role(rid),
	CONSTRAINT pid_fk FOREIGN KEY(pid) REFERENCES privilege(pid)
);

INSERT INTO user VALUES(1, '小巧');
INSERT INTO user VALUES(2, '张飞');
INSERT INTO user VALUES(3, '貂蝉');

INSERT INTO role VALUES(1, '女神');
INSERT INTO role VALUES(2, '屌丝');
INSERT INTO role VALUES(3, '老板');

INSERT INTO privilege VALUES(1, '玩跑车');
INSERT INTO privilege VALUES(2, '挖煤');
INSERT INTO privilege VALUES(3, '敲代码');

INSERT INTO role_privilege VALUES(1, 1);
INSERT INTO role_privilege VALUES(2, 2);
INSERT INTO role_privilege VALUES(3, 3);

INSERT INTO user_role VALUES(1, 1);
INSERT INTO user_role VALUES(2, 2);
INSERT INTO user_role VALUES(3, 3);
INSERT INTO user_role VALUES(1, 2);


-- 查询执行计划
EXPLAIN SELECT * FROM user WHERE uid = 1;

EXPLAIN SELECT * FROM user WHERE uname = '张飞';

-- EXPLAIN之id
-- 1、id相同表示加载表的顺序是从上到下
EXPLAIN SELECT * FROM user u, user_role ur, role r WHERE u.uid = ur.uid AND ur.rid = r.rid;

-- 2、id不同 id值越大，优先级越高，越先被执行
EXPLAIN SELECT * FROM role WHERE rid = (SELECT rid FROM user_role WHERE uid = (SELECT uid FROM user WHERE uname = '张飞'));

-- 3、id有相同，也有不同，同时存在。id相同的可以认为是一组，从上往下顺序执行；在所有组中，id的值越大，优先级越高，越先执行
EXPLAIN SELECT * FROM role r, (SELECT * FROM user_role ur WHERE ur.uid = (SELECT uid FROM user WHERE uname = '张飞')) t WHERE r.rid = t.rid;


-- EXPLAIN之select_type

-- SIMPLE:没有子查询和union
EXPLAIN SELECT * FROM user;
EXPLAIN SELECT * FROM user u, user_role ur WHERE u.uid = ur.uid;

-- PRIMARY:主查询，也就是子查询中的最外层查询
EXPLAIN SELECT * FROM role WHERE rid = (SELECT rid FROM user_role WHERE uid = (SELECT uid FROM user WHERE uname = '张飞'));

-- SUBQUERY:在select和where中包含子查询
EXPLAIN SELECT * FROM role WHERE rid = (SELECT rid FROM user_role WHERE uid = (SELECT uid FROM user WHERE uname = '张飞'));

-- DERIVED:在from中包含子查询，被标记为衍生表
EXPLAIN SELECT * FROM (SELECT * FROM user limit 2) t;

-- UNION
-- UNION RESULT
EXPLAIN SELECT * FROM user WHERE uid = 1 UNION SELECT * FROM user WHERE uid = 3;

-- EXPLAIN之type


-- NULL:不访问任何表，任何索引，直接返回结果
EXPLAIN SELECT NOW(); 
EXPLAIN SELECT RAND(); 

-- SYSTEM:查询系统表，表示直接从内存读取数据，不会从磁盘读取，但是5.7及以上版本不再显示system，直接显示ALL
EXPLAIN SELECT * FROM mysql.tables_priv;

-- CONST
EXPLAIN SELECT * FROM user WHERE uid = 2;
EXPLAIN SELECT * FROM user WHERE uname = '张飞';

CREATE UNIQUE INDEX index_uname ON user(uname);	-- 创建唯一索引
DROP INDEX index_uname ON user;	-- 删除索引

CREATE INDEX index_uname ON user(uname);

-- eq_ref	左表有主键，而且左表的每一行和右表的每一行刚好匹配

CREATE TABLE user2(
	id INT,
	name VARCHAR(20)
);

INSERT INTO user2 VALUES(1, '张三');
INSERT INTO user2 VALUES(2, '李四');
INSERT INTO user2 VALUES(3, '王五');

CREATE TABLE user2_ex(
	id INT,
	age INT
);

INSERT INTO user2_ex VALUES(1, 20);
INSERT INTO user2_ex VALUES(2, 21);
INSERT INTO user2_ex VALUES(3, 22);

-- 验证
EXPLAIN SELECT * FROM user2 a, user2_ex b WHERE a.id = b.id;	-- ALL

-- 给user2表添加主键索引
ALTER TABLE user2 ADD PRIMARY KEY(id); 

EXPLAIN SELECT * FROM user2 a, user2_ex b WHERE a.id = b.id;	-- eq_ref

-- 在user_ex表添加一个重复的行数据
EXPLAIN SELECT * FROM user2 a, user2_ex b WHERE a.id = b.id;	-- ALL

-- ref	左表有普通索引，和右表匹配时可能会匹配多行

-- 给user2表添加普通索引
ALTER TABLE user2 DROP PRIMARY KEY;
CREATE INDEX index_id ON user2(id);
 
EXPLAIN SELECT * FROM user2 a, user2_ex b WHERE a.id = b.id;	-- ref

-- RANGE	范围查询
EXPLAIN SELECT * FROM user2 WHERE id > 2; 

-- INDEX	把索引列的全部数据都扫描
EXPLAIN SELECT id FROM user2;	-- INDEX

-- ALL
EXPLAIN SELECT * FROM user2;	-- ALL


-- EXPLAIN其他字段指标

EXPLAIN SELECT * FROM user WHERE uid = 1;

EXPLAIN SELECT * FROM user_role WHERE uid = 1;

SHOW INDEX FROM user;
EXPLAIN SELECT * FROM user ORDER BY uname;
EXPLAIN SELECT uname, COUNT(*) FROM user GROUP BY uname;
EXPLAIN SELECT uid, COUNT(*) FROM user GROUP BY uid;


-- SHOW PROFILE分析SQL

-- 查看当前的MySQL是否支持profile
SELECT @@have_profiling;
-- 如果不支持，则需要设置打开
SET profiling = 1;

-- 执行SQL
SHOW DATABASES;
USE mydb13_optimize;
SHOW TABLES;
SELECT COUNT(*) FROM user;
SELECT * FROM user WHERE uid > 2;

SHOW PROFILES;

SHOW PROFILE FOR QUERY 712;

SHOW PROFILE cpu FOR QUERY 712;


-- trace分析优化器执行计划

SET optimizer_trace = "enabled=on", end_markers_in_json = ON;
SET optimizer_trace_max_mem_size = 1000000;

SELECT * FROM user a, user_role b, role c WHERE a.uid = b.uid AND b.rid = c.rid;

SELECT * FROM information_schema.optimizer_trace \G;


-- 使用索引优化

-- 数据准备
create table `tb_seller` (
    `sellerid` varchar (100),
    `name` varchar (100),
    `nickname` varchar (50),
    `password` varchar (60),
    `status` varchar (1),
    `address` varchar (100),
    `createtime` datetime,
    primary key(`sellerid`)
); 

insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('alibaba','阿里巴巴','阿里小店','e10adc3949ba59abbe56e057f20f883e','1','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('baidu','百度科技有限公司','百度小店','e10adc3949ba59abbe56e057f20f883e','1','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('huawei','华为科技有限公司','华为小店','e10adc3949ba59abbe56e057f20f883e','0','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('itcast','传智播客教育科技有限公司','传智播客','e10adc3949ba59abbe56e057f20f883e','1','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('itheima','黑马程序员','黑马程序员','e10adc3949ba59abbe56e057f20f883e','0','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('luoji','罗技科技有限公司','罗技小店','e10adc3949ba59abbe56e057f20f883e','1','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('oppo','OPPO科技有限公司','OPPO官方旗舰店','e10adc3949ba59abbe56e057f20f883e','0','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('ourpalm','掌趣科技股份有限公司','掌趣小店','e10adc3949ba59abbe56e057f20f883e','1','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('qiandu','千度科技','千度小店','e10adc3949ba59abbe56e057f20f883e','2','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('sina','新浪科技有限公司','新浪官方旗舰店','e10adc3949ba59abbe56e057f20f883e','1','北京市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('xiaomi','小米科技','小米官方旗舰店','e10adc3949ba59abbe56e057f20f883e','1','西安市','2088-01-01 12:00:00');
insert into `tb_seller` (`sellerid`, `name`, `nickname`, `password`, `status`, `address`, `createtime`) values('yijia','宜家家居','宜家家居旗舰店','e10adc3949ba59abbe56e057f20f883e','1','北京市','2088-01-01 12:00:00');
-- 创建组合索引 
create index idx_seller_name_sta_addr on tb_seller(name,status,address);


-- 避免索引失效应用

-- 全值匹配，和字段匹配成功即可，和字段无关
EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND STATUS = '1' AND address = '北京市';

EXPLAIN SELECT * FROM tb_seller WHERE address = '北京市' AND name = '小米科技' AND status = '1';

-- 最左前缀法则
-- 如果索引了多列，要遵守最左前缀法则。指的是查询从索引的最左前列开始，并且不跳过索引中的列
EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技';	-- 403

EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND status = '1';	-- 410

EXPLAIN SELECT * FROM tb_seller WHERE status = '1' AND name = '小米科技';	-- 410

-- 违法最左前缀法则，索引失效
EXPLAIN SELECT * FROM tb_seller WHERE status = '1';	-- NULL

-- 如果符合最左法则，但是出现跳跃某一列，只有最左列索引生效：
EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND address = '北京市'; 	-- 403


-- 其他匹配原则

-- 范围查询右边的列，不能使用索引
-- 根据前面的两个字段name，status查询是走索引的，但是最后一个条件address没有用到索引
EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND status > '1' AND address = '北京市';

-- 不要再索引列上进行运算操作，索引将失效
EXPLAIN SELECT * FROM tb_seller WHERE SUBSTRING(name, 3, 2) = '科技';

-- 字符串不加单引号，造成索引失效
EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND status = 1;

-- 尽量使用覆盖索引，避免select *
-- 需要从原表及磁盘上读取数据
EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND address = '北京市';

-- 从索引树中就可以查询到所有数据
EXPLAIN SELECT name FROM tb_seller WHERE name = '小米科技' AND address = '北京市';	-- 效率高
EXPLAIN SELECT name, status, address FROM tb_seller WHERE name = '小米科技' AND address = '北京市';	-- 效率高
EXPLAIN SELECT name, status, address, password FROM tb_seller WHERE name = '小米科技' AND address = '北京市';	-- 效率低

-- 用or分隔开的条件，那么涉及的索引都不会被用到
EXPLAIN SELECT * FROM tb_seller WHERE name = '黑马程序员' OR createtime = '2088-01-01 12:00:00';
EXPLAIN SELECT * FROM tb_seller WHERE name = '黑马程序员' OR address = '西安市';
EXPLAIN SELECT * FROM tb_seller WHERE name = '黑马程序员' OR status = '1';

-- 以%开头的like模糊查询，索引失效
EXPLAIN SELECT * FROM tb_seller WHERE name LIKE '科技%';	-- 用索引
EXPLAIN SELECT * FROM tb_seller WHERE name LIKE '%科技';	-- 不用索引
EXPLAIN SELECT * FROM tb_seller WHERE name LIKE '%科技%';	-- 不用索引

-- 弥补不足，不用*，使用索引列
EXPLAIN SELECT name FROM tb_seller WHERE name LIKE '%科技%';	-- 用索引

-- 如果MySQL评估使用索引比全表更慢，则不使用索引
-- 这种情况是由数据本身的特点来决定的
CREATE INDEX index_address ON tb_seller(address);

EXPLAIN SELECT * FROM tb_seller WHERE address = '北京市';	-- 没有使用索引
EXPLAIN SELECT * FROM tb_seller WHERE address = '西安市';	-- 没有使用索引

-- IS NULL，IS NOT NULL	有时有效，有时索引失效
CREATE INDEX index_nickname ON tb_seller(nickname);
EXPLAIN SELECT * FROM tb_seller WHERE nickname IS NULL;	-- 索引有效
EXPLAIN SELECT * FROM tb_seller WHERE nickname IS NOT NULL;	-- 索引无效

-- IN走索引，NOT IN索引失效
-- 普通索引
EXPLAIN SELECT * FROM tb_seller WHERE nickname IN('阿里小店', '百度小店');	-- 索引有效
EXPLAIN SELECT * FROM tb_seller WHERE nickname NOT IN('阿里小店', '百度小店');	-- 索引无效

-- 主键索引
EXPLAIN SELECT * FROM tb_seller WHERE sellerid IN('alibaba', 'baidu');	-- 使用索引
EXPLAIN SELECT * FROM tb_seller WHERE sellerid NOT IN('alibaba', 'baidu');	-- 使用索引

-- 单列索引和复合索引，尽量使用复合索引
create index idx_seller_name_sta_addr on tb_seller(name,status,address);
/*
	等价于：
		name
		name + status
		name + status + address
*/
-- 如果一张表有多个单列索引，即使where中都使用了这些索引列，则只有一个最优索引生效

DROP INDEX idx_seller_name_sta_addr ON tb_seller;
DROP INDEX index_nickname ON tb_seller;

SHOW INDEX FROM tb_seller;

CREATE INDEX index_name ON tb_seller(name);
CREATE INDEX index_status ON tb_seller(status);
CREATE INDEX index_address ON tb_seller(address);

EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND status = '1' AND address = '西安市';
EXPLAIN SELECT * FROM tb_seller WHERE name = '小米科技' AND status = '1';

-- SQL优化

-- 大批量插入数据
create table `tb_user` (
  `id` int(11) not null auto_increment,
  `username` varchar(45) not null,
  `password` varchar(96) not null,
  `name` varchar(45) not null,
  `birthday` datetime default null,
  `sex` char(1) default null,
  `email` varchar(45) default null,
  `phone` varchar(45) default null,
  `qq` varchar(32) default null,
  `status` varchar(32) not null comment '用户状态',
  `create_time` datetime not null,
  `update_time` datetime default null,
  primary key (`id`),
  unique key `unique_user_username` (`username`)
);

-- 主键顺序插入

-- 1、首先，检查一个全局系统变量 'local_infile' 的状态， 如果得到如下显示 Value=OFF，则说明这是不可用的
SHOW GLOBAL VARIABLES LIKE 'local_infile';

-- 2、修改local_infile值为on，开启local_infile
SET GLOBAL local_infile = 1;

-- 3、加载数据 
-- 结论：当通过load向表加载数据时，尽量保证文件中的主键有序，这样可以提高执行效率
LOAD DATA LOCAL INFILE 'D:\\sql_data\\sql1.log' INTO TABLE tb_user FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';

-- 关闭唯一性校验
-- 如果主键列数据本身就没有重复的，那么就没必要进行唯一性校验 
SET UNIQUE_CHECKS = 0;

SET UNIQUE_CHEKCS = 1;


-- 优化insert语句

CREATE TABLE tb_test(
	id INT PRIMARY KEY,
	name VARCHAR(20)
);

-- 如果需要同时对一张表插入很多行数据时，应该尽量使用多个值表的insert语句，这种方式将大大的缩减客户端与数据库之间的连接、关闭等消耗。使得效率比分开执行的单个insert语句快。

-- 原始方式为：
insert into tb_test values(1,'Tom');
insert into tb_test values(2,'Cat');
insert into tb_test values(3,'Jerry');
 
 
-- 优化后的方案为 ： 
 
insert into tb_test values(1,'Tom'),(2,'Cat')，(3,'Jerry');

-- 在事务中进行数据插入。
begin;
insert into tb_test values(1,'Tom');
insert into tb_test values(2,'Cat');
insert into tb_test values(3,'Jerry');
commit;


-- 数据有序插入
insert into tb_test values(4,'Tim');
insert into tb_test values(1,'Tom');
insert into tb_test values(3,'Jerry');
insert into tb_test values(5,'Rose');
insert into tb_test values(2,'Cat');
 
 
-- 优化后
insert into tb_test values(1,'Tom');
insert into tb_test values(2,'Cat');
insert into tb_test values(3,'Jerry');
insert into tb_test values(4,'Tim');
insert into tb_test values(5,'Rose');


-- 优化ORDER BY语句
CREATE TABLE `emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `age` int(3) NOT NULL,
  `salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
 
insert into `emp` (`id`, `name`, `age`, `salary`) values('1','Tom','25','2300');
insert into `emp` (`id`, `name`, `age`, `salary`) values('2','Jerry','30','3500');
insert into `emp` (`id`, `name`, `age`, `salary`) values('3','Luci','25','2800');
insert into `emp` (`id`, `name`, `age`, `salary`) values('4','Jay','36','3500');
insert into `emp` (`id`, `name`, `age`, `salary`) values('5','Tom2','21','2200');
insert into `emp` (`id`, `name`, `age`, `salary`) values('6','Jerry2','31','3300');
insert into `emp` (`id`, `name`, `age`, `salary`) values('7','Luci2','26','2700');
insert into `emp` (`id`, `name`, `age`, `salary`) values('8','Jay2','33','3500');
insert into `emp` (`id`, `name`, `age`, `salary`) values('9','Tom3','23','2400');
insert into `emp` (`id`, `name`, `age`, `salary`) values('10','Jerry3','32','3100');
insert into `emp` (`id`, `name`, `age`, `salary`) values('11','Luci3','26','2900');
insert into `emp` (`id`, `name`, `age`, `salary`) values('12','Jay3','37','4500');
 
-- 创建组合索引
create index idx_emp_age_salary on emp(age,salary);


-- ORDER BY

EXPLAIN SELECT * FROM emp ORDER BY age; -- Using filesort
EXPLAIN SELECT * FROM emp ORDER BY age, salary; -- Using filesort

EXPLAIN SELECT id FROM emp ORDER BY age; -- Using index
EXPLAIN SELECT id, age FROM emp ORDER BY age; -- Using index
EXPLAIN SELECT id, age, salary, name FROM emp ORDER BY age; -- Using filesort

-- ORDER BY后边的多个排序字段要求尽量排序方式相同
EXPLAIN SELECT id, age FROM emp ORDER BY age, salary; -- Using index
EXPLAIN SELECT id, age FROM emp ORDER BY age asc, salary DESC; -- Using index; Using filesort
EXPLAIN SELECT id, age FROM emp ORDER BY age DESC, salary DESC; -- Backward index scan; Using index

-- ORDER BY 后边的多个排序字段顺序尽量和组合索引字段顺序一致
EXPLAIN SELECT id, age FROM emp ORDER BY salary, age; -- Using index; Using filesort


-- Filesort的优化

SHOW VARIABLES LIKE 'max_length_for_sort_data';	-- 4096
SHOW VARIABLES LIKE 'sort_buffer_size';	-- 262144


-- 优化子查询
EXPLAIN SELECT * FROM user WHERE uid in (SELECT uid FROM user_role);

EXPLAIN SELECT * FROM user u, user_role ur WHERE u.uid = ur.uid;


-- 优化limit
SELECT COUNT(*) FROM tb_user;




