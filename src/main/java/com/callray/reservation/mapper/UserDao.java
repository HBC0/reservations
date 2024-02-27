package com.callray.reservation.mapper;

import com.callray.reservation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from users")
    List<User> selectUserAll();

    //insert

    //delete

    //update

}
