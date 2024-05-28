#!/bin/bash
array=(1 2 3 4 5);
# 获取数组长度
length=${#array[@]}
# 或者
length2=${#array[*]}
# 输出数组长度
echo $length
echo $length2
# 输出数组第三个元素
echo ${array[2]}
unset array[1]	#删除下标为1的元素也就是删除第二个元素
for i in ${array[@]}; 
do echo $i;
done
unset array	#删除数组中的所有元素
for i in ${array[@]};
do echo $i;
done

