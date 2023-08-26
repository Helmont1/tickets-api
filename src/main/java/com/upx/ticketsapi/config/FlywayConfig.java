package com.upx.ticketsapi.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FlywayConfig {
    @Bean
    Flyway flyway(Environment env) {
        var url = env.getProperty("spring.datasource.url");
        var user = env.getProperty("spring.datasource.username");
        var password = env.getProperty("spring.datasource.password");
        var location = new String[] { "classpath:db/migration" };
        var baseline = env.getProperty("spring.flyway.baseline-on-migrate");
        
        if (baseline == null) {
            baseline = "true";
        }

        var flyway = Flyway.configure()
                .dataSource(url, user, password)
                .defaultSchema("tickets")
                .createSchemas(true)
                .baselineOnMigrate(baseline.contains("true"))
                .baselineVersion(env.getProperty("spring.flyway.baseline-version"))
                .baselineDescription(env.getProperty("spring.flyway.baseline-description"))
                .locations(location)
                .load();
        flyway.migrate();
        return flyway;
    }

}
