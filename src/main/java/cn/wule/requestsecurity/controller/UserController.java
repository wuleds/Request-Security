package cn.wule.requestsecurity.controller;
//汉江师范学院 数计学院 吴乐创建于2023/11/10 17:10

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wule
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @GetMapping("/get")
    public String get()
    {
        return "user";
    }

    @GetMapping("/add")
    public String add()
    {
        return "user-add";
    }

    @GetMapping("/delete")
    public String delete()
    {
        return "user-delete";
    }

    @GetMapping("/update")
    public String update()
    {
        return "user-update";
    }
}