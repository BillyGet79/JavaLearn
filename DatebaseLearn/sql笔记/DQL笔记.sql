USE mydb2;
-- 一、简单查询

-- 1.查询所有的商品
SELECT pid,pname,price,category_id FROM product;
SELECT*FROM product;
-- 2.查询商品名和商品价格
SELECT pname,price FROM product;
-- 3.别名查询，使用的关键字是as
-- 3.1表别名
SELECT*FROM product AS p;
SELECT*FROM product p;
-- 3.2列别名
SELECT pname AS '商品名',price AS '商品价格' FROM product;
-- 4.去掉重复值
-- 针对某一列去掉重复值
SELECT DISTINCT price FROM product;
-- 针对于所有列，只有所有列都相等的时候才会被去重
SELECT DISTINCT * FROM product;
-- 5.查询结构式表达式（运算查询）
SELECT pname,price+10 FROM product;


-- 二、运算符
USE mydb2;

-- 1.算数运算符
SELECT 6+2;
SELECT 6-2;
SELECT 6*2;
SELECT 6/2;
SELECT 6 % 4;
-- 将所有商品的价格加10元
SELECT pname,price+10 AS new_price FROM product;
-- 将所有的商品价格上调10%
SELECT pname,price*1.1 AS new_price FROM product;
-- 2.比较运算符
-- 3.逻辑运算符
-- 查询商品名称为“海尔洗衣机”的商品所有信息
SELECT*FROM product WHERE pname='海尔洗衣机';
-- 查询价格为800的商品
SELECT pname FROM product WHERE price=800;
-- 查询价格不是800的所有商品
SELECT pname FROM product WHERE price !=800;
SELECT pname FROM product WHERE price<> 800;
SELECT pname FROM product WHERE NOT (price=800);
-- 查询商品价格大于60元的所有商品信息
SELECT*FROM product WHERE price> 60;
-- 查询商品价格在200到1000之间所有商品
SELECT pname FROM product WHERE price>=200 AND price<=800;
SELECT pname FROM product WHERE price>=200 && price<=800;
SELECT pname FROM product WHERE price BETWEEN 200 AND 1000;
-- 查询商品价格是200或800的所有商品
SELECT pname FROM product WHERE price=200 || price=800;
SELECT pname FROM product WHERE price=200 OR price=800;
SELECT pname FROM product WHERE price IN (200,800);
-- 查询含有'裤'字的所有商品
-- %用来匹配任意字符
SELECT pname FROM product WHERE pname LIKE '%裤%';
-- 查询以'海'开头的所有商品
SELECT pname FROM product WHERE pname LIKE '海%';
-- 查询第二个字为'蔻'的所有商品
SELECT pname FROM product WHERE pname LIKE '_蔻%';
-- 查询category_id为null的商品
-- 不可以用等于的方式查询值为null的项
SELECT pname FROM product WHERE category_id IS NULL;
-- 查询catgegory_id不为null分类的商品
SELECT pname FROM product WHERE category_id IS NOT NULL;
-- 使用least求最小值
SELECT LEAST(10,5,20) AS small_number;
-- 如果求最小函数的输入有null，那么最后一定返回null
SELECT LEAST(10,NULL,20);
-- 使用greatest求最大值
SELECT GREATEST(10,20,30) AS big_number;
-- 如果求最大函数的输入有null，那么最后一定返回null
SELECT GREATEST(10,NULL,30);
-- 4.位运算符（这个不会直接remake吧）
SELECT 3 & 5;
SELECT 3 | 5;
SELECT 3 ^ 5;
SELECT 3>> 1;
SELECT 3<< 1;
SELECT ~ 3;

-- 三、排序查询

-- 1.使用价格排序（降序）
SELECT*FROM product ORDER BY price DESC;
-- 2.在价格排序（降序）的基础上，以分类排序（降序）
SELECT*FROM product ORDER BY price DESC,category_id DESC;
-- 3.显示商品的价格（去重复），并排序（降序）
SELECT DISTINCT price FROM product ORDER BY price DESC;


-- 四、聚合查询

-- 1.查询商品的总条数
SELECT COUNT(pid) FROM product;
SELECT COUNT(*) FROM product;
-- 2.查询价格大于200商品的总条数
SELECT COUNT(pid) FROM product WHERE price> 200;
-- 3.查询分类为'c001'的所有商品的价格总和
SELECT SUM(price) FROM product WHERE category_id='c001';
-- 4.查询商品的最大价格
SELECT MAX(price) FROM product;
-- 5.查询商品的最小价格
SELECT MIN(price) FROM product;
SELECT MAX(price) max_price,MIN(price) min_price FROM product;
-- 6.查询分类为'c002'所有商品的平均价格
SELECT AVG(price) FROM product WHERE category_id='c002';

-- NULL值的处理
-- 创建表
CREATE TABLE test_null (
	c1 VARCHAR (20),
	c2 INT
);

-- 插入数据
INSERT INTO test_null VALUES ('aaa',3);
INSERT INTO test_null VALUES ('bbb',3);
INSERT INTO test_null VALUES ('ccc',NULL);
INSERT INTO test_null VALUES ('ddd',6);

-- 测试
SELECT COUNT(*),COUNT(c1),COUNT(c2) FROM test_null;
SELECT SUM(c2),MAX(c2),MIN(c2),AVG(c2) FROM test_null;

-- 五、分组查询

-- 1.统计各个分类商品的个数
SELECT category_id,COUNT(pid) FROM product GROUP BY category_id;
-- 如果GROUP BY字段后面跟了很多分类项，那么只有所有分类项都相同时才能分为一类
-- 注意，分组之后，SELECT的后面只能写分组字段和聚合函数

-- 2.统计各个分类商品的个数，且只显示个数大于4的信息
-- SQL执行顺序：FROM->GROUP BY->COUNT(pid)->SELECT->HAVING->ORDER BY
SELECT category_id,COUNT(pid) cnt FROM product GROUP BY category_id HAVING COUNT(pid) > 4 ORDER BY cnt;


-- 六、分页查询

-- 1.查询product表的前5条记录
SELECT * FROM product LIMIT 5;
-- 2.从第4条开始显示，显示5条
-- 因为SQL默认是从0开始，所以从第四条开始显示要写3
SELECT * FROM product LIMIT 3,5;


-- 七、INSERT INTO SELECT

-- INSERT INTO TABLE SELECT ...

CREATE TABLE product2(
	pname VARCHAR(20),
	price DOUBLE
);

INSERT INTO product2(pname,price) SELECT pname,price FROM product;
SELECT * FROM product2;

CREATE TABLE product3(
	category_id VARCHAR(20),
	product_count INT
);

INSERT INTO product3 SELECT category_id,count(*) FROM product GROUP BY category_id;
SELECT * FROM product3;










