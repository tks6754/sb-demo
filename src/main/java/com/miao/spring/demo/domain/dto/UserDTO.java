package com.miao.spring.demo.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
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


    public UserDTO(String nickname, Integer age, String headImg) {
        this.nickname = nickname;
        this.age = age;
        this.headImg = headImg;
    }
}
