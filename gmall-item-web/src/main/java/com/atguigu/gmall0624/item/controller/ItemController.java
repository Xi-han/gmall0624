package com.atguigu.gmall0624.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall0624.bean.SkuInfo;
import com.atguigu.gmall0624.bean.SkuSaleAttrValue;
import com.atguigu.gmall0624.bean.SpuSaleAttr;
import com.atguigu.gmall0624.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class ItemController {
    @Reference
    // 获取后台数据服务对象
    private ManageService manageService;

    @RequestMapping("{skuId}.html")
    public String getItem(@PathVariable String skuId, HttpServletRequest request){
        // select * from skuInfo where id = skuId
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        // 通过spuId ，skuId 查询销售属性集合
        List<SpuSaleAttr> spuSaleAttrList = manageService.getSpuSaleAttrListCheckBySku(skuInfo);
        // 销售属性值切换
        List<SkuSaleAttrValue> skuSaleAttrValueList = manageService.getSkuSaleAttrValueListBySpu(skuInfo.getSpuId());
        String key = "";
        HashMap<String, String> map = new HashMap<>();
        // {"122|126":"37","123|126":"38","124|128":"39","122|128":"40"} json 字符串！
        // 声明一个map  | map.put("122|126","37"); --- map 转换为json 字符串！ key = valueId|valueId  value = skuId
        if (skuSaleAttrValueList!=null && skuSaleAttrValueList.size()>0){
            // 拼接规则：1.  当循环的skuId 与 下一次循环的skuId 不相同的时候，停止拼接，并将key，value 放入map 集合中！ 当前key 应该清空！
            //           2.  循环到集合最后的时候，停止拼接 并将key，value 放入map 集合中！ 当前key 应该清空！
            //              map.put("122|126","37")
            //  itar
            for (int i = 0; i < skuSaleAttrValueList.size(); i++) {
                SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueList.get(i);
                if (key.length()>0){
                    key+="|";
                }
                key+=skuSaleAttrValue.getSaleAttrValueId(); // key = key + skuSaleAttrValue.getSaleAttrValueId();
                // 第一次：key = 122
                // 第二次：key = 122|126
                if ((i+1)==skuSaleAttrValueList.size() || !skuSaleAttrValue.getSkuId().equals(skuSaleAttrValueList.get(i+1).getSkuId())){
                    // 停止拼接，并将key，value 放入map 集合中！ 当前key 应该清空！
                    map.put(key,skuSaleAttrValue.getSkuId());
                    key="";
                }
            }
        }

        // 将map 转换为json 字符串
        String valuesSkuJson = JSON.toJSONString(map);

        // 保存json 字符串
        request.setAttribute("valuesSkuJson",valuesSkuJson);

        request.setAttribute("spuSaleAttrList",spuSaleAttrList);
        // 保存skuInfo 给页面渲染
        request.setAttribute("skuInfo",skuInfo);
        return "item";
    }



}
