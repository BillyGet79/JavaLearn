package cn.itcast.hotel;

import cn.itcast.hotel.pojo.HotelDoc;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

public class HotelSearchTests {
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        //客户端初始化连接
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("localhost:9200")
        ));
    }

    @Test
    void testMatchAll() throws IOException {
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        request.source().query(QueryBuilders.matchAllQuery());
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }

    @Test
    void testMatch() throws IOException {
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        request.source().query(QueryBuilders.matchQuery("all", "如家"));
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }

    @Test
    void testMultiMatch() throws IOException {
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        request.source().query(QueryBuilders.multiMatchQuery("如家", "name", "business"));
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }

    @Test
    void testTermQuery() throws IOException {
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        request.source().query(QueryBuilders.termQuery("city", "上海"));
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }

    @Test
    void testRangeQuery() throws IOException {
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        request.source().query(QueryBuilders.rangeQuery("price").gte(100).lte(300));
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }

    @Test
    void testBooleanQuery() throws IOException {
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        //2.1 创建布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //2.2 添加must条件
        boolQueryBuilder.must(QueryBuilders.termQuery("city", "上海"));
        //2.3 添加filter条件
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(250));
        //2.4 传入DSL参数
        request.source().query(boolQueryBuilder);
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }

    @Test
    void testPageAndSort() throws IOException {
        //页码，每页大小
        int page = 2;
        int size = 5;
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        //2.1 query
        request.source().query(QueryBuilders.matchAllQuery());
        //2.2 排序 sort
        request.source().sort("price", SortOrder.ASC);
        //2.3 分页 from、size
        request.source().from((page - 1) * size).size(size);
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }

    @Test
    void testHighlighter() throws IOException {
        //1、准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2、组织DSL参数
        //2.1 查询
        request.source().query(QueryBuilders.matchQuery("all", "如家"));
        //2.2 高亮
        request.source().highlighter(new HighlightBuilder().field("all").requireFieldMatch(false));
        //3、发送请求，得到响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4、解析响应结果
        handleResponse(response);
    }


    private static void handleResponse(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        //4.1 查询的总条数
        if (searchHits.getTotalHits() != null) {
            long total = searchHits.getTotalHits().value;
            System.out.println(total);
        }
        //4.2 查询的解惑数组
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            //4.3 得到source
            String json = hit.getSourceAsString();
            //4.4 打印
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            // 获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields)) {
                //根据字段名称获取高亮结果
                HighlightField highlightField = highlightFields.get("all");
                if (highlightField != null) {
                    //获取高亮值
                    String name = highlightField.getFragments()[0].string();
                    //覆盖非高亮结果
                    hotelDoc.setName(name);
                }
            }
            System.out.println(hotelDoc);
        }
    }


    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
}
