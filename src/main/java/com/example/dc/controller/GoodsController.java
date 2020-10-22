package com.example.dc.controller;

import com.example.dc.service.GoodsService;
import com.example.dc.utils.ElAdminResultBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:13 2020/9/14
 * @ Description：
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    @GetMapping("/detail/banner")
    public ElAdminResultBeans findDetailBanner(Integer goodsId){
        ElAdminResultBeans beans = goodsService.findDetailBanner(goodsId);
        return  beans;
    }

    @GetMapping("/detail/base")
    public ElAdminResultBeans findDetailBase(Integer goodsId){
        ElAdminResultBeans beans = goodsService.findDetailBase(goodsId);
        return  beans;
    }

    @GetMapping("/detail/shop")
    public ElAdminResultBeans findDetailShop(Integer goodsId){
        ElAdminResultBeans beans = goodsService.findDetailShop(goodsId);
        return  beans;
    }

    @GetMapping("/detail/image")
    public ElAdminResultBeans findDetailImage(Integer goodsId){
        ElAdminResultBeans beans = goodsService.findDetailImage(goodsId);
        return  beans;
    }

    @GetMapping("/detail/params")
    public ElAdminResultBeans findDetailParams(Integer goodsId){
        ElAdminResultBeans beans = goodsService.findDetailParams(goodsId);
        return  beans;
    }

    @GetMapping("/detail/comment")
    public ElAdminResultBeans findDetailComment(Integer goodsId){
        ElAdminResultBeans beans = goodsService.findDetailComment(goodsId);
        return  beans;
    }

    @GetMapping("/detail/recommend")
    public ElAdminResultBeans findDetailRecommend(Integer goodsId){
        ElAdminResultBeans beans = goodsService.findDetailRecommend(goodsId);
        return  beans;
    }


    @GetMapping("/cart/add")
    public ElAdminResultBeans addGoodsCart(Integer goodsId){
        ElAdminResultBeans beans = goodsService.addGoodsCart(goodsId);
        return  beans;
    }

    @GetMapping("/cart/num")
    public ElAdminResultBeans cartNum(){
        ElAdminResultBeans beans = goodsService.cartNum();
        return  beans;
    }

    @GetMapping("/cart/info")
    public ElAdminResultBeans cartInfo(){
        ElAdminResultBeans beans = goodsService.cartInfo();
        return  beans;
    }

}