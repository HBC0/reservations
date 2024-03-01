package com.callray.reservation.mapper;

import com.callray.reservation.entity.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestMapper{

    // 批量新增用户
    int batchAdd(List<Test> list);


    @Insert("<script>" +
            "insert into test (name, age) values " +
            "<foreach collection='studentList' item='item' separator=','> " +
            "(#{item.name}, #{item.age}) " +
            "</foreach> " +
            "</script>")
    int insertSplice(@Param("studentList") List<Test> studentList);

}
