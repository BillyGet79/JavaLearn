#!/bin/bash
# 简单的字符串截取
# 从字符串第一个字符开始往后截取10个字符
str="SnailClimb is a great man"
echo ${str:0:10}	# 输出：SnailClimb

# 根据表达式截取
var="https://www.runoob.com/linux/linux-shell-variable.html"
# %表示删除从后匹配, 最短结果
# %%表示删除从后匹配, 最长匹配结果
# #表示删除从头匹配, 最短结果
# ##表示删除从头匹配, 最长匹配结果
# 注: *为通配符, 意为匹配任意数量的任意字符
s1=${var%%t*}	# h
s2=${var%t*}	# https://www.runoob.com/linux/linux-shell-variable.h
s3=${var%%.*}	# https://www
s4=${var#*/}	# /www.runoob.com/linux/linux-shell-variable.html
s5=${var##*/}	# linux-shell-variable.html
echo $s1
echo $s2
echo $s3
echo $s4
echo $s5
