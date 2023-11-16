package cn.wule.requestsecurity.config;
//汉江师范学院 数计学院 吴乐创建于2023/11/10 23:01

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author wule
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Resource
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
    @Resource
    private AppAuthenticationFailHandler appAuthenticationFailHandler;
    @Resource
    private AppLogoutSuccessHandler appLogoutSuccessHandler;
    @Resource
    private AppAccessDenyHandler appAccessDenyHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/root/**").hasAuthority("root:all")
                .requestMatchers("/user/add").hasAuthority("user:add")
                .requestMatchers("/user/delete").hasAuthority("user:del")
                .anyRequest()
                .authenticated());
        http.httpBasic(withDefaults());
        http.formLogin((formLogin) ->
                formLogin
                        .loginPage("/toLogin") //配置登录接口
                        .usernameParameter("userName") //配置登录用户名参数
                        .passwordParameter("password") //配置登录密码参数
                        .loginProcessingUrl("/login/doLogin") //单击登录的接口
                        .failureForwardUrl("/toLogin") //登录失败的接口
                        .successForwardUrl("/main") //登录成功的接口
                        .permitAll());
        http.logout((logout) ->
                logout
                        .logoutSuccessUrl("/login/toLogin") //退出登录状态后的接口
                        .permitAll());
        http.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedHandler(appAccessDenyHandler));
        http.csrf(AbstractHttpConfigurer::disable);
        //禁止同源保护
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}