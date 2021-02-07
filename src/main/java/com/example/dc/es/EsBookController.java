//package com.example.dc.es;
//
//import com.alibaba.fastjson.JSONObject;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.text.Text;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.TermQueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * @ Author     ：duanchao
// * @ Date       ： 10:40 2021/1/28
// * @ Description：
// */
//@RestController
//@RequestMapping("/es/book")
//public class EsBookController {
//
//    @Autowired
////    @Qualifier("restHighLevelClient")
//    private RestHighLevelClient restHighLevelClient;
//
////    @Autowired
////    BookVoRepository bookVoRepository;
//
//    @Autowired
//    private  HtmlPageUtil htmlPageUtil;
//
//
//
//
//    @RequestMapping("/init")
//    public void bookVoList (String keyWord) throws IOException {
//        List<BookVo> jiexi = htmlPageUtil.jiexi(keyWord);
//
//        BulkRequest bulkRequest = new BulkRequest();
//        for (BookVo bookVo : jiexi) {
//            IndexRequest request = new IndexRequest("jd-book");
//            request.source(JSONObject.toJSONString(bookVo), XContentType.JSON);
//            bulkRequest.add(request);
//        }
//
//        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//
//        System.out.println(!bulk.hasFailures());
//    }
//
//
//    @RequestMapping("search")
//    public  List<Map<String,Object>> search(String keyWord,
//                                            @RequestParam(defaultValue = "1") Integer page,
//                                            @RequestParam(defaultValue = "10")Integer size) throws IOException {
//        SearchRequest searchRequest = new SearchRequest("jd-book");
//
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        TermQueryBuilder title = QueryBuilders.termQuery("title", keyWord);
//        sourceBuilder.query(title);
////        sourceBuilder.timeout()
//
//       //高亮
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("title");
//        highlightBuilder.preTags("<span style='color:red'>");
//        highlightBuilder.postTags("</span>");
//        highlightBuilder.requireFieldMatch(false);
//        sourceBuilder.highlighter(highlightBuilder) ;
//
//
//        sourceBuilder.from(page);
//        sourceBuilder.size(size);
//        searchRequest.source(sourceBuilder);
//
//        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        SearchHit[] hits = search.getHits().getHits();
//        List<Map<String,Object>> maps = new ArrayList<>();
//        for (SearchHit hit : hits) {
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//
//            //替换高亮
//            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//            HighlightField title1 = highlightFields.get("title");
//            if(null!=title1){
//                Text[] fragments = title1.fragments();
//                String text ="";
//                for (Text fragment : fragments) {
//                    text+=fragment;
//                }
//                sourceAsMap.put("title",text);
//
//            }
//
//
//            maps.add(sourceAsMap);
//
//        }
//
//        return maps;
//
//    }
//}