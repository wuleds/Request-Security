package cn.wule.requestsecurity.controller;
//汉江师范学院 数计学院 吴乐创建于2023/11/10 17:10

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wule
 */
@RestController
@RequestMapping("/admin")
public class AdminController
{
    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('admin:query')")
    public String get()
    {
        return "admin";
    }

    @GetMapping("/add")
    public String add()
    {
        return "admin-add";
    }

    @GetMapping("/delete")
    public String delete()
    {
        return "admin-delete";
    }

    @GetMapping("/update")
    public String update()
    {
        return "admin-update";
    }
}