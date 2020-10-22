package com.example.dc.vo.detail;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 11:24 2020/9/15
 * @ Description：
 */
@Data
public class GoodsShopVo {

    private int id;
    private String name;
    private String shopLogo;
    private Integer cGoods;
    private Integer cSells;

    private List<GoodsShopEvaVo> score;


}