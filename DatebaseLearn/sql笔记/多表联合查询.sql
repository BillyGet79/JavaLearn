-- 一、交叉连接查询

SELECT * FROM dept3,emp3;

-- 二、内连接查询

-- 查询每个部门的所属员工
SELECT * FROM dept3,emp3 WHERE deptno = dept_id;
SELECT * FROM dept3 INNER JOIN emp3 ON deptno = dept_id;













































