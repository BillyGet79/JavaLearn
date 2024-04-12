use mydb2;
CREATE TABLE student(
	id INT,
	name VARCHAR(20),
	gender VARCHAR(20),
	chinese INT,
	english INT,
	math INT
);

INSERT INTO student(id,name,gender,chinese,english,math) VALUES(1,'张明','男',89,78,90);
INSERT INTO student(id,name,gender,chinese,english,math) VALUES(2,'李进','男',67,53,95);
INSERT INTO student(id,name,gender,chinese,english,math) VALUES(3,'王五','女',87,78,77);
INSERT INTO student(id,name,gender,chinese,english,math) VALUES(4,'李一','女',88,98,92);
INSERT INTO student(id,name,gender,chinese,english,math) VALUES(5,'李财','男',82,84,67);
INSERT INTO student(id,name,gender,chinese,english,math) VALUES(6,'张宝','男',55,85,45);
INSERT INTO student(id,name,gender,chinese,english,math) VALUES(7,'黄蓉','女',75,65,30);
INSERT INTO student(id,name,gender,chinese,english,math) VALUES(7,'黄蓉','女',75,65,30);


-- 查询表中所有学生的信息
SELECT * FROM student;
-- 查询表中所有学生的姓名和对应的英语成绩
SELECT name, english FROM student;
-- 过滤表中重复数据
SELECT DISTINCT * FROM student;
-- 统计每个学生的总分
SELECT chinese+english+math FROM student;
-- 在所有学生总分数上加10分特长分
SELECT chinese+english+math+10 FROM student;
-- 使用别名表示学生分数
SELECT name, chinese '语文成绩', english '英语成绩', math '数学成绩' FROM student;
-- 查询英语成绩大于90分的同学
SELECT * FROM student WHERE english > 90;
-- 查询总分大于200分的所有同学
SELECT * FROM student WHERE chinese+english+math > 200;
-- 查询英语分数在80-90之间的同学
SELECT * FROM student WHERE english >= 80 && english <= 90;
-- 查询英语分数不在80-90之间的同学
SELECT * FROM student WHERE english < 80 || english > 90;
-- 查询数学分数为89，90，91的同学
SELECT * FROM student WHERE math = 89 || math = 90 || math = 91;
-- 查询所有姓李的学生英语成绩
SELECT * FROM student WHERE name LIKE '李%'
-- 查询数学分80并且语文分80的同学
SELECT * FROM student WHERE math = 80 && chinese = 80;
-- 查询英语80或者总分200的同学
SELECT * FROM student WHERE english = 80 || chinese + math + english = 200;
-- 对数学成绩降序排序后输出
SELECT * FROM student ORDER BY math DESC;
-- 对总分排序后输出，然后再按从高到低的顺序输出
SELECT * FROM student ORDER BY chinese + math + english DESC;
-- 对姓李的学生成绩排序输出
SELECT * FROM student WHERE name LIKE '李%' ORDER BY chinese + math + english DESC;
-- 查询男生和女生分别有多少人，并将人数降序排序输出
SELECT gender, COUNT(*) FROM student GROUP BY gender ORDER BY COUNT(*) DESC;


-- ---------------------------------------------
CREATE TABLE emp(
	empno INT,	-- 员工编号	
	ename VARCHAR(20),	-- 员工名字
	job VARCHAR(20),	-- 工作名字
	mgr INT,	-- 上级领导编号
	hiredate DATE,	-- 入职日期
	sal INT,	-- 薪资
	comm INT,	-- 奖金
	deptno INT	-- 部门编号
);

INSERT INTO emp VALUES(7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20);
INSERT INTO emp VALUES(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30);
INSERT INTO emp VALUES(7521,'WARD','SALESMAN',7698,'1981-02-22',1250,500,30);
INSERT INTO emp VALUES(7566,'JONES','MANAGER',7839,'1981-04-02',2975,NULL,20);
INSERT INTO emp VALUES(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30);
INSERT INTO emp VALUES(7698,'BLAKE','MANAGER',7839,'1981-05-01',2850,NULL,30);
INSERT INTO emp VALUES(7782,'CLARK','MANAGER',7839,'1981-06-09',2450,NULL,10);
INSERT INTO emp VALUES(7788,'SCOTT','ANALYST',7566,'1987-04-19',3000,NULL,20);
INSERT INTO emp VALUES(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10);
INSERT INTO emp VALUES(7844,'TURNER','SALESMAN',7698,'1981-09-08',1500,0,30);
INSERT INTO emp VALUES(7876,'ADAMS','CLERK',7788,'1987-05-23',1100,NULL,20);
INSERT INTO emp VALUES(7900,'JAMES','CLERK',7698,'1981-12-03',950,NULL,30);
INSERT INTO emp VALUES(7902,'FORD','ANALYST',7566,'1981-12-03',3000,NULL,20);
INSERT INTO emp VALUES(7934,'MILLER','CLERK',7782,'1981-01-23',1300,NULL,10);
-- ------------------------------------------------------------------------

-- 1、按员工编号升序排序不在10号部门工作的员工信息
SELECT * FROM emp WHERE deptno != 10 ORDER BY empno ASC;
-- 2、查询姓名第二个字母不是“A”且薪水大于1000元的员工信息，按年薪降序排列\
-- ifnull(comm,0) 如果comm的值为null，则当作0，不为null，则还是原来的值 
SELECT * FROM emp WHERE ename NOT LIKE '_A%' AND sal > 1000 ORDER BY (12*sal + IFNULL(comm,0)) DESC;
-- 3、求每个部门的平均薪水
SELECT deptno,AVG(sal) FROM emp GROUP BY deptno;
-- 4、求各个部门的最高薪水
SELECT deptno,MAX(sal) FROM emp GROUP BY deptno;
-- 5、求每个部门每个岗位的最高薪水
SELECT deptno,job,MAX(sal) FROM emp GROUP BY deptno, job;
-- 6、求平均薪水大于2000的部门编号
SELECT deptno,AVG(sal) FROM emp GROUP BY deptno HAVING AVG(sal) > 2000;
-- 7、将部门平均薪水大于1500的部门编号列出来，按部门平均薪水降序排列
SELECT deptno,AVG(sal) FROM emp GROUP BY deptno HAVING AVG(sal) > 1500 ORDER BY AVG(sal) DESC;
-- 8、选择公司中有奖金的员工姓名，工资
SELECT ename,sal FROM emp WHERE comm IS NOT NULL;
-- 9、查询员工最高工资和最低工资的差距
SELECT MAX(sal) - MIN(sal) FROM emp;















