-- 存储过程

USE mydb7_procedure;

-- 1.创建存储过程
delimiter $$
CREATE PROCEDURE proc01()
BEGIN
	SELECT empno, ename FROM emp;
END $$
delimiter;

-- 调用存储过程
CALL proc01();


-- 变量定义
delimiter $$
CREATE PROCEDURE proc02()
BEGIN
	DECLARE var_name01 VARCHAR(20) DEFAULT 'aaa';	-- 声明/定义变量
	set var_name01 = 'zhangsan';	-- 给变量赋值
	SELECT var_name01;	-- 输出变量的值
END $$
delimiter;

CALL proc02();











































