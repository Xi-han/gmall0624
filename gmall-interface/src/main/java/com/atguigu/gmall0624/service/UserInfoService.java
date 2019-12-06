package com.atguigu.gmall0624.service;

import com.atguigu.gmall0624.bean.UserAddress;
import com.atguigu.gmall0624.bean.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**
     * 查询所有用户数据
     * @return
     */
    List<UserInfo> findAll();

    /**
     *
     * @param name
     * @return
     */
    UserInfo getUserInfoByName(String name);

    /**
     *
     * @param userInfo
     * @return
     */
    List<UserInfo> getUserInfoListByName(UserInfo userInfo);
    /**
     *
     * @param userInfo
     * @return
     */
    List<UserInfo> getUserInfoListByNickName(UserInfo userInfo);
    // int ,boolean, void

    /**
     * 添加用户信息
     * @param userInfo
     */
    void addUser(UserInfo userInfo);

    /**
     *
     * @param userInfo
     */
    void updUser(UserInfo userInfo);

    /**
     *
     * @param userInfo
     */
    void delUser(UserInfo userInfo);

    /**
     * 根据用户ID查看用户收货地址列表
     * @param userId
     * @return
     */
    List<UserAddress> findUserAddressListByUserId(String userId);
}

