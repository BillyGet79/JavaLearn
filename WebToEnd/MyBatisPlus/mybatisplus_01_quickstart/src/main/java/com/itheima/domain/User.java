package com.itheima.domain;

//lombok
import lombok.*;

@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;

}
