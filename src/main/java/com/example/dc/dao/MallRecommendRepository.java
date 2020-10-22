package com.example.dc.dao;

import com.example.dc.entity.home.MallRecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface MallRecommendRepository extends JpaRepository<MallRecommendEntity,Integer>, JpaSpecificationExecutor<MallRecommendEntity> {
}