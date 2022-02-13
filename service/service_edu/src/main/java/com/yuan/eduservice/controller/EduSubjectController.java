package com.yuan.eduservice.controller;


import com.yuan.commonutils.R;
import com.yuan.eduservice.entity.EduSubject;
import com.yuan.eduservice.entity.subject.OneSubject;
import com.yuan.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yyss
 * @since 2022-02-06
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类
    // 获取到上传过来的文件，把文件内容读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        // 上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    // 课程分类列表（树形）
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        // list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

