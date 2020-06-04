/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.elastic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.nacovfil.motorbikeshop.model.ElasticPhoto;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 *
 * @author FN
 */
@Singleton
public class ElasticSearch {

    private static final String HOST = "localhost";
    private static final int PORT_ONE = 9200;
    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "http";

    private static RestHighLevelClient restHighLevelClient;
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String INDEX = "persondata";
    private static final String TYPE = "elasticPhoto";

    private static void insertPhoto(ElasticPhoto ep) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("id", ep.getId());
        dataMap.put("photoBytes", ep.getPhotoBytes());
        IndexRequest indexRequest = new IndexRequest("photos").id(ep.getId().toString()).source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (IOException ex) {
            ex.getLocalizedMessage();
        }

    }

    private static void updatePhotoInElastic(ElasticPhoto ep) {
        UpdateRequest updateRequest = new UpdateRequest("photos", ep.getId().toString())
                .fetchSource(true);    // Fetch Object after its update
        try {
            String personJson = objectMapper.writeValueAsString(ep);
            updateRequest.doc(personJson, XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (JsonProcessingException e) {
            e.getMessage();
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
    }

    private static void deletePhotoById(Integer id) {
        DeleteRequest deleteRequest = new DeleteRequest("photos", id.toString());
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
    }

    private static ElasticPhoto getPhotoById(Integer id) {
        GetRequest getRequest = new GetRequest("photos");
        getRequest.id(id.toString());
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        return getResponse != null ? objectMapper.convertValue(getResponse.getSourceAsMap(), ElasticPhoto.class) : null;
    }

    private static synchronized RestHighLevelClient makeConnection() {
        if (restHighLevelClient == null) {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(HOST, PORT_ONE, SCHEME),
                            new HttpHost(HOST, PORT_TWO, SCHEME)));
        }

        return restHighLevelClient;
    }

    private static synchronized void closeConnection() throws IOException {
        restHighLevelClient.close();
        restHighLevelClient = null;
    }

    public static void savePhoto(ElasticPhoto ep) throws IOException {
        makeConnection();
        insertPhoto(ep);
        closeConnection();
    }

    public static ElasticPhoto getPhoto(Integer id) throws IOException {
        makeConnection();
        ElasticPhoto toReturn = getPhotoById(id);
        closeConnection();
        return toReturn;
    }
    
    public static void updatePhoto(ElasticPhoto ep) throws IOException {
        makeConnection();
        updatePhotoInElastic(ep);
        closeConnection();
    }
    
    public static void deletePhoto(Integer id) throws IOException {
        makeConnection();
        deletePhotoById(id);
        closeConnection();
    }

}
