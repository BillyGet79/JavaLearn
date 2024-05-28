#!/bin/bash
# 获取字符串长度
name="SnailClimb"
# 第一种方式
echo ${#name} #输出10
# 第二种方式
expr length "$name"

