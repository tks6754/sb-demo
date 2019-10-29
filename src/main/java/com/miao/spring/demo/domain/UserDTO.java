package com.miao.spring.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private int id;

    private String name;

    private Integer age;
}
