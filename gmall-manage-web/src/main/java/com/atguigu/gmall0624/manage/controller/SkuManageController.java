package com.atguigu.gmall0624.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0624.bean.SkuInfo;
import com.atguigu.gmall0624.bean.SkuLsInfo;
import com.atguigu.gmall0624.bean.SpuImage;
import com.atguigu.gmall0624.bean.SpuSaleAttr;
import com.atguigu.gmall0624.service.ListService;
import com.atguigu.gmall0624.service.ManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SkuManageController {
    @Reference
    private ManageService manageService;

    @Reference
    private ListService listService;

    // http://localhost:8082/spuImageList?spuId=60
    @RequestMapping("spuImageList")
    public List<SpuImage> getSpuImageList(SpuImage spuImage){
        return manageService.getSpuImageList(spuImage);
    }
    // http://localhost:8082/spuSaleAttrList?spuId=60
    @RequestMapping("spuSaleAttrList")
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId){

        return manageService.getSpuSaleAttrList(spuId);
    }
    // http://localhost:8082/saveSkuInfo
    // @RequestMapping post,get
    @RequestMapping("saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
    }
    // 单个上传 http://localhost:8082/onSale?skuId=38
    // 批量上传 http://localhost:8082/onSale?skuIds=38,39,40,41
    // springmvc  第一种方式：/  第二种 *.do | *.action
    @RequestMapping("onSale")
    public void onSale(String skuId){
        SkuLsInfo skuLsInfo = new SkuLsInfo();
        // 给skuLsInfo 赋值 skuLsInfo的属性与skuInfo 属性一致！
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        // spring下BeanUtils
        BeanUtils.copyProperties(skuInfo,skuLsInfo);
        // 调用上传服务
        listService.saveSkuLsInfo(skuLsInfo);
    }
}
