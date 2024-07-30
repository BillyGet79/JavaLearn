package cn.itcast.hotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.itcast.hotel.mapper")
@SpringBootApplication
public class HotelAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelAdminApplication.class, args);
    }

}
