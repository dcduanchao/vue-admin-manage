package com.example.dc.vo.home;

import com.example.dc.vo.CommonListVo;
import lombok.Data;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:46 2020/9/8
 * @ Description：
 */
@Data
public class HomeBannerAndRecomendVo {

    private CommonListVo banner;

    private  CommonListVo recommend;
}