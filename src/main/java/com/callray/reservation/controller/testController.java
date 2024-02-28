package com.callray.reservation.controller;


import com.callray.reservation.entity.User;
import com.callray.reservation.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    private UserDao userDao;

    @GetMapping("v1")
    public String test1(){

        return userDao.selectUserAll().toString();

    }
    @GetMapping("v2")
    public String test2(){
        User user = new User();

//        new LocalDateTime
        user.setName("jack");
        user.setPassword("132");
        user.setEmail("12341234");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        System.out.println(user);
        userDao.addUser(user);

        return "success";

    }

}
