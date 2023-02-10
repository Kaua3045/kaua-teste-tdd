package com.kaua.testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("teste-e2e")
@SpringBootTest(classes = KauaTesteApplication.class)
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KauaTesteE2E {

//    @Container
//    public static final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8"));

    @Container
    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"));

    @DynamicPropertySource
    static void postgresDbProperties(DynamicPropertyRegistry registry) {
        postgres.start();
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
//        registry.add("spring.datasource.port", postgres::getJdbcUrl);
    }
//    @Container
//    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

//    @DynamicPropertySource
//    static void mongoDbProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//    @Container
//    private static final MySQLContainer<?> mysql = new MySQLContainer(DockerImageName.parse("mysql:latest"))
//            .withPassword("123456")
//            .withUsername("root")
//            .withDatabaseName("adm_videos");
//
//    @DynamicPropertySource
//    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", mysql::getJdbcUrl);
//        registry.add("spring.datasource.username", mysql::getUsername);
//        registry.add("spring.datasource.password", mysql::getPassword);
//    }

//    @BeforeAll
//    public void startContainer() {
//        postgres.start();
//    }

    @Test
    public void testWorking() {
        Assertions.assertTrue(postgres.isRunning());
    }
}
