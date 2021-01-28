package com.example.dc;
import com.example.dc.StoreDto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:53 2021/1/27
 * @ Description：
 */
@SpringBootTest
public class EsTest {

    @Autowired
    @Qualifier("restHighLevelClient")
    RestHighLevelClient restHighLevelClient;


    @Test
    public  void  jiexi() throws IOException {
        String url = "https://search.jd.com/Search?keyword=java";

        Document parse = Jsoup.parse(new URL(url),30000);
        Element j_goodsList = parse.getElementById("J_goodsList");
//        System.out.println(j_goodsList.html());
        Elements li = j_goodsList.getElementsByTag("li");

        for (Element el : li) {
            String image = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();


            System.out.println("==========================");
            System.out.println(image);
            System.out.println(price);
            System.out.println(title);
        }


    }

    @Test
    public  void  JsonTest(){
        testJson testJson = new testJson();
        testJson.setName("123");
        testJson.setStoreDto(new StoreDto("qq","dd"));
        System.out.println(JSONObject.toJSONString(testJson));

    }


    @Test
    public void  test() throws IOException {
        IndexRequest request = new IndexRequest("esboot1");
//        request.id("1");
        StoreDto storeDto = new StoreDto();
        storeDto.setName("dc");
        storeDto.setIcon("111");
        String json = JSONObject.toJSONString(storeDto);
        System.out.println(json);

//        request.source(json, XContentType.JSON);
//        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
//        System.out.println(JSONObject.toJSONString(index));


    }
    @Test
    public void  test1() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest();
        createIndexRequest.index("esboot");
//        createIndexRequest.mapping()
        restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);

    }

    @Test
    public void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("esboot");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("esboot");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(JSONObject.toJSONString(delete));
    }

    @Test
    public void getIndexData() throws IOException {
        GetRequest getRequest = new GetRequest("esboot1","NPGLQncB8YQFXPeZ9kN7");
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(documentFields));
    }

    @Test
    public void updateData() throws IOException {


        UpdateRequest updateRequest = new UpdateRequest("esboot1","NPGLQncB8YQFXPeZ9kN7");
        StoreDto storeDto = new StoreDto();
        storeDto.setName("段超2");
        storeDto.setIcon("");

        updateRequest.doc(JSON.toJSONString(storeDto),XContentType.JSON);

        UpdateResponse  update = restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(update));


    }

    //批量
    @Test
    public  void batchData() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<StoreDto> list = new ArrayList<>();
        for (int i = 10; i <20 ; i++) {
            StoreDto storeDto = new StoreDto();
            storeDto.setName("批量"+i);
            storeDto.setIcon("qqwww"+i);
            list.add(storeDto);
        }
        for (StoreDto storeDto : list) {
            IndexRequest request = new IndexRequest("esboot1");
            request.source(JSON.toJSONString(storeDto),XContentType.JSON);
            bulkRequest.add(request);
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//        BulkItemResponse[] items = bulk.getItems();
//        for (BulkItemResponse item : items) {
//            item.getId();
//
//        }
        System.out.println(JSONObject.toJSONString(bulk));


    }


    //查询

    @Test
    public  void  searchData() throws IOException {
//        SearchRequest searchRequest = new SearchRequest("esboot1");
        //或
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("esboot1");
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();


        //匹配所有
//        MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();

        //单字段查询
//        MatchQueryBuilder builder = QueryBuilders.matchQuery("name", "批量");
//        builder.fuzziness()

        //精准匹配 不带有中文分词器（标准分词器）  如：批量查询不到  批  或  量 单个可以查询到
//         TermQueryBuilder builder = QueryBuilders.termQuery("name", "批量");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = boolQueryBuilder.must();
        must.add(QueryBuilders.matchQuery("name", "批量"));
        must.add(QueryBuilders.matchQuery("icon","12q"));
        List<QueryBuilder> filter = boolQueryBuilder.filter();
        RangeQueryBuilder age = QueryBuilders.rangeQuery("age");
        age.gte("1");
        filter.add(age);

        searchSourceBuilder.query(boolQueryBuilder);


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("name");
//        field.highlighterType()

        highlightBuilder.field(field);
        highlightBuilder.preTags();
        highlightBuilder.postTags();
        searchSourceBuilder.highlighter(highlightBuilder);
        //默认10条
//      searchSourceBuilder.size(20);
//        排序  有些问题 text 无法排序稍后处理
//        searchSourceBuilder.sort("id", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(JSONObject.toJSONString(search));



    }




}