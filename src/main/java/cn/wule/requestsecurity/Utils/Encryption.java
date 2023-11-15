package cn.wule.requestsecurity.Utils;
//汉江师范学院 数计学院 吴乐创建于2023/11/10 22:22

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wule
 */
@Slf4j
public class Encryption
{
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //加密
        String encode1 = encoder.encode("123456");
        String encode2 = encoder.encode("123456");
        String encode3 = encoder.encode("123456");
        log.info("encode1:{}",encode1);
        log.info("encode2:{}",encode2);
        log.info("encode3:{}",encode3);
        //对比
        boolean matches1 = encoder.matches("123456", encode1);
        boolean matches2 = encoder.matches("123456", encode2);
        boolean matches3 = encoder.matches("123456", encode3);
        log.info("matches1:{}",matches1);
        log.info("matches2:{}",matches2);
        log.info("matches3:{}",matches3);
    }
    //123456 $2a$10$BhaYcPnLsfxJMODVzv42DeHXO9vwY9S5/LLIQ1WcVEQNXwg75FII2
}