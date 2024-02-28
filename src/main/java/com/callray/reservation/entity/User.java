package com.callray.reservation.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
