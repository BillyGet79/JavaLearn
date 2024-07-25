package cn.itcast.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
        //解析响应结果

    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
}
