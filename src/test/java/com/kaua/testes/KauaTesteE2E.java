package com.kaua.testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("teste-e2e")
@SpringBootTest(classes = KauaTesteApplication.class)
@Testcontainers
public class KauaTesteE2E {

    @Container
    private static final MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql:latest"))
            .withPassword("123456")
            .withUsername("root")
            .withDatabaseName("adm_videos");

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
    }

    @Test
    public void testWorking() {
        Assertions.assertTrue(mysql.isRunning());
    }
}
