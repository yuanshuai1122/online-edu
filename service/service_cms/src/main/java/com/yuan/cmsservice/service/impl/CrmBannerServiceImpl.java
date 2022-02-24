package com.yuan.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.cmsservice.entity.CrmBanner;
import com.yuan.cmsservice.mapper.CrmBannerMapper;
import com.yuan.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author yyss
 * @since 2022-02-12
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //查询所有banner
    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectAllBanner() {

        //根据id进行降序排列，显示排列之后前两条记录
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 4");
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
