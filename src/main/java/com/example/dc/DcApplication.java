package com.example.dc;

import com.example.dc.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DcApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder(){
        return  new SpringContextHolder();
    }

}
