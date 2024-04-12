-- 一、创建外键约束

-- 方式1-在创建表时设置外键约束
-- 创建部门表
CREATE TABLE IF NOT EXISTS dept(
	deptno VARCHAR(20) PRIMARY KEY,	-- 部门号
	name VARCHAR(20)	-- 部门名字
);
-- 创建员工表
CREATE TABLE IF NOT EXISTS emp(
	eid VARCHAR(20) PRIMARY KEY,
	ename VARCHAR(20),
	age INT,
	dept_id VARCHAR(20),
	CONSTRAINT emp_fk FOREIGN KEY(dept_id) REFERENCES dept(deptno)	-- 外键约束
);

-- 方式2-在创建表外设置外键约束
-- 创建部门表
CREATE TABLE IF NOT EXISTS dept2(
	deptno VARCHAR(20) PRIMARY KEY,	-- 部门号
	name VARCHAR(20)	-- 部门名字
);
-- 创建员工表
CREATE TABLE IF NOT EXISTS emp2(
	eid VARCHAR(20) PRIMARY KEY,	-- 员工编号
	ename VARCHAR(20),	-- 员工名字
	age INT,	-- 员工年龄
	dept_id VARCHAR(20)	-- 员工所属部门
);
-- 创建外键约束
ALTER TABLE emp ADD CONSTRAINT dept_id_fk FOREIGN KEY(dept_id) REFERENCES dept2(deptno);


-- 二、在外键约束下的数据操作

-- 1.数据插入

-- 添加主表数据
-- 注意必须先给主表添加数据
INSERT INTO dept VALUES('1001','研发部');
INSERT INTO dept VALUES('1002','销售部');
INSERT INTO dept VALUES('1003','财务部');
INSERT INTO dept VALUES('1004','人事部');

-- 添加从表数据
-- 注意给从表添加数据时，外键列的值不能随便写，必须依赖主表的主键列
insert into emp values('1','乔峰',20, '1001');
insert into emp values('2','段誉',21, '1001');
insert into emp values('3','虚竹',23, '1001');
insert into emp values('4','阿紫',18, '1002');
insert into emp values('5','扫地僧',35, '1002');
insert into emp values('6','李秋水',33, '1003');
insert into emp values('7','鸠摩智',50, '1003'); 
insert into emp values('8','天山童姥',60, '1005');  -- 不可以

-- 2.删除数据
/*
注意：
1.主表的数据被从表依赖时，不能删除，否则可以删除
2.从表的数据可以随便删除
*/
DELETE FROM dept WHERE deptno = '1001';	-- 不可以删除
DELETE FROM dept WHERE deptno = '1004';	-- 可以删除
DELETE FROM emp WHERE eid = '7';	-- 可以删除

-- 三、删除外键约束

ALTER TABLE emp2 DROP FOREIGN KEY dept_id_fk;


-- 四、多对多关系


-- 学生表和课程表（多对多）
-- 1 创建学合适呢个表student（左侧主表）
CREATE TABLE IF NOT EXISTS student(
	sid INT PRIMARY KEY auto_increment,
	name VARCHAR(20),
	age INT,
	gender VARCHAR(20)
);
-- 2 创建课程表course（右侧主表）
CREATE TABLE course(
	cid INT PRIMARY KEY auto_increment,
	cidname VARCHAR(20)
);
-- 3 创建中间表
CREATE TABLE score(
	sid INT,
	cid INT,
	score DOUBLE
);
-- 4 建立外键约束（2次）
ALTER TABLE score ADD FOREIGN KEY(sid) REFERENCES student(sid);
ALTER TABLE score ADD FOREIGN KEY(cid) REFERENCES course(cid);
-- 5给学生表添加数据
INSERT INTO student VALUES(1,'小龙女',18,'女'),(2,'阿紫',19,'女'),(3,'周芷若',20,'男');
-- 6给课程表添加数据
INSERT INTO course VALUES(1,'语文'),(2,'数学'),(3,'英语');
-- 7给中间表添加数据
INSERT INTO score VALUES(1,1,78),(1,2,75),(2,1,88),(2,3,90),(3,2,80),(3,3,65);

-- 修改和删除时，中间从表可以随便删除和修改，但是两边的主表受从表依赖的数据不能删除或者修改







