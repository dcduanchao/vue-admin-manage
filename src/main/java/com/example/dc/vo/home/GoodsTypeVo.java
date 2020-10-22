package com.example.dc.vo.home;

import lombok.Data;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:15 2020/9/10
 * @ Description：
 */
@Data
public class GoodsTypeVo {
    private int id;
    private String type;
    private String title;
    private String image;
    private Double price;
    private Integer cfav;
    private String link;
    private Integer count;
}