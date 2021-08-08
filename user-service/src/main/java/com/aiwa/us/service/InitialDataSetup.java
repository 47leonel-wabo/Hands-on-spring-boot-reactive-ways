package com.aiwa.us.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class InitialDataSetup implements CommandLineRunner {

    @Value("classpath:h2/init.sql")
    private Resource intialSql;

    private final R2dbcEntityTemplate mR2dbcEntityTemplate;

    @Autowired
    public InitialDataSetup(R2dbcEntityTemplate r2dbcEntityTemplate) {
        mR2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String query = StreamUtils.copyToString(intialSql.getInputStream(), UTF_8);

        //System.out.println(query);

        this.mR2dbcEntityTemplate
                .getDatabaseClient()
                .sql(query)
                .then()
                .subscribe();
    }
}
