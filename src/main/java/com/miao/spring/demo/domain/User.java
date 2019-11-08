package com.miao.spring.demo.domain;

import lombok.Data;

@Data
public class User {
    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像
     */
    private String headImg;
}