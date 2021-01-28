package com.example.dc.es;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:40 2021/1/28
 * @ Description：
 */
@Controller
@RequestMapping("es")
public class EsController {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private  HtmlPageUtil htmlPageUtil;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/init")
    public void bookVoList (String keyWord) throws IOException {
        List<BookVo> jiexi = htmlPageUtil.jiexi(keyWord);

        BulkRequest bulkRequest = new BulkRequest();
        for (BookVo bookVo : jiexi) {
            IndexRequest request = new IndexRequest("jd-book");
            request.source(JSONObject.toJSONString(bookVo), XContentType.JSON);
            bulkRequest.add(request);
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(!bulk.hasFailures());
    }
}