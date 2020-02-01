package com.ustc.sse.sseoj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2019/12/25 9:48
 */
@EnableRedisHttpSession
@SpringBootApplication
public class SseojApplication {

    public static void main(String[] args) {
        SpringApplication.run(SseojApplication.class, args);
    }

}
