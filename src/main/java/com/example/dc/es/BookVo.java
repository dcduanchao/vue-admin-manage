package com.example.dc.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;

/**
 * @ Author     ：duanchao
 * @ Date       ： 13:55 2021/1/28
 * @ Description：
 */
@Data
@Document(indexName = "jd-book")
public class BookVo {

    @Id
    private String id;

    private  String title;

//    @Field()
    private String price;

    private String img;

}