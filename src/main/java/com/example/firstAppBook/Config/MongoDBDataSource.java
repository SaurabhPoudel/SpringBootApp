package com.example.firstAppBook.Config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDBDataSource {
    private static volatile MongoDBDataSource instance;
    private final MongoTemplate mongoTemplate;

    private MongoDBDataSource() {
        MongoClient mongoClient = MongoClients.create("mongodb://mongodb:27017/bookdb");
        this.mongoTemplate = new MongoTemplate(mongoClient, "bookdb");
    }

    public static MongoDBDataSource getInstance() {
        if (instance == null) {
            synchronized (MongoDBDataSource.class) {
                if (instance == null) {
                    instance = new MongoDBDataSource();
                }
            }
        }
        return instance;
    }
        public MongoTemplate getMongoTemplate() {
            return mongoTemplate;
        }
}
// This code defines a singleton class MongoDBDataSource that provides a MongoTemplate instance for MongoDB operations.

