package com.miao.spring.demo.dao;

import com.miao.spring.demo.domain.User;

public interface UserMapper {

    User getById(Long id);

    User listUser();

    Long count();

}