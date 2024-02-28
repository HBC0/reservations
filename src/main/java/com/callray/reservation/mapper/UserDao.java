package com.callray.reservation.mapper;

import com.callray.reservation.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from users")
    List<User> selectUserAll();

    /**
     * 注册
     * @param user 用户信息
     */
    @Insert("insert into users(name, password,email,createTime,updateTime)" +
            " values (#{name},#{password},#{email},#{createTime},#{updateTime})")
    void addUser(User user);


    /**
     * 更改邮箱，密码
     * @param user pass
     * @return 修改成功返回true否则为false
     */
    @Update("update users set password = #{password}, email = #{email} where id = #{id}")
    Boolean updateUser(User user);

}
