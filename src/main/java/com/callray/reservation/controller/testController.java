package com.callray.reservation.controller;


import com.callray.reservation.entity.User;
import com.callray.reservation.mapper.UserDao;
import com.callray.reservation.utils.MailClient;
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
    @Autowired
    private MailClient mailClient;

    @GetMapping("v1")
    public String test1(){

        return userDao.selectUserAll().toString();

    }
    @GetMapping("v2")
    public String test2() {

//        mailClient.sendMail("1713037329@qq.com","test","nice too meet you!");

        User user = new User();

//        new LocalDateTime
        user.setName("jack");
        user.setPassword("132");
        user.setEmail("123");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

//        System.out.println(user);

        try{
            mailClient.sendMail("1713037329@qq.com","test","nice too meet you!");
            System.out.println("邮件发送成功！");
//            Boolean b = userDao.addUser(user);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

//        System.out.println(userDao.queryPasswordForEmail("123"));



        return "success";

    }

}
