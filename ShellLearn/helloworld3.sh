#!/bin/bash
# 单引号是不能转义的
name='SnailClimb'
hello='Hello, I am $name!'
echo $hello
hello="hello, I am $name"
echo $hello
