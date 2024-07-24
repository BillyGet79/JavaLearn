package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class HotelDocumentTests {
    @Autowired
    private IHotelService hotelService;
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        //客户端初始化连接
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("localhost:9200")
        ));
    }

    @Test
    void testAddDocument() throws IOException {
        //根据id查询酒店数据
        Hotel hotel = hotelService.getById(61083L);
        //转换为文档类型
        HotelDoc hotelDoc = new HotelDoc(hotel);
        //1、准备Request对象
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        //2、准备Json文档
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        //3、发送请求
        client.index(request, RequestOptions.DEFAULT);
    }

    @Test
    void testGetDocumentById() throws IOException {
        //1、创建request对象
        GetRequest request = new GetRequest("hotel", "61083");
        //2、发送请求，得到结果
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        //3、解析结果
        String json = response.getSourceAsString();

        HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
        System.out.println(hotelDoc);
    }

    @Test
    void testUpdateDocumentById() throws IOException {
        //1、创建request对象
        UpdateRequest request = new UpdateRequest("hotel", "61083");
        //2、准备参数
        request.doc(
            "price", "952",
                "starName", "四钻"
        );
        //3、更新文档
        client.update(request, RequestOptions.DEFAULT);
    }

    @Test
    void testDeleteDocument() throws IOException {
        //1、准备request对象
        DeleteRequest request = new DeleteRequest("hotel", "61083");
        //2、发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testBulk() throws IOException {
        //批量查询酒店数据
        List<Hotel> hotelList = hotelService.list();
        //1、创建Bulk请求
        BulkRequest request = new BulkRequest();
        //2、添加要批量提交的请求
        hotelList.forEach(hotel -> {
            HotelDoc hotelDoc = new HotelDoc(hotel);
            request.add(new IndexRequest("hotel").id(hotel.getId().toString()).source(JSON.toJSONString(hotelDoc), XContentType.JSON));
        });
        //3、发起bulk请求
        client.bulk(request, RequestOptions.DEFAULT);
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
}
