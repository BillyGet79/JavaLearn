package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.itheima")
@PropertySource({"jdbc.properties"})        //这个注解的配置文件书写不支持通配符
public class SpringConfig {

}
