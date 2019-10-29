package com.miao.spring.demo.dao;

import com.miao.spring.demo.domain.User;
import com.miao.spring.demo.domain.UserDTO;

import java.util.List;

public interface UserMapper {

    User getById(Integer id);

    List<User> listUser(UserDTO userDTO);

    int count(UserDTO userDTO);


}