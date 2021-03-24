package com.study.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MongoDBJDBC
 * @Description 连接
 * @Author liangxp
 * @Date 2021/3/4 16:17
 **/
public class MongoDBJDBC2 {
    public static void main(String[] args) {
        try {
            // 连接到MongoDB服务
            MongoClient mongoClient = new MongoClient("localhost",27017);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("mycol2");
            System.out.println("集合 mycol 选择成功");

            // 遍历文档
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()){
                System.out.println("遍历文档结果:");
                System.out.println(mongoCursor.next());
            }

            // 修改文档
//            collection.updateMany(Filters.eq("likes",100),new Document("$set",new Document("likes",200)));

            // 遍历文档
           /* FindIterable<Document> findIterable2 = collection.find();
            MongoCursor<Document> mongoCursor2 = findIterable2.iterator();
            while (mongoCursor2.hasNext()){
                System.out.println("遍历文档结果:");
                System.out.println(mongoCursor2.next());
            }*/

            // 删除符合条件的第一个文档
            collection.deleteOne(Filters.eq("likes",150));

            // 删除符合条件的所有文档
//            collection.deleteMany(Filters.eq("likes",100));
            // 检索查看结果
            FindIterable<Document> findIterable3 = collection.find();
            MongoCursor<Document> mongoCursor3 = findIterable3.iterator();
            while (mongoCursor3.hasNext()){
                System.out.println("遍历删除文档后结果:");
                System.out.println(mongoCursor3.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
    }
}
