package com.absys.Document.Anaylzer.config;


import com.pgvector.PGvector;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class PgVectorConfig {

    @Bean
    CommandLineRunner registerVector(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                PGvector.registerTypes(connection);
            }
        };
    }
}