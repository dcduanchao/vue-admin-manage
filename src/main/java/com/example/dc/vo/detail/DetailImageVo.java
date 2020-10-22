package com.example.dc.vo.detail;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:08 2020/9/15
 * @ Description：
 */
@Data
public class DetailImageVo {

    private  Integer goodsId;

    private List<ImageVo> detailImage;
}