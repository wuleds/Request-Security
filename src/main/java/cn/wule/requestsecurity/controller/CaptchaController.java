package cn.wule.requestsecurity.controller;
//汉江师范学院 数计学院 吴乐创建于2023/11/16 10:39

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author wule
 */
@Controller
@Slf4j
public class CaptchaController
{
    @Resource
    HttpServletRequest request;
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 50,4,20);
        String code = captcha.getCode();
        log.info("验证码为：{}",code);
        request.getSession().setAttribute("captcha",code);
        ImageIO.write(captcha.getImage(),"gif",response.getOutputStream());
    }
}