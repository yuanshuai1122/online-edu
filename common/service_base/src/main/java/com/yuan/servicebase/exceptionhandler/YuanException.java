package com.yuan.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yyss
 * @create: 2022-01-10 19:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YuanException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;
}
