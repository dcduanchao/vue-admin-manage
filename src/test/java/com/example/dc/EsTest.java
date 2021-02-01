package com.example.dc;
import com.example.dc.StoreDto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dc.bean.PageInfoBean;
import com.example.dc.es.BookVo;
import com.example.dc.es.BookVoRepository;
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
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        IndexRequest request = new IndexRequest("jb-book1");
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
        createIndexRequest.index("jb-book");
//        createIndexRequest.mapping()
        restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);

    }

    @Test
    public void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("jb-book");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("jb-book");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(JSONObject.toJSONString(delete));
    }

    @Test
    public void getIndexData() throws IOException {
        GetRequest getRequest = new GetRequest("jb-book1","NPGLQncB8YQFXPeZ9kN7");
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(documentFields));
    }

    @Test
    public void updateData() throws IOException {


        UpdateRequest updateRequest = new UpdateRequest("jb-book1","NPGLQncB8YQFXPeZ9kN7");
        StoreDto storeDto = new StoreDto();
        storeDto.setName("112");
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
            IndexRequest request = new IndexRequest("jb-book1");
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
//        SearchRequest searchRequest = new SearchRequest("jb-book1");
        //或
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("jd-book");
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();


        //匹配所有
//        MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();

        //单字段查询
        MatchQueryBuilder builder = QueryBuilders.matchQuery("title", "Java");
//        builder.fuzziness()

        //精准匹配 不带有中文分词器（标准分词器）  如：批量查询不到  批  或  量 单个可以查询到
//         TermQueryBuilder builder = QueryBuilders.termQuery("name", "批量");

//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        List<QueryBuilder> must = boolQueryBuilder.must();
//        must.add(QueryBuilders.matchQuery("name", "批量"));
//        must.add(QueryBuilders.matchQuery("icon","12q"));
//        List<QueryBuilder> filter = boolQueryBuilder.filter();
//        RangeQueryBuilder age = QueryBuilders.rangeQuery("age");
//        age.gte("1");
//        filter.add(age);

        searchSourceBuilder.query(builder);


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("title");
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

    @Autowired
    BookVoRepository bookVoRepository;


    @Test
    public void repositoryTest(){
        List<BookVo> java = bookVoRepository.findByTitle("java");

        System.out.println(JSONObject.toJSONString(java));

    }

    @Test
    public void repositorySave(){

//        BookVo bookVo = new BookVo();
//        bookVo.setTitle("bookVoRepository 保存测试");
//        bookVo.setPrice("100");
//        bookVo.setImg("测试");
//        //保存
//        BookVo save = bookVoRepository.save(bookVo);
//
//        System.out.println(JSONObject.toJSONString(save));
//        //id查询
//        BookVo bookVo1 = bookVoRepository.findById(save.getId()).get();
//        System.out.println(JSONObject.toJSONString(bookVo1));

        Sort sort = Sort.by(Sort.Direction.DESC,"price.keyword");

        PageRequest pageRequest = PageRequest.of(0,20,sort);
        Page<BookVo> all = bookVoRepository.findAll(pageRequest);
        List<BookVo> content = all.getContent();
        System.out.println("总条数："+all.getTotalElements());
        System.out.println(JSONObject.toJSONString(content));

//        bookVoRepository.search()
    }


    @Test
    public  void  EsSearch() throws IOException {


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("jd-book");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

         //查询所有
        //MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();

        //MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "java");
        TermQueryBuilder builder = QueryBuilders.termQuery("title", "java");
        sourceBuilder.query(builder);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        List<QueryBuilder> must = boolQueryBuilder.must();
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "java");
//        must.add(matchQueryBuilder)
        RangeQueryBuilder title = QueryBuilders.rangeQuery("title").gt("12");
        List<QueryBuilder> filter = boolQueryBuilder.filter();
        filter.add(title);
//        QueryBuilders.

        //排序
        sourceBuilder.sort("price.keyword",SortOrder.DESC);
        //分页
        sourceBuilder.from(2);
        sourceBuilder.size(10);

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
//        highlightBuilder.highlighterType()
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        //查询字段高亮
        highlightBuilder.requireFieldMatch(false);
        sourceBuilder.highlighter(highlightBuilder) ;
//        sourceBuilder.fetchSource()

        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();

        List<Map<String,Object>> maps = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            //替换高亮
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title1 = highlightFields.get("title");
            if(null!=title1){
                Text[] fragments = title1.fragments();
                String text ="";
                for (Text fragment : fragments) {
                    text+=fragment;
                }
                sourceAsMap.put("title",text);

            }
            maps.add(sourceAsMap);

        }
        System.out.println(JSONObject.toJSONString(maps));


    }


    @Test
    public  void  aggrTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("movie");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //统计字段数量 
//        ValueCountAggregationBuilder field = AggregationBuilders.count("age1").field("age");
        //去重统计
        //CardinalityAggregationBuilder field = AggregationBuilders.cardinality("age1").field("age");
        //过滤统计
        FilterAggregationBuilder field = AggregationBuilders.filter("age1",
                QueryBuilders.rangeQuery("age").gt(10))
                .subAggregation(AggregationBuilders.topHits("top_data"));
        //分组统计个数
//        TermsAggregationBuilder field = AggregationBuilders.terms("age1").field("age");


        //求值统计
//        AggregationBuilders.avg()
//        AggregationBuilders.min()
//        SumAggregationBuilder field = AggregationBuilders.sum("age1").field("age");
//        StatsAggregationBuilder field = AggregationBuilders.stats("age1").field("age");

        sourceBuilder.aggregation(field);

        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(search));
        Aggregations aggregations = search.getAggregations();
        System.out.println(JSONObject.toJSONString(aggregations));
//        SearchHit[] hits = search.getHits().getHits();
//        System.out.println(JSONObject.toJSONString(hits));
    }


}