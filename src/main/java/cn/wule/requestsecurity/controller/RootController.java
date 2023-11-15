package cn.wule.requestsecurity.controller;
//汉江师范学院 数计学院 吴乐创建于2023/11/10 17:11

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wule
 */
@RestController
@RequestMapping("/root")
public class RootController
{
    @GetMapping("/get")
    public String get()
    {
        return "root";
    }

    @GetMapping("/add")
    public String add()
    {
        return "root-add";
    }

    @GetMapping("/delete")
    public String delete()
    {
        return "root-delete";
    }

    @GetMapping("/update")
    public String update()
    {
        return "root-update";
    }
}