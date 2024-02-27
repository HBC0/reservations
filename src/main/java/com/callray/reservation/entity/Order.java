package com.callray.reservation.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String number;
    private String payment;             //支付方式
    private BigDecimal amount;          //金额
    private Integer status;             //订单状态
    private Integer roomId;
    private Integer userId;
    private LocalDateTime startDate;    //开始时间
    private LocalDateTime endDate;      //结束时间
    private String remark;              //备注
}
