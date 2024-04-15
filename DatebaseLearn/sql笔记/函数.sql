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




















