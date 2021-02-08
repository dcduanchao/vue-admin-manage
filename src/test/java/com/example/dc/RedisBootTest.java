package com.example.dc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 9:09 2021/2/4
 * @ Description：
 */
@SpringBootTest
@WebAppConfiguration
public class RedisBootTest {


   @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public  void  test(){

//        redisTemplate.opsForValue().set("name","学习redis");
//
//        Object name = redisTemplate.opsForValue().get("name");
//        System.out.println(name);

        StoreDto storeDto = new StoreDto();
        storeDto.setName("对象");
        storeDto.setIcon("1");
        redisTemplate.opsForValue().set("user",storeDto);
        Object user = redisTemplate.opsForValue().get("user");
        System.out.println(user);


//

    }


    @Test
    public void zcfz(){

        redisTemplate.opsForValue().set("k2","v1");
        List<RedisClientInfo> clientList = redisTemplate.getClientList();
        System.out.println(redisTemplate.opsForValue().get("k1"));
    }


}