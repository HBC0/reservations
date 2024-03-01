package com.callray.reservation.mapper;


import com.callray.reservation.entity.Inventory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface InventoryDao {

    //添加一条库存记录
    @Insert("insert into room_inventory(room_id,hotel_id,room_inventory,room_reserved,date,create_time,update_time)" +
            "values (#{roomId},#{hotelId},#{inventory},#{reserved},#{date},#{createTime},#{updateTime})")
    void addInventory(Inventory inventory);

    @Select("select * from room_inventory " +
            "where room_inventory.room_id = #{roomId} and room_inventory.date = #{date}")
    Inventory findByRoomNumberAndDate(@Param("roomId") Integer roomId,@Param("date") LocalDate date);

    /**
     * @param roomId     房间id
     * @param hotelId    酒店id
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @return           某时间区间内查询到的库存信息
     */
    @Select("select * from room_inventory where room_id = #{roomId} " +
            "and hotel_id = #{hotelId} and date between #{startDate} and #{endDate}")
    Inventory findByBetweenDate(Integer roomId,Integer hotelId,LocalDate startDate, LocalDate endDate);



}
