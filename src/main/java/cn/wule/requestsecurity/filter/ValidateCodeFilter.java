package cn.wule.requestsecurity.filter;
//汉江师范学院 数计学院 吴乐创建于2023/11/16 16:31

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author wule
 */
@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 判断路径为doLogin
        String uri = "/login/doLogin";
        String requestURI = request.getRequestURI();
        if(uri.equals(requestURI)){
            // 1.从前端获取验证码
            String code = request.getParameter("captcha");
            // 2.从Session获取验证码
            String captcha = (String) request.getSession().getAttribute("captcha");
            // 3.判断
            if(code.equals(captcha)){
                doFilter(request,response,filterChain);
            }else {
                request.getSession().setAttribute("msg","验证码error");
                response.sendRedirect("/toLogin");
            }
        }else {
            doFilter(request,response,filterChain);
        }
    }
}