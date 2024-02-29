package com.callray.reservation.service;

import com.callray.reservation.entity.User;
import com.callray.reservation.mapper.UserDao;
import com.callray.reservation.utils.MailClient;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * 注册，登录
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    /**
     * 注册
     * @param user 用户信息
     * @return 成功则返回“注册成功”否则返回失败原因
     */
    @Transactional
    public String register(User user) {
        String mag = null;
        try {
            if (0 != userDao.findEmail(user.getEmail())) {
                mag = "账号已存在";
                throw new Exception(mag);
            }
            userDao.addUser(user);
            return "注册成功";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return mag;
        }

    }

    //登录
    public User signIn(String email, String password) {

        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("账号不存在");
        }
        if (!password.equals(userDao.findPasswordByEmail(email))) {
            throw new RuntimeException("密码错误");
        }
        return user;

    }


}
