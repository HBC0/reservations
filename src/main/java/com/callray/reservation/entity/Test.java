package com.callray.reservation.entity;

import lombok.Data;

@Data
public class Test {
    private Integer id;
    private String name;
    private String age;

    public Test(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
