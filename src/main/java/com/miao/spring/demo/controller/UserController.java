package com.miao.spring.demo.controller;


import com.miao.spring.demo.domain.User;
import com.miao.spring.demo.http.GlobalResponse;
import com.miao.spring.demo.http.TableRet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/one")
    public GlobalResponse<User> getUser(){
        User user = new User(1, "miao", 27);

        //return GlobalResponse.success(user);

        return GlobalResponse.failed("40001", "错误演示");
    }

    @GetMapping("/list")
    public GlobalResponse<TableRet<User>> listUser(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "miao", 27));
        userList.add(new User(2, "kun", 28));

        TableRet<User> tableRet = new TableRet<>(2, 0, 1, true, 2, 1, 1, userList);
        return GlobalResponse.success(tableRet);
    }



}
