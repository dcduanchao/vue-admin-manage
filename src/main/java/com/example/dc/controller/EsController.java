package com.example.dc.controller;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ Author     ：duanchao
 * @ Date       ： 10:26 2021/1/27
 * @ Description：
 */
@RestController
@RequestMapping("es")
public class EsController {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    @GetMapping
    public  String test(){
        return  "";

    }
}