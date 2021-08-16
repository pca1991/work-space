package priv.austin.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import priv.austin.common.constant.CommonConsts;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * created by riger on 2021/2/9
 *
 * 自定义provider处理 array、null等：
 * https://www.cnblogs.com/liaojie970/p/14042211.html
 *
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setDateFormat(new SimpleDateFormat(CommonConsts.DATE_TIME_FORMAT));
        mapper.setTimeZone(TimeZone.getTimeZone(CommonConsts.TIME_ZONE));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    /**
     * SpringMVC 使用定制 ObjectMapper
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
