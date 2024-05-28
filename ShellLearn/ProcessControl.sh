#!/bin/bash
# if条件语句
a=3
b=9
if [ $a -eq $b ]
then
	echo "a 等于 b"
elif [ $a -gt $b ]
then
	echo "a 大于 b"
else
	echo "a 小于 b"
fi
# for循环语句
for loop in 1 2 3 4 5
do
	echo "The value is: $loop"
done
# 产生10个随机数
for i in {0..9};
do
	echo $RANDOM;
done
# 输出1到5
length=5
for((i=1;i<=length;i++))
do
	echo $i
done
# while语句
# 基本的while循环语句
int=1
while(($int<=5))
do
	echo $int
	let "int++"
done
# while循环可用于读取键盘信息
echo '按下<CTRL-D>退出'
echo -n '输入你最喜欢的电影'
while read FILM
do
	echo "是的！$FILM 是一个好电影"
done
# 无限循环
while true
do
	command
done