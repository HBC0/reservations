package com.callray.reservation.controller;

import com.callray.reservation.entity.User;
import com.callray.reservation.service.UserService;
import com.callray.reservation.utils.MailClient;
import com.callray.reservation.utils.Result;
import com.callray.reservation.utils.Tools;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Map;


@RestController
@RequestMapping("/v1")
public class UserController {

    private final MailClient mailClient;
    private final UserService userService;

    public UserController(MailClient mailClient, UserService userService) {
        this.mailClient = mailClient;
        this.userService = userService;
    }

    /**
     * @param email 收件人
     * @return 验证码信息
     */
    @GetMapping("/email")
    public Result<?> getCode(HttpServletRequest request,@RequestParam("email") String email){

        String code = Tools.generateRandom();

        try {
            mailClient.sendCode(email,code);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        HttpSession session = request.getSession();
        session.setAttribute(email, code);
        session.setMaxInactiveInterval(60);

        return Result.success("邮件发送成功");

    }

    //注册
    @PostMapping("/register")
    public Result<?> register(HttpServletRequest request,@RequestParam Map<String,Object> map) {

        User user = new User();
        user.setName((String) map.get("user_name"));
        user.setEmail((String) map.get("email"));
        user.setPassword(Tools.md5((String) map.get("password")));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        //检查账号是否已经注册
        String msg = userService.register(user);

        if (StringUtils.isBlank(msg)) {
            return Result.error("注册失败！");
        }

        //检查验证码
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute((String) map.get("email"));
        if (StringUtils.isBlank(code)){
            return Result.error("验证码过期，请重新获取");
        }
        String inputCode = (String) map.get("code");
        if (!code.equals(inputCode)){
            return Result.error("验证码错误");
        }

        return Result.success(msg);

    }


    //登录
    @GetMapping("/login")
    public Result<?> login (@RequestParam Map<String,Object> map){

        User user = new User();
        user.setEmail((String) map.get("email"));
        user.setPassword(Tools.md5((String) map.get("password")));
        try {
            User data= userService.signIn(user.getEmail(),user.getPassword());
            return Result.success(data);
        }catch (RuntimeException e){
            String msg= e.getMessage();
            return Result.error(msg);
        }

    }


}
