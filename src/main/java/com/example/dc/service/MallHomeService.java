package com.example.dc.service;

import com.example.dc.entity.home.MallBannerEntity;
import com.example.dc.entity.home.MallGoodsEntity;
import com.example.dc.entity.home.MallRecommendEntity;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:27 2020/9/8
 * @ Description：
 */
public interface MallHomeService {

    List<MallBannerEntity> findBannerAll();

    List<MallRecommendEntity> getRecommendList();

    long countType(String type);

    List<MallGoodsEntity> findGoodsPage(String type, Integer page, Integer pageSize);

}
