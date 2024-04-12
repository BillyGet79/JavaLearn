-- 一、交叉连接查询

USE mydb3
SELECT * FROM dept3,emp3;

-- 二、内连接查询

-- 查询每个部门的所属员工
SELECT * FROM dept3,emp3 WHERE deptno = dept_id;
SELECT * FROM dept3 INNER JOIN emp3 ON deptno = dept_id;

-- 查询研发部和销售部的所属员工
SELECT * FROM dept3, emp3 WHERE dept3.deptno = emp3.dept_id and name IN('研发部','销售部');
SELECT * FROM dept3 JOIN emp3 ON dept3.deptno = emp3.dept_id and name IN('研发部','销售部');

-- 查询每个部门的员工数，并升序排序
SELECT deptno, COUNT(1) AS total_cnt FROM dept3, emp3 WHERE dept3.deptno = emp3.dept_id GROUP BY deptno ORDER BY total_cnt;
SELECT deptno, COUNT(1) AS total_cnt FROM dept3 JOIN emp3 ON dept3.deptno = emp3.dept_id GROUP BY deptno ORDER BY total_cnt;

-- 查询人数大于等于3的部门，并按人数降序排序
SELECT deptno, COUNT(1) AS total_cnt FROM dept3, emp3 WHERE dept3.deptno = emp3.dept_id GROUP BY deptno HAVING total_cnt >= 3 ORDER BY total_cnt DESC;
SELECT deptno, COUNT(1) AS total_cnt FROM dept3 JOIN emp3 ON dept3.deptno = emp3.dept_id GROUP BY deptno HAVING total_cnt >= 3 ORDER BY total_cnt DESC;

-- 三、外连接查询

-- 查询哪些部门有员工，哪些部门没有员工
SELECT * FROM dept3 LEFT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id;

-- 查询员工有对应的部门，哪些没有
SELECT * FROM dept3 RIGHT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id;

-- 使用union关键字实现左外连接和右外连接的并集
SELECT * FROM dept3 LEFT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id 
UNION
SELECT * FROM dept3 RIGHT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id;









































