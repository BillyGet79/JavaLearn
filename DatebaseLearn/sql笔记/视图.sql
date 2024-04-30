-- 视图

USE mydb6_view;
-- 创建视图

CREATE OR REPLACE VIEW view1_emp AS SELECT ename, job FROM emp;

-- 查看表和视图
SHOW TABLES;
SHOW FULL TABLES;

SELECT * FROM view1_emp;

-- 修改视图
ALTER VIEW view1_emp 
AS
SELECT a.deptno, a.dname, a.loc, b.ename, b.sal FROM dept a, emp b WHERE a.deptno = b.deptno;

SELECT * FROM view1_emp;

-- 更新视图
CREATE OR REPLACE VIEW view1_emp
AS
SELECT ename, job FROM emp;

-- 做更新直接做到原表里面
UPDATE view1_emp set ename = '周瑜' WHERE ename = '鲁肃';
-- 由于往原表里面插入值，所以数据并没有给全，所以会报错
INSERT INTO view1_emp VALUES('孙权','文员');

-- 视图包含聚合函数不可更新
CREATE OR REPLACE VIEW view2_emp
AS
SELECT count(*) cnt FROM emp GROUP BY deptno;

SELECT * FROM view2_emp;

INSERT INTO view2_emp VALUES(100);
UPDATE view2_emp set cnt = 100;

-- 视图包含distinct不可更新
CREATE OR REPLACE view view3_emp
AS
SELECT DISTINCT job FROM emp;

SELECT DISTINCT job FROM view3_emp;

INSERT INTO view3_emp VALUES('财务');

-- -------------------------------
CREATE OR REPLACE VIEW view4_emp
AS
SELECT deptno, COUNT(*) FROM emp GROUP BY deptno;

INSERT INTO view4_emp values(30, 100);

-- ------------------------------
CREATE OR REPLACE VIEW view5_emp
AS
SELECT empno, ename FROM emp WHERE empno <= 5
UNION
SELECT empno, ename FROM emp WHERE empno > 5;

INSERT INTO view5_emp values(1015, '韦小宝');

-- ------------------------------
CREATE OR REPLACE VIEW view6_emp
AS
SELECT empno, ename, sal FROM emp WHERE sal = (SELECT MAX(sal) FROM emp);

INSERT INTO view6_emp VALUES(1015, '韦小宝', 30000);

-- ------------------------------
CREATE OR REPLACE VIEW view7_emp
AS
SELECT dname, ename, sal FROM emp a JOIN dept b ON a.deptno = b.deptno;

INSERT INTO view7_emp(dname, ename, sal) VALUES('行政部', '韦小宝', 30000);

-- ------------------------------
CREATE OR REPLACE VIEW view8_emp
AS
SELECT '行政部', '杨过';

INSERT INTO view8_emp VALUES('行政部', '韦小宝');


-- 视图其他操作

-- 重命名视图
RENAME TABLE view1_emp TO myview1;

-- 删除视图
DROP VIEW IF EXISTS myview1;



-- 练习

-- 1.查询部门平均薪水最高的部门名称
SELECT
	dname,
	avg_sal 
FROM
	dept a,
	(
	SELECT
		* 
	FROM
		(
		SELECT
			*,
			RANK() over ( ORDER BY avg_sal DESC ) rn 
		FROM
			( SELECT deptno, avg( sal ) avg_sal FROM emp GROUP BY deptno ) t 
		) tt 
	WHERE
		rn = 1 
	) ttt 
WHERE
	a.deptno = ttt.deptno;

-- ----------使用视图进行优化------------
CREATE OR REPLACE view test_view1
AS
SELECT deptno, avg(sal) avg_sal FROM emp GROUP BY deptno;

CREATE OR REPLACE view test_view2
AS
SELECT *, RANK() over ( ORDER BY avg_sal DESC ) rn FROM test_view1; 

CREATE OR REPLACE view test_view3
AS
SELECT * FROM test_view2 WHERE rn = 1;

SELECT
	dname,
	avg_sal 
FROM
	dept a,
	test_view3 t 
WHERE
	a.deptno = t.deptno;


-- 2.查询员工比所属领导薪资高的部门名、员工名、员工领导编号

-- 2.1 查询员工比领导工资高的部门号
CREATE OR REPLACE VIEW test_view4
AS
SELECT
	a.ename ename,
	a.sal esal,
	b.ename mgrname,
	b.sal msal,
	b.empno mempno,
	a.deptno 
FROM
	emp a,
	emp b 
WHERE
	a.mgr = b.empno && a.sal > b.sal;
-- 2.2 将第一步查询出来的部门号和部门表进行链表查询
SELECT
	a.dname,
	b.ename,
	b.mempno 
FROM
	dept a
	JOIN test_view4 b ON a.deptno = b.deptno;


-- 3.查询工资等级为4，2000年以后入职的工作地点为上海的员工编号、姓名和工资，并查询出薪资在前三名的员工信息
-- 3.1 查询工资等级为4，2000年以后入职的工作地点为上海的员工编号、姓名和工资
CREATE OR REPLACE VIEW test_view5
AS
SELECT
	b.empno,
	b.ename,
	b.sal 
FROM
	dept a
	JOIN emp b ON a.deptno = b.deptno 
	AND YEAR ( hiredate ) > '2000' 
	AND a.loc = '上海'
	JOIN salgrade c ON grade = 4 
	AND b.sal BETWEEN c.losal 
	AND c.hisal;

-- 3.2 查询出薪资在前三名的员工信息
CREATE OR REPLACE VIEW test_view6
AS
SELECT *, RANK() OVER(ORDER BY sal) rn FROM test_view5;

SELECT
	a.empno,
	a.ename,
	a.job,
	a.mgr,
	a.hiredate,
	a.sal,
	a.COMM 
FROM
	emp a,
	test_view6 b 
WHERE
	a.empno = b.empno 
	AND rn <= 3;

























