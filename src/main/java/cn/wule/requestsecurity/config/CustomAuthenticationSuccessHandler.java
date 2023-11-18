package cn.wule.requestsecurity.config;
//汉江师范学院 数计学院 吴乐创建于2023/11/17 17:17

import cn.hutool.jwt.JWTUtil;
import cn.wule.requestsecurity.model.User;
import cn.wule.requestsecurity.vo.UserSecurity;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author wule
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    @Resource
    private Gson gson;
    @Resource
    private JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //从认证信息中获取用户信息
        UserSecurity userSecurity = (UserSecurity)authentication.getPrincipal();
        User user = userSecurity.getUser();
        String userInfo = gson.toJson(user);
        //获取权限信息
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) userSecurity.getAuthorities();
        //生成jwt

    }
}