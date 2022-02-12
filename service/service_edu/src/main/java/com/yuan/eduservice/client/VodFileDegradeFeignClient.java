package com.yuan.eduservice.client;

import com.yuan.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: yyss
 * @create: 2022-02-12 19:28
 **/
@Component
public class VodFileDegradeFeignClient implements VodClient{
    // 出错之后,会执行
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
