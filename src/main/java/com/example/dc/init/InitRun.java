package com.example.dc.init;

import com.example.dc.dao.GoodsBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:37 2021/1/13
 * @ Description：
 */
@Component
public class InitRun implements CommandLineRunner {

    @Autowired
    GoodsBaseRepository goodsBaseRepository;
    @Override
    public void run(String... args) throws Exception {

        System.out.println("********启动加载*****");
        long count = goodsBaseRepository.count();

        System.out.println("goodsBaseRepository 查询条数"+count);
    }
}