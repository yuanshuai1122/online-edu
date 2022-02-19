package com.yuan.cmsservice.controller;

import com.yuan.cmsservice.entity.CrmBanner;
import com.yuan.cmsservice.service.CrmBannerService;
import com.yuan.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yyss
 * @create: 2022-02-12 22:27
 **/
@RestController
@RequestMapping("/educms/bannerfront")

public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}
