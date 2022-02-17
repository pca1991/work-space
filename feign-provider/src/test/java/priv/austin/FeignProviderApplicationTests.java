package priv.austin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Austin
 * @description 测试类
 * @date 2021/8/17 2:32 下午
 */
//@SpringBootTest
public class FeignProviderApplicationTests {

    @Test
    void contextLoads() {
        int i = 10;
        i += i >> 1;
        System.out.println( i);
        i += i >> 1;
        System.out.println( i);
    }
}
