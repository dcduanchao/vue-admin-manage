package com.example.dc.service.impl;
import com.example.dc.entity.cart.MallCartEntity;
import com.example.dc.vo.UserVo;
import java.sql.Timestamp;

import com.example.dc.dao.*;
import com.example.dc.enmus.DiscountTypeEnum;
import com.example.dc.enmus.ServerTypeEnum;
import com.example.dc.entity.goods.GoodsBannerEntity;
import com.example.dc.entity.goods.GoodsBaseEntity;
import com.example.dc.entity.goods.GoodsDetailImageEntity;
import com.example.dc.entity.home.MallGoodsEntity;
import com.example.dc.entity.home.MallShopEntity;
import com.example.dc.service.GoodsService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.ResponseUtils;
import com.example.dc.vo.GoodsBannerVo;
import com.example.dc.vo.KeyValueVo;
import com.example.dc.vo.detail.*;
import com.example.dc.vo.home.GoodsListVo;
import com.example.dc.vo.home.GoodsTypeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:17 2020/9/14
 * @ Description：
 */
@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GoodsBannerRepository goodsBannerRepository;
    @Autowired
    private GoodsBaseRepository goodsBaseRepository;

    @Autowired
    private MallShopRepository mallShopRepository;

    @Autowired
    private MallGoodsRepository mallGoodsRepository;
    @Autowired
    private GoodsDetailImageRepository goodsDetailImageRepository;

    @Autowired
    private  MallCartRepository mallCartRepository;


    @Override
    public ElAdminResultBeans findDetailBanner(Integer goodsId) {

        List<GoodsBannerEntity> byGoodsId = goodsBannerRepository.findByGoodsId(goodsId);
        GoodsBannerVo vo = new GoodsBannerVo();
        List<String> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(byGoodsId)) {
            list = byGoodsId.stream().map(GoodsBannerEntity::getImage).collect(Collectors.toList());
        }
        vo.setTopImage(list);

        return ResponseUtils.success(vo);
    }

    @Override
    public ElAdminResultBeans findDetailBase(Integer goodsId) {
        GoodsBaseEntity byGoodsId = goodsBaseRepository.findByGoodsId(goodsId);
        GodsBaseVo baseVo = new GodsBaseVo();
        BeanUtils.copyProperties(byGoodsId, baseVo);

        Integer discountType = Integer.valueOf(byGoodsId.getDiscountType());
        baseVo.setDiscountDesc(DiscountTypeEnum.getValueEnum(discountType).getDesc());

        String serverType = byGoodsId.getServerType();
        String[] split = StringUtils.split(serverType, ",");
        List<String> slist = new ArrayList<>();
        for (String s : split) {
            Integer i = Integer.valueOf(s);
            ServerTypeEnum valueEnum = ServerTypeEnum.getValueEnum(i);
            slist.add(valueEnum.getDesc());
        }
        baseVo.setServices(slist);


        return ResponseUtils.success(baseVo);
    }

    @Override
    public ElAdminResultBeans findDetailShop(Integer goodsId) {
        MallGoodsEntity goods = mallGoodsRepository.findById(goodsId).get();
        MallShopEntity shop = mallShopRepository.findById(goods.getShopId()).get();

        GoodsShopVo shopVo = new GoodsShopVo();
        shopVo.setId(shop.getId());
        shopVo.setName(shop.getStoreName());
        shopVo.setShopLogo(shop.getStoreIcon());
        shopVo.setCGoods(shop.getGoodsCount());
        shopVo.setCSells(shop.getSalesCount());
        List<GoodsShopEvaVo> goodsShopEvaVos = new ArrayList<>();
        GoodsShopEvaVo ds = new GoodsShopEvaVo();
        ds.setName("描述相符");
        ds.setScore(shop.getDescScore());
        ds.setBetter(shop.getDescBetter() == 1 ? true : false);
        goodsShopEvaVos.add(ds);

        GoodsShopEvaVo ps = new GoodsShopEvaVo();
        ps.setName("价格合理");
        ps.setScore(shop.getPriceScore());
        ps.setBetter(shop.getPriceBetter() == 1 ? true : false);
        goodsShopEvaVos.add(ps);

        GoodsShopEvaVo qs = new GoodsShopEvaVo();
        qs.setName("质量满意");
        qs.setScore(shop.getQualityScore());
        qs.setBetter(shop.getQualityBetter() == 1 ? true : false);
        goodsShopEvaVos.add(qs);
        shopVo.setScore(goodsShopEvaVos);
        return ResponseUtils.success(shopVo);
    }

    @Override
    public ElAdminResultBeans findDetailImage(Integer goodsId) {

        List<GoodsDetailImageEntity> byGoodsId = goodsDetailImageRepository.findByGoodsId(goodsId);
        DetailImageVo vo = new DetailImageVo();
        List<String> collect = byGoodsId.stream().map(GoodsDetailImageEntity::getImage).collect(Collectors.toList());
        vo.setGoodsId(goodsId);
        ImageVo imageVo = new ImageVo();
        imageVo.setKey("商品" + goodsId + "统一描述");
        imageVo.setList(collect);
        vo.setDetailImage(Arrays.asList(imageVo));
        return ResponseUtils.success(vo);
    }

    @Override
    public ElAdminResultBeans findDetailParams(Integer goodsId) {
        DetailParamsVo vo = new DetailParamsVo();
        List<List<String>> rule = new ArrayList<>();
        rule.add(Arrays.asList("尺码", "M", "L", "XL"));
        rule.add(Arrays.asList("肩宽", "10", "20", "30"));
        rule.add(Arrays.asList("衣长", "100", "100", "100"));
        rule.add(Arrays.asList("胸围", "50", "50", "50"));
        vo.setRule(rule);

        List<KeyValueVo> info = new ArrayList<>();
        info.addAll(Arrays.asList(new KeyValueVo("图案","字母/文字"),
                new KeyValueVo("厂名","XX厂有限公司"),
                new KeyValueVo("季节","春秋"),
                new KeyValueVo("颜色","彩虹七色"),
                new KeyValueVo("地址","犄角嘎达"),
                new KeyValueVo("材质","水做的"),
                new KeyValueVo("型号","理想型"),
                new KeyValueVo("厚薄","薄"),
                new KeyValueVo("风格","大傻型"),
                new KeyValueVo("潮流","时髦潮流")));
        vo.setInfo(info);


        return ResponseUtils.success(vo);
    }

    @Override
    public ElAdminResultBeans findDetailComment(Integer goodsId) {

        List<DetailCommentVo> list = new ArrayList<>();
        for (int i =0;i<2;i++){
            DetailCommentVo commentVo = new DetailCommentVo();
            UserVo userVo = new UserVo();
            userVo.setAvatar("https://s11.mogucdn.com/mlcdn/c45406/200716_2dd5aeg41i31cd12k62fj73ab47l7_400x400.jpg_48x48.webp");
            userVo.setUname("评论用户"+i+"--"+goodsId);

            commentVo.setUser(userVo);
            commentVo.setContent("衣服一眼就看中了，果断就入手了，到手时摸的质感面料摸着舒服，" +
                    "没有一点线头，质量非常好，版型很显瘦，去实体店里买的话就不止这个价格了。"+i+"--"+goodsId);
            commentVo.setCreated(new Timestamp(new java.util.Date().getTime()));

            commentVo.setImages(Arrays.asList("https://s11.mogucdn.com/mlcdn/c45406/200627_06e3l5138ijj5bfaba0kjicj6b3i3_800x800.jpg_100x100.jpg",
                    "https://s11.mogucdn.com/mlcdn/c45406/200627_0iafdfdikjjka024hcf031d935ha5_800x800.jpg_100x100.jpg"));

            list.add(commentVo);
        }



        return ResponseUtils.success(list);
    }

    @Override
    public ElAdminResultBeans findDetailRecommend(Integer goodsId) {
        MallGoodsEntity entity = mallGoodsRepository.findById(goodsId).get();
        List<MallGoodsEntity> byPageShop = mallGoodsRepository.findByPageShop(entity.getShopId());

        GoodsListVo vo = new GoodsListVo();
        List<GoodsTypeVo> list = new ArrayList<>();
        byPageShop.forEach(p->{
            GoodsTypeVo typeVo = new GoodsTypeVo();
            BeanUtils.copyProperties(p,typeVo);
            list.add(typeVo);
        });
        vo.setList(list);
        return  ResponseUtils.success(vo);
    }

    @Override
    public ElAdminResultBeans addGoodsCart(Integer goodsId) {
        MallCartEntity byGoodId = mallCartRepository.findByGoodId(goodsId);




        if(null==byGoodId){
            byGoodId = new MallCartEntity();
            byGoodId.setGoodId(goodsId);
            byGoodId.setGoodsNum(1);
        }else {
            byGoodId.setGoodsNum(byGoodId.getGoodsNum()+1);
        }
        mallCartRepository.save(byGoodId);
        return ResponseUtils.success();
    }

    @Override
    public ElAdminResultBeans cartNum() {
        long count = mallCartRepository.count();
        return ResponseUtils.success(count);
    }

    @Override
    public ElAdminResultBeans cartInfo() {
        List<MallCartEntity> all = mallCartRepository.findAll();

        List<Integer> goodsIds = all.stream().map(MallCartEntity::getGoodId).collect(Collectors.toList());
        List<MallGoodsEntity> byIdIn = mallGoodsRepository.findByIdIn(goodsIds);
        Map<Integer, MallGoodsEntity> goodsMap = byIdIn.stream()
                .collect(Collectors.toMap(MallGoodsEntity::getId, Function.identity()));

        List<GoodsTypeVo> list = new ArrayList<>();
        for (MallCartEntity me : all) {
            MallGoodsEntity entity = goodsMap.get(me.getGoodId());
            GoodsTypeVo vo = new GoodsTypeVo();
            BeanUtils.copyProperties(entity,vo);
            Integer num = me.getGoodsNum();
            vo.setCount(me.getGoodsNum());
            double price = num * entity.getPrice();
            vo.setPrice(price);
            list.add(vo);
        }
        return  ResponseUtils.success(list);
    }
}