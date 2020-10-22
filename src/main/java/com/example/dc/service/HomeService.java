package com.example.dc.service;

import com.example.dc.utils.ElAdminResultBeans;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:52 2020/9/8
 * @ Description：
 */

public interface HomeService {


    ElAdminResultBeans getHomeMultidata();

    ElAdminResultBeans getGoodsTypeList(String type, Integer page, Integer pageSize);

}
