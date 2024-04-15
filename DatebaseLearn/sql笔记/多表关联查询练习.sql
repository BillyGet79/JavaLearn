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

-- 添加部门和员工之间的主外键关系
ALTER TABLE emp ADD CONSTRAINT FOREIGN KEY emp(deptno) REFERENCES dept(deptno);

INSERT INTO emp VALUES(7369, 'smith', 'clerk', 7902, '1980-12-17', 800, NULL, 20);
INSERT INTO emp VALUES(7499, 'allen', 'salesman', 7698, '1981-02-20', 1600, 300, 30);
INSERT INTO emp VALUES(7521, 'ward', 'salesman', 7698, '1981-02-22', 1250, 500, 30);
INSERT INTO emp VALUES(7566, 'jones', 'manager', 7839, '1981-04-02', 2975, NULL, 20);
INSERT INTO emp VALUES(7654, 'martin', 'salesman', 7698, '1981-09-28', 1250, 1400, 30);
INSERT INTO emp VALUES(7698, 'blake', 'manager', 7839, '1981-05-01', 2850, NULL, 30);
INSERT INTO emp VALUES(7782, 'clark', 'manager', 7839, '1981-06-09', 2450, NULL, 10);
INSERT INTO emp VALUES(7788, 'scott', 'analyst', 7566, '1987-07-03', 3000, NULL, 20);
INSERT INTO emp VALUES(7839, 'king', 'president', NULL, '1981-11-17', 5000, NULL, 10);
INSERT INTO emp VALUES(7844, 'turner', 'salesman', 7698, '1981-09-18', 1500, 0, 30);
INSERT INTO emp VALUES(7876, 'adams', 'clerk', 7788, '1987-07-13', 1100, NULL, 20);
INSERT INTO emp VALUES(7900, 'james', 'clerk', 7698, '1981-12-03', 950, NULL, 30);
INSERT INTO emp VALUES(7902, 'ford', 'analyst', 7566, '1981-12-03', 3000, NULL, 20);
INSERT INTO emp VALUES(7934, 'miller', 'clerk', 7782, '1981-01-23', 1300, NULL, 10);

-- 创建工资登记表
CREATE TABLE salgrade(
	grade INT,	-- 等级
	losal DOUBLE,	-- 最低工资
	hisal DOUBLE	-- 最高工资
);

INSERT INTO salgrade VALUES(1, 700, 1200);
INSERT INTO salgrade VALUES(2, 1201, 1400);
INSERT INTO salgrade VALUES(3, 1401, 2000);
INSERT INTO salgrade VALUES(4, 2001, 3000);
INSERT INTO salgrade VALUES(5, 3001, 9999);

-- 练习
-- 1、返回拥有员工的部门名、部门号
SELECT DISTINCT a.dname, a.deptno FROM dept a, emp b WHERE a.deptno = b.deptno;

-- 2、工资水平多于smith的员工信息
SELECT * FROM emp WHERE sal > (SELECT sal FROM emp WHERE ename = 'smith');

-- 3、返回员工和所属经理的姓名
SELECT a.ename AS '姓名', b.ename AS '领导' FROM emp a, emp b WHERE a.mgr = b.empno;

-- 4、返回雇员的雇佣日期早于其经理雇佣日期的员工及其经理姓名
SELECT a.ename AS '姓名', b.ename AS '经理' FROM emp a, emp b WHERE a.mgr = b.empno && a.hiredate < b.hiredate;

-- 5、返回员工姓名及其所在的部门名称
SELECT ename, dname FROM emp, dept WHERE emp.deptno = dept.deptno;

-- 6、返回从事clerk工作的员工姓名和所在部门名称
SELECT ename AS '姓名', dname AS '部门名称' FROM emp, dept WHERE emp.deptno = dept.deptno && emp.job = 'clerk';

-- 7、返回部门号及其本部门的最低工资
SELECT deptno, MIN(sal) FROM emp GROUP BY deptno;  

-- 8、返回销售部(sales)所有员工的姓名
SELECT ename FROM emp WHERE deptno = (SELECT deptno FROM dept WHERE dname = 'sales');

-- 9、返回工资水平多余平均工资的员工
SELECT * FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);

-- 10、返回与scott从事相同工作的员工
SELECT * FROM emp WHERE job = (SELECT job FROM emp WHERE ename = 'scott');

-- 11、返回工资高于30部门所有员工工资水平的员工信息
SELECT * FROM emp WHERE sal > ALL(SELECT sal FROM emp WHERE deptno = 30);

-- 12、返回员工工作及其从事此工作的最低工资
SELECT job, MIN(sal) FROM emp GROUP BY job;

-- 13、计算出员工的年薪，并且以年薪排序
SELECT ename AS '姓名', (sal * 12 + IFNULL(comm,0)) AS '年薪' FROM emp ORDER BY sal * 12 + IFNULL(comm,0);

-- 14、返回工作处于第四级别的员工的姓名
SELECT ename FROM emp WHERE sal >= (SELECT losal FROM salgrade WHERE grade = 4) && sal <= (SELECT hisal FROM salgrade WHERE grade = 4);

-- 15、返回工资为二等级的职员名字、部门所在地
SELECT a.ename, b.loc FROM emp a, dept b 
	WHERE a.sal >= (SELECT losal FROM salgrade WHERE grade = 2) && a.sal <= (SELECT hisal FROM salgrade WHERE grade = 2) 
	AND a.deptno = b.deptno;













