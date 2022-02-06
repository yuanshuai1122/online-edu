package com.yuan.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author: yyss
 * @create: 2022-02-06 16:18
 **/
public class ExcelListener extends AnalysisEventListener<DemoData> {
    // 一行一行读取excel数据
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("****" + demoData);
    }

    // 读取表头内容
    public void invokeHeadmap(Map<Integer,String> headMap, AnalysisContext context) {
        System.out.println("表头： " + headMap);
    }


    // 读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
