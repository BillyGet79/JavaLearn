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


delimiter $$
CREATE PROCEDURE proc03()
BEGIN
	DECLARE my_ename VARCHAR(20);	-- 声明/定义变量
	SELECT ename INTO my_ename FROM emp WHERE empno = 1001;	-- 给变量赋值
	SELECT my_ename;	-- 输出变量的值
END $$
delimiter;

CALL proc03();


-- 用户变量

delimiter $$
CREATE PROCEDURE proc04()
BEGIN
	SET @var_name01 = 'beijing';
	SELECT @var_name01;
END $$
delimiter;

CALL proc04();
SELECT @var_name01;	-- 也可以使用用户变量


-- 全局变量

-- 查看全局变量
SHOW GLOBAL VARIABLES;
-- 查看某全局变量
SELECT @@global.auto_increment_increment;
-- 修改全局变量的值
SET GLOBAL sort_buffer_size = 40000;
SET @@global.sort_buffer_size = 33000;

SELECT @@global.sort_buffer_size;

-- 会话变量

-- 查看系统变量
SHOW SESSION VARIABLES;
-- 查看某会话变量
SELECT @@session.auto_increment_increment;
-- 修改会话变量的值
SET SESSION sort_buffer_size = 50000;
SET @@session.sort_buffer_size = 50000;

SELECT @@session.sort_buffer_size;


-- 传入参数：IN

-- 层状由参数的存储过程，传入员工编号，查找员工信息
delimiter $$
CREATE PROCEDURE proc06(IN empno INT)
BEGIN
	SELECT * FROM emp WHERE emp.empno = empno;
END $$
delimiter;

CALL proc06(1001);
CALL proc06(1002);

-- 封装由参数的存储过程，可以通过传入部门名和薪资，查询指定部门，并且薪资大于指定部门的员工信息。
delimiter $$
CREATE PROCEDURE proc07(IN dname VARCHAR(50), IN sal DECIMAL(7,2))
BEGIN
	SELECT * FROM dept a, emp b WHERE a.deptno = b.deptno && a.dname = dname && b.sal > sal;
END $$
delimiter;

CALL proc07('学工部', 20000);
CALL proc07('销售部', 10000);

-- 传出参数：OUT

-- 封装有参数的存储过程，传入员工编号，返回员工名字
delimiter $$
CREATE PROCEDURE proc08(IN in_empno INT, OUT out_ename VARCHAR(50))
BEGIN
	SELECT ename INTO out_ename FROM emp WHERE empno = in_empno;
END $$
delimiter;

CALL proc08(1002, @o_ename);
SELECT @o_ename;

-- 封装有参数的存储过程，传入员工编号，返回员工名字和薪资

delimiter $$
CREATE PROCEDURE proc09(IN in_empno INT, OUT out_ename VARCHAR(50), out out_sal DECIMAL(7,2))
BEGIN
	SELECT ename, sal INTO out_ename, out_sal FROM emp WHERE empno = in_empno;
END $$
delimiter;

CALL proc09(1001, @o_ename, @o_sal);
SELECT @o_ename, @o_sal;

-- INOUT

-- 传入一个数字，传出这个数字的10倍值
delimiter $$
CREATE PROCEDURE proc10(inout num INT)
BEGIN
	set num = num * 10;
END $$
delimiter;

set @inout_num = 3;
CALL proc10(@inout_num);
SELECT @inout_num;

-- 传入员工名，拼接部门号，传入薪资，求出年薪
delimiter $$
CREATE PROCEDURE proc11(INOUT inout_ename VARCHAR(50), INOUT inout_sal INT)
BEGIN
	SELECT concat_ws('_', deptno, ename) INTO inout_ename FROM emp WHERE ename = inout_ename;
	SET inout_sal = inout_sal * 12;
END $$
delimiter;

SET @inout_ename = '关羽';
SET @inout_sal = 3000;

CALL proc11(@inout_ename, @inout_sal);
SELECT @inout_ename, @inout_sal;

-- IF
-- 案例1
-- 输入一个学生的成绩，来判断成绩的级别：
/*
  score < 60 :不及格
  score >= 60  , score <80 :及格
  score >= 80 , score < 90 :良好
  score >= 90 , score <= 100 :优秀
  score > 100 :成绩错误
*/
delimiter $$
CREATE PROCEDURE proc_12_if(IN score INT)
BEGIN
	IF score < 60 
		THEN
			SELECT '不及格';
	ELSEIF score >= 60 AND score < 80
		THEN
			SELECT '及格';
	ELSEIF score >= 80 AND score < 90
		THEN
			SELECT '良好';
	ELSEIF score >= 90 AND score <= 100
		THEN
			SELECT '优秀';
	ELSE
		SELECT '成绩错误';
	END IF;
END $$
delimiter;

SET @score = 100;
CALL proc_12_if(@score);

-- 输入员工的名字，判断工资的情况。
/*
	sal < 10000 : 试用薪资
	sal >= 10000 and sal < 20000 : 转正薪资
	sal >= 20000 : 元老薪资
*/
delimiter $$
CREATE PROCEDURE proc_13_if(IN in_ename VARCHAR(20))
BEGIN
	DECLARE var_sal DECIMAL(7,2);
	DECLARE result VARCHAR(20);
	SELECT sal INTO var_sal FROM emp WHERE ename = in_ename;
	
	IF var_sal < 10000
		THEN
			SET result = '试用薪资';
	ELSEIF var_sal < 20000
		THEN
			SET result = '转正薪资';
	ELSE
		SET result = '元老薪资';
	END IF;
	SELECT result;
END $$
delimiter;


CALL proc_13_if('程普');
CALL proc_13_if('关羽');

-- CASE
-- 格式1
/*
	支付方式：
	1	微信支付
	2	支付宝支付
	3	银行卡支付
	4	其他支付方式
*/
delimiter $$
CREATE PROCEDURE proc14_case(IN pay_type INT)
BEGIN
	case pay_type
		WHEN 1 THEN SELECT '微信支付';
		WHEN 2 THEN SELECT '支付宝支付';
		WHEN 3 THEN SELECT '银行卡支付';
		ELSE SELECT '其他方式支付';
	END CASE;
END $$
delimiter;

CALL proc14_case(2);
CALL proc14_case(4);

-- 格式2
delimiter $$
CREATE PROCEDURE proc_15_case(IN score INT)
BEGIN
	CASE
	WHEN score < 60
		THEN select '不及格';
	WHEN score < 80
		THEN select '及格';
	WHEN score >= 80 AND score < 90
		THEN select '良好';
	WHEN score >= 90 AND score <= 100
		THEN select '优秀';
	ELSE
		SELECT '成绩错误';
	END CASE;
END $$
delimiter;

CALL proc_15_case(88);




























