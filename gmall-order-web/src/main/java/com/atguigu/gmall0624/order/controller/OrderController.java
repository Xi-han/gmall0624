package com.atguigu.gmall0624.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0624.bean.UserAddress;
import com.atguigu.gmall0624.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    @Reference
    private UserInfoService userInfoService;
    @RequestMapping("trade")
    @ResponseBody
    public List<UserAddress> trade(String userId){
        // 得到用户的收货地址列表
        return userInfoService.findUserAddressListByUserId(userId);

    }
}
