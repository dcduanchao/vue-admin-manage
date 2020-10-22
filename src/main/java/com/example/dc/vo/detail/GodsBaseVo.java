package com.example.dc.vo.detail;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 9:43 2020/9/15
 * @ Description：
 */

@Data
public class GodsBaseVo {


    private Integer goodsId;
    private String title;
    private Double price;
    private Double oldPrice;
    private Integer salesCount;
    private Integer collCount;
    private String discountDesc;
    private List<String> services;
}