-- 数据准备
CREATE DATABASE mydb7_procedure;
USE mydb7_procedure;

CREATE TABLE dept(
	deptno INT PRIMARY KEY,
	dname VARCHAR(20),
	loc VARCHAR(20)
);

INSERT INTO dept VALUES(10, '教研部', '北京');
INSERT INTO dept VALUES(20, '学工部', '上海');
INSERT INTO dept VALUES(30, '销售部', '广州');
INSERT INTO dept VALUES(40, '财务部', '武汉');

CREATE TABLE emp(
	empno INT PRIMARY KEY,
	ename VARCHAR(20),
	job VARCHAR(20),
	mgr INT,
	hiredate DATE,
	sal FLOAT,
	COMM FLOAT,
	deptno INT,
	CONSTRAINT emp_dept FOREIGN KEY(deptno) REFERENCES dept(deptno)
);

INSERT INTO emp VALUES(1001, '甘宁', '文员', 1013, '2000-12-17', 8000.00, NULL, 20);
INSERT INTO emp VALUES(1002, '黛绮丝', '销售员', 1006, '2001-02-20', 16000.00, 3000.00, 30);
INSERT INTO emp VALUES(1003, '殷天正', '销售员', 1006, '2001-02-22', 12500.00, 5000.00, 30);
INSERT INTO emp VALUES(1004, '刘备', '经理', 1009, '2001-04-02', 29750.00, NULL, 20);
INSERT INTO emp VALUES(1005, '谢逊', '销售员', 1006, '2001-09-28', 12500.00, 14000.00, 30);
INSERT INTO emp VALUES(1006, '关羽', '经理', 1009, '2001-05-01', 28500.00, NULL, 30);
INSERT INTO emp VALUES(1007, '张飞', '经理', 1009, '2001-09-01', 24500.00, NULL, 10);
INSERT INTO emp VALUES(1008, '诸葛亮', '分析师', 1004, '2007-04-19', 30000.00, NULL, 20);
INSERT INTO emp VALUES(1009, '曾阿牛', '董事长', NULL, '2001-11-17', 50000.00, NULL, 10);
INSERT INTO emp VALUES(1010, '韦一笑', '销售员', 1006, '2001-09-08', 15000.00, 0.00, 30);
INSERT INTO emp VALUES(1011, '周泰', '文员', 1008, '2007-05-23', 11000.00, NULL, 20);
INSERT INTO emp VALUES(1012, '程管', '文员', 1006, '2001-12-03', 9500.00, NULL, 30);
INSERT INTO emp VALUES(1013, '庞统', '分析师', 1004, '2001-12-03', 30000.00, NULL, 20);
INSERT INTO emp VALUES(1014, '黄盖', '文员', 1007, '2002-01-23', 13000.00, NULL, 10);

CREATE TABLE salgrade(
	grade INT,
	losal INT,
	hisal INT
);

INSERT INTO salgrade VALUES(1, 7000, 12000);
INSERT INTO salgrade VALUES(2, 12010, 14000);
INSERT INTO salgrade VALUES(3, 14010, 20000);
INSERT INTO salgrade VALUES(4, 20010, 30000);
INSERT INTO salgrade VALUES(5, 30010, 99990);


































