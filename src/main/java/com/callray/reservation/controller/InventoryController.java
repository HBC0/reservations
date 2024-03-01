package com.callray.reservation.controller;

import com.callray.reservation.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    //添加库存
    @PostMapping("/add")
    public void add(){
        inventoryService.addInventory(1);
    }

    //修改库存

    //查看库存

    //删除库存

}
