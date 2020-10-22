package com.example.dc.vo.home;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:16 2020/9/10
 * @ Description：
 */
@Data
public class GoodsListVo {

    private  String type;

    private   Integer  totalCount;

    private List<GoodsTypeVo> list;

}