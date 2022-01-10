package com.yuan.eduservice.controller;


import com.yuan.eduservice.entity.EduTeacher;
import com.yuan.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yyss
 * @since 2022-01-08
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //把service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    // 1、查询讲师表中的所有数据
    // restful风格
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher() {
        // 调用service的方法实现查询所有的操作
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    // 2、逻辑删除讲师的方法
    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        return flag;
    }


}

