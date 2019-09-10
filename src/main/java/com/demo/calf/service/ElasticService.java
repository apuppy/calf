package com.demo.calf.service;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ElasticService {

    private RestHighLevelClient client;

    /**
     * 初始化elasticsearch客户端
     */
    public ElasticService() {
        this.client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    public Boolean addIndex() throws IOException {
        Boolean indexRet;
        IndexRequest idxRequest = new IndexRequest("posts", "doc", "1");
        // 索引请求的数据格式分以下几种：原生json字符串,map,XContentBuilder
        //index_request json字符串
        IndexRequest request1 = fillIndexDataByString(idxRequest);
        //index_request map
        IndexRequest request2 = fillIndexDataByMap(idxRequest);
        // index_request XContentBuilder
        IndexRequest request3 = fillIndexDataByContentBuilder(idxRequest);
        //index_request raw json
        IndexRequest request4 = fillIndexDataBySortString(idxRequest);

        IndexRequest request = request1.opType(DocWriteRequest.OpType.CREATE);
        try {
            IndexResponse indexResponse = client.index(request); // perform request to create index
            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            long version = indexResponse.getVersion();
            DocWriteResponse.Result responseRet = indexResponse.getResult();
            if (responseRet == DocWriteResponse.Result.CREATED) {
                // TODO
            } else if (responseRet == DocWriteResponse.Result.UPDATED) {
                // TODO
            }
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                // TODO
            }
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                    String reason = failure.reason();
                    // TODO
                }
            }
            indexRet = true;
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                // TODO
            }
            indexRet = false;
        }
        return indexRet;
    }

    private IndexRequest fillIndexDataByString(IndexRequest indexRequest) {
        String jsonString = "{" +
                "\"user\" : \"Kim\"," +
                "\"postDate\" : \"2019-09-10\"," +
                "\"message\" : \"trying out elasticsearch.\"" +
                "}";
        return indexRequest.source(jsonString, XContentType.JSON);
    }

    private IndexRequest fillIndexDataByMap(IndexRequest indexRequest) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "Kim");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "try out elasticsearch.");
        return indexRequest.source(jsonMap);
    }

    private IndexRequest fillIndexDataByContentBuilder(IndexRequest indexRequest) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "Kim");
            builder.timeField("postDate", new Date());
            builder.field("message", "try out elasticsearch.");
        }
        builder.endObject();
        return indexRequest.source(builder);
    }

    private IndexRequest fillIndexDataBySortString(IndexRequest indexRequest) {
        return indexRequest.source(
                "user", "Kim",
                "postDate", new Date(),
                "message", "trying out elasticsearch."
        );
    }

    /**
     * 关闭elasticsearch客户端连接
     */
    public void destroy() {
        try {
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
