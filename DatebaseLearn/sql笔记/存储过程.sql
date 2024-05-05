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


-- 循环-WHILE

-- 创建测试表
CREATE TABLE user(
	uid INT PRIMARY KEY,
	username VARCHAR(50),
	password varchar(50)
);

-- 需求：向表中添加指定条数数据
delimiter $$
CREATE PROCEDURE proc16_while(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	label:WHILE i <= insertCount DO
		INSERT INTO user(uid, username, password) VALUES(i, CONCAT('user', i), '123456');
		SET i = i + 1;
	END WHILE label;
end$$
delimiter;

CALL proc16_while(10);

-- WHILE + LEAVE
TRUNCATE TABLE user;

delimiter $$
CREATE PROCEDURE proc17_while_leave(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	label:WHILE i <= insertCount DO
		INSERT INTO user(uid, username, password) VALUES(i, CONCAT('user', i), '123456');
		IF i = 5
			THEN LEAVE label;
		END IF;
		SET i = i + 1;
	END WHILE label;
end$$
delimiter;

CALL proc17_while_leave(10);

-- WHILE + ITERATE
CREATE TABLE user2(
	uid INT,
	username VARCHAR(50),
	password varchar(50)
);
TRUNCATE TABLE user2;

delimiter $$
CREATE PROCEDURE proc18_while_iterate(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 0;
	label:WHILE i < insertCount DO
		SET i = i + 1;
		IF i = 5
			THEN ITERATE label;
		END IF;
		INSERT INTO user2(uid, username, password) VALUES(i, CONCAT('user', i), '123456');
	END WHILE label;
	SELECT '循环结束';
end$$
delimiter;

CALL proc18_while_iterate(10);	-- 1 2 3 4 6 7 8 9 0


-- REPEAT
TRUNCATE TABLE user;
delimiter $$
CREATE PROCEDURE proc19_repeat(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	label:REPEAT
		INSERT INTO user(uid, username, password) VALUES(i, CONCAT('user', i), '123456');
		SET i = i + 1;
		UNTIL i > insertCount 
	END REPEAT label;
	SELECT '循环结束';
end$$
delimiter;

CALL proc19_repeat(10);

-- LOOP
TRUNCATE TABLE user;
delimiter $$
CREATE PROCEDURE proc20_loop(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	label: LOOP
		INSERT INTO user(uid, username, password) VALUES(i, CONCAT('user-', i), '123456');
		SET i = i + 1;
		IF i > insertCount
			THEN
				LEAVE label;
		END IF;
	END LOOP label;
END $$
delimiter;

CALL proc20_loop(100);

-- 操作游标(CURSOR)

-- 声明游标
-- 打开游标
-- 通过游标获取值
-- 关闭游标

-- 需求：输入一个部门名，查询该部门员工的编号、名字、薪资，将查询的结果集添加游标
DROP PROCEDURE proc21_cursor;
delimiter $$
CREATE PROCEDURE proc21_cursor(in in_dname VARCHAR(50))
BEGIN
-- 	定义局部变量
	DECLARE var_empno VARCHAR(50);
	DECLARE var_ename VARCHAR(50);
	DECLARE var_sal DECIMAL(7,2);
-- 	声明游标
	DECLARE my_cursor CURSOR FOR
		SELECT empno, ename, sal
		FROM dept a, emp b
		WHERE a.deptno = b.deptno AND a.dname = in_dname;
-- 	打开游标
	OPEN my_cursor;
-- 	通过游标获取值
	label:LOOP
		FETCH my_cursor INTO var_empno, var_ename, var_sal;
		SELECT var_empno, var_ename, var_sal;
	END LOOP label;
-- 	关闭游标
	CLOSE my_cursor;
END $$
delimiter;

CALL proc21_cursor('销售部');


-- 异常处理-句柄HANDLER
DROP PROCEDURE proc22_handler;
delimiter $$
CREATE PROCEDURE proc22_handler(in in_dname VARCHAR(50))
BEGIN
-- 	定义局部变量
	DECLARE var_empno VARCHAR(50);
	DECLARE var_ename VARCHAR(50);
	DECLARE var_sal DECIMAL(7,2);
-- 	定义标记值
	DECLARE flag int DEFAULT 1;
-- 	声明游标
	DECLARE my_cursor CURSOR FOR
		SELECT empno, ename, sal
		FROM dept a, emp b
		WHERE a.deptno = b.deptno AND a.dname = in_dname;
-- 	定义句柄：定义异常的处理方式
	/*
		1.异常处理完之后程序该怎么执行
			CONTINUE:继续执行剩余代码
			EXIT:直接终止程序
			UNDO:不支持
		2.触发条件
			条件码:
			条件名:
				SQLWARNING
				NOT FOUND
				SQLEXCEPTION
		3.异常触发之后执行什么代码
			设置flag的值 --> 0
	*/
	DECLARE CONTINUE HANDLER FOR 1329 SET flag = 0;
-- 	打开游标
	OPEN my_cursor;
-- 	通过游标获取值
	label:LOOP
		FETCH my_cursor INTO var_empno, var_ename, var_sal;
-- 	判断flag，如果flag的值为1，则执行，否则不执行
		IF flag = 1 THEN
			SELECT var_empno, var_ename, var_sal;
		ELSE
			LEAVE label;
		END IF;
	END LOOP label;
-- 	关闭游标
	CLOSE my_cursor;
END $$
delimiter;

CALL proc22_handler('销售部');



-- 练习

CREATE DATABASE mydb18_proc_demo;
USE mydb18_proc_demo;
DROP PROCEDURE IF EXISTS proc22_demo;
delimiter $$
CREATE PROCEDURE proc22_demo()
BEGIN
	DECLARE next_year INT;	-- 下一个月的年份
	DECLARE next_month INT;	-- 下一个月的月份
	DECLARE next_month_day INT;	-- 下一个月最后一天的日期
	
	DECLARE next_month_str VARCHAR(2);	-- 下一个月的月份字符串
	DECLARE next_month_day_str VARCHAR(2);	-- 下一个月的日字符串
	
	-- 处理每天的表名
	DECLARE table_name_str VARCHAR(10);
	DECLARE t_index INT DEFAULT 1;
	
	-- 获取下个月的年份
	SET next_year = YEAR(DATE_ADD(NOW(), INTERVAL 1 MONTH));
	
  -- 获取下个月是几月
	SET next_month = MONTH(DATE_ADD(NOW(), INTERVAL 1 MONTH));
	
  -- 下个月最后一天是几号
	SET next_month_day = DAYOFMONTH(LAST_DAY(DATE_ADD(NOW(),INTERVAL 1 MONTH)));
	
	IF next_month < 10
		THEN SET next_month_str = CONCAT('0', next_month);	-- 1 --> 01
	ELSE
		SET next_month_str = CONCAT('', next_month);	-- 12 --> 12
	END IF;
	
	WHILE t_index <= next_month_day DO
		IF t_index < 10
			THEN SET next_month_day_str = CONCAT('0', t_index);
		ELSE
			SET next_month_day_str = CONCAT('', t_index);
		END IF;
		
    -- 2021_11_01
		SET table_name_str = CONCAT(next_year, '_', next_month_str, '_', next_month_day_str);
		-- 拼接create SQL
		SET @create_table_sql = concat(
                    'create table user_',
                    table_name_str,
                    '(`uid` INT ,`ename` varchar(50) ,`information` varchar(50)) COLLATE=\'utf8_general_ci\' ENGINE=InnoDB');
		-- FROM后面不能使用局部变量
		PREPARE create_table_stmt FROM @create_table_sql;
		EXECUTE create_table_stmt;
		DEALLOCATE PREPARE create_table_stmt;
		
		set t_index = t_index + 1;
		
	END WHILE;
END $$
delimiter;

CALL proc22_demo();

-- 存储函数

-- 创建一个数据库
CREATE DATABASE mydb9_function;
USE mydb9_function;

-- 允许创建函数权限信任
set GLOBAL log_bin_trust_function_creators = TRUE;
-- 创建存储函数-没有参数
DROP FUNCTION IF EXISTS myfunc1_emp;
delimiter $$
CREATE FUNCTION myfunc1_emp() RETURNS INT
BEGIN
	-- 定义局部变量
	DECLARE cnt INT DEFAULT 0;
	SELECT COUNT(*) INTO cnt FROM emp;
	RETURN cnt;
END $$
delimiter;

-- 调用存储函数
SELECT myfunc1_emp();

-- 创建存储函数-有输入参数
-- 需求：传入一个员工的编号，返回员工的名字
DROP FUNCTION IF EXISTS myfunc2_emp;
delimiter $$
CREATE FUNCTION myfunc2_emp(in_empno INT) RETURNS VARCHAR(50)
BEGIN
	-- 定义局部变量
	DECLARE out_ename VARCHAR(50);
	SELECT ename INTO out_ename FROM emp WHERE empno = in_empno;
	RETURN out_ename;
END $$
delimiter;

-- 调用存储函数
SELECT myfunc2_emp(1008);













