package priv.austin.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import priv.austin.model.dto.NotifyDto;

/**
 * @author Austin
 * @description 测试类
 * @date 2021/11/17 3:12 下午
 */
@Slf4j
@RestController
public class TestController {
    @PostMapping("/test")
    public String test(@RequestBody NotifyDto notifyDto){
        log.info("\n ----------> 收到通知 ：" + JSONUtil.toJsonStr(notifyDto));
        return "{\"retCode\":\"0000\"}";
    }
}
