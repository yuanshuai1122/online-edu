package com.yuan.eduservice.entity.vo;

import lombok.Data;

/**
 * @author: yyss
 * @create: 2022-02-08 12:36
 **/
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
