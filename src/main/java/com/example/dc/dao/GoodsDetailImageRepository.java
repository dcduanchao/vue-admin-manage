package com.example.dc.dao;

import com.example.dc.entity.goods.GoodsDetailImageEntity;
import com.example.dc.entity.home.MallBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface GoodsDetailImageRepository extends JpaRepository<GoodsDetailImageEntity,Integer>, JpaSpecificationExecutor<GoodsDetailImageEntity> {
    List<GoodsDetailImageEntity> findByGoodsId(Integer goodsId);
}