package com.callray.reservation.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Hotel {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String address;
}
