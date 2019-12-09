package com.atguigu.gmall0624.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0624.bean.BaseSaleAttr;
import com.atguigu.gmall0624.bean.SpuInfo;
import com.atguigu.gmall0624.service.ManageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SpuManageController {
    @Reference
    private ManageService manageService;
    @RequestMapping("spuList")
    public List<SpuInfo> spuList(String catalogId3){
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalogId3);
        List<SpuInfo> spuInfoList = manageService.getSpuInfoList(spuInfo);
        return spuInfoList;
    }
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> getBaseSaleAttrList(){
        return   manageService.getBaseSaleAttrList();
    }
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
        return  "OK";
    }

}
