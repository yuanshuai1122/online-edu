package com.yuan.eduservice.controller;


import com.yuan.commonutils.R;
import com.yuan.eduservice.client.VodClient;
import com.yuan.eduservice.entity.EduVideo;
import com.yuan.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author yyss
 * @since 2022-02-07
 */
@Api(tags = "小节管理")
@RestController
@RequestMapping("/eduservice/video")

public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        // 根据小节id 获取视频id
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {
            // 根据视频id，远程调用实现删除
            vodClient.removeAlyVideo(videoSourceId);
        }
        // 删除小节
        videoService.removeById(id);
        return R.ok();
    }

    //修改小节 TODO

}

