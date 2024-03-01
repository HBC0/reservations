package com.callray.reservation.service;


import com.callray.reservation.entity.Inventory;
import com.callray.reservation.mapper.InventoryDao;
import com.callray.reservation.mapper.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private RoomDao roomDao;

    //增加1好房间一个月的库存
    @Transactional
    public void addInventory(Integer roomId){
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusMonths(1);
        Inventory inventory = new Inventory();

        for (LocalDate date = today; date.isBefore(endDate); date = date.plusDays(1)) {
            inventory.setRoomId(roomId);
            inventory.setDate(date);
            inventory.setInventory(1);  // 每天 增加一个房间的库存
            inventory.setReserved(0);
            inventory.setHotelId(1);
            inventory.setCreateTime(LocalDateTime.now());
            inventory.setUpdateTime(LocalDateTime.now());

            // 检查是否已存在该日期的库存记录，如果存在则更新，否则新增
            Inventory existingInventory =
                    inventoryDao.findByRoomNumberAndDate(inventory.getRoomId(),inventory.getDate());
            if (existingInventory != null) {
                existingInventory.setInventory(existingInventory.getInventory() + 1);
                inventoryDao.addInventory(existingInventory);
            } else {
                inventoryDao.addInventory(inventory);
            }
        }
    }


}
