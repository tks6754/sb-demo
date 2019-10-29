package com.miao.spring.demo.controller;


import com.miao.spring.demo.domain.UserDTO;
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
        UserDTO userDTO = new UserDTO(1, "miao", 27);

        //return GlobalResponse.success(user);

        return GlobalResponse.failed("40001", "错误演示");
    }

    @GetMapping("/list")
    public GlobalResponse<TableRet<UserDTO>> listUser(){
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO(1, "miao", 27));
        userDTOList.add(new UserDTO(2, "kun", 28));

        TableRet<UserDTO> tableRet = new TableRet<>(2, 0, 1, true, 2, 1, 1, userDTOList);
        return GlobalResponse.success(tableRet);
    }



}
