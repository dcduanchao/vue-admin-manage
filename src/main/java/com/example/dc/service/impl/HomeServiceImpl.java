package com.example.dc.service.impl;
import com.example.dc.entity.home.MallGoodsEntity;
import com.example.dc.utils.ResponseUtils;
import com.example.dc.vo.CommonVo;
import java.util.ArrayList;
import com.example.dc.vo.CommonListVo;

import com.example.dc.entity.home.MallBannerEntity;
import com.example.dc.entity.home.MallRecommendEntity;
import com.example.dc.service.HomeService;
import com.example.dc.service.MallHomeService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.vo.home.GoodsListVo;
import com.example.dc.vo.home.GoodsTypeVo;
import com.example.dc.vo.home.HomeBannerAndRecomendVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:52 2020/9/8
 * @ Description：
 */

@Service
public class HomeServiceImpl  implements HomeService {

    @Autowired
    MallHomeService mallHomeService;

    @Override
    public ElAdminResultBeans getHomeMultidata() {
        List<MallBannerEntity> banner = mallHomeService.findBannerAll();
        List<MallRecommendEntity> recommend = mallHomeService.getRecommendList();

        HomeBannerAndRecomendVo vo = new HomeBannerAndRecomendVo();
        CommonListVo bannerVo = new CommonListVo();
        banner.forEach(p->{

        });
        List<CommonVo> bannerList = new ArrayList<>();
        banner.forEach(p->{
            CommonVo commonVo = new CommonVo();
            BeanUtils.copyProperties(p,commonVo);
            bannerList.add(commonVo);
        });
        bannerVo.setList(bannerList);

        CommonListVo recVo = new CommonListVo();
        List<CommonVo> recommendList = new ArrayList<>();
        recommend.forEach(p->{
            CommonVo commonVo = new CommonVo();
            BeanUtils.copyProperties(p,commonVo);
            recommendList.add(commonVo);
        });
        recVo.setList(recommendList);


        vo.setBanner(bannerVo);
        vo.setRecommend(recVo);

        return ResponseUtils.success(vo);
    }

    @Override
    public ElAdminResultBeans getGoodsTypeList(String type, Integer page, Integer pageSize) {

        long count = mallHomeService.countType(type);

        GoodsListVo vo = new GoodsListVo();
        List<GoodsTypeVo> list = new ArrayList<>();
        vo.setType(type);
        if(count<=0){
            vo.setTotalCount(0);
            vo.setList(list);
            return  ResponseUtils.success(vo);

        }

        List<MallGoodsEntity> goodsPage = mallHomeService.findGoodsPage(type, page, pageSize);
        goodsPage.forEach(p->{
            GoodsTypeVo typeVo = new GoodsTypeVo();
            BeanUtils.copyProperties(p,typeVo);
            list.add(typeVo);
        });

        vo.setTotalCount((int)count);
        vo.setList(list);
        return  ResponseUtils.success(vo);
    }
}