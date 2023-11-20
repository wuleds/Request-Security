package cn.wule.requestsecurity.filter;
//汉江师范学院 数计学院 吴乐创建于2023/11/20 11:58

import cn.wule.requestsecurity.vo.HttpRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 验证jwt的过滤器
 * @author wule
 */
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if("toLogin".equals(uri)){
            doFilter(request,response,filterChain);
            return;
        }
        String strAuth = request.getHeader("Authorization");

}