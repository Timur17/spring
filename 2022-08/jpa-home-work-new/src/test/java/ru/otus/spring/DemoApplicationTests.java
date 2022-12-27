package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@EnableMongock
@DataMongoTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

}
