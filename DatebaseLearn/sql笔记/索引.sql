-- 索引

-- 单列索引

-- 普通索引

-- 创建索引
CREATE DATABASE mydb5;
USE mydb5;

-- 方式1-创建表的时候直接指定
CREATE TABLE student(
	sid INT PRIMARY KEY,
	card_id VARCHAR(20),
	name VARCHAR(20),
	gender VARCHAR(20),
	age INT,
	birth DATE,
	phone_num VARCHAR(20),
	score DOUBLE,
	INDEX index_name(name)	-- 给name列创建索引
);

-- 只有使用了name进行查找才会通过索引查找
SELECT * FROM student WHERE name = '张三';

-- 方式2-直接创建
CREATE INDEX index_gender ON student(gender);

-- 方式3-修改表结构（添加索引）
ALTER TABLE student ADD INDEX index_age(age);


-- 查看索引

-- 1、查看数据库所有索引
-- 数据库会为主键自动建立索引
SELECT * FROM mysql.innodb_index_stats a WHERE a.database_name = 'mydb5';

-- 2、查看表中所有索引
SELECT * FROM mysql.innodb_index_stats a WHERE a.database_name = 'mydb5' AND a.table_name LIKE '%student%';

-- 3、查看表中所有索引
SHOW INDEX FROM student;


-- 删除索引
DROP INDEX index_gender on student;

ALTER TABLE student DROP INDEX index_age;


-- 唯一索引

-- 创建索引
-- 方式1-创建表的时候直接指定
CREATE TABLE student2(
	sid INT PRIMARY KEY,
	card_id VARCHAR(20),
	name VARCHAR(20),
	gender VARCHAR(20),
	age INT,
	birth DATE,
	phone_num VARCHAR(20),
	score DOUBLE,
	UNIQUE index_card_id(card_id)	-- 给card_id列创建索引
);

-- 方式2-直接创建
CREATE TABLE student2(
	sid INT PRIMARY KEY,
	card_id VARCHAR(20),
	name VARCHAR(20),
	gender VARCHAR(20),
	age INT,
	birth DATE,
	phone_num VARCHAR(20),
	score DOUBLE
);
CREATE UNIQUE INDEX index_card_id ON student2(card_id);

-- 方式3-修改表结构（添加索引）
ALTER TABLE student2 ADD UNIQUE index_phone_num(phone_num);


-- 删除索引
DROP INDEX index_card_id on student2;

ALTER TABLE student2 DROP INDEX index_phone_num;


-- 组合索引

-- 创建索引--普通索引
CREATE INDEX index_phone_name ON student(phone_num, name);

-- 删除索引
DROP INDEX index_phone_name ON student;

-- 创建索引--唯一索引
-- 创建这个索引之后，插入值时不能出现相同的键值对
-- 组合索引符合最左原则，即只有使用了括号内左侧的列进行查找才会使用索引
CREATE UNIQUE INDEX index_phone_name ON student(phone_num, name);

-- 删除索引
DROP INDEX index_phone_name ON student;


-- 全文索引
show variables like '%ft%';

-- 数据准备
-- 创建表的时候添加全文索引
create table t_article (
     id int primary key auto_increment ,
     title varchar(255) ,
     content varchar(1000) ,
     writing_date date -- , 
     -- fulltext (content) -- 创建全文检索
);

insert into t_article values(null,"Yesterday Once More","When I was young I listen to the radio",'2021-10-01');
insert into t_article values(null,"Right Here Waiting","Oceans apart, day after day,and I slowly go insane",'2021-10-02'); 
insert into t_article values(null,"My Heart Will Go On","every night in my dreams,i see you, i feel you",'2021-10-03');
insert into t_article values(null,"Everything I Do","eLook into my eyes,You will see what you mean to me",'2021-10-04');
insert into t_article values(null,"Called To Say I Love You","say love you no new year's day, to celebrate",'2021-10-05');
insert into t_article values(null,"Nothing's Gonna Change My Love For You","if i had to live my life without you near me",'2021-10-06');
insert into t_article values(null,"Everybody","We're gonna bring the flavor show U how.",'2021-10-07');

-- 修改表结构添加全文索引
ALTER TABLE t_article ADD FULLTEXT index_content(content);

-- 添加全文索引
CREATE FULLTEXT INDEX index_content ON t_article(content);

-- 使用全文索引
-- 由于innodb_ft_min_token_size变量值为3，所以长度为2的搜索是没有结果的
SELECT * FROM t_article WHERE MATCH(content) AGAINST('yo')	-- 没有结果

SELECT * FROM t_article WHERE MATCH(content) AGAINST('you')	-- 有结果

-- 空间索引
create table shop_info (
  id  int  primary key auto_increment comment 'id',
  shop_name varchar(64) not null comment '门店名称',
  geom_point geometry not null comment '经纬度',
  spatial key geom_index(geom_point)
);






















































