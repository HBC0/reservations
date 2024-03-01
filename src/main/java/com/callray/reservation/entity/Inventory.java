package com.callray.reservation.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Inventory {

    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer inventory;
    private Integer reserved;
    private LocalDate date;
    private Integer roomId;
    private Integer hotelId;

}
