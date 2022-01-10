package com.yuan.servicebase.exceptionhandler;
import com.yuan.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: yyss
 * @create: 2022-01-10 19:13
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 全局异常
    // 指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody // 为了能够返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理...");
    }

    // 自定义异常
    @ExceptionHandler(YuanException.class)  // 除数为0
    @ResponseBody // 为了能够返回数据
    public R error(YuanException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
