package com.example.dc.dao;

import com.example.dc.entity.goods.GoodsBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface GoodsBannerRepository extends JpaRepository<GoodsBannerEntity,Integer>, JpaSpecificationExecutor<GoodsBannerEntity> {


    List<GoodsBannerEntity> findByGoodsId(Integer goodsId);
}