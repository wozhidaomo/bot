package com.zhuoyao.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhuoyao.model.Yaoling;
import com.zhuoyao.service.YaolingDataDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class StoreDataConller {

    @Autowired
    YaolingDataDeal yaolingDataDeal;

    @RequestMapping(value={"/save"})
    public String storeData(@RequestParam(value="datas")String datas){
        System.out.println(datas);
List<Yaoling> ys=JSONArray.parseArray(datas.substring(1),Yaoling.class);
        System.out.println(111);
        yaolingDataDeal.batchSave(ys);

        return "ok";
    }

    @RequestMapping(value={"/abc"})
    public String test(@RequestParam(value="abc")String abc){
        return abc;
    }
}
