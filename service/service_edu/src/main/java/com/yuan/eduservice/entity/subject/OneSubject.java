package com.yuan.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yyss
 * @create: 2022-02-07 13:03
 **/
// 一级分类
@Data
public class OneSubject {

    private String id;

    private String title;

    // 一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
