package com.xwn.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author xwn
 * @date 2021/1/8 0:01
 */


@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
    public Object getUser(Authentication authentication, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(authorization.lastIndexOf("bearer") + 7);
        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
