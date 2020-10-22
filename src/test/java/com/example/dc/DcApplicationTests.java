package com.example.dc;

import com.example.dc.dao.*;
import com.example.dc.entity.goods.GoodsBannerEntity;
import com.example.dc.entity.goods.GoodsBaseEntity;
import com.example.dc.entity.goods.GoodsDetailImageEntity;
import com.example.dc.entity.home.MallGoodsEntity;
import com.example.dc.entity.home.MallShopEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
@WebAppConfiguration
class DcApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    MallGoodsRepository mallGoodsRepository;
    @Autowired
    GoodsBannerRepository goodsBannerRepository;
    @Autowired
    GoodsBaseRepository goodsBaseRepository;
    @Autowired
    MallShopRepository mallShopRepository;
    @Autowired
    GoodsDetailImageRepository goodsDetailImageRepository;

//    @BeforeEach
//    public  void  setupMockMvc(){
//        MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .build();
//
//    }


    List<String> imageList = Arrays.asList("https://s10.mogucdn.com/mlcdn/c45406/200908_38h6e3fjkh9bld23288f31gi6jhfh_710x750.jpg",
            "https://s10.mogucdn.com/mlcdn/c45406/190520_1bag70e8cjfdj807k4kbcgg095lia_220x320.gif",
            "https://s5.mogucdn.com/mlcdn/c45406/200622_1fegbfhgcejl7ahbd711k2529k3b5_3888x5832.jpg_440x587.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200526_85jldi3l0jd3ba2agbija5a21a0c8_3999x5999.jpg_440x587.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/55cf19/200707_7bf42ieg01ci3l763085384kk7d1d_640x962.png_440x587.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200512_8gg4lbddgehjk86ik487d674hfk04_4800x7200.jpg_440x587.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200329_2aiibaa0dkfkegk4kl6ibdf9hcbdi_640x960.jpg_440x587.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/190519_4ba90jgcief63d0a6i8ee0fhdd06a_640x960.jpg_440x587.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200827_713ga3e52ij86k87dldflkg92ba4f_3500x4667.jpg_640x960.v1cAC.70.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200827_23j262042g387kh42g224b94a2h4g_3500x4667.jpg_640x960.v1cAC.70.webp",
            "https://s11.mogucdn.com/mlcdn/c45406/200728_54kdghj800gk9l9g36a6h19ddfb78_3800x5700.jpg_750x1000.v1cAC.81.jpg",
            "https://s11.mogucdn.com/mlcdn/c45406/200728_429e32882kki3h9gh45j1388k0dk9_3800x5700.jpg_750x1000.v1cAC.81.jpg",
            "https://s5.mogucdn.com/mlcdn/c45406/200822_6869igf4h24b8cde133b52ic399jl_660x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200818_040197303agl3a1a8kch01fh610ll_1125x1500.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200731_181575eh5a978kg7ljl9f4j5ifg6b_640x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200624_2k9jalbdlchicbh7ai96k2c6454e7_640x959.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200624_4hbiial5ebbfadi7570066j74ccc5_5000x7500.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200903_5fj25akh7cfll7f3kck17bj59g9jh_3999x5999.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/55cf19/200815_208gig4cg34dd1kb82f5356acfge8_640x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/180505_841jlj543c9022l96lf15he0jjf2f_640x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200822_34lfkkgc6e2h6gg4a3gicifglgg2d_700x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200830_1cj3i532383aajaijbbd4h7l02ecl_4999x7499.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/55cf19/200906_141a4f4i7hba2eglg72ki840ehc3g_640x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200808_6jakbkak41kc890hc8h916bg9aa20_640x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200802_01ddd33067bcigj8118d16d4b0be6_640x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/181005_7i295330af5442l3i8f1a0h17hdi4_640x960.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/190826_0k0kdi5e2adj4jgijh0ef2892bj74_640x960.png_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/191228_8h4h35dhfh4h7c9ijk888hh5g70fe_5999x8998.jpg_640x854.v1cAC.40.webp",
            "https://s5.mogucdn.com/mlcdn/c45406/200820_28g9c0g74eihi0j6hcc0b20f3899j_640x960.jpg_640x854.v1cAC.40.webp");


    @Test
    void contextLoads() {

        List<MallGoodsEntity> entityList = new ArrayList<>();
        String type = "sell";
        String title = "精选";
        for (int i = 0; i < 100; i++) {

            MallGoodsEntity entity = new MallGoodsEntity();
            entity.setType(type);
            entity.setTitle(title + i);
            Random random = new Random();
            int r = random.nextInt(imageList.size());
            entity.setImage(imageList.get(r));
            double v = random.nextDouble() * 200;
            entity.setPrice(v);
            int f = random.nextInt(100);
            entity.setCfav(f);
            entity.setLink("https://act.mogujie.com/huanxin0001?acm=3.mce.2_10_1jhwa.43542.0.ccy5br4OlfK0Q.pos_0-m_454801-sd_119");
            entityList.add(entity);

//            mallGoodsRepository.save(entity);

        }

//        mallGoodsRepository.saveAll(entityList);


    }

    @Test
    void updatagoods() {
        List<MallGoodsEntity> all = mallGoodsRepository.findAll();
        for (MallGoodsEntity entity : all) {
            Random random = new Random();
            int r = random.nextInt(imageList.size());
            entity.setImage(imageList.get(r));

        }
        mallGoodsRepository.saveAll(all);
    }

    @Test
    public void goodsBanner() {
        List<MallGoodsEntity> all = mallGoodsRepository.findAll();
        List<Integer> goodsIds = all.stream().map(MallGoodsEntity::getId).collect(Collectors.toList());

        goodsIds.forEach(p -> {
            List<GoodsBannerEntity> list = new ArrayList<>();
            Random random = new Random();
            int r = random.nextInt(5) + 1;
            for (int i = 0; i < r; i++) {
                GoodsBannerEntity bannerEntity = new GoodsBannerEntity();
                bannerEntity.setGoodsId(p);
                bannerEntity.setTitle("商品详情" + p);
                int l = random.nextInt(imageList.size());
                bannerEntity.setImage(imageList.get(l));
                list.add(bannerEntity);
            }
//            goodsBannerRepository.saveAll(list);
        });
    }


    @Test
    public void goodsBase() {
        List<MallGoodsEntity> all = mallGoodsRepository.findAll();
        List<Integer> goodsIds = all.stream().map(MallGoodsEntity::getId).collect(Collectors.toList());
        List<GoodsBaseEntity> list = new ArrayList<>();
        List<String> servr = Arrays.asList("1,2,3,4", "2,3,4,5", "1,3,4,5", "1,2,4,5", "1,2,3,5");
        goodsIds.forEach(p -> {
            GoodsBaseEntity entity = new GoodsBaseEntity();
            entity.setGoodsId(p);
            entity.setTitle("商品详情" + p + "标题");
            Random random = new Random();
            double v = random.nextDouble() * 200;
            entity.setPrice(v);
            entity.setOldPrice(v - 25);
            int r = random.nextInt(3) + 1;
            entity.setDiscountType(String.valueOf(r));
            int s = random.nextInt(5000) + 500;
            entity.setSalesCount(s);
            entity.setCollCount(s - 250);
            int t = random.nextInt(5);
            entity.setServerType(servr.get(t));
            list.add(entity);

        });
        goodsBaseRepository.saveAll(list);
    }


    @Test
    public void addShopId() {
        List<MallGoodsEntity> all = mallGoodsRepository.findAll();
        Random random = new Random();
        all.forEach(p -> {
            int r = random.nextInt(3) + 1;
            p.setShopId(r);
        });
        mallGoodsRepository.saveAll(all);
    }




    static  List<StoreDto> storeList = new ArrayList<>();
    static {

        StoreDto s1 = new StoreDto();
        s1.setName("末末女孩");
        s1.setIcon("https://s5.mogucdn.com/mlcdn/c45406/180310_65i851h5b4bclbgik208j6f5a89ak_200x200.jpg_100x100.jpg");

        StoreDto s2 = new StoreDto();
        s2.setName("阁阁靓衣坊");
        s2.setIcon("https://s5.mogucdn.com/mlcdn/c45406/180820_5f79lba676c631bfl9k01883djj55_200x200.jpg_100x100.jpg");

        StoreDto s3 = new StoreDto();
        s3.setName("俞兆林小灰灰");
        s3.setIcon("https://s5.mogucdn.com/mlcdn/c45406/200721_7ckccldkblgab4c07hea5e0k8g2k1_200x200.jpg_100x100.jpg");

        storeList.add(s1);
        storeList.add(s2);
        storeList.add(s3);


    }

    @Test
    public void shopInit() {
        List<MallShopEntity> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 1; i <= 3; i++) {
            MallShopEntity entity = new MallShopEntity();
            StoreDto storeDto = storeList.get(i - 1);
            entity.setStoreName(storeDto.getName());
            entity.setStoreIcon(storeDto.getIcon());

            int s = random.nextInt(50000) + 500;
            entity.setSalesCount(s);
            int g = random.nextInt(200) + 10;
            entity.setGoodsCount(g);
            double ds = random.nextDouble() * 5;
            entity.setDescScore(ds);
            if (ds > 2.5) {
                entity.setDescBetter(1);
            } else {
                entity.setDescBetter(0);
            }

            double ps = random.nextDouble() * 5;
            entity.setPriceScore(ps);
            if (ps > 2.5) {
                entity.setPriceBetter(1);
            } else {

                entity.setPriceBetter(0);
            }

            double qs = random.nextDouble() * 5;
            entity.setQualityScore(qs);
            if (qs> 2.5) {
                entity.setQualityBetter(1);
            } else {
                entity.setQualityBetter(0);
            }

            list.add(entity);

        }

        mallShopRepository.saveAll(list);
    }

    @Test
    public  void addShopImge(){
        List<MallGoodsEntity> all = mallGoodsRepository.findAll();
        List<Integer> goodsIds = all.stream().map(MallGoodsEntity::getId).collect(Collectors.toList());
        Random random = new Random();
        List<GoodsDetailImageEntity> entityListAll = new ArrayList<>();
        goodsIds.forEach(p->{
            List<Integer> ran = new ArrayList<>();
            while (true) {
                int s = random.nextInt(imageList.size());
                if (!ran.contains(s)) {
                    ran.add(s);
                }
                if(ran.size()>10){
                    break;
                }
            }
            List<GoodsDetailImageEntity> entityList = new ArrayList<>();
            for (Integer i : ran) {
                GoodsDetailImageEntity entity = new GoodsDetailImageEntity();
                entity.setGoodsId(p);
                entity.setImage(imageList.get(i));
                entityList.add(entity);
            }
            entityListAll.addAll(entityList);

        });

        goodsDetailImageRepository.saveAll(entityListAll);
    }

}
