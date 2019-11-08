package com.miao.spring.demo.controller;


import com.miao.spring.demo.domain.dto.UserDTO;
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
    public GlobalResponse<UserDTO> getUser(){
        UserDTO userDTO = new UserDTO( "miao", 27, "https://user-gold-cdn.xitu.io/2019/9/21/16d5296dbc509b77");

        return GlobalResponse.success(userDTO);

        //return GlobalResponse.failed("40001", "错误演示");
    }

    @GetMapping("/list")
    public GlobalResponse<TableRet<UserDTO>> listUser(){
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO( "miao", 27, "https://user-gold-cdn.xitu.io/2019/9/21/16d5296dbc509b77"));
        userDTOList.add(new UserDTO( "kun", 28, "https://user-gold-cdn.xitu.io/2019/9/21/16d5296dbc509b77"));

        TableRet<UserDTO> tableRet = new TableRet<>(2, 0, 1, true, 2, 1, 1, userDTOList);
        return GlobalResponse.success(tableRet);
    }



}
