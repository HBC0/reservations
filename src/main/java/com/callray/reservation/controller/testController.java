package com.callray.reservation.controller;


import com.callray.reservation.entity.Test;
import com.callray.reservation.entity.User;
import com.callray.reservation.mapper.RoomDao;
import com.callray.reservation.mapper.TestMapper;
import com.callray.reservation.mapper.UserDao;
import com.callray.reservation.utils.MailClient;
import org.apache.catalina.core.ApplicationContext;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TestMapper test;

    @GetMapping("v1")
    public String test1(){

        return userDao.selectUserAll().toString();

    }
    @GetMapping("v2")
    public String test2() {

//        mailClient.sendMail("1713037329@qq.com","test","nice too meet you!");

        User user = new User();

//        new LocalDateTime
        user.setName("jack");
        user.setPassword("132");
        user.setEmail("123");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

//        System.out.println(user);

        try{
            mailClient.sendMail("1713037329@qq.com","test","nice too meet you!");
            System.out.println("邮件发送成功！");
//            Boolean b = userDao.addUser(user);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

//        System.out.println(userDao.queryPasswordForEmail("123"));



        return "success";

    }

    @GetMapping("v3")
    public void test3(@RequestParam Map<String,Object> map){

        System.out.println(map);
        System.out.println(map.toString());
        System.out.println(map.get("age"));
        System.out.println(map.get("name"));
//        return userDao.selectUserAll().toString();

    }
 @GetMapping("v4")
    public void test4(@RequestBody Map<String,Object> map){

        System.out.println(map);
        System.out.println(map.toString());
        System.out.println(map.get("age"));
        System.out.println(map.get("name"));
//        return userDao.selectUserAll().toString();

    }

    @GetMapping("v5")
    public void test5(){

        ArrayList<Test> arrayList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++){
            Test student = new Test("Jerry","12");
            arrayList.add(student);
        }
        System.out.println(arrayList.size());
        test.batchAdd(arrayList);
        long endTime = System.currentTimeMillis();
        System.out.println("插入数据消耗时间：" + (endTime - startTime));
    }

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @GetMapping("/v6")
    public void batchInsert2() {
        ArrayList<Test> arrayList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        // 模拟数据
        for (int i = 0; i < 50000; i++){
            Test student = new Test("Jack" + i,"24");
            arrayList.add(student);
        }
//        System.out.println(arrayList);
//        List<Test> subList = arrayList.subList(2, 4);
//        System.out.println(subList);
//        test.insertSplice(subList);
        int count = arrayList.size();
        int pageSize = 2000; // 每批次插入的数据量
        int threadNum = count / pageSize + 1; // 线程数
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum-1; i++) {
            int startIndex = i * pageSize;
            int endIndex = Math.min(count, (i + 1) * pageSize);
//            System.out.println(startIndex+"33333"+endIndex);
            List<Test> subList = arrayList.subList(startIndex, endIndex);
            threadPoolTaskExecutor.execute(() -> {
                DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
                TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
                try {
                    test.insertSplice(subList);
                    transactionManager.commit(status);
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    throw e;
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        long endTime = System.currentTimeMillis();
        System.out.println("插入数据消耗时间：" + (endTime - startTime));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private RoomDao roomDao;

    @GetMapping("v7")
    public List<Map<String, Object>> test7(){

        return roomDao.findAll();

    }

    @GetMapping("v8")
    public List<Map<String, Object>> test8(){

        return roomDao.findAllNot();

    }

}
