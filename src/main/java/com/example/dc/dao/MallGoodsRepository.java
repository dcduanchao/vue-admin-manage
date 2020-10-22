package com.example.dc.dao;

import com.example.dc.entity.home.MallGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface MallGoodsRepository extends JpaRepository<MallGoodsEntity,Integer>, JpaSpecificationExecutor<MallGoodsEntity> {

    long countByType(String type);

    @Query(value = "SELECT * FROM `mall_goods` WHERE type=?1 LIMIT ?2,?3",nativeQuery = true)
    List<MallGoodsEntity> findByPage(String type, Integer startPage, Integer pageSize);

    @Query(value = "SELECT * FROM `mall_goods` WHERE shop_id=?1 LIMIT 0,20",nativeQuery = true)
    List<MallGoodsEntity>  findByPageShop(Integer shopId);

    List<MallGoodsEntity>  findByIdIn(List<Integer> goodsIds);
}