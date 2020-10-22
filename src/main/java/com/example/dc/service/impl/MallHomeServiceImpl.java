package com.example.dc.service.impl;

import com.example.dc.dao.MallBannerRepository;
import com.example.dc.dao.MallGoodsRepository;
import com.example.dc.dao.MallRecommendRepository;
import com.example.dc.entity.home.MallBannerEntity;
import com.example.dc.entity.home.MallGoodsEntity;
import com.example.dc.entity.home.MallRecommendEntity;
import com.example.dc.service.MallHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:28 2020/9/8
 * @ Description：
 */
@Service
public class MallHomeServiceImpl implements MallHomeService {

    @Autowired
    MallBannerRepository mallBannerRepository;
    @Autowired
    MallRecommendRepository mallRecommendRepository;
    @Autowired
    MallGoodsRepository mallGoodsRepository;


    @Override
    public List<MallBannerEntity>  findBannerAll(){
        return  mallBannerRepository.findAll();
    }

    @Override
    public List<MallRecommendEntity> getRecommendList() {
        return  mallRecommendRepository.findAll();

    }

    @Override
    public long countType(String type) {

        long count = mallGoodsRepository.countByType(type);


        return count;
    }

    @Override
    public List<MallGoodsEntity> findGoodsPage(String type, Integer page, Integer pageSize) {
        Integer startPage = (page-1)*pageSize;
        return mallGoodsRepository.findByPage(type,startPage,pageSize);
    }


}