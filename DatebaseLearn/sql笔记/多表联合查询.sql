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

-- 左外连接
-- 查询哪些部门有员工，哪些部门没有员工
SELECT * FROM dept3 LEFT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id;

-- 右外连接
-- 查询员工有对应的部门，哪些没有
SELECT * FROM dept3 RIGHT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id;

-- 使用满外连接
-- 使用union关键字实现左外连接和右外连接的并集
-- UNION是将两个查询结果上下拼接，并去重
SELECT * FROM dept3 LEFT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id 
UNION
SELECT * FROM dept3 RIGHT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id;

-- 将左外连接和右外连接拼接在一起，不去重
SELECT * FROM dept3 LEFT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id 
UNION ALL
SELECT * FROM dept3 RIGHT OUTER JOIN emp3 ON dept3.deptno = emp3.dept_id;

-- 四、子查询

-- 查询年龄最大的员工信息，显示信息包含员工号、员工名字、员工年龄
-- 1.查询最大年龄：SELECT MAX(age) FROM emp3;

-- 2.让每一个员工年龄和最大年龄进行比较，相等则满足条件
SELECT * FROM emp3 WHERE age = (SELECT MAX(age) FROM emp3);	-- 单行单列，可以作为一个值来用

-- 查询年研发部和销售部的员工信息，包含员工号、员工名字
-- 方式1-关联查询
SELECT * FROM dept3 a JOIN emp3 b ON a.deptno = b.dept_id AND (name = '研发部' OR name = '销售部');
-- 方式2-子查询
-- 2.1 先查询研发部和销售部的部门号:deptno
SELECT deptno FROM dept3 WHERE name = '研发部' OR name = '销售部';
-- 2.2 查询哪个员工的部门号是1001 或者 1002
SELECT * FROM emp3 WHERE dept_id IN (SELECT deptno FROM dept3 WHERE name = '研发部' OR name = '销售部');


-- 查询研发部20岁以下的员工信息，包括员工号、员工名字、部门名字
-- 方式1-关联查询
SELECT * FROM dept3 a JOIN emp3 b on a.deptno = b.dept_id and (name = '研发部' and age < 20);
-- 方式2-子查询
-- 2.1 在部门表中查询研发部信息
SELECT * FROM dept3 WHERE name = '研发部';
-- 2.2 在员工表中查询年龄小于20岁的员工信息
SELECT * FROM emp3 WHERE age < 20;
-- 2.3 将以上两个查询的结果进行关联查询
SELECT * FROM (SELECT * FROM dept3 WHERE name = '研发部') t1 JOIN (SELECT * FROM emp3 WHERE age < 20) t2 ON t1.deptno = t2.dept_id;

-- 子查询关键字-ALL

-- 查询年龄大于'1003'部门所有年龄的员工信息
SELECT * FROM emp3 WHERE age > ALL(SELECT age FROM emp3 WHERE dept_id = '1003');

-- 查询不属于任何一个部门的员工信息
SELECT * FROM emp3 WHERE dept_id != ALL(SELECT deptno FROM dept3);










































