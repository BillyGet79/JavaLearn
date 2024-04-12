-- 1.创建表
/*
创建员工表employee，字段如下：
id(员工编号)，name(员工名字)，gender(员工性别)，salary(员工薪资)
*/

-- USE mydb1;
-- 可以不选择数据库，直接用.的方式在相应的数据库添加表
CREATE TABLE IF NOT EXISTS mydb1.employee(
	id INT,
	name VARCHAR(20),
	gender VARCHAR(10),
	salary DOUBLE
);

-- 2.插入数据
/*
1,'张三','男',2000
2,'李四','男',1000
3,'王五','女',4000
*/

-- 因为要对所有列定义值，所以这里不指定列了
INSERT INTO employee VALUES
(1,'张三','男',2000),
(2,'李四','男',1000),
(3,'王五','女',4000);

-- 3.student
-- 将所有员工薪水修改为5000元
UPDATE employee SET salary=5000;

-- 将姓名为'张三'的员工薪水修改为3000元
UPDATE employee SET salary=3000 WHERE name='张三';

-- 将姓名为'李四'的员工薪水修改为4000元，gender改为女
UPDATE employee SET salary=4000,gender='女' WHERE name='李四';

-- 将'王五'的薪水在原有的基础上增加1000元
UPDATE employee SET salary=salary+1000 WHERE name='王五';



