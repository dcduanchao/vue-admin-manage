package com.example.dc.service;

import com.example.dc.utils.ElAdminResultBeans;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:16 2020/9/14
 * @ Description：
 */
public interface GoodsService {
    ElAdminResultBeans findDetailBanner(Integer id);

    ElAdminResultBeans findDetailBase(Integer goodsId);

    ElAdminResultBeans findDetailShop(Integer goodsId);

    ElAdminResultBeans findDetailImage(Integer goodsId);

    ElAdminResultBeans findDetailParams(Integer goodsId);

    ElAdminResultBeans findDetailComment(Integer goodsId);

    ElAdminResultBeans findDetailRecommend(Integer goodsId);

    ElAdminResultBeans addGoodsCart(Integer goodsId);

    ElAdminResultBeans cartNum();

    ElAdminResultBeans cartInfo();

}
