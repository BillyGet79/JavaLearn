#!/bin/bash
# 不带参数没有返回值的函数
hello(){
    echo "这是我的第一个shell函数！"
}
echo "---------函数开始执行----------"
hello
echo "---------函数执行完毕----------"

# 有返回值的函数
# 输入两个数字之后相加并返回结果：
funcWithReturn(){
    echo "输入第一个数字："
    read aNum
    echo "输入第二个数字："
    read anotherNum
    echo "两个数字分别为 $aNum 和 $anotherNum !"
    return $(($aNum+$anotherNum))
}
funcWithReturn
echo "输入的两个数字之和为 $?"
# 带参数的函数
funcWithParam(){
    echo "第一个参数为 $1 !"
    echo "第二个参数为 $2 !"
    echo "第十个参数为 $10 !"
    echo "第十个参数为 ${10} !"
    echo "第十一个参数为 ${11} !"
    echo "参数总数有 $# 个!"
    echo "作为一个字符串输出所有参数 $* !"
}
funcWithParam 1 2 3 4 5 6 7 8 9 34 73
