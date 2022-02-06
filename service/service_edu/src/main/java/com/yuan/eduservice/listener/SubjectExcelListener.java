package com.yuan.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.eduservice.entity.EduSubject;
import com.yuan.eduservice.entity.excel.SubjectData;
import com.yuan.eduservice.service.EduSubjectService;
import com.yuan.servicebase.exceptionhandler.YuanException;

/**
 * @author: yyss
 * @create: 2022-02-06 17:10
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    // 因为SubjectExcelListener不能交给Spring管理，需要自己new,不能注入其他对象
    // 不能实现数据库操作
    public EduSubjectService subjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // 读取excel中内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null) {
            throw new YuanException(20001,"文件数据为空");
        }
        // 一行一行读取，每次读取有两个值，第一个值为一级分类，第二个值为二级分类
        // 判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectData.getOneSubjectName(), subjectService);
        if (existOneSubject == null) { // 表里没有相同的一级分类
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        // 获取一级分类的id值
        String pid = existOneSubject.getId();
        // 添加二级分类
        // 判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), subjectService, pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }

    }
    // 判断一级分类不能重复添加
    private EduSubject existOneSubject(String name, EduSubjectService subjectService) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
    // 判断二级分类不能重复添加
    private EduSubject existTwoSubject(String name, EduSubjectService subjectService,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
