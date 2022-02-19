package com.yuan.eduservice.controller;

import com.yuan.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yyss
 * @create: 2022-01-13 16:25
 **/
@Api(tags = "登录管理")
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    // login
    @PostMapping("/login")
    public R login() {
        return R.ok().data("token","admin");
    }

    // info
    @GetMapping("/info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://yuan-online-edu.oss-cn-beijing.aliyuncs.com/img/avatar.jpeg");
    }
}
