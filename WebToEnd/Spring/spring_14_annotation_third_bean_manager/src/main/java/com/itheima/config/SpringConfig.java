package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.itheima.dao")
@PropertySource("jdbc.properties")
@Import({JdbcConfig.class})
public class SpringConfig {

}
