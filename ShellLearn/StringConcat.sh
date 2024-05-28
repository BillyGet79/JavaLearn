#!/bin/bash
name="SnailClimb"
# 使用双引号拼接
greeting="hello,"$name" !"
greeting_1="hello, ${name} !"
echo $greeting
echo $greeting_1
# 使用单引号拼接
greeting_2='hello, '$name' !'
greeting_3='hello, ${name} !'
echo $greeting_2
echo $greeting_3
