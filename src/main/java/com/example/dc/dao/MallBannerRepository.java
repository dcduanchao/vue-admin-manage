package com.example.dc.dao;

import com.example.dc.entity.home.MallBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface MallBannerRepository extends JpaRepository<MallBannerEntity,Integer>, JpaSpecificationExecutor<MallBannerEntity> {
}