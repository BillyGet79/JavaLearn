use mydb2;
-- 正则表达式


-- ^ 在字符串开始处进行匹配
SELECT 'abc' REGEXP '^a';
SELECT * FROM product WHERE pname REGEXP '^海';

-- $ 在字符串末尾开始匹配
SELECT 'abc' REGEXP 'a$';
SELECT 'abc' REGEXP 'c$';
SELECT * FROM product WHERE pname REGEXP '水$';

-- . 匹配任意字符，可以匹配除了换行符之外的任意字符
SELECT 'abc' REGEXP '.b';
SELECT 'abc' REGEXP '.c';
SELECT 'abc' REGEXP 'a.';

-- [...] 匹配括号内的任意单个字符
SELECT 'abc' REGEXP '[xyz]';
SELECT 'abc' REGEXP '[xaz]';

-- [^...] 注意^符号只有在[]内才是取反的意思，在别的地方都是表示开始处匹配
SELECT 'a' REGEXP '[^abc]';
SELECT 'x' REGEXP '[^abc]';
SELECT 'abc' REGEXP '[^a]';

-- a* 匹配0个或多个a，包括空字符串。	可以作为占位符使用，有没有指定字符都可以匹配到数据
SELECT 'stab' REGEXP '.ta*b';
SELECT 'stb' REGEXP '.ta*b';
SELECT '' REGEXP 'a*';

-- a+ 匹配1个或者多个a，但是不包括空字符
SELECT 'stab' REGEXP '.ta+b';
SELECT 'stb' REGEXP '.ta+b';

-- a? 匹配0个或者1个a
SELECT 'stb' REGEXP '.ta?b';
SELECT 'stab' REGEXP '.ta?b';
SELECT 'staab' REGEXP '.ta?b';

-- a1|a2 匹配a1或者a2
SELECT 'a' REGEXP 'a|b';
SELECT 'b' REGEXP 'a|b';
SELECT 'b' REGEXP '^(a|b)';
SELECT 'a' REGEXP '^(a|b)';
SELECT 'c' REGEXP '^(a|b)';

-- a{m} 匹配m个a
SELECT 'auuuuc' REGEXP 'au{4}c';
SELECT 'auuuuc' REGEXP 'au{3}c';

-- a{m,} 匹配m个或者更多个a
SELECT 'auuuuc' REGEXP 'au{3,}c';
SELECT 'auuuuc' REGEXP 'au{4,}c';
SELECT 'auuuuc' REGEXP 'au{5,}c';

-- a{m,n}匹配m到n个a，包含m和n
SELECT 'auuuuc' REGEXP 'au{3,5}c';
SELECT 'auuuuc' REGEXP 'au{4,5}c';
SELECT 'auuuuc' REGEXP 'au{5,10}c';

-- (abc)
-- abc作为一个序列匹配，不用括号括起来都是用单个字符去匹配，如果要把多个字符作为一个整体去匹配就需要用到括号，所以括号适合上面的所有情况
SELECT 'xababy' REGEXP 'x(abab)y';
SELECT 'xababy' REGEXP 'x(ab)*y';
SELECT 'xababy' REGEXP 'x(ab){1,2}y';






















