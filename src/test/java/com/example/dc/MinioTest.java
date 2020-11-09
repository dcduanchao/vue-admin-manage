package com.example.dc;

import com.alibaba.fastjson.JSON;
import com.example.dc.enmus.ContentTypeEnum;
import com.example.dc.enmus.MinioBucketTypeEnum;
import com.example.dc.utils.MinioClientUtils;
import io.minio.messages.Bucket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:33 2020/11/5
 * @ Description：
 */
@SpringBootTest
@WebAppConfiguration
public class MinioTest {

    @Autowired
    MinioClientUtils minioClientUtils;

    @Test
    public  void  listBuckets() throws Exception {
        List<Bucket> buckets = minioClientUtils.listBuckets();
        List<String> collect = buckets.stream().map(Bucket::name).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));

    }
    @Test
    public  void  test() throws Exception {
//        boolean buckets = minioClientUtils.makeBucket("public-bucket");
//        System.out.println(buckets);


        File file = new File("C:\\Users\\R480\\Desktop\\2.jpg");
        InputStream inputStream = new FileInputStream(file);
        minioClientUtils.putObject(MinioBucketTypeEnum.PUBLIC_BUCKET.getValue(),"image/211.jpg",
                ContentTypeEnum.IMAGE_JPEG.getValue(),inputStream);

    }

    @Test
    public void listObjects() throws Exception {
        minioClientUtils.listObjects(MinioBucketTypeEnum.PUBLIC_BUCKET.getDesc());
    }


    @Test
    public void removeObjects() throws Exception {
        minioClientUtils.removeObjects(MinioBucketTypeEnum.PUBLIC_BUCKET.getDesc(), Arrays.asList("11","22"));
    }

}