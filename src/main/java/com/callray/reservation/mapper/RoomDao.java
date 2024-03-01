package com.callray.reservation.mapper;

import com.callray.reservation.entity.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomDao {


    //查询有库存的房间
    @Select("SELECT DISTINCT room.*,room_inventory.date FROM room " +
            "JOIN room_inventory ON room.id = room_inventory.room_id " +
            "WHERE room_inventory.room_inventory=1")
    List<Map<String,Object>> findAll();

    //查询有库存的房间
    @Select("SELECT DISTINCT room.*,room_inventory.date FROM room " +
            "JOIN room_inventory ON room.id = room_inventory.room_id " +
            "WHERE room_inventory.room_reserved=1")
    List<Map<String,Object>> findAllNot();

    //添加房间
    @Insert("INSERT INTO room(room_number,create_time,update_time,price,details,reviews,hotel_id)" +
            "values (#{roomNumver},#{createTime},#{updateTime},#{price},#{details},#{reviews},#{hotelId})")
    void addRoom(Room room);

    //删除房间

    //修改room信息

    //查看room信息


}
