-- 一、主键约束

-- 1.创建单列主键约束

-- 方式1

USE mydb1;
CREATE TABLE emp1(
	eid INT PRIMARY KEY,
	name VARCHAR(20),
	deptID INT,
	salary DOUBLE
);

-- 方式2

USE mydb1;
CREATE TABLE emp2(
	eid INT,
	name VARCHAR(20),
	deptID INT,
	salary DOUBLE,
	CONSTRAINT pk1 PRIMARY KEY(eid)
);

-- 主键的作用
-- 主键约束的键非空且唯一

INSERT INTO emp2(eid,name,deptId,salary) VALUES(1001,'张三',10,5000);
-- 当主键列的值重复的时候，添加是无效的，会报错
INSERT INTO emp2(eid,name,deptId,salary) VALUES(1001,'李四',20,6000);
-- 主键列的值是不能空的，否则会宝座
INSERT INTO emp2(eid,name,deptId,salary) VALUES(NULL,'王五',30,7000);

-- 2.联合主键
-- 所谓的联合主键，就是这个主键是由一张表中多个字段组成的。
CREATE TABLE emp3(
	name VARCHAR(20),
	deptId INT,
	salary DOUBLE,
	CONSTRAINT pk2 PRIMARY KEY(name,deptId)
);

INSERT INTO emp3 VALUES('张三',10,5000);
-- 联合主键的两列都是主键，只要这些主键并不是都不相同，就视为不相同，就可以插入数据
INSERT INTO emp3 VALUES('张三',20,5000);
INSERT INTO emp3 VALUES('王五',20,5000);
-- 虽然可以部分主键相同，但是都不能为空
INSERT INTO emp3 VALUES(NULL,30,5000);
INSERT INTO emp3 VALUES('赵六',NULL,5000);
INSERT INTO emp3 VALUES(NULL,NULL,5000);


-- 3.通过修改表结构添加主键

-- 添加单列主键
CREATE TABLE emp4(
	eid INT,
	name VARCHAR(20),
	deptId INT,
	salary DOUBLE
);
ALTER TABLE emp4 ADD PRIMARY KEY(eid);

CREATE TABLE emp5(
	eid INT,
	name VARCHAR(20),
	deptId INT,
	salary DOUBLE
);
ALTER TABLE emp5 ADD PRIMARY KEY(name,deptId);

-- 4.删除主键
-- 删除主键是不分单列和多列的，直接全部删除

-- 删除单列主键
ALTER TABLE emp1 DROP PRIMARY KEY;

-- 删除多列主键
ALTER TABLE emp5 DROP PRIMARY KEY;

-- 二、自增长约束

-- 自增正约束
USE mydb1;
CREATE TABLE t_user1(
	id INT PRIMARY KEY auto_increment,
	name VARCHAR(20)
);

INSERT INTO t_user1 VALUES(NULL,'张三');
INSERT INTO t_user1(name) VALUES('李四');

-- DELETE删除数据之后，自增长还是在最后一个值基础上加1
DELETE FROM t_user1;

-- 前面插了两个值删除后，断点为3，所以从3开始进行标注
INSERT INTO t_user1 VALUES(NULL,'张三');
INSERT INTO t_user1(name) VALUES('李四');

-- TRUNCATE删除之后，自增长从1开始
TRUNCATE t_user1;

INSERT INTO t_user1 VALUES(NULL,'张三');
INSERT INTO t_user1(name) VALUES('李四');

-- 指定自增长的初始值
-- 方式1，创建表时指定
CREATE TABLE t_user2(
	id INT PRIMARY KEY auto_increment,
	name VARCHAR(20)
)auto_increment=100;

-- id从给定的值开始
INSERT INTO t_user2 VALUES(NULL,'张三');
INSERT INTO t_user2(name) VALUES('李四');

-- TRUNCATE即使给定初始开始值，删除后依旧从系统自定的1开始
TRUNCATE t_user2;

INSERT INTO t_user2 VALUES(NULL,'张三');
INSERT INTO t_user2(name) VALUES('李四');

-- 方式2，创建表之后指定
CREATE TABLE t_user3(
	id INT PRIMARY KEY auto_increment,
	name VARCHAR(20)
);

ALTER TABLE t_user3 auto_increment=200;

INSERT INTO t_user3 VALUES(NULL,'张三');
INSERT INTO t_user3(name) VALUES('李四');

-- 三、非空约束

-- 创建非空约束
-- 方式1，创建表时指定
CREATE TABLE t_user6(
	id INT,
	name VARCHAR(20) NOT NULL,
	address VARCHAR(20) NOT NULL
);

-- 如果定义了 NOT NULL，那么在insert这个数据的时候必须要添加这个值，即使没有做声明
INSERT INTO t_user6(id) VALUES(1001);
INSERT INTO t_user6(id) VALUES(1001,NULL,NULL);
-- 这个NULL表示的是字符串NULL
INSERT INTO t_user6(id,name,address) VALUES(1001,'NULL','NULL');
-- 这个代表空串
INSERT INTO t_user6(id,name,address) VALUES(1001,'','');

-- 方式2，创建表之后指定
CREATE TABLE t_user7(
	id INT,
	name VARCHAR(20),
	address VARCHAR(20)
);

ALTER TABLE t_user7 MODIFY name VARCHAR(20) NOT NULL;
ALTER TABLE t_user7 MODIFY address VARCHAR(20) NOT NULL;

DESC t_user7;

-- 删除非空约束
ALTER TABLE t_user7 MODIFY name VARCHAR(20);
ALTER TABLE t_user7 MODIFY address VARCHAR(20);


-- 四、唯一约束

-- 1.添加唯一约束
-- 方式1，创建表时指定
USE mydb1;
CREATE TABLE t_user8(
	id INT,
	name VARCHAR(20),
	phone_number VARCHAR(20) UNIQUE
);

INSERT INTO t_user8 VALUES(1001,'张三',138);
-- 重复会报错
INSERT INTO t_user8 VALUES(1002,'张三',138);
INSERT INTO t_user8 VALUES(1002,'张三',139);
-- NULL可以多次插入执行
-- 在MySQL中，NULL和任何值都不相同，甚至和自己都不相同
INSERT INTO t_user8 VALUES(1003,'张三3',NULL);
INSERT INTO t_user8 VALUES(1004,'张三4',NULL);

-- 方式2，创建表之后指定
CREATE TABLE t_user9(
	id INT,
	name VARCHAR(20),
	phone_number VARCHAR(20) UNIQUE
);

ALTER TABLE t_user9 add CONSTRAINT unique_pn UNIQUE(phone_number);

INSERT INTO t_user9 VALUES(1001,'张三',138);
INSERT INTO t_user9 VALUES(1002,'张三2',138);

-- 2.删除唯一约束

-- 如果建立约束的时候没有使用名字，那么这个变量就是我们使用约束的列的名字
ALTER TABLE t_user9 DROP INDEX unique_pn;


-- 五、默认约束

-- 1.创建默认约束
-- 方式1，创建表时指定
USE mydb1;
CREATE TABLE t_user10(
	id INT,
	name VARCHAR(20),
	address VARCHAR(20) DEFAULT '北京'
);

-- 对某列执行默认约束的时候，如果没有给这一列赋值，那么会对这一列默认赋值
INSERT INTO t_user10(id,NAME) VALUES(1001,'张三');
INSERT INTO t_user10(id,NAME,address) VALUES(1002,'张三2','上海');
INSERT INTO t_user10 VALUES(1003,'李四',NULL);

-- 方式2，创建表之后指定
CREATE TABLE t_user11(
	id INT,
	name VARCHAR(20),
	address VARCHAR(20)
);

ALTER TABLE t_user11 MODIFY address VARCHAR(20) DEFAULT '深圳';

INSERT INTO t_user11(id,NAME) VALUES(1001,'张三');
INSERT INTO t_user11(id,NAME,address) VALUES(1002,'张三2','上海');
INSERT INTO t_user11 VALUES(1003,'李四',NULL);

-- 2.删除默认约束

ALTER TABLE t_user11 MODIFY address VARCHAR(20) DEFAULT NULL;

-- 六、零填充约束

-- 1.添加约束
CREATE TABLE t_user12(
	id INT ZEROFILL,
	name VARCHAR(20)
);

INSERT INTO t_user12 VALUES(123,'张三');
INSERT INTO t_user12 VALUES(1,'李四');

-- 2.删除约束

ALTER TABLE t_user12 MODIFY id INT;

INSERT INTO t_user12 VALUES(123,'张三');
INSERT INTO t_user12 VALUES(1,'李四');





