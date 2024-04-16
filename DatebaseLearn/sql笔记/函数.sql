create database mydb4;
use mydb4;
 
create table emp(
    emp_id int primary key auto_increment comment '编号',
    emp_name char(20) not null default '' comment '姓名',
    salary decimal(10,2) not null default 0 comment '工资',
    department char(20) not null default '' comment '部门'
);
 
insert into emp(emp_name,salary,department) 
values('张晶晶',5000,'财务部'),('王飞飞',5800,'财务部'),('赵刚',6200,'财务部'),('刘小贝',5700,'人事部'),
('王大鹏',6700,'人事部'),('张小斐',5200,'人事部'),('刘云云',7500,'销售部'),('刘云鹏',7200,'销售部'),
('刘云鹏',7800,'销售部');

-- MySQL函数

-- 一、聚合函数
-- 这里我们只学习group_concat()函数

-- 1、将所有员工的名字合并成一行
SELECT GROUP_CONCAT(emp_name) FROM emp;

-- 2、指定分隔符合并
SELECT GROUP_CONCAT(emp_name SEPARATOR ';') FROM emp;

-- 3、指定排序方式和分隔符
-- 使用group by分组之后，concat会将每一组的数据进行合并
SELECT department, GROUP_CONCAT(emp_name SEPARATOR ';') FROM emp GROUP BY department;
SELECT department, GROUP_CONCAT(emp_name ORDER BY salary DESC SEPARATOR ';') FROM emp GROUP BY department;


-- 二、数学函数
-- 求绝对值
SELECT ABS(-10);	-- 10
SELECT ABS(10);		-- 10


-- 向上取整
SELECT CEIL(1.5);	-- 2
SELECT CEIL(1.0);	-- 1

-- 向下取整
SELECT FLOOR(1.1);	-- 1
SELECT FLOOR(1.9);	-- 1

-- 取列表最大值
SELECT GREATEST(1,2,3);	-- 3

-- 取列表最小值
SELECT LEAST(1,2,3);	-- 1

-- 取模
SELECT MOD(5, 2);	-- 1

-- 取x的y次方
SELECT POW(2, 3);	-- 8

-- 取随机数
SELECT RAND();
SELECT FLOOR(RAND() * 100);

-- 取小数的四舍五入
SELECT ROUND(3.1415);	-- 3
SELECT ROUND(3.5454);	-- 4
SELECT ROUND(3.5415, 3);	-- 3.542

-- 将小数直接截取到指定位数
SELECT TRUNCATE(3.1415,3);	-- 3.141

-- 三、字符串函数

-- 获取字符串字符个数
SELECT CHAR_LENGTH('hello');	-- 5
SELECT CHAR_LENGTH('你好吗');	-- 3

SELECT LENGTH('hello');		-- 5
SELECT LENGTH('你好吗');	-- 9

-- 字符串合并
SELECT CONCAT('hello','world');

-- 指定分隔符进行字符串合并
SELECT CONCAT_WS(' ', 'hello','world');

-- 返回字符串在列表中第一次出现的位置
SELECT field('aaa', 'aaa', 'bbb', 'ccc');
SELECT field('bbb', 'aaa', 'bbb', 'ccc');

-- 去除字符串左边空格
SELECT ltrim('   aaa');

-- 去除字符串右边空格
SELECT rtrim('aaa   ');

-- 字符串截取
-- 从第二个字符开始截取，截取长度为3
SELECT MID("hello world",2,3)

-- 获取字符串A在字符串中出现的位置
SELECT POSITION('abc' IN 'habcelloaidislj');

-- 字符串替换
SELECT REPLACE('helloaaaworld','aaa','bbb');

-- 字符串反转
SELECT REVERSE('hello');

-- 返回字符串的后几个字符
SELECT right('hello', 3);	-- 返回最后三个字符

-- 字符串比较
-- 字典序比较
SELECT STRCMP('hello', 'world');

-- 字符串截取
SELECT SUBSTR('hello', 2, 3);

-- 小写转大写
SELECT UCASE("hello world");
SELECT UPPER("hello world");

-- 大写转小写
SELECT LCASE("HELLO WORLD");
SELECT LOWER("HELLO WORLD");

-- 四、日期函数

-- 获取时间戳（毫秒值）
SELECT UNIX_TIMESTAMP();

-- 将一个日期字符串转为毫秒值
SELECT UNIX_TIMESTAMP('2021-12-21 08:08:08');

-- 将时间戳毫秒值转为指定格式的日期
SELECT FROM_UNIXTIME(UNIX_TIMESTAMP(),'%Y-%m-%d %H:%i:%s');

-- 获取当前的年月日
SELECT CURDATE();
SELECT CURRENT_DATE();

-- 获取当前的时分秒
SELECT CURRENT_TIME();
SELECT CURTIME();

-- 获取年月日和时分秒
SELECT CURRENT_TIMESTAMP();

-- 从日期字符串中获取年月日
SELECT DATE(CURRENT_TIMESTAMP());

-- 获取日期之间的差值
SELECT DATEDIFF(CURRENT_DATE(),CURRENT_DATE());

-- 获取时间差值（秒级）
SELECT TIMEDIFF('12:12:34','10:18:56');

-- 日期格式化
SELECT DATE_FORMAT('2021-1-1 1:1:1','%Y-%m-%d %H:%i:%s');

-- 将字符串转为日期
SELECT STR_TO_DATE('2021-1-1 1:1:1','%Y-%m-%d %H:%i:%s')

-- 将日期进行减法
SELECT DATE_SUB('2021-10-01',INTERVAL 2 DAY);
SELECT DATE_SUB('2021-10-01',INTERVAL 2 MONTH);

-- 将日期进行加法
SELECT DATE_ADD('2021-10-01',INTERVAL 2 DAY);
SELECT DATE_ADD('2021-10-01',INTERVAL 2 MONTH);

-- 从日期中获取小时/年/月
SELECT EXTRACT(HOUR FROM '2021-12-13 11:12:13');
SELECT EXTRACT(YEAR FROM '2021-12-13 11:12:13');
SELECT EXTRACT(MONTH FROM '2021-12-13 11:12:13');

-- 获取给定日期所在月的最后一天
SELECT LAST_DAY('2021-10-01');

-- 获取指定年份和天数的日期
SELECT MAKEDATE('2021',53);

-- 根据日期获取年月日，时分秒
SELECT YEAR('2021-12-13 11:12:13');
SELECT MONTH('2021-12-13 11:12:13');
SELECT MINUTE('2021-12-13 11:12:13');

-- 根据日期获取信息
SELECT MONTHNAME('2021-12-13 11:12:13');
SELECT DAYNAME('2021-12-13 11:12:13');
SELECT DAYOFMONTH('2021-12-13 11:12:13');
SELECT DAYOFWEEK('2021-12-13 11:12:13');
SELECT DAYOFYEAR('2021-12-13 11:12:13');

-- 
SELECT WEEK('2021-12-13 11:12:13');
SELECT WEEKDAY('2021-12-13 11:12:13');
SELECT YEARWEEK('2021-12-13 11:12:13');
SELECT NOW();


-- 五、控制流函数

-- IF逻辑控制语句
SELECT IF(5 > 3, '大于', '小于');
USE mydb3;
SELECT IF(score >= 85, '优秀', '及格') flag FROM score;

SELECT IFNULL(5,0);
SELECT IFNULL(NULL,0);

USE test1;
SELECT *, IFNULL(comm,0) comm_flag FROM emp;

SELECT ISNULL(5);	-- 0
SELECT ISNULL(NULL);	-- 1

SELECT NULLIF(12,12);	-- NULL
SELECT NULLIF(12,13);	-- 12

-- CASE WHEN语句

SELECT
	CASE 5
		WHEN 1 THEN '你好'
		WHEN 2 THEN 'hello'
		WHEN 5 THEN '正确'
		ELSE
			'其他'
	END AS info;
	
SELECT
	CASE 
		WHEN 2 > 1 THEN '你好'
		WHEN 2 < 1 THEN 'hello'
		WHEN 3 > 2 THEN '正确'
		ELSE
			'其他'
	END AS info;

use mydb4; 
-- 创建订单表
create table orders(
 oid int primary key, -- 订单id
 price double, -- 订单价格
 payType int -- 支付类型(1:微信支付 2:支付宝支付 3:银行卡支付 4：其他)
);
 
insert into orders values(1,1200,1);
insert into orders values(2,1000,2);
insert into orders values(3,200,3);
insert into orders values(4,3000,1);
insert into orders values(5,1500,2);

-- 方式1
SELECT
CASE payType
	WHEN 1 THEN '微信支付'
	WHEN 2 THEN '支付宝支付'
	WHEN 3 THEN '银行卡支付'
	ELSE
		'其他支付方式'
END AS payTypeStr
FROM orders;

-- 方式2
SELECT
CASE 
	WHEN payType = 1 THEN '微信支付'
	WHEN payType = 2 THEN '支付宝支付'
	WHEN payType = 3 THEN '银行卡支付'
	ELSE
		'其他支付方式'
END AS payTypeStr
FROM orders;

-- 六、窗口函数


use mydb4; 
create table employee( 
   dname varchar(20), -- 部门名 
   eid varchar(20), 
   ename varchar(20), 
   hiredate date, -- 入职日期 
   salary double -- 薪资
); 

insert into employee values('研发部','1001','刘备','2021-11-01',3000);
insert into employee values('研发部','1002','关羽','2021-11-02',5000);
insert into employee values('研发部','1003','张飞','2021-11-03',7000);
insert into employee values('研发部','1004','赵云','2021-11-04',7000);
insert into employee values('研发部','1005','马超','2021-11-05',4000);
insert into employee values('研发部','1006','黄忠','2021-11-06',4000);
insert into employee values('销售部','1007','曹操','2021-11-01',2000);
insert into employee values('销售部','1008','许褚','2021-11-02',3000);
insert into employee values('销售部','1009','典韦','2021-11-03',5000);
insert into employee values('销售部','1010','张辽','2021-11-04',6000);
insert into employee values('销售部','1011','徐晃','2021-11-05',9000);
insert into employee values('销售部','1012','曹洪','2021-11-06',6000);

-- 序号函数
-- 对每个部门的员工按照薪资排序，并给出排名
SELECT
dname,
ename,
salary,
ROW_NUMBER() OVER(
	PARTITION BY dname 
	ORDER BY salary DESC
) AS rn
FROM employee;

-- RANK()如果排序的字段相同，那么标记序号相同，后面会将相同后的下一个字段跳过
SELECT
dname,
ename,
salary,
RANK() OVER(
	PARTITION BY dname 
	ORDER BY salary DESC
) AS rn
FROM employee;

-- 相同的值依旧会标记，但是相同后的下一个字段不跳过
SELECT
dname,
ename,
salary,
DENSE_RANK() OVER(
	PARTITION BY dname 
	ORDER BY salary DESC
) AS rn
FROM employee;

-- 求出每个部门薪资排在前三名的员工	-分组求TOPN
-- 一定要用子查询，因为SELECT语句一定会先执行FROM和WHERE
SELECT 
* 
FROM(
	SELECT
		dname,
		ename,
		salary,
		DENSE_RANK() over(
			PARTITION BY dname 
			ORDER BY salary DESC
		) AS rn
	FROM employee
) t WHERE t.rn <= 3;

-- 对所有员工进行全局排序（不分组）
SELECT
*
FROM(
	SELECT
		dname,
		ename,
		salary,
		DENSE_RANK() over(
			ORDER BY salary DESC
		) AS rn
	FROM employee
) t WHERE t.rn <= 3;

-- 开窗聚合函数

-- 实现排序之后逐行值累加
SELECT
	dname,
	ename,
	salary,
	SUM(salary) OVER(
		PARTITION BY dname
		ORDER BY hiredate
	) AS pv1
FROM employee;

-- 如果没有加ORDER BY，默认把分组内的所有数据进行sum操作
SELECT
	dname,
	ename,
	salary,
	SUM(salary) OVER(
		PARTITION BY dname
	) AS c1
FROM employee;

-- 从顶行加到当前行
SELECT
	dname,
	ename,
	hiredate,
	salary,
	SUM(salary) OVER(
		PARTITION BY dname
		ORDER BY hiredate
		rows BETWEEN unbounded preceding AND current ROW 
	) AS c1
FROM employee;

-- 从向上3行加到当前行
SELECT
	dname,
	ename,
	hiredate,
	salary,
	SUM(salary) OVER(
		PARTITION BY dname
		ORDER BY hiredate
		rows BETWEEN 3 preceding AND current ROW 
	) AS c1
FROM employee;

-- 向上三行加到向后一行
SELECT
	dname,
	ename,
	hiredate,
	salary,
	SUM(salary) OVER(
		PARTITION BY dname
		ORDER BY hiredate
		rows BETWEEN 3 preceding AND 1 following
	) AS c1
FROM employee;

-- 从当前行加到最后
SELECT
	dname,
	ename,
	hiredate,
	salary,
	SUM(salary) OVER(
		PARTITION BY dname
		ORDER BY hiredate
		rows BETWEEN current row AND unbounded following
	) AS c1
FROM employee;

-- 分布函数

/*
rn1: 没有partition,所有数据均为1组，总行数为12，
     第一行：小于等于3000的行数为3，因此，3/12=0.25
     第二行：小于等于4000的行数为5，因此，5/12=0.4166666666666667
rn2: 按照部门分组，dname='研发部'的行数为6,
     第一行：研发部小于等于3000的行数为1，因此，1/6=0.16666666666666666
*/
SELECT
	dname,
	ename,
	salary,
	CUME_DIST() OVER(
		ORDER BY salary
	) AS rn1,
	CUME_DIST() OVER(
		PARTITION BY dname
		ORDER BY salary
	) AS rn2
FROM employee;


/*
 rn2:
  第一行: (1 - 1) / (6 - 1) = 0
  第二行: (1 - 1) / (6 - 1) = 0
  第三行: (3 - 1) / (6 - 1) = 0.4
*/
SELECT
	dname,
	ename,
	salary,
	RANK() OVER(
		PARTITION BY dname
		ORDER BY salary DESC
	) AS rn1,
	PERCENT_RANK() OVER(
		PARTITION BY dname
		ORDER BY salary
	) AS rn2
FROM employee;














