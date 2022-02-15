package com.yuan.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yyss
 * @since 2022-01-08
 */
public interface EduTeacherService extends IService<EduTeacher> {

    // 分页查询讲师方法
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
