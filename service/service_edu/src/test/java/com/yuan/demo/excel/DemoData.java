package com.yuan.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: yyss
 * @create: 2022-02-06 15:59
 **/
@Data
public class DemoData {

    // 设置excel表头名称
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}
