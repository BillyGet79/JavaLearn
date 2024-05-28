#!/bin/bash
# 算数运算符
a=3;b=3;
val=`expr $a + $b`
# 输出：6
echo "Total value: $val"
# 关系运算符
score=90
maxscore=100
if [ $score -eq $maxscore ]
then
	echo "A"
else
	echo "B"
fi
# 逻辑运算符
a=$((1 && 0))
# 输出：0；逻辑与运算只有相与的两边都是1，与的结果才是1；否则与的结果是0
echo $a
# 字符串运算符
a="abc"
b="efg"
if [ $a = $b ]
then
	echo "a 等于 b"
else
	echo "a 不等于 b"
fi

