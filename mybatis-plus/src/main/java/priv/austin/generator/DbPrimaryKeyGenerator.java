package priv.austin.generator;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Austin
 * @description Db 主键策略
 * @date 2023/6/28 14:55
 */
@Component
public class DbPrimaryKeyGenerator {
    //主键生成策略必须使用 INPUT
    //支持父类定义 @KeySequence 子类继承使用
    //支持主键类型指定(3.3.0 开始自动识别主键类型)
    //内置支持：
    //DB2KeyGenerator
    //H2KeyGenerator
    //KingbaseKeyGenerator
    //OracleKeyGenerator
    //PostgreKeyGenerator
    //如果内置支持不满足你的需求，可实现 IKeyGenerator 接口来进行扩展.
    @Bean
    public IKeyGenerator keyGenerator() {
        return new OracleKeyGenerator();
    }
}
