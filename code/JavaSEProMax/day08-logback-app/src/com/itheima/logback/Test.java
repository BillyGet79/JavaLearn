package com.itheima.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 目标：快速搭建Logback日志框架，记录程序的执行情况到控制台 到文件中。
 */
public class Test {
    //创建Logback的日志对象，代表了日志技术
    public static final Logger LOGGER = LoggerFactory.getLogger("Test.class");
    public static void main(String[] args) {
            try {
                LOGGER.debug("main方法开始执行了~~~");
                LOGGER.info("我开始记录第二行日志，我要开始做除法");
                int a = 10;
                int b = 0;
                LOGGER.trace("a=" + a);
                LOGGER.trace("b=" + b);

                System.out.println(a / b);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("功能出现异常，" + e);
            }
    }
}
