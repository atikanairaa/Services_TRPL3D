// File: src/main/java/com/naira/peminjaman_service/config/JPAConfig.java
package com.naira.peminjaman_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.naira.peminjaman_service.repository")
public class JPAConfig {
}