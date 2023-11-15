package cn.wule.requestsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wule
 */
@SpringBootApplication
@MapperScan("cn.wule.requestsecurity.dao")
public class RequestSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestSecurityApplication.class, args);
    }

}