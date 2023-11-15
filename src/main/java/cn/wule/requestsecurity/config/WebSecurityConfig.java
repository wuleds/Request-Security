package cn.wule.requestsecurity.config;
//汉江师范学院 数计学院 吴乐创建于2023/11/10 23:01

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                        .anyRequest()
                        .authenticated());
        http.httpBasic(withDefaults());
        http.formLogin((formLogin) -> formLogin.successHandler(appAuthenticationSuccessHandler).failureHandler(appAuthenticationFailHandler).permitAll());
        http.logout((logout) -> logout.logoutSuccessHandler(appLogoutSuccessHandler).permitAll());
        http.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedHandler(appAccessDenyHandler));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}