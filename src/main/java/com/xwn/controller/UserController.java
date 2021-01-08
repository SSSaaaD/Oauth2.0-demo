package com.xwn.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xwn
 * @date 2021/1/8 0:01
 */


@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
    public Object getUser(Authentication authentication){
        return authentication.getPrincipal();
    }
}
