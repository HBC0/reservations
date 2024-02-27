package com.callray.reservation.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Room {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer number;
    private BigDecimal price;
    private String address;
    private String details;
    private Integer status;
    private String reviews;
}
