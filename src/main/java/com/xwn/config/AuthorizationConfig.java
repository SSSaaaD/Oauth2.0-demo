package com.xwn.config;

import com.xwn.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author xwn
 * @date 2021/1/7 23:54
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DetailService detailService;

    /*@Autowired
    private TokenStore RedisTokenStore;*/

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    /*
     * 密码模式需要的!*/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(detailService)
                //把获取到的accessToken转成JWTtoken
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter);
        //redis存储token
        //.tokenStore(RedisTokenStore);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //客户端ID
                .withClient("xwn")
                //秘钥
                .secret(passwordEncoder.encode("123"))
                //重定向地址,千万不要写错
                .redirectUris("http://localhost:8081/login")
                //令牌过期时间
                .accessTokenValiditySeconds(60)
                //刷新令牌过期时间
                .refreshTokenValiditySeconds(86400)
                //授权范围
                .scopes("all")
                //自动授权,不需要手动按授权
                //.autoApprove(true)
                //授权类型(authorization_code(授权码模式),password(密码模式))
                .authorizedGrantTypes("authorization_code", "password","refresh_token");
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //获取秘钥必须要身份验证,单点登录必须要配置的
        security.tokenKeyAccess("isAuthenticated()");
    }
}
