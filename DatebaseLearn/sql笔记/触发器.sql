-- mysql触发器

-- 数据准备
CREATE DATABASE IF NOT EXISTS mydb10_trigger;
USE mydb10_trigger;

-- 用户表
CREATE TABLE user(
	uid INT PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL
);

-- 用户信息操作日志表
CREATE TABLE user_logs(
	id INT PRIMARY KEY auto_increment,
	time TIMESTAMP,
	log_text VARCHAR(255)
);

-- 需求1：当user表添加一行数据，则会在user_log添加日志记录
-- 定义触发器
DROP TRIGGER IF EXISTS trigger_test1;
CREATE TRIGGER trigger_test1 AFTER INSERT
ON user FOR EACH ROW
INSERT INTO user_logs VALUES(NULL, NOW(), '有新用户添加');

-- 在user表中添加数据，让触发器执行
INSERT INTO USER VALUES(1, '张三', '123456');
INSERT INTO USER VALUES(2, '李四', '234567');
INSERT INTO USER VALUES(3, '王五', '345678');

-- 需求2：当user表被修改时，则会在user_log添加日志记录
DROP TRIGGER IF EXISTS trigger_test2;
delimiter $$
CREATE TRIGGER trigger_test2 BEFORE UPDATE
ON user FOR EACH ROW
BEGIN
	INSERT INTO user_logs VALUES(NULL, NOW(), '有用户信息被修改');
END $$
delimiter;

-- 在user表中修改数据，让触发器自动执行
UPDATE user SET password = '888888' WHERE uid = 1;
UPDATE user SET password = '666666' WHERE uid = 1;

-- NEW和OLD

-- INSERT类型的触发器

-- NEW
DROP TRIGGER IF EXISTS trigger_test3;
CREATE TRIGGER trigger_test3 AFTER INSERT
ON user FOR EACH ROW
INSERT INTO user_logs VALUES(NULL, NOW(), CONCAT('有新用户添加，信息为：',NEW.uid, NEW.username, NEW.password));

INSERT INTO USER VALUES(4, '赵六', '123456');

-- UPDATE类型的触发器
-- NEW
CREATE TRIGGER trigger_test5 AFTER UPDATE
ON user FOR EACH ROW
INSERT INTO user_logs VALUES(NULL, NOW(), CONCAT_WS(',', '有用户信息修改，信息修改之后为：',NEW.uid, NEW.username, NEW.password));

UPDATE user SET password = '777777' WHERE uid = 1;

-- OLD
DROP TRIGGER IF EXISTS trigger_test4;
CREATE TRIGGER trigger_test4 BEFORE UPDATE
ON user FOR EACH ROW
INSERT INTO user_logs VALUES(NULL, NOW(), CONCAT('有用户信息修改，信息修改之前为：',OLD.uid, OLD.username, OLD.password));

UPDATE user SET password = '999999' WHERE uid = 4;

-- DELETE类型触发器
-- OLD
DROP TRIGGER IF EXISTS trigger_test6;
CREATE TRIGGER trigger_test6 BEFORE DELETE
ON user FOR EACH ROW
INSERT INTO user_logs VALUES(NULL, NOW(), CONCAT_WS(',', '有用户信息被删除，被删除用户信息为：',OLD.uid, OLD.username, OLD.password));

DELETE FROM user WHERE uid = 4;

-- 查看触发器
SHOW TRIGGERS;

-- 删除触发器
DROP TRIGGER IF EXISTS trigger_test1;


































































