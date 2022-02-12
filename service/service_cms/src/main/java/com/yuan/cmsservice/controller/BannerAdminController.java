package com.yuan.cmsservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.cmsservice.entity.CrmBanner;
import com.yuan.cmsservice.service.CrmBannerService;
import com.yuan.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yyss
 * @create: 2022-02-12 22:25
 **/
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    // TODO 后台banner增删改查的前端页面没写

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    //2 添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }
}
