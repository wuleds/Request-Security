package cn.wule.requestsecurity.controller;
//汉江师范学院 数计学院 吴乐创建于2023/11/10 22:38

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author wule
 */
@RestController
@Slf4j
public class UserLoginDataController
{
    /**方式一*/
    @GetMapping("/getAuthentication")
    public Authentication login(Authentication authentication)
    {
        return authentication;
    }

    /**方式二*/
    @GetMapping("/getPrincipal")
    public Principal login(Principal principal)
    {
        return principal;
    }

    /**方式三*/
    @GetMapping("/getSecurityContextHolder")
    public Principal getData()
    {
        //获取安全上下文，再获取认证信息
        return SecurityContextHolder.getContext().getAuthentication();
    }
}