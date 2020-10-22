package com.example.dc.controller;

import com.example.dc.service.HomeService;
import com.example.dc.utils.ElAdminResultBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:50 2020/9/8
 * @ Description：
 */
@RestController
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private HomeService homeService;

    @GetMapping("multidata")
    public ElAdminResultBeans getHomeMultidata(){

        ElAdminResultBeans beans =  homeService.getHomeMultidata();

        return  beans;

    }


    @GetMapping("goods/data")
    public  ElAdminResultBeans getGoodsType(String type, Integer page,
                                            @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize){
        ElAdminResultBeans beans =  homeService.getGoodsTypeList(type,page,pageSize);
        return  beans;
    }

}