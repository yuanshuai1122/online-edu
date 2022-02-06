package com.yuan.demo.excel;

import com.alibaba.excel.EasyExcel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yyss
 * @create: 2022-02-06 16:02
 **/
public class TestEasyExcel {
    public static void main(String[] args) {
//        // 实现excel写的操作
//        // 1、设置写入文件夹地址和excel文件名称
//        String filename = "/Users/yuanshuai/Downloads/write.xlsx";
//
//        // 2、调用easyexcel里面的方法实现操作
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());


        // 实现excel的读操作
        String filename = "/Users/yuanshuai/Downloads/write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    // 创建方法返回list集合
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
        }
    }

