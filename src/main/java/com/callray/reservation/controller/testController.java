package com.callray.reservation.controller;


import com.callray.reservation.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    private UserDao userDao;

    @GetMapping("v1")
    public String test(){


        return userDao.selectUserAll().toString();

    }

}
