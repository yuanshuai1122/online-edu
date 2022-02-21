package com.yuan.educenter.service;

import com.yuan.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author yyss
 * @since 2022-02-13
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(UcenterMember member);

    //注册的方法
    void register(RegisterVo registerVo);

    //根据openid判断
    UcenterMember getOpenIdMember(String openid);

    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
