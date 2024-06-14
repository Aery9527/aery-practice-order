package org.aery.practice.order.utils.fortest;

import org.aery.practice.order.utils.config.RedisBaseConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import redis.embedded.RedisServer;

@Configuration
@Import({RedisAutoConfiguration.class, RedisBaseConfig.class})
public class ForRedisTestConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public RedisServer redisEmbeddedServer() {
        return new RedisServer();
    }

}
