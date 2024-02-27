package com.callray.reservation.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Picture {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String picture;
    private Integer roomId;
}
