package priv.austin.config;

import okhttp3.OkHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Austin
 * @description RestTemplate配置类
 * @date 2022/6/13 14:41
 */
@Configuration
public class RestTemplateConfig {
    //OkHttp > Apache的HttpClient > HttpURLConnection

    /**
     * 初始化
     * @return RestTemplate
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate(){
        return getOkHttpRequestFactory();
    }

    /**
     * 1.使用了JDK自带的HttpURLConnection作为底层HTTP客户端实现。
     * @return RestTemplate
     */
    private RestTemplate getHttpURLConnectionRequestFactory() {
        return new RestTemplate();
    }

    /**
     * 2.使用HttpClient作为底层客户端
     * @return RestTemplate
     */
    private RestTemplate getHttpClientRequestFactory() {
        int timeout = 5 * 1000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new RestTemplate( new HttpComponentsClientHttpRequestFactory(client));
    }

    /**
     * 3.使用OkHttpClient作为底层客户端
     * @return RestTemplate
     */
    private RestTemplate getOkHttpRequestFactory(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        return new RestTemplate( new OkHttp3ClientHttpRequestFactory(okHttpClient));
    }

}
