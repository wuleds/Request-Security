package cn.wule.requestsecurity.config;
//汉江师范学院 数计学院 吴乐创建于2023/11/22 0:09

import cn.wule.requestsecurity.Utils.JWTTest;
import cn.wule.requestsecurity.vo.HttpRequest;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wule
 */
@Component
@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler
{
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private Gson gson;
    @Resource
    private JWTTest jwtTest;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String auth = request.getHeader("Authorization");
        if(auth == null){
            HttpRequest<String> httpRequest = HttpRequest.<String>builder()
                    .code(401)
                    .message("token为空")
                    .build();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(gson.toJson(httpRequest));
            return;
        }
        String jwt = auth.replace("bearer ", "");
        boolean result = jwtTest.verifyJWT(jwt);
        if(!result){
            HttpRequest<String> httpRequest = HttpRequest.<String>builder()
                    .code(401)
                    .message("token非法")
                    .build();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(gson.toJson(httpRequest));
            return;
        }
        //从redis中删除缓存，使之失效
        stringRedisTemplate.delete("auth:"+ jwt);
    }
}