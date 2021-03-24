package com.study.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MongoDBJDBC
 * @Description 连接
 * @Author liangxp
 * @Date 2021/3/4 16:17
 **/
public class MongoDBJDBC {
    public static void main(String[] args) {
        try {
            // 连接到MongoDB服务
            MongoClient mongoClient = new MongoClient("localhost",27017);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("mycol");
            System.out.println("集合 mycol 选择成功");

            // 插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title","MongoDB")
                    .append("description","databse")
                    .append("likes",100)
                    .append("by","liangxp");
            List<Document> list = new ArrayList<>();
            list.add(document);

            collection.insertMany(list);
            System.out.println("插入文档成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
    }
}
