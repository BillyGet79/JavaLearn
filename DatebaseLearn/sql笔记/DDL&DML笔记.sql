-- 1、DDL操作之数据库操作

-- 查看所有数据库
SHOW DATABASES;
-- 创建数据库
CREATE DATABASE MYDB1;
CREATE DATABASE IF NOT EXISTS MYDB1;
-- 选择使用哪一个数据
USE mydb1;
-- 删除数据库
DROP DATABASE mydb1;
DROP DATABASE  IF EXISTS mydb1;

-- 修改数据库编码
ALTER DATABASE mydb1 CHARACTER SET utf8;

-- 创建表

-- 选择mydb1
USE mydb1;

CREATE TABLE IF NOT EXISTS student(
	sid INT,
	name VARCHAR(20),
	gender VARCHAR(20),
	age INT,
	birth DATE,
	address VARCHAR(20)
);

-- 查看当前数据库所有的表
SHOW TABLES;
-- 查看指定表的创建语句
SHOW CREATE TABLE student;
/*
CREATE TABLE `student` (
  `sid` int DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3
*/
-- 查看表结构
DESC student;
-- 删除表
DROP TABLE student;

-- 修改表结构
-- 添加列
ALTER TABLE student ADD dept VARCHAR(20);

-- 为student表的dept字段更换为department VARCHAR(30)
ALTER TABLE student CHANGE dept department VARCHAR(30); 

-- 删除student表中department这列
ALTER TABLE student DROP department;

-- 将student表的名字改为stu
RENAME TABLE student TO stu;

-- DML操作
-- 1.数据的插入

-- 格式1：
INSERT INTO student(sid,name,gender,age,birth,address,score)
VALUES(1001,'张三','男',18,'2001-12-23','北京',85.5);

-- 一次插入多个数据
INSERT INTO student(sid,name,gender,age,birth,address,score)
VALUES
(1002,'李四','男',19,'2002-09-13','上海',78.5),
(1003,'王五','女',17,'2003-11-29','深圳',66.5);

-- 只插入部分列的值
INSERT INTO student(sid) VALUES(1004);
INSERT INTO student(sid,name) VALUES(1005,'赵六');

-- 格式2：
INSERT INTO student VALUES(1006,'张华','女',21,'1999-01-21','广州',79);

-- 一次插入多个数据
INSERT INTO student VALUES
(1008,'钱博','男',21,'2005-05-07','北京',98),
(1007,'李方','男',24,'1998-05-04','武汉',89);

-- 2.数据修改

-- 将所有学生的地址修改为重庆
UPDATE student SET address='重庆';
-- 将id为1004的学生的地址修改为北京
UPDATE student SET address='北京' WHERE sid=1004;
-- 将id为1005的学生的地址修改为北京，成绩修改为100
UPDATE student SET address='北京',score=100 WHERE sid=1005;

-- 3.数据删除

-- 删除sid为1004的学生数据
DELETE FROM student WHERE sid=1004;
-- 删除表所有数据
DELETE FROM student;
-- 清空表数据
TRUNCATE TABLE student;
TRUNCATE student;



