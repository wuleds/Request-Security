package cn.wule.requestsecurity.filter;
//汉江师范学院 数计学院 吴乐创建于2023/11/20 11:58

import cn.wule.requestsecurity.Utils.JWTTest;
import cn.wule.requestsecurity.model.User;
import cn.wule.requestsecurity.vo.HttpRequest;
import cn.wule.requestsecurity.vo.JwtUserInfo;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 验证jwt的过滤器
 * @author wule
 */
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter
{
    @Resource
    private JWTTest jwtTest;
    @Resource
    private Gson gson;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if ("/toLogin".equals(uri) || "/captcha".equals(uri) || "/signin".equals(uri) || "/login/doLogin".equals(uri)) {
            doFilter(request, response, filterChain);
            return;
        }
        String strAuth = request.getHeader("Authorization");
        if (strAuth == null) {
            HttpRequest<String> httpRequest = HttpRequest.<String>builder()
                    .code(401)
                    .message("token为空")
                    .build();
            writeJson(request,response,gson.toJson(httpRequest));
            return;
        }

        String jwt = strAuth.replace("bearer ", "");
        log.info("jwt:{}", jwt);
        if (!jwtTest.verifyJWT(jwt)) {
            HttpRequest<String> httpRequest = HttpRequest.<String>builder()
                    .code(401)
                    .message("token验证失败")
                    .build();
            writeJson(request,response,gson.toJson(httpRequest));
            return;
        }else {
            JwtUserInfo userInfo = jwtTest.getJwtUserInfo(jwt);
            List<SimpleGrantedAuthority> authList = userInfo.getAuthList().stream().map(SimpleGrantedAuthority::new).toList();
            User user = User.builder()
                    .userId(userInfo.getUseId())
                    .userName(userInfo.getUserName())
                    .build();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, authList);
            //把token放到安全上下文里面
            SecurityContextHolder.getContext().setAuthentication(token);
            doFilter(request, response, filterChain);
        }
        request.setAttribute("jwt", jwt);
    }

    private void writeJson(HttpServletRequest request,HttpServletResponse response,String jwtResult) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jwtResult);
        printWriter.flush();
    }
}