// File: src/main/java/com/naira/peminjaman_service/config/MongoConfig.java
package com.naira.peminjaman_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.naira.peminjaman_service.repository")
public class MongoConfig {
}