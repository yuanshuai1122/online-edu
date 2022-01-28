package com.yuan.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.commonutils.R;
import com.yuan.eduservice.entity.EduTeacher;
import com.yuan.eduservice.entity.vo.TeacherQuery;
import com.yuan.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    //把service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    // 1、查询讲师表中的所有数据
    // restful风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAllTeacher() {
        // 调用service的方法实现查询所有的操作
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    // 2、逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true)
                               @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }


    // 3 分页查询讲师方法
    // current 当前页
    // limit 每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        // 创建配置对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
         // 调用方法实现分页
        // 调用方法的时候，底层封装，把分页所有数据封装到pageTeacher里面了
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();// 总记录数
        List<EduTeacher> records = pageTeacher.getRecords();// 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 4 条件查询带分页
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis 动态sql 类似

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // 判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            // 构造条件
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)) {
            // 构造条件
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)) {
            // 构造条件
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            // 构造条件
            wrapper.le("gmt_create", end);
        }

        // 排序
        wrapper.orderByDesc("gmt_create");

        // 调用方法条件查询带分页
        eduTeacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();// 总记录数
        List<EduTeacher> records = pageTeacher.getRecords();// 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 5 添加讲师的方法
    @PostMapping("addTeacher")
    public R addTeacher (@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    // 6 根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher (@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    // 讲师修改功能
    @PostMapping("updateTeacher")
    public R updateTeacher (@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

}

