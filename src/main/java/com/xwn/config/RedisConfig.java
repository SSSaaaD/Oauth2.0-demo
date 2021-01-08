package com.xwn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author xwn
 * @date 2021/1/8 15:30
 */
@Configuration
public class RedisConfig {


   /* @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    *//*
    * redis存储token配置*//*
    @Bean
    public TokenStore RedisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }*/

}
