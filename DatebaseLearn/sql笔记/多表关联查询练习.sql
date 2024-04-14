-- 创建test1数据库
CREATE DATABASE test1;

-- 选择使用test1数据库
USE test1;


-- 创建部门表
CREATE TABLE dept(
	deptno INT PRIMARY KEY,	-- 部门编号
	dname VARCHAR(14), -- 部门名称
	loc varchar(13)	-- 部门地址
);

INSERT INTO dept VALUES (10, 'accounting', 'new york');
INSERT INTO dept VALUES (20, 'research', 'dallas');
INSERT INTO dept VALUES (30, 'sales', 'chicago');
INSERT INTO dept VALUES (40, 'operations', 'boston');

-- 创建员工表
CREATE TABLE emp(
	empno INT PRIMARY KEY,	-- 员工编号
	ename VARCHAR(10),	-- 员工姓名
	job VARCHAR(9),	-- 员工工作
	mgr INT,	-- 员工直属领导编号
	hiredate DATE,	-- 入职时间
	sal DOUBLE,	-- 工资
	comm DOUBLE,	-- 奖金
	deptno INT	-- 对应dept表的外键	
)



