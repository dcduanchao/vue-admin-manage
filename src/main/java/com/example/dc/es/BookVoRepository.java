package com.example.dc.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:37 2021/1/29
 * @ Description：
 */

public interface BookVoRepository extends ElasticsearchRepository<BookVo,String> {

    List<BookVo> findByTitle(String title);
}