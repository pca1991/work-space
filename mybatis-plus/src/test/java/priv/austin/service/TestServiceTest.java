package priv.austin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TestServiceTest {
    @Resource
    TestService testService;

    @Test
    public void test(){
        testService.a();
    }
}