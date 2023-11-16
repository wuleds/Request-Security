package cn.wule.requestsecurity.controller;
//汉江师范学院 数计学院 吴乐创建于2023/11/15 22:42

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wule
 */
@Controller
@Slf4j
public class MainPageController
{
    @RequestMapping(value = "/main",method = RequestMethod.POST)
    public String mainPage()
    {
        //返回thymeleaf的逻辑视图，物理视图 = 前缀 + 逻辑视图名 + 后缀
        return "main";
    }
}