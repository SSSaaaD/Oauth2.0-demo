package com.xwn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author xwn
 * @date 2021/1/7 23:59
 */

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有请求拦截
                .anyRequest()
                .authenticated()
                .and()
                //请求路径匹配,需要token令牌才能访问
                .requestMatchers()
                .antMatchers("/user/**");
    }
}
