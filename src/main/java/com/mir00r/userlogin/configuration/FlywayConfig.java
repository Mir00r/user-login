package com.mir00r.userlogin.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Configuration
public class FlywayConfig {
    private final DataSource dataSource;

    public FlywayConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        return Flyway.configure()
                .outOfOrder(false)
                .dataSource(this.dataSource).load();
    }
}
